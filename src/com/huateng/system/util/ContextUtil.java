/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-3-8       first release
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
package com.huateng.system.util;

import org.springframework.context.ApplicationContext;

import com.huateng.log.Log;

/**
 * Title:上下文调用工具
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-3-8
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class ContextUtil {
	
	private static ApplicationContext context;
	
	public static void setContext(ApplicationContext ctx) {
		context = ctx;
	}
	/**
	 * 获得sping对象
	 * @param id
	 * @return
	 */
	public static Object getBean(String id) {
		Object obj = context.getBean(id);
		if(obj == null) {
			Log.log("bean id [ " + id + " ] not found in context path");
		}
		return obj;
	}

}
