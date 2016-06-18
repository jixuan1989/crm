/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-3-12       first release
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
package com.huateng.dwr.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.common.select.SelectOption;
import com.huateng.log.Log;
import com.huateng.system.util.JSONBean;

/**
 * Title:异步读取下拉列表内容
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-3-12
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author liuxianxian
 * 
 * @version 1.0
 */
public class SelectOptionsDWR {
	
	
	/**
	 * 根据下拉列表所显示数据类型显示内容
	 * @param txnId
	 * @return
	 */
	public String getComboData(String txnId,HttpServletRequest request,	HttpServletResponse response) {
		String jsonData = "{[{'value':'','name':'没有找到可选内容'}]}";
		try {
			//获得操作员信息
			Operator operator = (Operator) request.getSession().getAttribute(Constants.OPERATOR_INFO);
			Map<String, String> dataMap = SelectOption.getSelectView(txnId, new Object[]{operator});
			Iterator<String> iter = dataMap.keySet().iterator();
			if(iter.hasNext()) {
				Map<String, Object> jsonDataMap = new HashMap<String, Object>();
				LinkedList<Object> jsonDataList = new LinkedList<Object>();
				Map<String, String> tmpMap = null;
				String key = null;
				while(iter.hasNext()) {
					tmpMap = new LinkedHashMap<String, String>();
					key = iter.next();
					tmpMap.put("value", key);
					tmpMap.put("name", dataMap.get(key));
					jsonDataList.add(tmpMap);
				}
				//jsonDataMap.put("data", jsonDataList);
				//jsonData = JSONBean.genMapToJSON(jsonDataMap);
				jsonData = JSONBean.genListToJSON(jsonDataList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log(e.getMessage());
		}
		return jsonData;
	}
	
	/**
	 * 根据下拉列表所显示数据类型显示内容，可带参数
	 * @param txnId
	 * @return
	 */
	public String getComboDataWithParameter(String txnId,String parameter,HttpServletRequest request,HttpServletResponse response) {		
		String jsonData = "{[{'value':'','name':'没有找到可选内容'}]}";
		try {
			//获得操作员信息
			Operator operator = (Operator) request.getSession().getAttribute(Constants.OPERATOR_INFO);
			Map<String, String> dataMap = SelectOption.getSelectView(txnId, new Object[]{operator,parameter});
			Iterator<String> iter = dataMap.keySet().iterator();
			if(iter.hasNext()) {
				Map<String, Object> jsonDataMap = new HashMap<String, Object>();
				LinkedList<Object> jsonDataList = new LinkedList<Object>();
				Map<String, String> tmpMap = null;
				String key = null;
				while(iter.hasNext()) {
					tmpMap = new LinkedHashMap<String, String>();
					key = iter.next();
					tmpMap.put("value", key);
					tmpMap.put("name", dataMap.get(key));
					jsonDataList.add(tmpMap);
				}
				//jsonDataMap.put("data", jsonDataList);
				jsonData = JSONBean.genListToJSON(jsonDataList);
				//jsonData = JSONBean.genMapToJSON(jsonDataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log(e.getMessage());
		}
		return jsonData;
	}
	
	/**
	 * 获得数据集
	 * @param txnId
	 * @param request
	 * @param response
	 * @return
	 * 2010-8-18上午11:36:58
	 */
	public Map<String, String> getDataMap(String txnId,HttpServletRequest request,
			HttpServletResponse response)  {
		try {
		//获得操作员信息
			Operator operator = (Operator) request.getSession().getAttribute(Constants.OPERATOR_INFO);
			Map<String, String> dataMap = SelectOption.getSelectView(txnId, new Object[]{operator});
			return dataMap;
		} catch (Exception e) {
			e.printStackTrace();
			Log.log(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获得权限集
	 * @param txnId
	 * @param request
	 * @param response
	 * @return
	 * 2010-8-18上午11:36:58
	 */	
	public String getFuncAllData(String txnId,HttpServletRequest request,HttpServletResponse response) {
		String jsonData = "{data:[{'valueField':'','displayField':'没有找到可选内容'}]}";
		try {
			//获得操作员信息
			Operator operator = (Operator) request.getSession().getAttribute(Constants.OPERATOR_INFO);
			Map<String, String> dataMap = SelectOption.getSelectView(txnId, new Object[]{operator});
			Iterator<String> iter = dataMap.keySet().iterator();
			if(iter.hasNext()) {
				Map<String, Object> jsonDataMap = new HashMap<String, Object>();
				LinkedList<Object> jsonDataList = new LinkedList<Object>();
				Map<String, String> tmpMap = null;
				String key = null;
				while(iter.hasNext()) {
					tmpMap = new LinkedHashMap<String, String>();
					key = iter.next();
					tmpMap.put("valueField", key);
					tmpMap.put("displayField", dataMap.get(key));
					jsonDataList.add(tmpMap);
				}
				jsonDataMap.put("data", jsonDataList);
				jsonData = JSONBean.genMapToJSON(jsonDataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log(e.getMessage());
		}
		return jsonData;
	}
	
}