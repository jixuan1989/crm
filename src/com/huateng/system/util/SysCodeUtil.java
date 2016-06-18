/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-7-16       first release
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

import java.util.ResourceBundle;

/**
 * 
 * Title:系统返回码信息
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-8-31
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class SysCodeUtil {
	
	private static String SYS_CODE_FILE = "sysCode";
	private static String ERR_CODE_FILE = "errCode";
	private static String DISP_CODE_MAPPING_FILE = "codeDispMapping";
	
	private static ResourceBundle BUNDLE1 = ResourceBundle.getBundle(SYS_CODE_FILE);
	private static ResourceBundle BUNDLE2 = ResourceBundle.getBundle(ERR_CODE_FILE);
	private static ResourceBundle BUNDLE3 = ResourceBundle.getBundle(DISP_CODE_MAPPING_FILE);
	/**
	 * 获得交易信息
	 * @param key
	 * @return
	 */
	public static String getSysCode(String key) {
		return BUNDLE1.getString(key);
	}
	/**
	 * 获取错误信息
	 * @param key
	 * @return
	 * 2011-7-1下午04:20:55
	 */
	public static String getErrCode(String key) {
		return BUNDLE2.getString(key);
	}
	
	public static String getMapDisp(String key) {
		return BUNDLE3.getString(key);
	}
}
