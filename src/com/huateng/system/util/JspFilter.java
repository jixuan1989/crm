package com.huateng.system.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.commquery.dao.ICommQueryDAO;

public class JspFilter implements Filter {

	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI();		
		if(StringUtils.contains(url, ".jsp") && !StringUtils.contains(url, "login")) {
			Operator operator = (Operator)req.getSession().getAttribute(Constants.OPERATOR_INFO);
			Set<String> menuSet = null;
			if(operator != null) 
				menuSet = getMenuId(operator.getOprDegree());
			
			String subUrl = "";
			subUrl = url.substring(url.lastIndexOf("/"), url.length());
			
			if(subUrl.indexOf(".jsp") > 0) {
				subUrl = subUrl.substring(0,subUrl.indexOf(".jsp"));
				if(StringUtils.isNotBlank(subUrl)) {
					subUrl = subUrl.substring(2, subUrl.length());
				}
			}
			
			if(operator == null || StringUtils.isEmpty(operator.getOprId())) {
				res.sendRedirect("timeout.asp");
			} else if(!menuSet.isEmpty() && StringUtils.isNotBlank(subUrl) && !StringUtils.equalsIgnoreCase(subUrl, "ain_0") && !menuSet.contains(subUrl)) {
				res.sendRedirect("timeout.asp");
			} else {
				chain.doFilter(req, res);
			}
		} else {
			chain.doFilter(req, res);
		}
	}
	
	private Set<String> getMenuId(String degree) {
		Set<String> idSet = new HashSet<String>();
		String sql = "select VALUE_ID FROM TBL_ROLE_FUNC_MAP WHERE KEY_ID = " + degree;
		ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
		List<Object> funcMapList = commQueryDAO.findBySQLQuery(sql);
		for(Object obj : funcMapList) {
			if(obj != null) {
				BigDecimal id = (BigDecimal)obj;
				idSet.add(id.toString());
			}
		}
		return idSet;
	}
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
