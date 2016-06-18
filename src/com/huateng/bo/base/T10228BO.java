package com.huateng.bo.base;

import java.util.HashMap;

import com.huateng.common.Operator;

/**
 * Title:行业细类操作
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-8-10
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author 
 * 
 * @version 1.0
 */
public interface T10228BO {

	public String group(HashMap<String,String> params,Operator operator);// 合并细类

	public String split(HashMap<String,String> params,Operator operator); // 拆分细类
}
