/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-3-9       first release
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
package com.huateng.startup.init;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.huateng.common.Constants;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.common.MenuUtil;
import com.huateng.po.TblFuncInf;
import com.huateng.system.util.ContextUtil;
import com.huateng.system.util.JSONBean;

/**
 * Title:系统菜单初始化
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-3-9
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class MenuInfoUtil {
	
	private static JSONBean allMenuBean = new JSONBean();
	
	/**
	 * 加载全部菜单
	 */
	@SuppressWarnings("unchecked")
	public static void init() {
		
		String hql = "from com.huateng.po.TblFuncInf t where t.FuncType in ('0','1','2') order by t.FuncId";
		ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
		List<TblFuncInf> funcInfList = commQueryDAO.findByHQLQuery(hql);
		for(int i = 0,n = funcInfList.size(); i < n; i++) {
			TblFuncInf tblFuncInf = funcInfList.get(i);
			
			if(Constants.MENU_LVL_1.equals(tblFuncInf.getFuncType())) {//一级菜单
				Map<String, Object> menuBean = new LinkedHashMap<String, Object>();
				String menuId = tblFuncInf.getFuncId().toString().trim();
				menuBean.put(Constants.MENU_ID, menuId);
				menuBean.put(Constants.MENU_TEXT, tblFuncInf.getFuncName().trim());
				menuBean.put(Constants.MENU_CLS, Constants.MENU_FOLDER);
				allMenuBean.addJSONArrayElement(menuBean);
			} else if(Constants.MENU_LVL_2.equals(tblFuncInf.getFuncType())) {//二级菜单
				Map<String, Object> menuBean = new LinkedHashMap<String, Object>();
				menuBean.put(Constants.MENU_ID, tblFuncInf.getFuncId().toString().trim());
				menuBean.put(Constants.MENU_TEXT, tblFuncInf.getFuncName().trim());
				menuBean.put(Constants.MENU_PARENT_ID, tblFuncInf.getFuncParentId().toString().trim());
				menuBean.put(Constants.MENU_CLS, Constants.MENU_FOLDER);
				addLvl2Menu(menuBean);
			} else if(Constants.MENU_LVL_3.equals(tblFuncInf.getFuncType())) {
				Map<String, Object> menuBean = new LinkedHashMap<String, Object>();
				menuBean.put(Constants.MENU_ID, tblFuncInf.getFuncId().toString().trim());
				menuBean.put(Constants.MENU_TEXT, tblFuncInf.getFuncName().trim());
				menuBean.put(Constants.MENU_PARENT_ID, tblFuncInf.getFuncParentId().toString().trim());
				menuBean.put(Constants.MENU_LEAF, true);
				menuBean.put(Constants.MENU_URL, tblFuncInf.getPageUrl().trim());
				menuBean.put(Constants.MENU_CLS, Constants.MENU_FILE);
				
				if("-".equals(tblFuncInf.getIconPath().trim())) {
					menuBean.put(Constants.TOOLBAR_ICON, Constants.TOOLBAR_ICON_MENUITEM);
				} else {
					menuBean.put(Constants.TOOLBAR_ICON, tblFuncInf.getIconPath().trim());
				}
				addLvl3Menu(menuBean);
				
			}
		}
		
		//删除没有用的菜单
		List<Object> menuLvl1List = allMenuBean.getDataList();
		for(int i = 0; i < menuLvl1List.size(); i++) {
			Map<String, Object> menuLvl1Bean = (Map<String, Object>) menuLvl1List.get(i);
			if(!menuLvl1Bean.containsKey(Constants.MENU_CHILDREN)) {
				menuLvl1List.remove(i);
				i--;
				continue;
			}
			List<Object> menuLvl2List = (List<Object>) menuLvl1Bean.get(Constants.MENU_CHILDREN);
			for(int j = 0; j < menuLvl2List.size(); j++) { 
				Map<String, Object> menuLvl2Bean = (Map<String, Object>) menuLvl2List.get(j);
				if(!menuLvl2Bean.containsKey(Constants.MENU_CHILDREN)) {
					menuLvl2List.remove(j);
					menuLvl1Bean.put(Constants.MENU_CHILDREN, menuLvl2List);
					menuLvl1List.set(i, menuLvl1Bean);
					allMenuBean.setDataList(menuLvl1List);
					j--;
				}
			}
		}
	}
	
	/**
	 * 将二级菜单添加到一级菜单中
	 * @param menuBean
	 */
	@SuppressWarnings("unchecked")
	private static void addLvl2Menu(Map<String, Object> menuBean) {
		List<Object> menuLvl1List = allMenuBean.getDataList();
		for(int i = 0,n = menuLvl1List.size(); i < n; i++) {
			Map<String, Object> tmpMenuBean = (Map<String, Object>) menuLvl1List.get(i);
			//如果二级菜单的父菜单编号和一级菜单编号一致，则加入到该一级菜单中
			if(tmpMenuBean.get(Constants.MENU_ID).toString().trim().equals(
					menuBean.get(Constants.MENU_PARENT_ID).toString().trim())) {
				if(!tmpMenuBean.containsKey(Constants.MENU_CHILDREN)) {
					LinkedList<Object> menuLvl2List = new LinkedList<Object>();
					menuLvl2List.add(menuBean);
					tmpMenuBean.put(Constants.MENU_CHILDREN,menuLvl2List);
				} else {
					LinkedList<Object> menuLvl2List = (LinkedList<Object>) tmpMenuBean.get(Constants.MENU_CHILDREN);
					menuLvl2List.add(menuBean);
					tmpMenuBean.put(Constants.MENU_CHILDREN,menuLvl2List);
				}
				menuLvl1List.set(i, tmpMenuBean);
			}
		}
		allMenuBean.setDataList(menuLvl1List);
	}
	
	/**
	 * 将三级菜单添加到二级菜单中
	 * @param menuBean
	 */
	@SuppressWarnings("unchecked")
	private static void addLvl3Menu(Map<String, Object> menuBean) {
		List<Object> menuLvl1List = allMenuBean.getDataList();
		for(int i = 0,n = menuLvl1List.size(); i < n; i++) {
			Map<String, Object> tmpMenuBeanLvl1 = (Map<String, Object>) menuLvl1List.get(i);
			LinkedList<Object> menuLvl2List = (LinkedList<Object>) tmpMenuBeanLvl1.get(Constants.MENU_CHILDREN);
			if(menuLvl2List == null) {
				continue;
			}
			for(int j = 0,m = menuLvl2List.size(); j < m; j++) {
				Map<String, Object> tmpMenuBeanLvl2 = (Map<String, Object>) menuLvl2List.get(j);
				//如果三级菜单的父菜单编号和 二级菜单编号一致，则加入到该一级菜单中
				if(tmpMenuBeanLvl2.get(Constants.MENU_ID).toString().trim().equals(
						menuBean.get(Constants.MENU_PARENT_ID).toString().trim())) {
					if(!tmpMenuBeanLvl2.containsKey(Constants.MENU_CHILDREN)) {
						LinkedList<Object> menuLvl3List = new LinkedList<Object>();
						menuLvl3List.add(menuBean);
						tmpMenuBeanLvl2.put(Constants.MENU_CHILDREN, menuLvl3List);
					} else {
						LinkedList<Object> menuLvl3List = (LinkedList<Object>) tmpMenuBeanLvl2.get(Constants.MENU_CHILDREN);
						menuLvl3List.add(menuBean);
						tmpMenuBeanLvl2.put(Constants.MENU_CHILDREN, menuLvl3List);
					}
					menuLvl2List.set(j, tmpMenuBeanLvl2);
				}
			}
			tmpMenuBeanLvl1.put(Constants.MENU_CHILDREN,menuLvl2List);
			menuLvl1List.set(i, tmpMenuBeanLvl1);
		}
		allMenuBean.setDataList(menuLvl1List);
	}
	
	/**
	 * 通过级别分配交易权限
	 * @param degree
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, Object> setOperatorMenuWithDegree(String degree,String contextPath,Document document) {
		String sql = "select * FROM TBL_ROLE_FUNC_MAP WHERE KEY_ID in ( " + degree + ")";
		ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
		List<Object[]> funcMapList = commQueryDAO.findBySQLQuery(sql);
		valueIdRole(funcMapList);
		List<Object> menuLvl1List = allMenuBean.getDataList();
		LinkedHashMap<String, Object> menuIndexMap = new LinkedHashMap<String, Object>();
		String  panelStr = "";
		//遍历寻找匹配的菜单
		for(int i = 0,n = menuLvl1List.size(); i < n; i++) {
			Map<String, Object> menuLvl1Map = (Map<String, Object>) menuLvl1List.get(i);
			LinkedList<Object> menuLvl2List = (LinkedList<Object>) menuLvl1Map.get(Constants.MENU_CHILDREN);
			List<String> menuList = new ArrayList<String>();
			for(int j = 0,m = menuLvl2List.size(); j < m; j++) {
				Map<String, Object> menuLvl2Map = (Map<String, Object>) menuLvl2List.get(j);
				LinkedList<Object> menuLvl3List = (LinkedList<Object>) menuLvl2Map.get(Constants.MENU_CHILDREN);
				for(int k = 0,l = menuLvl3List.size(); k < l; k++) {
					Map<String, Object> menuLvl3Map = (Map<String, Object>) menuLvl3List.get(k);
					for(int a = 0,b = funcMapList.size(); a < b; a++) {
						if(StringUtils.trim(funcMapList.get(a)[1].toString()).equals(
								StringUtils.trim(menuLvl3Map.get(Constants.MENU_ID).toString()))) {
							if(!menuIndexMap.containsKey(menuLvl1Map.get(Constants.MENU_ID).toString())) {
								Map<String, Object> menuLvl1HashMap = new LinkedHashMap<String, Object>();
								String menuId = menuLvl1Map.get(Constants.MENU_ID).toString();
								menuLvl1HashMap.put(Constants.MENU_ID, menuLvl1Map.get(Constants.MENU_ID));
								menuLvl1HashMap.put(Constants.MENU_TEXT, menuLvl1Map.get(Constants.MENU_TEXT));
								menuLvl1HashMap.put(Constants.MENU_CLS, menuLvl1Map.get(Constants.MENU_CLS));
								menuLvl1HashMap.put(Constants.MENU_HANDLER, Constants.MENU_LVL1_FUNC);
								menuLvl1HashMap.put(Constants.TOOL_BAR_TYPE, Constants.TOOL_BAR_BUTTON);
								menuLvl1HashMap.put(Constants.TOOLBAR_ICON, Constants.TOOLBAR_ICON_MENU);
								menuLvl1HashMap.put(Constants.IS_EXPAND, true);
								
								if("1".equals(menuId) && panelStr.indexOf("baseTree") == -1){
									panelStr = panelStr + "baseTree,";
								}
								if("2".equals(menuId) && panelStr.indexOf("mchtTree") == -1){
									panelStr = panelStr + "mchtTree,";
								}
								if("3".equals(menuId) && panelStr.indexOf("termTree") == -1){
									panelStr = panelStr + "termTree,";
								}
								if("5".equals(menuId) && panelStr.indexOf("queryTree") == -1){
									panelStr = panelStr + "queryTree,";
								}
								if("9".equals(menuId) && panelStr.indexOf("onLinePayTree") == -1){
									panelStr = panelStr + "onLinePayTree,";
								}
								if("10".equals(menuId) && panelStr.indexOf("taskTree") == -1){
									panelStr = panelStr + "taskTree,";
								}
								if("8".equals(menuId) && panelStr.indexOf("clearTree") == -1){
									panelStr = panelStr + "clearTree,";
								}
								if("4".equals(menuId) && panelStr.indexOf("riskTree") == -1){
									panelStr = panelStr + "riskTree,";
								}
								if("11".equals(menuId) && panelStr.indexOf("provisionsTree") == -1){
									panelStr = panelStr + "provisionsTree,";
								}
								if("12".equals(menuId) && panelStr.indexOf("errorTree") == -1){
									panelStr = panelStr + "errorTree,";
								}
								Map<String, Object> menuLvl2HashMap = new LinkedHashMap<String, Object>();
								menuLvl2HashMap.put(Constants.MENU_ID, menuLvl2Map.get(Constants.MENU_ID));
								menuLvl2HashMap.put(Constants.MENU_TEXT, menuLvl2Map.get(Constants.MENU_TEXT));
								menuLvl2HashMap.put(Constants.MENU_PARENT_ID, menuLvl2Map.get(Constants.MENU_PARENT_ID));
								menuLvl2HashMap.put(Constants.MENU_CLS, menuLvl2Map.get(Constants.MENU_CLS));
								menuLvl2HashMap.put(Constants.TOOLBAR_ICON, Constants.TOOLBAR_ICON_MENUITEM);
								menuLvl2HashMap.put(Constants.IS_EXPAND, true);
								
								Map<String, Object> menuLvl3HashMap = new LinkedHashMap<String, Object>();
								int index = menuList.lastIndexOf((String)menuLvl3Map.get(Constants.MENU_ID));
								LinkedList<Object> menuLvl3Child = new LinkedList<Object>();
								//System.out.println((String)menuLvl3Map.get(Constants.MENU_ID) + ">>>>>>>>>" + index + ">>>>>" + menuList.size());
								if(-1 == index){
									String tbar = MenuUtil.initMenu((String)menuLvl3Map.get(Constants.MENU_ID),(String)funcMapList.get(a)[5]);
									menuLvl3HashMap.put(Constants.MENU_ID, menuLvl3Map.get(Constants.MENU_ID));
									menuLvl3HashMap.put(Constants.MENU_TEXT, menuLvl3Map.get(Constants.MENU_TEXT));
									menuLvl3HashMap.put(Constants.MENU_PARENT_ID, menuLvl3Map.get(Constants.MENU_PARENT_ID));
									menuLvl3HashMap.put(Constants.MENU_LEAF, true);
									menuLvl3HashMap.put(Constants.MENU_URL, menuLvl3Map.get(Constants.MENU_URL));
									menuLvl3HashMap.put(Constants.ROLE_FLAG, tbar);
									menuLvl3HashMap.put(Constants.MENU_CLS, menuLvl3Map.get(Constants.MENU_CLS));
									menuLvl3HashMap.put(Constants.MENU_HANDLER, Constants.MENU_LVL3_FUNC);
									menuLvl3HashMap.put(Constants.TOOLBAR_ICON, menuLvl3Map.get(Constants.TOOLBAR_ICON));
									menuLvl3HashMap.put(Constants.IS_EXPAND, true);
									
									menuList.add((String)menuLvl3Map.get(Constants.MENU_ID));
									
									menuLvl3Child.add(menuLvl3HashMap);
								}else{
									Map<String, Object> menu = (Map<String, Object>)menuLvl3Child.get(index);
									menu.put(Constants.ROLE_FLAG,(String)menu.get(Constants.ROLE_FLAG) + funcMapList.get(a)[5]);
								}
								
								
								menuLvl2HashMap.put(Constants.MENU_CHILDREN, menuLvl3Child);
								menuLvl2HashMap.put(Constants.TOOL_BAR_CHILDREN, menuLvl3Child);
								LinkedList<Object> menuLvl2Child = new LinkedList<Object>();
								menuLvl2Child.add(menuLvl2HashMap);
								menuLvl1HashMap.put(Constants.MENU_CHILDREN, menuLvl2Child);
								menuLvl1HashMap.put(Constants.TOOL_BAR_CHILDREN, menuLvl2Child);
								
								menuIndexMap.put(menuLvl1Map.get(Constants.MENU_ID).toString(), menuLvl1HashMap);
							} else {
								Map<String, Object> menuLvl1HashMap = (Map<String, Object>) 
													menuIndexMap.get(menuLvl1Map.get(Constants.MENU_ID).toString());
								LinkedList<Object> menuLvl2Child = (LinkedList<Object>) menuLvl1HashMap.get(Constants.MENU_CHILDREN);
								boolean hasMenu = false;
								for(int c = 0,d = menuLvl2Child.size(); c < d; c++) {
									Map<String, Object> menuLvl2HashMap = (Map<String, Object>) menuLvl2Child.get(c);
									if(StringUtils.trim(menuLvl2HashMap.get(Constants.MENU_ID).toString()).equals(
											StringUtils.trim(menuLvl2Map.get(Constants.MENU_ID).toString()))) {
										LinkedList<Object> menuLvl3Child = (LinkedList<Object>) menuLvl2HashMap.get(Constants.MENU_CHILDREN);
										Map<String, Object> menuLvl3HashMap = new LinkedHashMap<String, Object>();
										int index = menuList.lastIndexOf((String)menuLvl3Map.get(Constants.MENU_ID));
										if(-1 == index){
											String tbar = MenuUtil.initMenu((String)menuLvl3Map.get(Constants.MENU_ID),(String)funcMapList.get(a)[5]);
											menuLvl3HashMap.put(Constants.MENU_ID, menuLvl3Map.get(Constants.MENU_ID));
											menuLvl3HashMap.put(Constants.MENU_TEXT, menuLvl3Map.get(Constants.MENU_TEXT));
											menuLvl3HashMap.put(Constants.MENU_PARENT_ID, menuLvl3Map.get(Constants.MENU_PARENT_ID));
											menuLvl3HashMap.put(Constants.MENU_LEAF, true);
											menuLvl3HashMap.put(Constants.MENU_URL, menuLvl3Map.get(Constants.MENU_URL));
											menuLvl3HashMap.put(Constants.ROLE_FLAG, tbar);
											menuLvl3HashMap.put(Constants.MENU_CLS, menuLvl3Map.get(Constants.MENU_CLS));
											menuLvl3HashMap.put(Constants.MENU_HANDLER, Constants.MENU_LVL3_FUNC);
											menuLvl3HashMap.put(Constants.TOOLBAR_ICON, menuLvl3Map.get(Constants.TOOLBAR_ICON));
											menuLvl3HashMap.put(Constants.IS_EXPAND, true);
											
											menuList.add((String)menuLvl3Map.get(Constants.MENU_ID));
											menuLvl3Child.add(menuLvl3HashMap);
										}
//										else{
//											Map<String, Object> menu = (Map<String, Object>)menuLvl3Child.get(index);
//											menu.put(Constants.ROLE_FLAG,(String)menu.get(Constants.ROLE_FLAG) + funcMapList.get(a)[5]);
//										}
										menuLvl2HashMap.put(Constants.MENU_CHILDREN, menuLvl3Child);
										menuLvl2HashMap.put(Constants.TOOL_BAR_CHILDREN, menuLvl3Child);
										menuLvl2HashMap.put(Constants.IS_EXPAND, true);
										menuLvl2Child.set(c, menuLvl2HashMap);
										hasMenu = true;
										break;
									}
								}
								if(!hasMenu) {
									String tbar = MenuUtil.initMenu((String)menuLvl3Map.get(Constants.MENU_ID),(String)funcMapList.get(a)[5]);
									Map<String, Object> menuLvl2HashMap = new HashMap<String, Object>();
									menuLvl2HashMap.put(Constants.MENU_ID, menuLvl2Map.get(Constants.MENU_ID));
									menuLvl2HashMap.put(Constants.MENU_TEXT, menuLvl2Map.get(Constants.MENU_TEXT));
									menuLvl2HashMap.put(Constants.MENU_PARENT_ID, menuLvl2Map.get(Constants.MENU_PARENT_ID));
									menuLvl2HashMap.put(Constants.MENU_CLS, menuLvl2Map.get(Constants.MENU_CLS));
									menuLvl2HashMap.put(Constants.TOOLBAR_ICON, Constants.TOOLBAR_ICON_MENUITEM);
									menuLvl2HashMap.put(Constants.IS_EXPAND, true);
									LinkedList<Object> menuLvl3Child = new LinkedList<Object>();
									Map<String, Object> menuLvl3HashMap = new LinkedHashMap<String, Object>();
									menuLvl3HashMap.put(Constants.MENU_ID, menuLvl3Map.get(Constants.MENU_ID));
									menuLvl3HashMap.put(Constants.MENU_TEXT, menuLvl3Map.get(Constants.MENU_TEXT));
									menuLvl3HashMap.put(Constants.MENU_PARENT_ID, menuLvl3Map.get(Constants.MENU_PARENT_ID));
									menuLvl3HashMap.put(Constants.MENU_LEAF, true);
									menuLvl3HashMap.put(Constants.MENU_URL, menuLvl3Map.get(Constants.MENU_URL));
									menuLvl3HashMap.put(Constants.MENU_CLS, menuLvl3Map.get(Constants.MENU_CLS));
									menuLvl3HashMap.put(Constants.ROLE_FLAG, tbar);
									menuLvl3HashMap.put(Constants.MENU_HANDLER, Constants.MENU_LVL3_FUNC);
									//menuLvl3HashMap.put(Constants.TOOLBAR_ICON, Constants.TOOLBAR_ICON_MENUITEM);
									menuLvl3HashMap.put(Constants.TOOLBAR_ICON, menuLvl3Map.get(Constants.TOOLBAR_ICON));
									menuLvl3HashMap.put(Constants.IS_EXPAND, true);
									
									menuLvl3Child.add(menuLvl3HashMap);
									menuLvl2HashMap.put(Constants.MENU_CHILDREN, menuLvl3Child);
									menuLvl2HashMap.put(Constants.TOOL_BAR_CHILDREN, menuLvl3Child);
									menuLvl2Child.add(menuLvl2HashMap);
								}
								menuLvl1HashMap.put(Constants.MENU_CHILDREN, menuLvl2Child);
								menuLvl1HashMap.put(Constants.TOOL_BAR_CHILDREN, menuLvl2Child);
								menuLvl1HashMap.put(Constants.IS_EXPAND, true);
								menuIndexMap.put(menuLvl1Map.get(Constants.MENU_ID).toString(), menuLvl1HashMap);
							}
						}
					}
				}
			}
		}
		menuIndexMap.remove(Constants.TREE_MENU_PANEL);
		if(panelStr.length() != 0){
			menuIndexMap.put(Constants.TREE_MENU_PANEL, panelStr.substring(0, panelStr.length()-1));
		}else{
			menuIndexMap.put(Constants.TREE_MENU_PANEL, "");
		}
		return menuIndexMap;
	}
	
	private static void valueIdRole(List<Object[]> funcMapList) {
		List<Object[]> list = new ArrayList<Object[]>();
		
		for(int i=0;i<funcMapList.size();i++){
			Object[] obj = funcMapList.get(i);
			BigDecimal valueId = (BigDecimal) obj[1];
			String roleFlag = (String)obj[5];
			
			for(int j=i+1;j<funcMapList.size();j++){
				Object[] obj2 = funcMapList.get(j);
				BigDecimal valueId2 = (BigDecimal) obj2[1];
				String roleFlag2 = (String)obj2[5];
				if(valueId.equals(valueId2)){
					roleFlag = roleFlag + roleFlag2;
					list.add(obj2);
				}
			}
			obj[5] = roleFlag;
		}
		for(Object[] ov : list){
			funcMapList.remove(ov);
		}
	}
}
