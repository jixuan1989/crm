/* @(#)
 *
 * Project:spdb
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2011-11-9       first release
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
package com.huateng.dwr.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huateng.bo.base.T10601BO;
import com.huateng.log.Log;
import com.huateng.po.TblCompanyEmployee;
import com.huateng.system.util.ContextUtil;

import net.sf.json.JSONArray;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-11-9
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class T10401 {
	
	private TblCompanyEmployee employeeInfo;
	/**
	 * 查询人员信息
	 * @param mchntNo
	 * @return
	 * 2010-8-26下午11:01:01
	 */
	public String getEmployeeInfo(String employeeId,HttpServletRequest request,HttpServletResponse response) {
		String jsonData = null;
		try {
			T10601BO t10601BO = (T10601BO) ContextUtil.getBean("T10601BO");
			employeeInfo = t10601BO.get(employeeId);			
			if(employeeInfo != null){
				jsonData = JSONArray.fromObject(employeeInfo).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log(e.getMessage());
		}
		return jsonData;
	}

	/**
	 * @param employeeInfo the employeeInfo to set
	 */
	public void setEmployeeInfo(TblCompanyEmployee employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	
}
