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
package com.huateng.bo.impl.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.huateng.bo.base.T10401BO;
import com.huateng.bo.base.T10601BO;
import com.huateng.common.Constants;
import com.huateng.common.ErrorCode;
import com.huateng.common.Operator;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.dao.iface.base.TblCompanyEmployeeDAO;
import com.huateng.log.Log;
import com.huateng.po.TblCompanyEmployee;
import com.huateng.po.TblCompanyStepment;
import com.huateng.po.TblOprInfo;
import com.huateng.system.util.BeanUtils;
import com.huateng.system.util.GenerateNextId;
import com.huateng.system.util.JSONBean;

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
public class T10601BOTarget implements T10601BO{
	
	private TblCompanyEmployeeDAO tblCompanyEmployeeDAO;
	private ICommQueryDAO commQueryDAO;
	private T10401BO t10401BO;

	public T10401BO getT10401BO() {
		return t10401BO;
	}

	public void setT10401BO(T10401BO t10401bo) {
		t10401BO = t10401bo;
	}

	public void setCommQueryDAO(ICommQueryDAO commQueryDAO) {
		this.commQueryDAO = commQueryDAO;
	}

	/**
	 * @return the tblCompanyEmployeeDAO
	 */
	public TblCompanyEmployeeDAO getTblCompanyEmployeeDAO() {
		return tblCompanyEmployeeDAO;
	}

	/**
	 * @param tblCompanyEmployeeDAO the tblCompanyEmployeeDAO to set
	 */
	public void setTblCompanyEmployeeDAO(TblCompanyEmployeeDAO tblCompanyEmployeeDAO) {
		this.tblCompanyEmployeeDAO = tblCompanyEmployeeDAO;
	}

	
	public String add(TblCompanyEmployee tblCompanyEmployee,Operator operator) {
		if(tblCompanyEmployee==null)
			return "参数错误，请返回.";
		
		String empId = StringEscapeUtils.escapeSql(tblCompanyEmployee.getEmployeeId());
		//判断员工号是否已经存在
		String sql = "select EMPLOYEE_NUM from TBL_COMPANY_EMPLOYEE where EMPLOYEE_ID= '" + empId + "'";
		List list = commQueryDAO.findBySQLQuery(sql.toString());
		if (list != null && list.size() > 0) {
			return "该员工编号已经被使用";
		}
		
		String employeeId = GenerateNextId.getNextSequence("TBL_COMPANY_EMPLOYEE");
		if(employeeId == null) 
			return "系统出现异常，请联系管理员.";
		tblCompanyEmployee.setEmployeeId(empId);
		tblCompanyEmployee.setId(employeeId);
		tblCompanyEmployeeDAO.save(tblCompanyEmployee);
		Log.log("添加人员信息成功。操作员编号：" + operator.getOprId());
		return Constants.SUCCESS_CODE;
	}

	@SuppressWarnings("unchecked")
	public String delete(String id,Operator operator){
		
		if(StringUtils.isBlank(id))
			return "参数错误，请返回.";

		id = StringEscapeUtils.escapeSql(id);
		String sql = "select * from TBL_OPR_INFO where OPR_NAME= '"	+ id + "' ";
		List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
		// 该人员下的操作员信息是否存在
		if (dataList.size() > 0) {
			return ErrorCode.T10601_01;
		}
		
		tblCompanyEmployeeDAO.delete(id);
		Log.log("删除人员息成功。操作员编号：" + operator.getOprId());
		return Constants.SUCCESS_CODE;
	}
	
	public String update(String companyEmployeeList,Operator operator)throws  Exception{
		JSONBean jsonBean=new JSONBean();
		jsonBean.parseJSONArrayData(companyEmployeeList);
		int len = jsonBean.getArray().size();
		List<TblCompanyEmployee> tblCompanyEmployeeList=new ArrayList<TblCompanyEmployee>();
		TblCompanyEmployee tblCompanyEmployee=null;
		List<TblOprInfo> tblOprInfoList = new ArrayList<TblOprInfo>();
		List<TblOprInfo> oprList = new ArrayList<TblOprInfo>();
		List<TblCompanyStepment> tblCompanyStepmentList = new ArrayList<TblCompanyStepment>();
		String id;
		String oprHql = "";
		String brhHql = "";
		for(int i=0;i<len;i++){
			id=jsonBean.getJSONDataAt(i).getString("id");
			tblCompanyEmployee=get(id);
			jsonBean.setObject(jsonBean.getJSONDataAt(i));
			BeanUtils.setObjectWithPropertiesValue(tblCompanyEmployee,jsonBean,true);			
			tblCompanyEmployeeList.add(tblCompanyEmployee);
			brhHql = "from com.huateng.po.TblCompanyStepment t where t.id = " + tblCompanyEmployee.getStepment();
			oprHql = "from com.huateng.po.TblOprInfo t where t.oprName = " + tblCompanyEmployee.getId();
			tblCompanyStepmentList = commQueryDAO.findByHQLQuery(brhHql);
			oprList = commQueryDAO.findByHQLQuery(oprHql);
			for(TblOprInfo opr:oprList){
				if(tblCompanyStepmentList.size() > 0){
					opr.setBrhId(tblCompanyStepmentList.get(0).getBrhId());
				}
				tblOprInfoList.add(opr);
			}
		}
		for(TblCompanyEmployee item : tblCompanyEmployeeList) {
			tblCompanyEmployeeDAO.update(item);
		}
		t10401BO.update(tblOprInfoList);
		Log.log("更新人员信息成功。操作员编号：" + operator.getOprId());		
		return Constants.SUCCESS_CODE;
	}
	
	public TblCompanyEmployee get(String id) {
		return tblCompanyEmployeeDAO.get(id);
	}

}
