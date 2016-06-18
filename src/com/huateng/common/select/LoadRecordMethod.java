/* @(#)
 *
 * Project:PFConsole
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   Gavin      2011-6-21       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2011 Huateng Software, Inc. All rights reserved.
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
package com.huateng.common.select;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-6-21
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author Gavin
 * 
 * @version 1.0
 */
public class LoadRecordMethod {

	/**
	 * 格式化输出
	 * 
	 * @param src
	 * @return 2011-6-22下午01:43:30
	 */
	public String getMessage(String src) {
		return "{\"data\":" + src + "}";
	}

	

	/**
	 * 多对象格式化输出
	 * 
	 * @param src
	 * @return 2011-6-22下午01:43:30
	 */
	public String getMessage(String[] srcs) {

		StringBuffer sb = new StringBuffer("{\"data\":[{");
		for (String src : srcs) {
			src = src.substring(2).trim();
			sb.append(src);
			sb.delete(sb.length() - 2, sb.length());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}]}");
		return sb.toString();
	}
	public String getTermParam(String hexString)
	{
		if("".equals(hexString)){
			return "000000000";
		}
		NumberFormat formatter = new DecimalFormat("000000000");
		String bString = "", tmp;  
        for (int i = 0; i < hexString.length(); i++)  
        {  
            tmp = "0000"  + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));  
            bString += tmp.substring(tmp.length() - 4);  
        }  
        return formatter.format(Integer.parseInt(bString.replaceAll("^(0+)", "")));  
	}
	
}
