package com.huateng.common.grid;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.common.StringUtil;
import com.huateng.system.util.CommonFunction;

/**
 * Title:信息列表动态获取方法集合
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-6-6
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @version 1.0
 */
public class GridConfigMethod {
	
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim()))
			return true;
		else
			return false;
	}
	/**
	 * 查询机构信息
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getBrhInfoBelow(int begin, HttpServletRequest request) {
		Object[] ret = new Object[2];
		Operator operator = (Operator) request.getSession().getAttribute(
				Constants.OPERATOR_INFO);

		StringBuffer whereSql = new StringBuffer();
		whereSql.append(" WHERE " + "BRH_ID IN " + operator.getBrhBelowId());

		if (isNotEmpty(request.getParameter("brhId"))) {
			whereSql.append(" AND BRH_ID = '" + request.getParameter("brhId")
					+ "' ");
		}
		if (isNotEmpty(request.getParameter("brhName"))) {
			whereSql.append(" AND BRH_NAME  like '%"
					+ request.getParameter("brhName") + "%' ");
		}

		String sql = "SELECT BRH_ID,BRH_LEVEL,BRH_TYPE,UP_BRH_ID,BRH_NAME,BRH_ADDR,BRH_TEL_NO,POST_CD,"
				+ "BRH_CONT_NAME,CUP_BRH_ID,RESV1 FROM TBL_BRH_INFO "
				+ whereSql.toString();
		String countSql = "SELECT COUNT(*) FROM TBL_BRH_INFO "
				+ whereSql.toString();

		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

	/**
	 * 查询操作员信息
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getOprInfoWithBrh(int begin,
			HttpServletRequest request) {
		Object[] ret = new Object[2];
		String sql = "SELECT OPR_ID,BRH_ID,OPR_NAME,OPR_GENDER,REGISTER_DT,OPR_TEL,OPR_MOBILE FROM TBL_OPR_INFO WHERE BRH_ID = '"
				+ request.getParameter("brhId") + "'";
		String countSql = "SELECT COUNT(1) FROM TBL_OPR_INFO WHERE BRH_ID = '"
				+ request.getParameter("brhId") + "'";

		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

	/**
	 * 查询当前操作员下属操作员信息
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getOprInfo(int begin, HttpServletRequest request) {
		Object[] ret = new Object[2];
		Operator operator = (Operator) request.getSession().getAttribute(
				Constants.OPERATOR_INFO);

		StringBuffer whereSql = new StringBuffer();
		whereSql.append(" WHERE  a.OPR_NAME=b.EMPLOYEE_NUM  AND b.STEPMENT=c.ID AND "
				+ "a.BRH_ID IN " + operator.getBrhBelowId());
		if (isNotEmpty(request.getParameter("oprId"))) {
			whereSql.append(" AND a.OPR_ID = '" + request.getParameter("oprId")
					+ "' ");
		}

		if (isNotEmpty(request.getParameter("brhId"))) {
			whereSql.append(" AND a.BRH_ID = '" + request.getParameter("brhId")
					+ "' ");
		}
		if (isNotEmpty(request.getParameter("oprName"))) {
			whereSql.append(" AND b.NAME = '" + request.getParameter("oprName")
					+ "' ");
		}
		if (isNotEmpty(request.getParameter("oprDegree"))) {
			whereSql.append(" AND a.OPR_DEGREE = '"
					+ request.getParameter("oprDegree") + "' ");
		}
		if (isNotEmpty(request.getParameter("oprStep"))) {
			whereSql.append(" AND b.STEPMENT = '"
					+ request.getParameter("oprStep") + "' ");
		}
		if (isNotEmpty(request.getParameter("startDate"))) {
			whereSql.append(" AND REGISTER_DT >= '"
					+ request.getParameter("startDate") + "' ");
		}
		if (isNotEmpty(request.getParameter("endDate"))) {
			whereSql.append(" AND REGISTER_DT <= '"
					+ request.getParameter("endDate") + "' ");
		}

		String sql = " SELECT a.OPR_ID,a.BRH_ID,a.OPR_DEGREE,b.NAME,b.SEX,REGISTER_DT,b.TELEPHONE,b.MOBIEPHONE,PWD_OUT_DATE,OPR_STA FROM TBL_OPR_INFO a, TBL_COMPANY_EMPLOYEE b,TBL_COMPANY_STEPMENT c  "
				+ whereSql;

		String countSql = "SELECT COUNT(*) FROM ( " + sql + ")";

		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

	/**
	 * 查询当前操作员下属操作员信息
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getPositionInf(int begin, HttpServletRequest request) {
		Object[] ret = new Object[2];

		StringBuffer whereSql = new StringBuffer();
		StringBuffer orderBy = new StringBuffer();
		whereSql.append(" WHERE  1=1 ");
		orderBy.append(" ORDER BY to_number(A.id) ");
		if (isNotEmpty(request.getParameter("positionNameId"))) {
			whereSql.append(" AND A.POSITION_NAME like '%"
					+ request.getParameter("positionNameId") + "%' ");
		}

		if (isNotEmpty(request.getParameter("positionDesd"))) {
			whereSql.append(" AND A.POSITION_DES like '%"
					+ request.getParameter("positionDesd") + "%' ");
		}

		String sql = " SELECT A.ID,A.POSITION_NAME,A.POSITION_DES,A.CREATE_TIME,A.CREATE_OPR  FROM TBL_POSITION_INF A  "
				+ whereSql + orderBy;

		String countSql = "SELECT COUNT(*) FROM ( " + sql + ")";

		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

	/**
	 * 查询交易日志信息
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getTxnInfo(int begin, HttpServletRequest request) {
		Object[] ret = new Object[2];
		Operator operator = (Operator) request.getSession().getAttribute(
				Constants.OPERATOR_INFO);

		StringBuffer whereSql = new StringBuffer();

		if (!StringUtil.isNull(request.getParameter("oprNo"))) {
			whereSql.append(" and a.OPR_ID = '" + request.getParameter("oprNo")
					+ "' ");
		}
		if (!StringUtil.isNull(request.getParameter("startDate"))) {
			whereSql.append(" and TXN_DATE >= '"
					+ request.getParameter("startDate") + "' ");
		}
		if (!StringUtil.isNull(request.getParameter("endDate"))) {
			whereSql.append(" and TXN_DATE <= '"
					+ request.getParameter("endDate") + "' ");
		}
		if (!StringUtil.isNull(request.getParameter("brhId"))) {
			whereSql.append(" and b.BRH_ID = '" + request.getParameter("brhId")
					+ "' ");
		}
		if (!StringUtil.isNull(request.getParameter("conTxn"))) {
			whereSql.append(" and TXN_CODE = '"
					+ request.getParameter("conTxn") + "' ");
		}

		String sql = "SELECT a.OPR_ID,TXN_DATE,TXN_TIME,TXN_NAME,TXN_STA,ERR_MSG "
				+ "FROM TBL_TXN_INFO a , TBL_OPR_INFO b WHERE a.OPR_ID = b.OPR_ID AND "
				+ "b.BRH_ID IN "
				+ operator.getBrhBelowId()
				+ whereSql.toString() + " ORDER BY TXN_TIME DESC";

		String countSql = "SELECT COUNT(*) "
				+ "FROM TBL_TXN_INFO a , TBL_OPR_INFO b WHERE a.OPR_ID = b.OPR_ID AND "
				+ "b.BRH_ID IN " + operator.getBrhBelowId()
				+ whereSql.toString();

		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

	/**
	 * 公司人员信息查询
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getCompanyInfo(int begin, HttpServletRequest request) {
		Object[] ret = new Object[2];
		Operator operator = (Operator) request.getSession().getAttribute(
				Constants.OPERATOR_INFO);
		StringBuffer whereSql = new StringBuffer();
		whereSql.append(" WHERE 1=1 ");
		if (!StringUtil.isNull(request.getParameter("depName"))) {
			whereSql.append(" and NAME like '%"
					+ request.getParameter("depName") + "%' ");
		}
		String sql = "SELECT ID ,NAME ,STEP_ID,BRH_ID FROM TBL_COMPANY_STEPMENT "+whereSql+" and BRH_ID IN "
				+ operator.getBrhBelowId() +" ORDER BY ID";
		String countSql = "SELECT COUNT(*) FROM ("+sql+")";
		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

	/**
	 * 公司人员信息查询
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getCompanyEmployeeInfo(int begin,
			HttpServletRequest request) {
		Operator operator = (Operator) request.getSession().getAttribute(
				Constants.OPERATOR_INFO);
		Object[] ret = new Object[2];
		StringBuffer whereSql = new StringBuffer();
		whereSql.append(" WHERE 1=1 ");
		if (!StringUtil.isNull(request.getParameter("employeeNum"))) {
			whereSql.append(" and a.employee_Id = '"
					+ request.getParameter("employeeNum") + "' ");
		}
		if (!StringUtil.isNull(request.getParameter("name"))) {
			whereSql.append(" and a.name like '%"
					+ request.getParameter("name") + "%' ");
		}
		if (!StringUtil.isNull(request.getParameter("stepment"))) {
			whereSql.append(" and a.stepment = '"
					+ request.getParameter("stepment") + "' ");
		}
		if (!StringUtil.isNull(request.getParameter("job"))) {
			whereSql.append(" and a.job = '" + request.getParameter("job")
					+ "' ");
		}
		if (!StringUtil.isNull(request.getParameter("levels"))) {
			whereSql.append(" and a.levels = '"
					+ request.getParameter("levels") + "' ");
		}

		String order = " ORDER BY a.EMPLOYEE_NUM";
		String sql = "select a.employee_Num,a.employee_Id,a.name as aname,a.sex,b.name as bname,getPositionInf(a.job),a.contact,a.levels,"
				+ "a.education,a.email,a.entry_Time,a.telephone,a.rotation_Time,a.professional,a.graduate,a.birthday,a.address,"
				+ "a.permanent_Address,a.identity_Num,a.contact_Telephone,a.over_time,a.mobiephone,getEmpName(a.parent_empnum) from tbl_company_employee a"
				+ " left outer join tbl_company_stepment b on a.stepment=b.id "
				+ whereSql.toString()
				+ " and b.brh_id in "
				+ operator.getBrhBelowId() + order;
		;
		String countSql = "SELECT COUNT(*) FROM (" + sql + ")";

		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

	/**
	 * 公司人员信息维护
	 * 
	 * @param begin
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getCompanyEmployee(int begin,
			HttpServletRequest request) {
		Operator operator = (Operator) request.getSession().getAttribute(
				Constants.OPERATOR_INFO);
		Object[] ret = new Object[2];
		StringBuffer whereSql = new StringBuffer();
		whereSql.append(" WHERE 1=1 ");
		if (!StringUtil.isNull(request.getParameter("employeeNum"))) {
			whereSql.append(" and a.employee_Id = '"
					+ request.getParameter("employeeNum") + "' ");
		}
		if (!StringUtil.isNull(request.getParameter("name"))) {
			whereSql.append(" and a.name like  '%"
					+ request.getParameter("name") + "%' ");
		}
		if (!StringUtil.isNull(request.getParameter("stepment"))) {
			whereSql.append(" and a.stepment = '"
					+ request.getParameter("stepment") + "' ");
		}
		if (!StringUtil.isNull(request.getParameter("job"))) {
			whereSql.append(" and a.job = '" + request.getParameter("job")
					+ "' ");
		}
		if (!StringUtil.isNull(request.getParameter("levels"))) {
			whereSql.append(" and a.levels = '"
					+ request.getParameter("levels") + "' ");
		}
		String order = " ORDER BY a.EMPLOYEE_NUM";
		String sql = "SELECT a.EMPLOYEE_NUM,a.employee_Id,a.NAME as ANAME,a.SEX,a.STEPMENT,a.JOB,a.LEVELS,"
				+ " a.EDUCATION,a.EMAIL,a.ENTRY_TIME,a.TELEPHONE,a.parent_empnum FROM TBL_COMPANY_EMPLOYEE  a "
				+ " LEFT JOIN TBL_COMPANY_STEPMENT b ON a.STEPMENT = b.ID  "
				+ whereSql.toString()
				+ " and b.brh_id in "
				+ operator.getBrhBelowId() + order;
		;
		String countSql = "SELECT COUNT(1) FROM TBL_COMPANY_EMPLOYEE  a  LEFT JOIN TBL_COMPANY_STEPMENT b ON a.STEPMENT = b.ID "
				+ whereSql.toString()
				+ " and b.brh_id in"
				+ operator.getBrhBelowId();

		List<Object[]> dataList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql, begin, Constants.QUERY_RECORD_COUNT);
		String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
				countSql);
		ret[0] = dataList;
		ret[1] = count;
		return ret;
	}

}
