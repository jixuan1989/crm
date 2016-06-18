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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.huateng.bo.base.T10401BO;
import com.huateng.bo.base.T10500BO;
import com.huateng.common.Constants;
import com.huateng.common.ErrorCode;
import com.huateng.po.TblCompanyEmployee;
import com.huateng.po.TblCompanyStepment;
import com.huateng.po.TblOprInfo;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.BeanUtils;
import com.huateng.system.util.ContextUtil;
import com.huateng.system.util.GenerateNextId;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-10-9
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class T10500Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String brhId;
	private String tblCompanyStepmentList;
	// 库房信息BO
	private T10500BO t10500BO = (T10500BO) ContextUtil.getBean("T10500BO");
	private T10401BO t10401BO = (T10401BO) ContextUtil.getBean("T10401BO");

	@Override
	protected String subExecute() throws Exception {
		try {
			if ("add".equals(method)) {
				rspCode = add();
			} else if ("delete".equals(method)) {
				rspCode = delete();
			} else if ("update".equals(method)) {
				rspCode = update();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log("操作员编号：" + operator.getOprId() + "，对厂商型号的维护操作" + getMethod()
					+ "失败，失败原因为：" + e.getMessage());
		}

		return rspCode;
	}

	public String add() {

		TblCompanyStepment tblCompanyStepment = new TblCompanyStepment();

		if (StringUtils.isEmpty(id))
			return "部门编号不能为空";
		
		id = StringEscapeUtils.escapeSql(id);
		
		//判断部门编号是否已经存在
		String sql = "select ID from TBL_COMPANY_STEPMENT where STEP_ID= '" + id + "'";
		List list = commQueryDAO.findBySQLQuery(sql.toString());
		if (list != null && list.size() > 0) {
			return "该部门编号已经被使用";
		}

		sql = "select count(1) from tbl_company_stepment where name='"+name+"' and brh_id='"+brhId+"'" ;
		String count = commQueryDAO.findCountBySQLQuery(sql);
		if(!StringUtils.equals("0", count)){
			return "该部门名称已存在，请确认！" ;
		}
		
		String pid = GenerateNextId.getNextSequence("TBL_COMPANY_STEPMENT");
		if(pid == null) 
			return "系统出现异常，请联系管理员.";
		tblCompanyStepment.setId(pid);
		tblCompanyStepment.setName(name);
		tblCompanyStepment.setStepId(id);
		tblCompanyStepment.setBrhId(brhId);
		t10500BO.add(tblCompanyStepment);

		log("添加部门信息成功。操作员编号：" + operator.getOprId());

		return Constants.SUCCESS_CODE;
	}

	public String delete() {
		
		if(StringUtils.isEmpty(id))
			return "操作错误";

		id = StringEscapeUtils.escapeSql(id);
		
		String sql = "select * from TBL_COMPANY_EMPLOYEE where STEPMENT= '"	+ id + "' ";
		List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
		// 判断是否有人员信息在该部门下
		if (dataList.size() > 0) {
			return ErrorCode.T10500_01;
		}
		
		t10500BO.delete(id);
		
		log(id+ " 部门删除成功。操作员编号：" + operator.getOprId());
		
		return Constants.SUCCESS_CODE;
	}

	public String update() throws Exception {
		jsonBean.parseJSONArrayData(getTblCompanyStepmentList());
		int len = jsonBean.getArray().size();
		List<TblCompanyStepment> tblCompanyStepmentList = new ArrayList<TblCompanyStepment>();
		List<TblOprInfo> tblOprInfoList = new ArrayList<TblOprInfo>();
		List<TblOprInfo> oprList = new ArrayList<TblOprInfo>();
		List<TblCompanyEmployee> empList = new ArrayList<TblCompanyEmployee>();
		String empHql = "";
		String oprHql = "";
		TblCompanyStepment tblCompanyStepment = null;
		for (int i = 0; i < len; i++) {
			id = jsonBean.getJSONDataAt(i).getString("id");
			tblCompanyStepment = t10500BO.get(id);
			jsonBean.setObject(jsonBean.getJSONDataAt(i));
			BeanUtils.setObjectWithPropertiesValue(tblCompanyStepment,jsonBean, true);
			tblCompanyStepmentList.add(tblCompanyStepment);
			String sql = "select count(1) from tbl_company_stepment where name='"+tblCompanyStepment.getName()+"' and brh_id='"+tblCompanyStepment.getBrhId()+"'" ;
			String count = commQueryDAO.findCountBySQLQuery(sql);
			if(!StringUtils.equals("0", count)){
				return "该部门名称已存在，请确认！" ;
			}
			empHql = "from com.huateng.po.TblCompanyEmployee t where t.stepment = " + tblCompanyStepment.getId();
			empList = commQueryDAO.findByHQLQuery(empHql);
			for(TblCompanyEmployee emp:empList){ 
				oprHql = "from com.huateng.po.TblOprInfo t where t.oprName = " + emp.getId();
				oprList = commQueryDAO.findByHQLQuery(oprHql);
				for(TblOprInfo opr:oprList){
					opr.setBrhId(tblCompanyStepment.getBrhId());
					tblOprInfoList.add(opr);
				}
			}
		}
		t10500BO.update(tblCompanyStepmentList);
		t10401BO.update(tblOprInfoList);
		log("更新部门信息成功。操作员编号：" + operator.getOprId());
		return Constants.SUCCESS_CODE;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tblCompanyStepmentList
	 */
	public String getTblCompanyStepmentList() {
		return tblCompanyStepmentList;
	}

	/**
	 * @param tblCompanyStepmentList
	 *            the tblCompanyStepmentList to set
	 */
	public void setTblCompanyStepmentList(String tblCompanyStepmentList) {
		this.tblCompanyStepmentList = tblCompanyStepmentList;
	}

	/**
	 * @return the brhId
	 */
	public String getBrhId() {
		return brhId;
	}

	/**
	 * @param brhId the brhId to set
	 */
	public void setBrhId(String brhId) {
		this.brhId = brhId;
	}

}
