/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-6-3       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2008 Huateng Software, Inc. All rights reserved.
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
package com.huateng.struts.system.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.common.StringUtil;
import com.huateng.startup.init.MenuInfoUtil;
import com.huateng.system.util.CommonFunction;

/**
 * Title:登录后跳转
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-6-3
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class LoginRedirectAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.huateng.struts.system.action.BaseAction#subExecute()
	 */
	@Override
	protected String subExecute() throws Exception {
		Operator operator = (Operator) getSessionAttribute(Constants.OPERATOR_INFO);
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = null;
		try {
			document = reader.read(ServletActionContext.getRequest().getSession().getServletContext().getResourceAsStream(Constants.MENU_CONFIG_CONTEXTPATH));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkedHashMap<String,Object> menuMap = MenuInfoUtil.setOperatorMenuWithDegree(operator.getOprDegree(),
						ServletActionContext.getRequest().getContextPath(),document);
		Iterator<String> iter = menuMap.keySet().iterator();
		LinkedList<Object> toolList = new LinkedList<Object>();
		List<String> menuList = new ArrayList<String>();
		while(iter.hasNext()) {
				toolList.add(menuMap.get(iter.next()));
		}
		String toolBarStr = JSONArray.fromObject(toolList).toString();
		//转换JSON格式中的方法名称
		
		toolBarStr = toolBarStr.replaceAll(Constants.MENU_LVL1_JSON_FUNC, Constants.MENU_LVL1_FUNC).
									replaceAll(Constants.MENU_LVL3_JSON_FUNC, Constants.MENU_LVL3_FUNC);
		//设置操作员
		setSessionAttribute(Constants.OPERATOR_INFO, operator);
		//去掉菜单树图标样式
		removeTreeCls(menuMap);
		//设置菜单树
		setSessionAttribute(Constants.TREE_MENU_MAP, menuMap);
		//设置工具栏
		setSessionAttribute(Constants.TOOL_BAR_STR, toolBarStr);
		//TODO 临时处理，以后请优化
		loadLastOperInfo(operator);
		String panelStr = menuMap.get(Constants.TREE_MENU_PANEL).toString();
		setSessionAttribute(Constants.TREE_MENU_PANEL, panelStr);
		return SUCCESS;
	}
	
	private void loadLastOperInfo(Operator operator) {
		
		StringBuffer whereSql = new StringBuffer();
		
		if(operator == null)
			return;
		
		if (!StringUtil.isNull(operator.getOprId())) {
			whereSql.append(" and a.OPR_ID = '" + operator.getOprId() + "' ");
		}
		
		/*String sql = "SELECT a.OPR_ID,TXN_DATE,TXN_TIME,TXN_NAME,TXN_STA,ERR_MSG "
			+ "FROM TBL_TXN_INFO a , TBL_OPR_INFO b WHERE a.OPR_ID = b.OPR_ID AND "
			+ "b.BRH_ID IN " + operator.getBrhBelowId()
			+ whereSql.toString()
			+ " AND ROWNUM=1 ORDER BY TXN_DATE DESC, TXN_TIME DESC";*/
		
		/*String sql = "SELECT *　FROM (SELECT a.OPR_ID,TXN_DATE,TXN_TIME,TXN_NAME,TXN_STA,ERR_MSG "
			+ "FROM TBL_TXN_INFO a , TBL_OPR_INFO b WHERE a.OPR_ID = b.OPR_ID AND "
			+ "b.BRH_ID IN " + operator.getBrhBelowId()
			+ whereSql.toString()
			+ " ORDER BY TXN_DATE DESC, TXN_TIME DESC) WHERE ROWNUM < 2";*/
		
		/*SELECT * FROM (SELECT *　FROM (SELECT a.OPR_ID,TXN_DATE,TXN_TIME,TXN_NAME,TXN_STA,ERR_MSG FROM TBL_TXN_INFO a , TBL_OPR_INFO b WHERE a.OPR_ID = b.OPR_ID AND b.BRH_ID IN ('0000','00001','0001','1111') 
		and a.OPR_ID = '0000'  ORDER BY TXN_DATE DESC, TXN_TIME DESC) WHERE ROWNUM<3
		ORDER BY TXN_TIME)  WHERE ROWNUM<3*/
		
		String sql = "SELECT * FROM (SELECT *　FROM (SELECT a.OPR_ID,TXN_DATE,TXN_TIME,TXN_NAME,TXN_STA,ERR_MSG "
			+ "FROM TBL_TXN_INFO a , TBL_OPR_INFO b WHERE a.OPR_ID = b.OPR_ID AND "
			+ "b.BRH_ID IN " + operator.getBrhBelowId()
			+ whereSql.toString()
			+ " ORDER BY TXN_DATE DESC, TXN_TIME DESC) WHERE ROWNUM < 3" 
			+ " ORDER BY TXN_TIME)  WHERE ROWNUM<2";
		
		List<Object[]> dataList = CommonFunction.getCommQueryDAO().findBySQLQuery(sql);
		StringBuffer buffer = new StringBuffer();
		for(Object[] obj :dataList) {
			//buffer.append("操作员:" + obj[0] + ", 上次登录时间: " + obj[1] + " " + obj[2] + " : " + obj[3]);
			buffer.append("操作员:" + obj[0] + "上次登录时间: " + obj[1].toString().substring(0, 4)+"-"+obj[1].toString().substring(4, 6)+"-"+obj[1].toString().substring(6, 8)+" "+obj[2].toString().substring(0, 2)+":"+obj[2].toString().substring(2, 4)+":"+obj[2].toString().substring(4, 6)+ " : " + obj[3]);
		}
		if(StringUtils.isNotEmpty(buffer.toString()))
			setSessionAttribute("OPERATOR_INFO", buffer.toString());
	}
	
	/**
	 * 去掉菜单树图标样式
	 * @param menuMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map removeTreeCls(Map<String,Object> menuMap) {
		Iterator<String> iter = menuMap.keySet().iterator();
		String key;
		Map map;
		List list;
		while(iter.hasNext()) {
			key = iter.next();
			if(menuMap.get(key) instanceof String && 
						Constants.TOOLBAR_ICON.equals(key)) {
				menuMap.remove(key);
				return menuMap;
			} else if(menuMap.get(key) instanceof Map) {
				map = (Map) menuMap.get(key);
				if(map.get(Constants.TOOLBAR_ICON) != null) {
					map.remove(Constants.TOOLBAR_ICON);
				}
				map = removeTreeCls(map);
				menuMap.put(key, map);
			}else if(menuMap.get(key) instanceof List) {
				list = (List)menuMap.get(key);
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i) instanceof Map) {
						list.set(i, removeTreeCls((Map)list.get(i)));
					}
				}
				menuMap.put(key, list);
			}
		}
		return menuMap;
	}

}
