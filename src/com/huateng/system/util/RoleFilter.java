package com.huateng.system.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.huateng.common.Constants;
import com.huateng.common.Operator;

public class RoleFilter implements Filter {
	private FilterConfig filterConfig = null;
	private static ThreadLocal<Operator> currSubject = new ThreadLocal<Operator>();
	@Override
	public void destroy() {
		filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		Operator operator = (Operator)req.getSession().getAttribute(Constants.OPERATOR_INFO);
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		filterConfig = arg0;
	}
	public static Operator getPrincipal() {
		return currSubject.get();
	}
	public static JSONObject getCurrentUser() {
		Operator opr = getPrincipal();
		System.out.println(opr != null);
		if (opr != null) {
			JSONObject result = new JSONObject();
			try {
				result.put("oprid", opr.getOprId());
				result.put("oprname", opr.getOprName());
				result.put("degree", opr.getOprDegree());
				System.out.println("opr.getOprDegree()---------" + opr.getOprDegree());
			} catch (JSONException e) {
				e.getMessage();
			}
			return result;
		} else {
			return null;
		}
	}
}
