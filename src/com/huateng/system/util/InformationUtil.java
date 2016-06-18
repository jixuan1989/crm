/* @(#)
 *
 * Project:PFConsole
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ --------- ---------------------------------------------------
 *   Gavin        2011-8-10       first release
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
package com.huateng.system.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.common.StringUtil;

/**
 * Title:
 * 
 * File: InformationUtil.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-8-10
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author Gavin
 * 
 * @version 1.0
 */
public class InformationUtil {

	/**
	 * 组装title
	 * 
	 * @param base
	 * @param size
	 * @return 2011-8-10下午04:23:46
	 */
	public static String[] createTitles(String base, int size) {
		String[] s = new String[size];
		for (int i = 0; i < size; i++) {
			s[i] = base + String.valueOf(i + 1);
		}
		return s;
	}
	
	
	/**
	 * 根据机构编号获取机构名称
	 * 
	 * @return
	 * 2011-8-13下午12:04:32
	 */
	public static String getBrhName(String brhId){
		try {
			String sql = "select brh_name from tbl_brh_info where brh_id = '" + brhId + "'";
				List list = CommonFunction.getCommQueryDAO().findBySQLQuery(sql);
			if (null != list && !list.isEmpty() && !StringUtil.isNull(list.get(0))) {
				return list.get(0).toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String getInstName(String instCode){
		try {
			String sql = "select inst_name from tbl_ins_inf where inst_code = '" + instCode + "'";
				List list = CommonFunction.getCommQueryDAO().findBySQLQuery(sql);
			if (null != list && !list.isEmpty() && !StringUtil.isNull(list.get(0))) {
				return list.get(0).toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String changeMonth(String date, int amount, boolean back){
		int curMonth = Integer.parseInt(date.substring(4, 6));
		int changeYear = 0;
		int changeMonth = 0;
		if (amount > curMonth - 1) {
			changeYear = (amount - curMonth + 1)/12 + 1;
			changeMonth = amount/12 + 1;
		} else {
			changeMonth = amount;
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
		System.out.println(changeMonth + "||" + changeYear);
		if (back) {
			c.roll(Calendar.MONTH, -1 * changeMonth);
			c.roll(Calendar.YEAR, -1 * changeYear);
		} else {
			c.roll(Calendar.MONTH, changeMonth);
			c.roll(Calendar.YEAR, changeYear);
		}
		SimpleDateFormat date8 = new SimpleDateFormat("yyyyMMdd");
		return date8.format(c.getTime());
	}
	/**
	 * 根据当前机构获得当前和下级BRH_ID
	 * @param id 行内机构号
	 * @return
	 */
	public static String getBrhGroupString(String id) {
		if (StringUtil.isNull(id)) {
			return "('" + id + "')";
		}
		try {
			
			Map<String, String> map = new HashMap<String, String>();
			
			map.put(id, id);
			
			map = CommonFunction.getBelowBrhMap(map);
			
			return CommonFunction.getBelowBrhInfo(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "('" + id + "')";
		}
	}
	/**
	 * 根据商户号获得其所属机构号
	 * 
	 * @return
	 * 2011-8-13下午12:04:32
	 */
	public static String getBrhIdByMchntNo(String mchntNo){
		try {
			String sql = "select ACQ_INST_ID from TBL_MCHT_BASE_INF where MCHT_NO = '" + mchntNo + "'";
				List list = CommonFunction.getCommQueryDAO().findBySQLQuery(sql);
			if (null != list && !list.isEmpty() && !StringUtil.isNull(list.get(0))) {
				return list.get(0).toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 取得清算账号
	 * 
	 * @param brhId
	 * @return
	 */
	public static String getSettleAcct(String brhId){
		try {
			String sql = "select BRH_ID,INNER_ACCT2 from SETTLE_DOC_INFO where TRIM(BRH_ID) = '" + brhId.trim() + "'";
			List list = CommonFunction.getCommQueryDAO().findBySQLQuery(sql);
			if (null != list && !list.isEmpty()) {
				Object[] obj = (Object[]) list.get(0);
				return String.valueOf(obj[1]);
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}