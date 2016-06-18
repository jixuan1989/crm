/* @(#)
 *
 * Project:spdb
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2011-10-17       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2011 Huateng Software, Inc. All rights reserved.
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

import com.huateng.bo.base.T10601BO;
import com.huateng.po.TblCompanyEmployee;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.ContextUtil;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-10-17
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class T10601Action extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	private String id;
	private String tblCompanyEmployeeList;
	private TblCompanyEmployee tblCompanyEmployee;
	//信息BO
	private T10601BO t10601BO = (T10601BO) ContextUtil.getBean("T10601BO");
	@Override
	protected String subExecute() throws Exception {
		try {
			if("add".equals(method)) {
				rspCode = t10601BO.add(tblCompanyEmployee,operator);
			}else if("delete".equals(method)){
				rspCode = t10601BO.delete(id,operator);
			}else if("update".equals(method)){
				rspCode= t10601BO.update(tblCompanyEmployeeList,operator);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log("操作员编号：" + operator.getOprId()+ "，对人员信息的维护操作" + getMethod() + "失败，失败原因为："+e.getMessage());
		}
		
		return rspCode;
	}	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the tblCompanyEmployeeList
	 */
	public String getTblCompanyEmployeeList() {
		return tblCompanyEmployeeList;
	}

	/**
	 * @param tblCompanyEmployeeList the tblCompanyEmployeeList to set
	 */
	public void setTblCompanyEmployeeList(String tblCompanyEmployeeList) {
		this.tblCompanyEmployeeList = tblCompanyEmployeeList;
	}

	public void setTblCompanyEmployee(TblCompanyEmployee tblCompanyEmployee) {
		this.tblCompanyEmployee = tblCompanyEmployee;
	}
	
	public TblCompanyEmployee getTblCompanyEmployee() {
		return tblCompanyEmployee;
	}

}
