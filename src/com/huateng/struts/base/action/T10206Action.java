/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-7-18       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2010 Huateng Software, Inc. All rights reserved.
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
package com.huateng.struts.base.action;

import java.lang.reflect.InvocationTargetException;

import com.huateng.common.Constants;
import com.huateng.po.base.TblEmvPara;
import com.huateng.po.base.TblEmvParaPK;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.GenerateNextId;

/**
 * Title:角色信息维护
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-7-18
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class T10206Action extends BaseAction {
		
	private java.lang.String usageKey;
	private java.lang.String paraOrg;
	private java.lang.String a9F06;
	private java.lang.String a9F22;
	private java.lang.String dF05;
	private java.lang.String dF06;
	private java.lang.String dF07;
	private java.lang.String dF04;
	private java.lang.String dF02;
	private java.lang.String dF03;
	
	
	
	/* (non-Javadoc)
	 * @see com.huateng.struts.system.action.BaseAction#subExecute()
	 */
	@Override
	protected String subExecute(){
		try {
			if("add".equals(method)) { // 新增CA银联公钥信息
				rspCode = add();
			} else if("delete".equals(method)) { //删除CA银联公钥信息
				rspCode = delete();
			} else if("update".equals(method)) { //同步CA银联公钥信息
				rspCode = update();
			} else if("edit".equals(method)) {  // 更新权限信息
				rspCode = edit();
			}
		} catch (Exception e) {
			log("操作员编号：" + operator.getOprId()+ "，CA银联公钥信息维护" + getMethod() + "失败，失败原因为："+e.getMessage());
		}
		return rspCode;
	}
	
	/**
	 * 添加CA银联公钥信息
	 * @return
	 */
	private String add() {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("9F06 + 05 + ").append(a9F06).append("&");
		sbf.append("9F22 + 01 + ").append(a9F22).append("&");
		sbf.append("DF05 + 08 + ").append(dF05).append("&");
		sbf.append("DF06 + 01 + ").append(dF06).append("&");
		sbf.append("DF07 + 01 + ").append(dF07).append("&");
		sbf.append("DF04 + 01 + ").append(dF04).append("&");
		sbf.append("DF02 + 90(HEX = DEC 144) + ").append(dF02).append("&");
		sbf.append("DF03 + 14(HEX = DEC 20) + ").append(dF03);
		
		String paraIdx = GenerateNextId.getParaIdx(usageKey);
		String paraId = GenerateNextId.getParaId();
		
		TblEmvParaPK pk = new TblEmvParaPK(usageKey,paraIdx);
		
		TblEmvPara tblEmvPara = new TblEmvPara(pk);
		tblEmvPara.setParaId(paraId);
		tblEmvPara.setGenFlag("0");
		tblEmvPara.setParaLen(String.valueOf(sbf.length()));
		tblEmvPara.setParaOrg(paraOrg);
		tblEmvPara.setParaSta("0");		
		tblEmvPara.setParaVal(sbf.toString());
		tblEmvPara.setRecOprId(operator.getOprId());
		tblEmvPara.setRecUpdOpr(operator.getOprId());
		tblEmvPara.setRecCrtTs(CommonFunction.getCurrentDateTime());
		tblEmvPara.setRecUpdOpr(CommonFunction.getCurrentDateTime());
		tblEmvPara.setRecUpdTs(CommonFunction.getCurrentDateTime());		
			
		return Constants.SUCCESS_CODE;
	}
	
	
	/**
	 * 删除权限信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String delete() {
		
//		String sql = "select * from tbl_opr_info where opr_degree = " + roleId;
//		List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
//		if(dataList.size() > 0) {
//			return "该CA银联公钥下已经存在操作员信息，无法删除";
//		}
//		t10301BO.delete(Integer.parseInt(roleId));
//		log("删除CA银联公钥信息维护成功。操作员编号：" + operator.getOprId()+ "，被删除操CA银联公钥编号：" + roleId);
		return Constants.SUCCESS_CODE;
	}
	
	/**
	 * 更新CA银联公钥信息
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private String update() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
//		jsonBean.parseJSONArrayData(getRoleInfoList());
//		
//		int len = jsonBean.getArray().size();
//		
//		TblRoleInf tblRoleInf = null;
//		
//		List<TblRoleInf> tblRoleInfList = new ArrayList<TblRoleInf>(len);
//		
//		for(int i = 0; i < len; i++) {
//			jsonBean.setObject(jsonBean.getJSONDataAt(i));
//			
//			tblRoleInf = new TblRoleInf();
//			
//			tblRoleInf.setRecUpdOpr(operator.getOprId());
//			
//			tblRoleInf.setRecUpdTs(CommonFunction.getCurrentDateTime());
//			
//			BeanUtils.setObjectWithPropertiesValue(tblRoleInf, jsonBean, true);
//			
//			tblRoleInfList.add(tblRoleInf);
//		}
//		t10301BO.update(tblRoleInfList);
//		log("更新CA银联公钥信息维护成功。操作员编号：" + operator.getOprId()+ "，被添加CA银联公钥编号：" + roleId);	
		return Constants.SUCCESS_CODE;
	}
	
	/**
	 * 更新CA银联公钥的权限信息
	 * @return
	 */
	public String edit() {
		
//		jsonBean.parseJSONArrayData(getMenuList());
//		
//		int len = jsonBean.getArray().size();
//		
//		List<TblRoleFuncMap> roleFuncMapList = new ArrayList<TblRoleFuncMap>(len);
//		
//		TblRoleFuncMapPK tblRoleFuncMapPK = null;
//		
//		TblRoleFuncMap tblRoleFuncMap = null;
//		
//		for(int i = 0; i < len; i++) {
//			
//			tblRoleFuncMap = new TblRoleFuncMap();
//			
//			tblRoleFuncMapPK = new TblRoleFuncMapPK();
//			
//			tblRoleFuncMapPK.setKeyId(Integer.parseInt(roleId));
//			
//			tblRoleFuncMapPK.setValueId(Integer.parseInt(jsonBean.getJSONDataAt(i).getString("valueId")));
//			
//			tblRoleFuncMap.setId(tblRoleFuncMapPK);
//			
//			tblRoleFuncMap.setRecCrtTs(CommonFunction.getCurrentDateTime());
//			
//			tblRoleFuncMap.setRecUpdOpr(operator.getOprId());
//			
//			tblRoleFuncMap.setRecUpdTs(CommonFunction.getCurrentDateTime());
//			
//			roleFuncMapList.add(tblRoleFuncMap);
//		}
//		
//		t10301BO.updateRoleMenu(roleFuncMapList,t10301BO.getMenuList(Integer.parseInt(roleId)));	
//		log("更新CA银联公钥的权限信息成功添加CA银联公钥信息维护成功。操作员编号：" + operator.getOprId()+ "，被添加CA银联公钥编号：" + roleId);			
		return Constants.SUCCESS_CODE;
	}


	public java.lang.String getUsageKey() {
		return usageKey;
	}

	public void setUsageKey(java.lang.String usageKey) {
		this.usageKey = usageKey;
	}

	public java.lang.String getParaOrg() {
		return paraOrg;
	}

	public void setParaOrg(java.lang.String paraOrg) {
		this.paraOrg = paraOrg;
	}

	public java.lang.String getA9F06() {
		return a9F06;
	}

	public void setA9F06(java.lang.String a9f06) {
		a9F06 = a9f06;
	}

	public java.lang.String getA9F22() {
		return a9F22;
	}

	public void setA9F22(java.lang.String a9f22) {
		a9F22 = a9f22;
	}

	public java.lang.String getDF05() {
		return dF05;
	}

	public void setDF05(java.lang.String df05) {
		dF05 = df05;
	}

	public java.lang.String getDF06() {
		return dF06;
	}

	public void setDF06(java.lang.String df06) {
		dF06 = df06;
	}

	public java.lang.String getDF07() {
		return dF07;
	}

	public void setDF07(java.lang.String df07) {
		dF07 = df07;
	}

	public java.lang.String getDF04() {
		return dF04;
	}

	public void setDF04(java.lang.String df04) {
		dF04 = df04;
	}

	public java.lang.String getDF02() {
		return dF02;
	}

	public void setDF02(java.lang.String df02) {
		dF02 = df02;
	}

	public java.lang.String getDF03() {
		return dF03;
	}

	public void setDF03(java.lang.String df03) {
		dF03 = df03;
	}
	
	
	
}
