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

public class CacheFilter implements Filter {
	
	private FilterConfig filterConfig = null;

	public void init(FilterConfig arg0) throws ServletException {
		filterConfig = arg0;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uriStr = req.getRequestURI().toLowerCase();
		HttpServletResponse res = (HttpServletResponse) response;
		boolean hasCache = (uriStr.indexOf("ext-base.js") > 0 || uriStr.indexOf("ext-all.js") > 0);
		if (hasCache) {
			res.setHeader("Cache-Control", "private, max-age=2592000");
		} else {
			res.setHeader("Cache-Control", "no-cache");
			res.setDateHeader("Expires", 0);
			res.setHeader("Pragma", "No-cache");
		}
		chain.doFilter(req, res);
	}

	public void destroy() {
		filterConfig = null;
	}
}
