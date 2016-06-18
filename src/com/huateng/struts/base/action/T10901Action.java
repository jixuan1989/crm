package com.huateng.struts.base.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.huateng.common.Constants;
import com.huateng.common.StringUtil;
import com.huateng.dao.iface.base.TblWarningParamDAO;
import com.huateng.dao.iface.base.TblWarningRuleDAO;
import com.huateng.po.base.TblWarningParam;
import com.huateng.po.base.TblWarningRule;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.ContextUtil;
import com.huateng.system.util.SysParamUtil;

/**
 * Title:MIS商户交易管理
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-6-7
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author 
 * 
 * @version 1.0
 */
public class T10901Action extends BaseAction {
	private static Logger log = Logger.getLogger(T10901Action.class);
	private static final long serialVersionUID = 1L;
	private String mchtno ;
	private String termno ;
	private TblWarningRuleDAO tblWarningRuleDAO = (TblWarningRuleDAO) ContextUtil.getBean("tblWarningRuleDAO");
	private TblWarningParamDAO tblWarningParamDAO = (TblWarningParamDAO) ContextUtil.getBean("tblWarningParamDAO");
	
	private String productId;
	private String startTime;
	private String endTime;
	private String status;
	
	private String paramName;
	private String paramKey;
	private String paramValue;
	
	

	@Override
	protected String subExecute(){
		try {
			if("signIn".equals(getMethod())) {			
				rspCode = signIn();			
			} else if("batchAccount".equals(getMethod())) {
				rspCode = batchAccount();
			} else if("batchSend".equals(getMethod())) {
				rspCode = batchSend();
			} else if("editParam".equals(getMethod())) {
				rspCode = editParam();
			} else if("addRule".equals(getMethod())) {
				rspCode = addRule();
			} else if("editRule".equals(getMethod())) {
				rspCode = editRule();
			}
		} catch (Exception e) {
			log("操作员编号：" + operator.getOprId()+ "，对MIS商户交易管理操作" + getMethod() + "失败，失败原因为："+e.getMessage());
			e.printStackTrace();
			try {
				throw new Exception(e.getMessage());
			} catch (Exception e1) {
				
			}
		}
		return rspCode;
	}
	
	private String addRule() {
		if(productId != null){
			if(tblWarningRuleDAO.get(productId) != null){
				return "规则已经存在不能重复添加！";
			} else {
				TblWarningRule tblWarningRule = new TblWarningRule();
				tblWarningRule.setProductId(productId);
				tblWarningRule.setStartTime(startTime);
				tblWarningRule.setEndTime(endTime);
				tblWarningRule.setStatus(status);
				tblWarningRuleDAO.save(tblWarningRule);				
			}
		} else {
			return "添加规则对象不能为空！";
		}
		return Constants.SUCCESS_CODE;
	}

	private String editParam() {
		if(paramKey != null){
			TblWarningParam tblWarningParam = tblWarningParamDAO.get(paramKey);
			if(tblWarningParam == null){
				return "未找到要修改的数据！";
			} else {
				tblWarningParam.setParamName(paramName);
				tblWarningParam.setParamValue(paramValue);
				tblWarningParamDAO.update(tblWarningParam);				
			}
		} else {
			return "修改参数不能为空！";
		}
		return Constants.SUCCESS_CODE;
	}

	private String editRule() {
		if(productId != null){
			TblWarningRule tblWarningRule = tblWarningRuleDAO.get(productId);
			if(tblWarningRule == null){
				return "未找到要修改的数据！";
			} else {
				tblWarningRule.setEndTime(endTime);
				tblWarningRule.setStartTime(startTime);
				tblWarningRule.setStatus(status);
				tblWarningRuleDAO.update(tblWarningRule);				
			}
		} else {
			return "修改数据不能为空！";
		}
		return Constants.SUCCESS_CODE;
	}

	/**
	 * 签到交易
	 * @throws Exception
	 */
	private String signIn() throws Exception {		
		StringBuffer message = new StringBuffer();
		message.append("6011");
		message.append(mchtno);/* 任务编号 */
		message.append(termno); /* 日终日期 */
		message.append(CommonFunction.fillString("  ", ' ', 2 + 256 + 2 + 256, true));
		
		String ip = SysParamUtil.getParam("IP");
		int port = Integer.parseInt(SysParamUtil.getParam("TradePort"));
		
		String sendMessage = CommonFunction.fillString(String.valueOf(message.toString().length()), '0', 4, false);
		sendMessage += message.toString();
		String ret = "";
		
		Socket socket = null;
		OutputStream outputStream = null;
		InputStream inputstream = null;
		
		try {
			
			// 连接
			log.info("Connection " + ip + ":" + port);
			socket = new Socket(ip,port);
			socket.setSoTimeout(1000 * 60);
			
			// 发送
			log.info("Send Message [" + sendMessage + "]");
			outputStream = socket.getOutputStream();
			outputStream.write(sendMessage.getBytes());
			outputStream.flush();
			
			// 接收
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));			
			String temp;
			while(true){
				temp = br.readLine();
				if(!StringUtil.isNull(temp)){
					ret += temp;
				}else{
					break;
				}
			}
			log.info("Rcv Message [" + ret + "]");

			// 解析
			if (!StringUtil.isNull(ret)) {				
				if ("00".equals(ret.substring(8, 10))) {
					return Constants.SUCCESS_CODE;
				} else {
					return Constants.FAILURE_CODE;
				}
			}
			return  Constants.FAILURE_CODE;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (null != outputStream) {
					outputStream.close();
				}
				if (null != inputstream) {
					inputstream.close();
				}
				if (null != socket) {
					socket.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return  Constants.FAILURE_CODE;
		
	}
	
	/**
	 * 批结算交易
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private String batchAccount() throws Exception {
		
		StringBuffer message = new StringBuffer();
		message.append("6021");
		message.append(mchtno);/* 任务编号 */
		message.append(termno); /* 日终日期 */
		message.append(CommonFunction.fillString("  ", ' ', 2 + 256 + 2 + 256, true));
		
		String ip = SysParamUtil.getParam("IP");
		int port = Integer.parseInt(SysParamUtil.getParam("TradePort"));
		
		String sendMessage = CommonFunction.fillString(String.valueOf(message.toString().length()), '0', 4, false);
		sendMessage += message.toString();
		String ret = "";
		
		Socket socket = null;
		OutputStream outputStream = null;
		InputStream inputstream = null;
		
		try {
			
			// 连接
			log.info("Connection " + ip + ":" + port);
			socket = new Socket(ip,port);
			socket.setSoTimeout(1000 * 60);
			
			// 发送
			log.info("Send Message [" + sendMessage + "]");
			outputStream = socket.getOutputStream();
			outputStream.write(sendMessage.getBytes());
			outputStream.flush();
			
			// 接收
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));			
			String temp;
			while(true){
				temp = br.readLine();
				if(!StringUtil.isNull(temp)){
					ret += temp;
				}else{
					break;
				}
			}
			log.info("Rcv Message [" + ret + "]");

			// 解析
			if (!StringUtil.isNull(ret)) {
				if ("00".equals(ret.substring(8, 10))) {
					return  Constants.SUCCESS_CODE;
				} else {
					return  Constants.FAILURE_CODE;
				}
			}
			return  Constants.FAILURE_CODE;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (null != outputStream) {
					outputStream.close();
				}
				if (null != inputstream) {
					inputstream.close();
				}
				if (null != socket) {
					socket.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return  Constants.FAILURE_CODE;
	}
	
	/**
	 * 批上送交易
	 * @return
	 */
	private String batchSend() throws Exception {
	
		return Constants.SUCCESS_CODE;
	}

	/**
	 * @return the mchtno
	 */
	public String getMchtno() {
		return mchtno;
	}

	/**
	 * @param mchtno the mchtno to set
	 */
	public void setMchtno(String mchtno) {
		this.mchtno = mchtno;
	}

	/**
	 * @return the termno
	 */
	public String getTermno() {
		return termno;
	}

	/**
	 * @param termno the termno to set
	 */
	public void setTermno(String termno) {
		this.termno = termno;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * @return the positionName
	 */
	
	
}
