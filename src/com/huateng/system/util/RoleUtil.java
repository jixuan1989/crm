package com.huateng.system.util;

import java.util.List;

import com.huateng.common.StringUtil;
import com.huateng.commquery.dao.ICommQueryDAO;


public class RoleUtil{
	public static String getRoleFlag(String degree,String txnCode){
		ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil
				.getBean("CommQueryDAO");
		String result = null;
		String txn = "select ROLE_FLAG from TBL_ROLE_FUNC_MAP "
				+ "where KEY_ID = '" + degree
				+ "' and VALUE_ID = '" + txnCode.trim() + "'";
		List list = commQueryDAO.findBySQLQuery(txn);
		if (null != list && !list.isEmpty()) {
			if (!StringUtil.isNull(list.get(0))) {
				result = list.get(0).toString();
			}
		}
		return result;
	}

}
