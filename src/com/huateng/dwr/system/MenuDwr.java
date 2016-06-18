package com.huateng.dwr.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.system.util.ContextUtil;

public class MenuDwr {
	public String getRole(String menuId,HttpServletRequest request,HttpServletResponse response){
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = null;
		StringBuffer str = new StringBuffer();
		
		try {
			document = reader.read(request.getSession().getServletContext().getResourceAsStream(Constants.MENU_CONFIG_CONTEXTPATH));
			Element root = document.getRootElement();
			str.append("<table width='85%' style='word-break:break-all; word-wrap:break-all;'><tr>");
	        List<Element> elements = root.elements(Constants.MENU_CONFIG_ID);
	        for(int i = 0; i < elements.size(); i++) {
	        	Element menuElement = elements.get(i);
	        	String id = menuElement.attributeValue(Constants.SLT_XML_ID).trim();
	        	if(menuId.equals(id)){
	        		List<Element> btnElements = menuElement.elements(Constants.BUTTON_CONFIG);
		        	for(int j = 0; j < btnElements.size(); j++) {
		        		Element btn = btnElements.get(j);
		        		String value = btn.element(Constants.BUTTON_CONFIG_VALUE).getText();
		        		String name = btn.element(Constants.BUTTON_CONFIG_TEXT).getText();
		        		str.append("<td><input type='checkbox' name=" + menuId + "_check value='" + value + "' />" + name +"</td>");
			        	if(btnElements.size() > 4 && j == 3){
			        		str.append("</tr><tr>");
			        	}
		        	}
	        	}
	        }
	        str.append("<tr></table>");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return str.toString();
	}
	public String getRoleWithDegree(String menuId,String degree,HttpServletRequest request,HttpServletResponse response){
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = null;
		StringBuffer str = new StringBuffer();
		String roleFlag = null;
		Operator operator = (Operator) request.getSession().getAttribute(Constants.OPERATOR_INFO);
		String sql = "select * FROM TBL_ROLE_FUNC_MAP WHERE KEY_ID in ( " + degree + ") and  value_id = " + menuId;
		
		try {
			ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
			List<Object[]> funcMapList = commQueryDAO.findBySQLQuery(sql);
			if(funcMapList.size() > 0){
				roleFlag = funcMapList.get(0)[5].toString();
			}
			document = reader.read(request.getSession().getServletContext().getResourceAsStream(Constants.MENU_CONFIG_CONTEXTPATH));
			Element root = document.getRootElement();
			str.append("<table width='85%' style='word-break:break-all; word-wrap:break-all;'><tr>");
	        List<Element> elements = root.elements(Constants.MENU_CONFIG_ID);
	        for(int i = 0; i < elements.size(); i++) {
	        	Element menuElement = elements.get(i);
	        	String id = menuElement.attributeValue(Constants.SLT_XML_ID).trim();
	        	if(menuId.equals(id)){
	        		List<Element> btnElements = menuElement.elements(Constants.BUTTON_CONFIG);
		        	for(int j = 0; j < btnElements.size(); j++) {
		        		Element btn = btnElements.get(j);
		        		String value = btn.element(Constants.BUTTON_CONFIG_VALUE).getText();
		        		String name = btn.element(Constants.BUTTON_CONFIG_TEXT).getText();
		        		str.append("<td><input type='checkbox' name=" + menuId + "_check value='" + value + "' ");
			        	if(roleFlag.contains(value)){
			        		str.append("checked='checked'");
			        	}
			        	str.append(" />" + name +"</td>");
			        	if(btnElements.size() > 4 && j == 3){
			        		str.append("</tr><tr>");
			        	}
		        	}
	        	}
	        }
	        str.append("<tr></table>");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return str.toString();
	}
}
