/* @(#)
 *
 * Project:PFConsole
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   Gavin      2011-10-26       first release
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
package com.huateng.common.select;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.huateng.common.Operator;
import com.huateng.common.TblMchntInfoConstants;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.system.util.ContextUtil;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-10-26
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author Gavin
 * 
 * @version 1.0
 */
public class DynamicSQL extends DynamicSQLSupport{
	
	static ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
	
	
	/**
	 * 查询银行名称
	 * @param inputValue 查询条件
	 * @param operator 操作员
	 * @param request
	 * @return
	 * 2012-4-5上午09:55:35
	 */
	public static DynamicSqlBean getBankName(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select BRH_ID, BRH_NAME from tbl_brh_info where 1=1 ";
				
		sql += " order by BRH_ID";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getMchntId(String inputValue, Operator operator, HttpServletRequest request){
			
			String sql = "select MCHT_NO, trim(MCHT_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_MCHT_BASE_INF where (MCHT_STATUS = '0' OR MCHT_STATUS='21') ";
			
			sql += provideSqlDouLike(sql, "MCHT_NO ", inputValue);
			sql += provideSqlIn(sql, "ACQ_INST_ID", operator.getBrhBelowId());
			
			sql += " order by MCHT_NO";
			return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getMchntSerialNo(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select Serial_No, trim(MCHT_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_MCHT_BASE_INF where (MCHT_STATUS = '0' OR MCHT_STATUS='21') ";
		
		sql += provideSqlDouLike(sql, "MCHT_NO ", inputValue);
		sql += provideSqlIn(sql, "ACQ_INST_ID", operator.getBrhBelowId());
		
		sql += " order by MCHT_NO";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getBankMchntSerialNo(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select Serial_No, trim(MCHT_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_BANK_MCHT_INF "
				+"where (MCHT_STATUS = '0' OR MCHT_STATUS='21') ";
		
		sql += provideSqlDouLike(sql, "MCHT_NO ", inputValue);
		sql += provideSqlIn(sql, "ACQ_INST_ID", operator.getBrhBelowId());
		
		sql += " order by MCHT_NO";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getTradeMchntId(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select MCHT_NO, MCHT_NO ||' - '|| MCHT_NM as MCHT_NMS from TBL_MCHT_BASE_INF  ";
		
		sql += provideSqlDouLike(sql, " MCHT_NO ||' - '|| MCHT_NM ", inputValue);
		sql += provideSqlIn(sql, "ACQ_INST_ID", operator.getBrhBelowId());
		
		sql += " order by REC_UPD_TS DESC";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
 
	public static DynamicSqlBean getBelowTradeMchntId(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select MCHT_NO, trim(MCHT_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_MCHT_BASE_INF where (MCHT_STATUS = '0' OR MCHT_STATUS='21') ";
		
		sql += provideSqlDouLike(sql, "MCHT_NO ", inputValue);
		sql += provideSqlIn(sql, "ACQ_INST_ID", operator.getBrhBelowId());
		if(!"3".equals(operator.getOprDegree())){
			sql +=  " and oper_na in (select employee_num from tbl_company_employee start with employee_num='"+operator.getEmployee().getId()+"' connect by prior employee_num=parent_empnum)";
		}	
		sql += " order by MCHT_NO";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	public static DynamicSqlBean getTermId(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select TERM_ID from TBL_TERM_INF where MCHT_STATUS = '0' ";
		
		sql += provideSqlDouLike(sql, "TERM_ID", inputValue);
		
		sql += " order by TERM_ID";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getMchntNo(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select MCHT_NO, trim(MCHT_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_MCHT_BASE_INF where MCHT_STATUS = '0' ";
		//TODO 当全部都输对时，会查询不出，但是现在我不想改了
		sql += provideSql(sql, "trim(MCHT_NO) ||' - '|| trim(MCHT_NM)", inputValue);
		sql += provideSqlIn(sql, "ACQ_INST_ID", operator.getBrhBelowId());
		
		sql += " order by MCHT_NO";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	
	public static DynamicSqlBean getMchntIdTmp(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select MCHT_NO, trim(MCHT_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_MCHT_BASE_INF_TMP where MCHT_STATUS = '0' ";
		
		sql += provideSqlLike(sql, "MCHT_NO ||' - '|| MCHT_NM", inputValue);
		sql += provideSqlIn(sql, "ACQ_INST_ID", operator.getBrhBelowId());
		
		sql += " order by MCHT_NO";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	public static DynamicSqlBean getMchntTmp(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select SERIAL_NO, trim(SERIAL_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_MCHT_BASE_INF_TMP ";
		
		sql += provideSqlLike(sql, "SERIAL_NO ||' - '|| MCHT_NM", inputValue);
		sql += provideSqlIn(sql, "Belong_Brh", operator.getBrhBelowId());
		
		sql += " order by SERIAL_NO";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getAreaCode(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select CITY_CODE_NEW,CITY_CODE_NEW||' - '||CITY_NAME from CST_CITY_CODE ";
		
		sql += provideSqlDouLike(sql, "CITY_CODE_NEW||' - '||CITY_NAME", inputValue);
		
		sql += " order by CITY_CODE_NEW";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 机具映射查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtMapping(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_mcht_base_inf where" +
				" mcht_no in(select distinct mcht_no from tbl_pos_mapping where is_normal='1') and ACQ_INST_ID in "+operator.getBrhBelowId();
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += " order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 机具映射查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getTermMapping(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select TERM_ID from tbl_term_inf where TERM_ID in(select distinct TERM_NO from tbl_pos_mapping where is_normal='1')";
		
		sql += provideSqlDouLike(sql,"TERM_ID",inputValue);
		
		sql += "order by TERM_ID";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 任务信息查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getTaskInfo(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_mcht_base_inf where mcht_no in(select distinct mcht_no from tbl_task_info )";
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 任务信息查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getEmployeeReverse(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select EMPLOYEE_NUM,NAME from tbl_company_employee ";

		sql += provideSqlORDouLike(sql,"NAME",inputValue);
		sql += provideSqlORDouLike(sql,"employee_num",inputValue);
		
		sql += "order by EMPLOYEE_NUM";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 待调机任务查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getTaskAdust(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_mcht_base_inf where mcht_no in(select distinct mcht_no from tbl_task_info  where  task_state='5' )";
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	/**
	 * 收据查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:51:09
	 */
	public static DynamicSqlBean getMchtReceipt(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_mcht_base_inf where mcht_no in(select distinct mcht_no from tbl_receipt_inf )";
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 预约退押金查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:56:43
	 */
	public static DynamicSqlBean getMchtBespeak(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_bank_mcht_inf where serial_no in(select distinct mcht_no from tbl_bespeak_damage )";
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 押金查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午04:00:10
	 */
	public static DynamicSqlBean getMchtDeposit(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_mcht_base_inf where mcht_no in(select distinct mcht_no from tbl_deposit_inf )";
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 赔偿金查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:51:09
	 */
	public static DynamicSqlBean getDamageInfo(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_mcht_base_inf where mcht_no in(select distinct mcht_no from tbl_damage_inf )";
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 查询导入
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午04:17:30
	 */
	public static DynamicSqlBean getUploadTmp(String inputValue, Operator operator, HttpServletRequest request){
		String sql ="select mcht_no,mcht_no||'-'||mcht_nm from tbl_mcht_base_inf where mcht_no in(select distinct mcht_no from tbl_Upload_tmp_inf )";
		
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	/**
	 * 查询终端启用商户
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtStop(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct a.mcht_cd,trim(a.MCHT_CD) ||' - '|| trim(b.MCHT_NM) as MCHT_NMS  " +
				" from tbl_term_inf_tmp a left join tbl_mcht_base_inf b on a.MCHT_CD=b.MCHT_NO " +
				" where term_sta='1' and  b.acq_inst_id in "+operator.getBrhBelowId();
		sql += provideSqlDouLike(sql, "a.MCHT_CD ||' - '|| b.MCHT_NM", inputValue);
		sql += " order by a.mcht_cd ";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	/**
	 * 查询终端停用商户
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtNormal(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct a.mcht_cd,trim(a.MCHT_CD) ||' - '|| trim(b.MCHT_NM) as MCHT_NMS " +
				" from tbl_term_inf_tmp a left join tbl_mcht_base_inf b on a.MCHT_CD=b.MCHT_NO " +
				" where term_sta='4' and  b.acq_inst_id in "+operator.getBrhBelowId();
		sql += provideSqlDouLike(sql, "a.MCHT_CD ||' - '|| b.MCHT_NM", inputValue);
		sql += " order by a.mcht_cd ";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 查询终端审核商户
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtDecide(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct a.mcht_cd,trim(a.MCHT_CD) ||' - '|| trim(b.MCHT_NM) as MCHT_NMS " +
				" from tbl_term_inf_tmp a left join tbl_mcht_base_inf b on a.MCHT_CD=b.MCHT_NO " +
				" where term_sta in ('0','2','3','5','6') and  b.acq_inst_id in "+operator.getBrhBelowId();
		sql += provideSqlDouLike(sql, "a.MCHT_CD ||' - '|| b.MCHT_NM", inputValue);
		sql += " order by a.mcht_cd ";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	/**
	 * 查询所有终端商户
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtQuery(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct a.mcht_cd,trim(a.MCHT_CD) ||' - '|| trim(b.MCHT_NM) as MCHT_NMS  " +
				" from tbl_term_inf_tmp a left join tbl_mcht_base_inf b on a.MCHT_CD=b.MCHT_NO where b.acq_inst_id in "+operator.getBrhBelowId();
		sql += provideSqlDouLike(sql, "a.MCHT_CD ||' - '|| b.MCHT_NM", inputValue);
		sql += " order by a.mcht_cd ";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 查询换签商户
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getHuanQian(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct MCHT_NO, trim(MCHT_NO) ||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_DEPOSIT_INF where STATUS ='2' and MACHINE_TYPE='0'  ";
		sql += provideSqlDouLike(sql,"mcht_no||'-'||mcht_nm",inputValue);
		sql += "order by mcht_no";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 商户退回拒绝查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtBack(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct MCHNT_ID,trim(MCHNT_ID) ||' - '|| trim(b.MCHT_NM) as MCHT_NMS   from TBL_MCHNT_REFUSE a left join tbl_mcht_base_inf_tmp b on a.MCHNT_ID=b.MCHT_NO ";
		sql += provideSqlDouLike(sql, " MCHNT_ID ||' - '|| b.MCHT_NM", inputValue);
		sql += " order by MCHNT_ID ";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 上门回访查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtCallback(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct a.MCHT_NO,trim(a.MCHT_NO) ||' - '|| trim(b.MCHT_NM) as MCHT_NMS   from TBL_TASK_INFO a left join tbl_mcht_base_inf_tmp b on a.MCHT_NO=b.MCHT_NO  where task_type=1 ";
		sql += provideSqlDouLike(sql, " a.MCHT_NO ||' - '|| b.MCHT_NM", inputValue);
		sql += " order by a.MCHT_NO ";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 装机指令查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 * 2012-2-20下午03:49:22
	 */
	public static DynamicSqlBean getMchtMachine(String inputValue, Operator operator, HttpServletRequest request){
		String sql =" select distinct a.MCHT_NO,trim(a.MCHT_NO) ||' - '|| trim(b.MCHT_NM) as MCHT_NMS   from TBL_TASK_INFO a left join tbl_mcht_base_inf_tmp b on a.MCHT_NO=b.MCHT_NO  where task_type=0 and task_state=7 ";
		sql += provideSqlDouLike(sql, " a.MCHT_NO ||' - '|| b.MCHT_NM", inputValue);
		sql += " order by a.MCHT_NO ";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	/**
	 * 商户查询
	 * @param inputValue
	 * @param operator
	 * @param reques
	 * @return
	 * 2012-3-29下午3:07:53
	 */
	public static DynamicSqlBean getMerNum(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "select SA_MER_NO ,SA_MER_NO ||' - '|| SA_MER_CH_NAME  FROM  TBL_CTL_MCHT_INF";
		sql += provideSqlDouLike(sql,"SA_MER_NO || ' - '|| SA_MER_CH_NAME",inputValue);
		sql += " order by SA_MER_NO";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 查询代码中文解释
	 * @param inputValue
	 * @param operator
	 * @param reques
	 * @return
	 */
	public static DynamicSqlBean getColName(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "select key,value from cst_sys_param where owner='"+inputValue+"' order by sortno";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	
	/**
	 * 查询风控规则
	 * @param inputValue
	 * @param operator
	 * @param reques
	 * @return 
	 * @return
	 */
	
	public static DynamicSqlBean getRiskModel (String inputValue,Operator operator,HttpServletRequest request){
		String sql = "select  sa_model_kind , sa_model_kind   from tbl_risk_inf where sa_type = '1' ";
		return new DynamicSqlBean(sql,commQueryDAO);
	}
	
	
	/**
	 * 查询集团商户号 ，从清算明细表中查询
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 */
	
	public static DynamicSqlBean getGroupMchtInf2(String inputValue,Operator operator , HttpServletRequest request ){
		String sql  = "select distinct  a.mcht_group_id,a.mcht_group_id || '-' || trim(b.mcht_name)  from tbl_mchnt_infile_dtl a , tbl_group_mcht_inf b where a.mcht_group_id = b.group_mcht_cd AND  a.FILE_STA = '2' ";
		return new DynamicSqlBean(sql,commQueryDAO);
	}
	
	/**
	 * 查询子商户支付凭证集团商户
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 */
	public static DynamicSqlBean getGroupMchtInf1(String inputValue,Operator operator , HttpServletRequest request ){
		String sql  = "select distinct a.mcht_group_id, a.mcht_group_id|| '-' || trim(b.mcht_name)  from tbl_mchnt_infile_dtl a , tbl_group_mcht_inf b where a.mcht_group_id = b.group_mcht_cd AND  a.FILE_STA = '1' ";
		return new DynamicSqlBean(sql,commQueryDAO);
	}
	
	/**
	 * 查询子商户Id
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 */
	public static DynamicSqlBean getMchtOfGroup (String inputValue,Operator operator ,HttpServletRequest request ){
		String sql = "select mcht_no ,trim (mcht_no)||'-'||trim(mcht_nm) as mchtName from tbl_mcht_base_inf where mcht_group_flag = '2' ";
		return new DynamicSqlBean(sql,commQueryDAO);
	}
	
	/**
	 * 集团清算明细表中的 集团号-集团名称
	 * @param inputValue
	 * @param operator
	 * @param request
	 * @return
	 */
	
	public static DynamicSqlBean getGroupMchtCdInfile(String inputValue,Operator operator ,HttpServletRequest request){
		String sql = "SELECT GROUP_MCHT_CD,GROUP_MCHT_CD ||'-'|| MCHT_NAME AS MCHTNAME FROM TBL_GROUP_MCHT_INF WHERE MCHT_STATUS = '"+TblMchntInfoConstants.MCHNT_ST_OK+"'";
		return new DynamicSqlBean(sql,commQueryDAO);
	}
	
	/**
	 * 查询代理商
	 * @param inputValue
	 * @param operator
	 * @param reques
	 * @return
	 */
	public static DynamicSqlBean getAgent(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "SELECT AGENT_NO,AGENT_NM FROM TBL_AGENT_INF ";
		sql += provideSqlORDouLike(sql, "AGENT_NM ", inputValue);
		sql += provideSqlORDouLike(sql, "AGENT_NO ", inputValue);
		sql +=" ORDER BY AGENT_NO ";
 		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getGroupMchtInfo (String inputValue,Operator operator,HttpServletRequest request){
		String sql = "SELECT GROUP_MCHT_CD,GROUP_MCHT_CD ||'-'|| MCHT_NAME AS MCHTNAME FROM TBL_GROUP_MCHT_INF WHERE MCHT_STATUS = '"+TblMchntInfoConstants.MCHNT_ST_OK+"'";
		sql += provideSqlORDouLike(sql, "GROUP_MCHT_CD ", inputValue);
		sql += provideSqlORDouLike(sql, "MCHT_NAME ", inputValue);			
		return new DynamicSqlBean(sql,commQueryDAO);
	}
 
	//获取开户银行信息
	public static DynamicSqlBean getBankInfo(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select UNION_BANK_NO,UNION_BANK_NO||'-'||BANK_NM  AS BANK_INFO FROM TBL_BANK_INFO ";

		sql += provideSqlDouLike(sql, "UNION_BANK_NO||'-'||BANK_NM ", inputValue);
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	//获取开户银行信息
	public static DynamicSqlBean getAccountInfo(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select PAY_ACCT,PAY_ACCT||'-'||PAY_ACCT_NM  AS BANK_INFO FROM TBL_PAY_ACCOUNT_INF WHERE STATUS='1' ";
		
		sql += provideSqlDouLike(sql, "PAY_ACCT||'-'||PAY_ACCT_NM ", inputValue);
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	//获取快钱商户
	public static DynamicSqlBean getKqMchtId(String inputValue, Operator operator, HttpServletRequest request){
		String sql = " select MCHT_NO,trim(MCHT_NO)||' - '|| trim(MCHT_NM) as MCHT_NMS from TBL_MCHT_BASE_INF WHERE PRODUCT_ID = '0003' ";
		sql += provideSqlDouLike(sql,"MCHT_NO",inputValue);
		return new DynamicSqlBean(sql,commQueryDAO);
	}
	
	/**
	 * 查询MCC
	 * @param inputValue
	 * @param operator
	 * @param reques
	 * @return
	 */
	public static DynamicSqlBean getMccTp(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "SELECT MCHNT_TP,DESCR FROM TBL_INF_MCHNT_TP";
		sql += provideSqlDouLike(sql, "DESCR ", inputValue);
		sql +=" ORDER BY MCHNT_TP ";
 		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 查询通道
	 * @param inputValue
	 * @param operator
	 * @param reques
	 * @return
	 */
	public static DynamicSqlBean getInsInf(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "SELECT INST_CODE,INST_CODE||'-'||INST_NAME FROM TBL_INS_INF";
		sql += provideSqlDouLike(sql, "INST_CODE||'-'||INST_NAME ", inputValue);
		sql +=" ORDER BY INST_CODE ";
 		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	public static DynamicSqlBean getBankBrhInfo (String inputValue,Operator operator,HttpServletRequest request){
		String sql="SELECT BRH_ID,BRH_ID||'-'||BRH_NAME as BRH_NAME FROM TBL_BRH_INFO WHERE BRH_TYPE='1' ";
		sql += provideSqlORDouLike(sql, "BRH_ID", inputValue);
		sql += provideSqlORDouLike(sql, "BRH_NAME ", inputValue);
		sql+=" order by BRH_ID";
		return new DynamicSqlBean(sql,commQueryDAO);
	} 
	

	public static DynamicSqlBean getCommSysParam(String inputValue,Operator operator,HttpServletRequest request) {
		String sql = " select KEY,VALUE from CST_SYS_PARAM where OWNER='"+inputValue+"' ";
		return new DynamicSqlBean(sql,commQueryDAO);
	}
	
	//获取渤海银行付款用途
	public static DynamicSqlBean getBhUseType(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select key,value from cst_sys_param where owner='bhUseType' order by sortno";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
	
	//获取接受支付短信验证码的手机号
	public static DynamicSqlBean getMobileNo(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "select key,value from cst_sys_param where owner='BhPayCodeMobile' order by sortno";
		return new DynamicSqlBean(sql, commQueryDAO);

	}
	//银行卡BIN
	public static DynamicSqlBean getBankbin(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "select  distinct trim(bin_sta_no) as key,  trim(bin_sta_no) as NAME from tbl_bank_bin_inf  ";
		sql += provideSqlDouLike(sql, " trim(bin_sta_no) ", inputValue);
		sql +=" ORDER BY  key ";
 		return new DynamicSqlBean(sql, commQueryDAO);
	}
	public static DynamicSqlBean getBinBank(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "SELECT DISTINCT TRIM(INS_ID_CD) as key,TRIM(INS_ID_CD) as name FROM TBL_BANK_BIN_INF ";
		sql += provideSqlDouLike(sql, "trim(INS_ID_CD) ", inputValue);
		sql +=" ORDER BY key ";
 		return new DynamicSqlBean(sql, commQueryDAO);
	}
	/**
	 * 查询新通道
	 * @param inputValue
	 * @param operator
	 * @param reques
	 * @return
	 */
	public static DynamicSqlBean getInsInfN(String inputValue, Operator operator, HttpServletRequest reques){
		String sql = "SELECT INST_CODE,INST_CODE||'-'||INST_NAME FROM TBL_INS_INF_N";
		sql += provideSqlDouLike(sql, "INST_CODE||'-'||INST_NAME ", inputValue);
		sql +=" ORDER BY INST_CODE ";
 		return new DynamicSqlBean(sql, commQueryDAO);
	}
	public static DynamicSqlBean getPowCode(String inputValue, Operator operator, HttpServletRequest request){
		
		String sql = "SELECT KEY,VALUE FROM CST_SYS_PARAM WHERE OWNER = 'PowSupCode' ";
		
		sql += provideSqlDouLike(sql, "VALUE", inputValue);
		
		sql += " order by VALUE";
		return new DynamicSqlBean(sql, commQueryDAO);
	}
}
