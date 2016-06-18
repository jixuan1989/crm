package com.huateng.common;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MenuUtil {
	private static Document document = null;
	public static void init(ServletContext context){
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(context.getResourceAsStream(Constants.MENU_CONFIG_CONTEXTPATH));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String initMenu(String id,String roleFlag){
		LinkedList<Object> toolList = new LinkedList<Object>();
		try {
			Element root = document.getRootElement();
	        List<Element> elements = root.elements(Constants.MENU_CONFIG_ID);
	        for(int i = 0; i < elements.size(); i++) {
	        	Element menuElement = elements.get(i);
	        	String meunId = menuElement.attributeValue(Constants.SLT_XML_ID).trim();
	        	if(meunId.equals(id)){
	        		List<Element> btnElements = menuElement.elements(Constants.BUTTON_CONFIG);
		        	for(int j = 0; j < btnElements.size(); j++) {
		        		Element btn = btnElements.get(j);
		        		StringBuffer str = new StringBuffer();
		        		String value = btn.element(Constants.BUTTON_CONFIG_VALUE).getText();
		        		if(roleFlag.contains(value)){
		        			String btnId = btn.element(Constants.BUTTON_CONFIG_ID).getText();
		        			String name = btn.element(Constants.BUTTON_CONFIG_TEXT).getText();
			        		String action = btn.element(Constants.BUTTON_CONFIG_ACTION).getText();
			        		String cls = btn.element(Constants.BUTTON_CONFIG_CLS).getText();
			        		String disabled = btn.element(Constants.BUTTON_CONFIG_DISABLED).getText();
			        		String width = btn.element(Constants.BUTTON_CONFIG_WIDTH).getText();
			        		str.append("{xtype:'button',").append(Constants.BUTTON_CONFIG_TEXT).append(":'").append(name).append("',");
			        		str.append(Constants.BUTTON_CONFIG_ID).append(":'").append(btnId).append("',");
			        		str.append(Constants.BUTTON_CONFIG_VALUE).append(":'").append(value).append("',");
			        		str.append(Constants.BUTTON_CONFIG_ACTION).append(":'").append(action).append("',");
			        		str.append(Constants.BUTTON_CONFIG_CLS).append(":'").append(cls).append("',");
			        		str.append(Constants.BUTTON_CONFIG_DISABLED).append(":").append(disabled).append(",");
			        		str.append(Constants.BUTTON_CONFIG_WIDTH).append(":").append(width).append(",");
			        		str.append(Constants.BUTTON_CONFIG_MARGIN).append(":").append(Constants.BUTTON_CONFIG_MARGIN_VALUE).append("}");
			        		toolList.add(str.toString());
		        		}else{
		        			String btnId = btn.element(Constants.BUTTON_CONFIG_ID).getText();
		        			String name = btn.element(Constants.BUTTON_CONFIG_TEXT).getText();
			        		String action = btn.element(Constants.BUTTON_CONFIG_ACTION).getText();
			        		String cls = btn.element(Constants.BUTTON_CONFIG_CLS).getText();
			        		String disabled = btn.element(Constants.BUTTON_CONFIG_DISABLED).getText();
			        		String width = btn.element(Constants.BUTTON_CONFIG_WIDTH).getText();
			        		str.append("{xtype:'button',").append(Constants.BUTTON_CONFIG_TEXT).append(":'").append(name).append("',");
			        		str.append(Constants.BUTTON_CONFIG_ID).append(":'").append(btnId).append("',");
			        		str.append(Constants.BUTTON_CONFIG_VALUE).append(":'").append(value).append("',");
			        		str.append(Constants.BUTTON_CONFIG_ACTION).append(":'").append(action).append("',");
			        		str.append(Constants.BUTTON_CONFIG_CLS).append(":'").append(cls).append("',");
			        		str.append(Constants.BUTTON_CONFIG_DISABLED).append(":").append(disabled).append(",");
			        		str.append(Constants.BUTTON_CONFIG_WIDTH).append(":").append(width).append(",");
			        		str.append("hidden:true,");
			        		str.append(Constants.BUTTON_CONFIG_MARGIN).append(":").append(Constants.BUTTON_CONFIG_MARGIN_VALUE).append("}");
			        		toolList.add(str.toString());
		        		}
		        	}
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		String toolBarStr = JSONArray.fromObject(toolList).toString();
		return toolBarStr;
	}
}
