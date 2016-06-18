package com.huateng.system.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SystemInterceptorAction implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Map session = ai.getInvocationContext().getSession();
		
		Operator operator = (Operator) session.get(Constants.OPERATOR_INFO);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String path = request.getRequestURI();
		if (path.endsWith("login.asp") || path.endsWith("logout.asp")
				|| path.endsWith("resetPwd.asp") || null != operator) {
			return ai.invoke();
		} else {
			boolean flag = isAjaxRequest(request);
			if(!flag){
				return Action.LOGIN;
			}else {
				response.sendError(408);
	            return null;
			}
			
		}
	}

	/**
	 * 判断是否是ajax访问
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
