/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-5-21       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2008 Huateng Software, Inc. All rights reserved.
 *
 *       This software is the confidential and proprietary information of
 *       Shanghai HUATENG Software Co., Ltd. ("Confidential Information").
 *       You shall not disclose such Confidential Information and shall use it
 *       only in accordance with the terms of the license agreement you entered
 *       into with Huateng.
 *
 * Warning:
 * =============================================================================
 *
 */
package com.huateng.struts.system.action;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huateng.bo.base.T10101BO;
import com.huateng.bo.base.T10601BO;
import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.common.StringUtil;
import com.huateng.common.SysCodeConstants;
import com.huateng.common.TblTxnInfoConstants;
import com.huateng.dao.iface.TblTxnInfoDAO;
import com.huateng.dao.iface.base.TblOprInfoDAO;
import com.huateng.po.TblBrhInfo;
import com.huateng.po.TblCompanyEmployee;
import com.huateng.po.TblOprInfo;
import com.huateng.po.TblTxnInfo;
import com.huateng.po.TblTxnInfoPK;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.ContextUtil;
import com.huateng.system.util.Encryption;
import com.huateng.system.util.GenerateNextId;
import com.huateng.system.util.MsgSendUtil;
import com.huateng.system.util.SocketClient;
import com.huateng.system.util.SysCodeUtil;
import com.huateng.system.util.SysParamUtil;

/**
 * Title:系统登录
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-5-21
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -915297506148396706L;

	/**操作员编号*/
	private String oprid;
	/**操作员密码*/
	private String password;
	/**验证码*/
	private String random;
	private String loginType;
	
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	/* (non-Javadoc)
	 * @see com.huateng.struts.system.action.BaseAction#subExecute()
	 */
	@Override
	protected String subExecute() throws Exception {
		
		TblOprInfoDAO tblOprInfoDAO = (TblOprInfoDAO) ContextUtil.getBean("OprInfoDAO");
		//人员信息
		T10601BO t10601BO = (T10601BO)ContextUtil.getBean("T10601BO");
		if(loginType == null || "".equals(loginType)){
			//判断验证码是否输入正确
			if(!StringUtils.equals(random, (String) getSessionAttribute("randomCode"))){
				writeErrorMsg("登录失败，验证码失效或输入错误，点击更换验证码！");
				this.log("登录失败，验证码失效或输入错误，点击更换验证码！", TblTxnInfoConstants.TXN_STA_FAILURE);
				return SUCCESS;
			}
		}
		TblOprInfo tblOprInfo = tblOprInfoDAO.get(oprid);
		if(tblOprInfo == null){
			writeErrorMsg("用户名不存在或密码不正确。");
			return SUCCESS;
		}
		// 判断操作员合法性
		if(CommonFunction.getCurrentDate().equals(tblOprInfo.getPwdWrLastDt().trim()) && "1".equals(tblOprInfo.getOprSta().trim())) {
			this.log("登录失败，操作员被冻结", TblTxnInfoConstants.TXN_STA_FAILURE);
			log("登录失败，操作员被冻结。编号[ " + oprid + " ]");
			writeErrorMsg("该操作员已经被冻结，请与管理员联系。");
			return SUCCESS;
		}
		
		//判断操作员是否存在
		if(tblOprInfo == null) {
			this.log("登录失败，操作员不存在", TblTxnInfoConstants.TXN_STA_FAILURE);
			log("登录失败，操作员不存在。编号[ " + oprid + " ]");
			writeErrorMsg("登录失败，操作员不存在");
			return SUCCESS;
		}
		
		
		// 判断密码是否过期
		if(Integer.parseInt(tblOprInfo.getPwdOutDate().trim()) <= Integer.parseInt(CommonFunction.getCurrentDate())) {
			this.log("登录失败，操作员密码过期", TblTxnInfoConstants.TXN_STA_FAILURE);
			log("登录失败，操作员密码过期。编号[ " + oprid + " ]");
			writeAlertMsg("您的密码已经过期，请进行修改。", SysCodeUtil.getSysCode(SysCodeConstants.RESET_PWD));
			return SUCCESS;
		}
		
		//判断密码输入是否正确
		String oprPassword = StringUtils.trim(tblOprInfo.getOprPwd());
		password = StringUtils.trim(Encryption.encrypt(password));
		if(!oprPassword.equals(password)) {
			if(!CommonFunction.getCurrentDate().equals(tblOprInfo.getPwdWrLastDt())) {
				tblOprInfo.setPwdWrLastDt(CommonFunction.getCurrentDate());
				tblOprInfo.setPwdWrTm("1");
				tblOprInfo.setOprSta("0");
			} else {
				tblOprInfo.setPwdWrTm(String.valueOf(Integer.parseInt(tblOprInfo.getPwdWrTm().trim()) + 1));
			}
			//tblOprInfo.setPwdWrTmTotal(String.valueOf(Integer.parseInt(tblOprInfo.getPwdWrTmTotal().trim()) + 1));
			
			// 如果输入错误总次数大于5次或当天输入错误次数大于3次则冻结操作员
			if(/*Integer.parseInt(tblOprInfo.getPwdWrTmTotal().trim()) >= 5 || */
					(Integer.parseInt(tblOprInfo.getPwdWrTm().trim()) >= 3 && CommonFunction.getCurrentDate().equals(tblOprInfo.getPwdWrLastDt().trim()))) {
				this.log("登录失败，用户名或密码错误", TblTxnInfoConstants.TXN_STA_FAILURE);
				tblOprInfo.setOprSta("1");
			}
			tblOprInfoDAO.update(tblOprInfo);
			log("登录失败，密码错误。编号[ " + oprid + " ]");
			writeErrorMsg("登录失败，您输入的用户名或密码错误");
			return SUCCESS;
		}
		//验证通过
		T10101BO t10101BO = (T10101BO) ContextUtil.getBean("T10101BO");
		TblBrhInfo tblBrhInfo = t10101BO.get(tblOprInfo.getBrhId());
		
		//根据操作员编号获取对应人员信息
		TblCompanyEmployee employee = t10601BO.get(tblOprInfo.getOprName());
		
		//设置操作员信息
		Operator operator = new Operator();
		operator.setOprId(oprid);
		if(null != employee) {
			operator.setOprName(employee.getName());
			operator.setEmployee(employee);
		} else {
			operator.setOprName(StringUtils.trim(tblOprInfo.getOprName()));
		}
		operator.setOprBrhId(tblBrhInfo.getId());
		operator.setOprBrhName(StringUtils.trim(tblBrhInfo.getBrhName()));
		operator.setOprDegree(tblOprInfo.getOprDegree());
		operator.setOprBrhLvl(tblBrhInfo.getBrhLevel());
		
		//本行及下属机构信息MAP
		Map<String, String> brhMap = new LinkedHashMap<String, String>();
		brhMap.put(operator.getOprBrhId(),operator.getOprBrhName());
		operator.setBrhBelowMap(CommonFunction.getBelowBrhMap(operator.getOprBrhId(), brhMap));
		operator.setBrhBelowId(CommonFunction.getBelowBrhInfo(operator.getBrhBelowMap()));
		log("本行及下属机构编号：" + operator.getBrhBelowId());
		setSessionAttribute(Constants.OPERATOR_INFO, operator);
		
		String sql = "select cup_brh_id from tbl_brh_info where trim(brh_id) = '" + operator.getOprBrhId().trim() + "'";
		List list = CommonFunction.getCommQueryDAO().findBySQLQuery(sql);
		if (null != list && !list.isEmpty()) {
			if (!StringUtil.isNull(list.get(0))) {
				String cup = (String) list.get(0);
				setSessionAttribute("cupBrhId", cup);
			} else {
				setSessionAttribute("cupBrhId", "-");
			}
		}
		//TODO 临时添加逻辑，实现单点登录
		//TODO Don't submit
		//tblOprInfo.setOprSta("1");
		
		tblOprInfo.setPwdWrLastDt(CommonFunction.getCurrentDate());
		tblOprInfo.setPwdWrTm("0");
		tblOprInfoDAO.update(tblOprInfo);
		this.log("登录成功", TblTxnInfoConstants.TXN_STA_SUCCESS);
		if("0".equals(tblOprInfo.getIsValMsg())){
			int random = (int)((Math.random()*9+1)*100000);
			String str = "【支付通】收单系统登录验证码："+ random;
			MsgSendUtil.sendMsg(SysParamUtil.getParam("MSG_IP"), SysParamUtil.getParam("MSG_PORT"), str, "ACTLOGIN", tblOprInfo.getOprTel(), "fjsui32329_219382$");
			ServletActionContext.getRequest().getSession().setAttribute(SysCodeConstants.MSG_VALIDATE, random);
			writeAlertMsg("请输入短信验证码", SysCodeUtil.getSysCode(SysCodeConstants.MSG_VALIDATE));
			return SUCCESS;
		}else{
			writeSuccessMsg("登录失败，您输入的密码错误");
			return SUCCESS;
		}
	}
	
	//TODO 临时处理登陆日志信息，将来需要修改
	private void log(String oper, String sta) {
		TblTxnInfoDAO txnInfoDAO = (TblTxnInfoDAO) ContextUtil.getBean("TxnInfoDAO");
		TblTxnInfo tblTxnInfo = new TblTxnInfo();
		TblTxnInfoPK tblTxnInfoPK = new TblTxnInfoPK();				
		String acctTxnDate = CommonFunction.getCurrentDate();				
		tblTxnInfoPK.setAcctDate(acctTxnDate);
		tblTxnInfoPK.setTxnSeqNo(GenerateNextId.getTxnSeq());				
		tblTxnInfo.setId(tblTxnInfoPK);
		
		String currentTime = CommonFunction.getCurrentDateTime();				
		tblTxnInfo.setTxnDate(currentTime.substring(0, 8));
		tblTxnInfo.setTxnTime(currentTime.substring(8, 14));				
		tblTxnInfo.setTxnCode("");
		tblTxnInfo.setSubTxnCode("");
		tblTxnInfo.setOprId(oprid);
		if(operator != null) {
			tblTxnInfo.setOrgCode(operator.getOprBrhId());
		} else {
			tblTxnInfo.setOrgCode("");
		}
		tblTxnInfo.setIpAddr(getRequest().getRemoteHost());
		tblTxnInfo.setTxnName(oper);
//		tblTxnInfo.setTxnSta(TblTxnInfoConstants.TXN_STA_FAILURE);
		tblTxnInfo.setTxnSta(sta);
		tblTxnInfo.setErrMsg("-");
		txnInfoDAO.save(tblTxnInfo);
	}
	public static void main(String[] s){
	}
	public String getOprid() {
		return oprid;
	}

	public void setOprid(String oprid) {
		this.oprid = oprid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

}
