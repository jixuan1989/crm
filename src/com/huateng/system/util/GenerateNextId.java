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
package com.huateng.system.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;

import as.huateng.common.CommonFunction;

import com.huateng.common.StringUtil;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.log.Log;
import com.huateng.po.TblSequence;

import freemarker.template.SimpleDate;

/**
 * Title:生成系统编号
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
public class GenerateNextId {
	
	private static ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
	
	/**
	 * 获得idcd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getDiscId() {
		String idcd = "00000";
		String sql = "select max(DISC_CD) from TBL_INF_DISC_CD";
		List idList = commQueryDAO.findBySQLQuery(sql);
		if (idList.size() != 0 && idList.get(0) != null && idList.get(0).toString().trim().length() != 0){
			idcd = CommonFunction.fillString(idList.get(0).toString(),'0', 5, false);
		}			
		idcd = CommonFunction.fillString(String.valueOf(Integer.parseInt(idcd) + 1), '0', 5, false);
		return idcd;
		
	}
	
	
	/**
	 * 获得银联卡编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getBankBinId() {
		String sql = "select min(IND + 1) from TBL_BANK_BIN_INF where " +
				"(IND + 1) not in (select IND from TBL_BANK_BIN_INF)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "1";
		}
		
	}
	
	/**
	 * 获得角色信息的编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getNextRoleId() {
		String sql = "select min(role_id + 1) from tbl_role_inf where " +
				"(role_id + 1) not in (select role_id from tbl_role_inf)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		return resultSet.get(0).toString();
	}
	/**
	 * 型号维护
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getNextTypeNo() {
		String sql = "select min(type_no + 1) from tbl_factory_type where (type_no + 1) not in (select type_no from tbl_factory_type)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "1000";
		}
	}
	/**
	 * 获得机具编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getNextPosNo(String posNo) {
		String sql = "select min(ID + 1) from TBL_POS_INF where (ID + 1) not in (select ID from TBL_POS_INF)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "1000";
		}
	}
	
	/**
	 * 获得机具信息表编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getNextPostTxnNo() {
		String sql = "select min(ID + 1) from TBL_POS_RECORD where (ID + 1) not in (select ID from TBL_POS_RECORD)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "1000";
		}
	}
	/**
	 * 获得厂商编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getFactoryId() {
		String sql = "select min(FACTORY_ID + 1) from tbl_factory_management where (FACTORY_ID + 1) not in (select FACTORY_ID from tbl_factory_management)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "0000";
		}
	}

	/**
	 * 获得调单编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getNextOrderId() {
		String sql = "select min(ID + 1) from tbl_mcht_order where (ID + 1) not in (select ID from tbl_mcht_order)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "1000";
		}
	}
	
	/**
	 * 获得服务费编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getServiceFeeId() {
		String sql = "select min(ID + 1) from tbl_mcht_fee where (ID + 1) not in (select ID from tbl_mcht_fee)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "0000";
		}
	}
	
	/**
	 * 获得押金编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getDepositId() {
		String sql = "select min(ID + 1) from tbl_mcht_deposit where (ID + 1) not in (select ID from tbl_mcht_deposit)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "1000";
		}
	}
	/**
	 * 获得任务编号
	 * @return
	 */
	public static synchronized String getTaskId() {
//		String sql = "select min(ID + 1) from tbl_task_info where (ID + 1) not in (select ID from tbl_task_info)";
//		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
//		if(resultSet.size()>0 && resultSet.get(0) != null){
//			return resultSet.get(0).toString();
//		}else{
//			return "1000";
//		}
		return getNextSequence("TBL_TASK_INFO");
	}
	/**
	 * @deprecated
	 * 获取表下一个主键ID值
	 * @param key 主键ID
	 * @param table 需要生成下一主键ID的表名
	 * @return
	 * 2011-11-14上午09:49:17
	 */
	public static synchronized String getNextID(String key, String table) {
		if(StringUtils.isBlank(key) || StringUtils.isBlank(table)) 
			throw new NullPointerException("Parameter is null in method getNextID(String key, String table)");
		
		key = StringUtils.strip(key);
		table = StringUtils.strip(table);
			
		StringBuilder sql = new StringBuilder("SELECT MIN("+ key + "+" + 1 +") FROM ");
		sql.append(table)
			.append(" WHERE ")
			.append("(" + key + "+" + "1" + ")")
			.append(" NOT IN (SELECT "+ key +" FROM " + table + ")");
		
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql.toString());
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "1";
		}
	}
	
	public static String getNextSequence_datePlusSeq(String tableName){
		synchronized(GenerateNextId.class) {
			Assert.assertNotNull(tableName);
			if(StringUtils.isBlank(tableName)) 
				throw new NullPointerException("Parameter is null in method getNextSequence(String tableName)");
			
			String sql = "select id,nowdate FROM Tbl_Sequence t WHERE t.table_Name='"+tableName+"'";
			List<Object[]> resultSet = commQueryDAO.findBySQLQuery(sql);
			if(resultSet != null && resultSet.size() > 0) {
				for(Object[] obj : resultSet) {
					if(obj != null) {
						String seq =obj[0]+"";
						String Date = obj[1]+"" ;
						String nowSeq = null ;
						long id = Long.parseLong(seq);
						String nowDate = CommonFunction.getCurrDate();
						try {
							if(!nowDate.equals(Date)){
								id = 0 ;
							}
							nowSeq = StringUtil.beforFillValue((id + 1)+"", 6, '0');
							
							sql = "UPDATE TBL_SEQUENCE t SET t.ID='"+nowSeq+"',NOWDATE='"+nowDate+"' WHERE t.TABLE_NAME='"+tableName+"'";
							commQueryDAO.excute(sql);
							return nowDate+nowSeq ;
						} catch (NumberFormatException e) {
							Log.log("方法getNextSequence(String tableName)出现异常，异常信息：" + e);
							return null;
						}
					}
				}
			} else {
				sql = "INSERT INTO TBL_SEQUENCE VALUES ('"+tableName+"', '1','"+ CommonFunction.getCurrDate()+"')";
				commQueryDAO.excute(sql);
				return "1";
			}
			
			return null;
		}
	
	}
	/**
	 * 通过表TBL_SEQUENCE来维护主键
	 * @param tableName 需要产生主键的表名
	 * @return null if has Exception
	 * 2011-11-23下午05:04:39
	 */
	public static String getNextSequence(String tableName) {
		synchronized(GenerateNextId.class) {
			Assert.assertNotNull(tableName);
			if(StringUtils.isBlank(tableName)) 
				throw new NullPointerException("Parameter is null in method getNextSequence(String tableName)");
			
			String sql = "FROM com.huateng.po.TblSequence t WHERE t.tableName='"+tableName+"'";
			List<TblSequence> resultSet = commQueryDAO.find(sql);
			if(resultSet != null && resultSet.size() > 0) {
				for(TblSequence tbl : resultSet) {
					if(tbl != null) {
						String seq = tbl.getId();
						try {
							long id = Long.parseLong(seq);
							id = (id + 1);
							sql = "UPDATE TBL_SEQUENCE t SET t.ID='"+id+"' WHERE t.TABLE_NAME='"+tableName+"'";
							commQueryDAO.excute(sql);
							return id + "";
						} catch (NumberFormatException e) {
							Log.log("方法getNextSequence(String tableName)出现异常，异常信息：" + e);
							return null;
						}
					}
				}
			} else {
				sql = "INSERT INTO TBL_SEQUENCE(Table_Name,Id) VALUES ('"+tableName+"', '1')";
				commQueryDAO.excute(sql);
				return "1";
			}
			
			return null;
		}
	}
	
	/**
	 * 获得回访派工任务编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getTaskCallbackId() {
		String sql = "select min(ID + 1) from tbl_task_callback where (ID + 1) not in (select ID from tbl_task_callback)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "0000";
		}
	}
	/**
	 * 获得其他任务信息编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getTaskOtherId() {
		String sql = "select min(ID + 1) from tbl_task_other where (ID + 1) not in (select ID from tbl_task_other)";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.size()>0 && resultSet.get(0) != null){
			return resultSet.get(0).toString();
		}else{
			return "0000";
		}
	}
	
	/**
	 * 内部编号获取，
	 * @param str 要生成内部编号的表名称
	 * @return
	 */
	public static synchronized String getSequences(String str) {
		String sql ="select t.table_name, t.nowdate, t.id  FROM tbl_sequence t " +
				" where t.table_name = '"+str+"' and t.nowdate = to_CHAR(sysdate, 'yyyymmdd')";
		List resultSet = commQueryDAO.findBySQLQuery(sql);
		String serial = "";
		if(resultSet.size() == 1){
			Object [] os = (Object[]) resultSet.get(0);
			String serialdate = (String) os[1];
			String serialno = (String) os[2];
			serial =serialdate + String.format("%06d", Long.parseLong(serialno));
			sql = "update tbl_sequence set id = '"+(Long.parseLong(serialno)+1)+"' WHERE nowdate = to_CHAR(sysdate, 'yyyymmdd') and table_name = '"+str+"'";
			commQueryDAO.excute(sql);
		}else {
			sql = "update tbl_sequence set nowdate = to_CHAR(sysdate, 'yyyymmdd'), " +
					" id = '2' WHERE table_name = '"+str+"'";
			commQueryDAO.excute(sql);
			serial = new SimpleDateFormat("yyyyMMdd").format(new Date())+String.format("%06d", 1);
			
		}
		return serial;
	}
	/**
	 * 生成商户编号
	 * @param str 862+地区码+MCC
	 * @return
	 */
	public static synchronized String createMchntNo(String str) {
		String sql ="select t.mchnt_prefix,t.mchnt_serialno from TBL_MCHNT_SEQUENCE t where t.mchnt_prefix = '"+str+"'";
		List resultSet = commQueryDAO.findBySQLQuery(sql);
		String serial = "";
		if(resultSet.size() == 1){
			Object [] os = (Object[]) resultSet.get(0);
			String mchntPrefix = (String) os[1];
			serial = str+mchntPrefix;
			sql = "update TBL_MCHNT_SEQUENCE set mchnt_serialno = '"+(Long.parseLong(mchntPrefix)+1)+"' WHERE mchnt_prefix = '"+str+"'";
			commQueryDAO.excute(sql);
		}else {
			sql = "insert into TBL_MCHNT_SEQUENCE values('"+str+"','"+5001+"')";
			commQueryDAO.excute(sql);
			serial = str+"5000";
		}
		return serial;
	}
	/**
	 * 获得终端类型编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getTermTpId() {
		String sql = "select min(term_tp + 1) from tbl_term_tp where " +
				"(term_tp + 1) not in (select term_tp from tbl_term_tp)";
		List<Double> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.get(0) == null) {
			return "01";
		}
		int id = resultSet.get(0).intValue();
		if(id == 100) {
			return "";
		}
		return CommonFunction.fillString(String.valueOf(id), '0', 2, false);
	}
	
	/**
	 * 查询操作日志流水号
	 * 
	 * @return 2010-12-9 上午10:21:38 Shuang.Pan
	 */
	public synchronized static String getTxnSeq() {
		String sql = "SELECT SEQ_TERM_NO.NEXTVAL FROM DUAL";
		sql = commQueryDAO.findBySQLQuery(sql).get(0).toString();
		sql = "1" + CommonFunction.fillString(sql, '0', 14, false);
		return sql;
	}
	
	/**
	 * 查询CA银联公钥编号
	 * 
	 * @return 2010-12-9 上午10:21:38 Shuang.Pan
	 */
	public synchronized static String getParaId() {
		String sql = "SELECT SEQ_EMV_PARA_NO.NEXTVAL FROM DUAL";
		sql = commQueryDAO.findBySQLQuery(sql).get(0).toString();
		sql = "1" + CommonFunction.fillString(sql, '0', 8, false);
		return sql;
	}
	
	/**
	 * 获得内部参数索引编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized String getParaIdx(String usageKey) {
		String sql = "select min(para_idx + 1) from tbl_emv_para where " +
				"(para_idx + 1) not in (select tm.para_idx from tbl_emv_para tm where tm.usage_key="+usageKey+") ";
		List<BigDecimal> resultSet = commQueryDAO.findBySQLQuery(sql);
		if(resultSet.get(0) == null) {
			return "01";
		}
		int id = resultSet.get(0).intValue();
		if(id == 100) {
			return "";
		}
		return CommonFunction.fillString(String.valueOf(id), '0', 2, false);
	}
	/**
	 * 生成终端号
	 * @return
	 */
	public static synchronized String createTermNo(){
		String serialNo = GenerateNextId.getNextSequence("TBL_TERM_INF");
		String termNo = "862" + String.format("%05d", Long.parseLong(serialNo));
		String sql = "select * from tbl_term_inf_tmp where term_id='"+termNo+"'";
		List list = commQueryDAO.findBySQLQuery(sql);
		if(list.size() == 0){
			return termNo;
		}else{
			createTermNo();
		}
		return null;
	};
	
	public static String getNextSequence_datePlusSeq(String tableName,long needAdd){
		synchronized(GenerateNextId.class) {
			Assert.assertNotNull(tableName);
			if(StringUtils.isBlank(tableName)) 
				throw new NullPointerException("Parameter is null in method getNextSequence(String tableName)");
			
			String sql = "select id,nowdate FROM Tbl_Sequence t WHERE t.table_Name='"+tableName+"'";
			List<Object[]> resultSet = commQueryDAO.findBySQLQuery(sql);
			if(resultSet != null && resultSet.size() > 0) {
				for(Object[] obj : resultSet) {
					if(obj != null) {
						String seq =obj[0]+"";
						String Date = obj[1]+"" ;
						String returnSeq = null ;
						String udpateSeq = null ;
						long id = Long.parseLong(seq);
						String nowDate = CommonFunction.getCurrDate();
						try {
							if(!nowDate.equals(Date)){
								id = 0 ;
							}
							returnSeq = StringUtil.beforFillValue((id + 1)+"", 6, '0');
							udpateSeq = StringUtil.beforFillValue((id + needAdd)+"", 6, '0');
							
							sql = "UPDATE TBL_SEQUENCE t SET t.ID='"+udpateSeq+"',NOWDATE='"+nowDate+"' WHERE t.TABLE_NAME='"+tableName+"'";
							commQueryDAO.excute(sql);
							return nowDate+returnSeq ;
						} catch (NumberFormatException e) {
							Log.log("方法getNextSequence(String tableName)出现异常，异常信息：" + e);
							return null;
						}
					}
				}
			} else {
				sql = "INSERT INTO TBL_SEQUENCE VALUES ('"+tableName+"', '1','"+ CommonFunction.getCurrDate()+"')";
				commQueryDAO.excute(sql);
				return "1";
			}
			
			return null;
		}
	
	}
	public static String getNextIdDes(String tableName,String dateFormat,int numberlen){

		synchronized(GenerateNextId.class) {
			Assert.assertNotNull(tableName);
			if(StringUtils.isBlank(tableName)) 
				throw new NullPointerException("Parameter is null in method getNextSequence(String tableName)");
			
			String sql = "select id,nowdate FROM Tbl_Sequence t WHERE t.table_Name='"+tableName+"'";
			List<Object[]> resultSet = commQueryDAO.findBySQLQuery(sql);
			String nowDate = CommonFunction.getCurrDate(dateFormat);
			String nowSeq = null ;
			if(resultSet != null && resultSet.size() > 0) {
				for(Object[] obj : resultSet) {
					if(obj != null) {
						String seq =obj[0]+"";
						String Date = obj[1]+"" ;
						long id = Long.parseLong(seq);
						
						try {
							if(!nowDate.equals(Date)){
								id = 0 ;
							}
							nowSeq = StringUtil.beforFillValue((id + 1)+"", numberlen, '0');
							
							sql = "UPDATE TBL_SEQUENCE t SET t.ID='"+nowSeq+"',NOWDATE='"+nowDate+"' WHERE t.TABLE_NAME='"+tableName+"'";
							commQueryDAO.excute(sql);
							return nowDate+nowSeq ;
						} catch (NumberFormatException e) {
							Log.log("方法getNextSequence(String tableName)出现异常，异常信息：" + e);
							return null;
						}
					}
				}
			} else {
				nowSeq = StringUtil.beforFillValue((1)+"", numberlen, '0');
				sql = "INSERT INTO TBL_SEQUENCE VALUES ('"+tableName+"','"+nowSeq+"','"+nowDate+"')";
				commQueryDAO.excute(sql);
				return nowDate+nowSeq ;
			}
			return null;
		}
		
	}
}
