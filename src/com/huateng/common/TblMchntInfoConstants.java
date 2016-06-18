/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-7-30       first release
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
package com.huateng.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:商户信息宏定义
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-7-30
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class TblMchntInfoConstants {
	public static Map<String,String> mapType = new HashMap<String,String>(6);
	public static Map<String,String> mapRank = new HashMap<String,String>(3);
	public static Map<String,String> mapResult = new HashMap<String,String>(7);
	
	static {
		mapType.put(TblMchntInfoConstants.ACTION_TYPE_ADD, "增加");
		mapType.put(TblMchntInfoConstants.ACTION_TYPE_EDIT, "修改");
		mapType.put(TblMchntInfoConstants.ACTION_TYPE_STOP, "停用");
		mapType.put(TblMchntInfoConstants.ACTION_TYPE_START, "恢复");
		mapType.put(TblMchntInfoConstants.ACTION_TYPE_NORMAL, "正常");
		mapType.put(TblMchntInfoConstants.ACTION_TYPE_TMP_SAVE, "临时保存 ");
		
		mapRank.put(TblMchntInfoConstants.AUDIT_RANK_PART, "分公司");
		mapRank.put(TblMchntInfoConstants.AUDIT_RANK_HQ, "总公司");
		//mapRank.put(TblMchntInfoConstants.AUDIT_RANK_NORMAL, "正常（无）");
		
		mapResult.put(TblMchntInfoConstants.AUDIT_RESULT_PASS, "通过");
		mapResult.put(TblMchntInfoConstants.AUDIT_RESULT_SCHECK, "待审核");
		mapResult.put(TblMchntInfoConstants.AUDIT_RESULT_BACK, "退回");
		//mapResult.put(TblMchntInfoConstants.AUDIT_RESULT_NORMAL, "正常（无）");
		mapResult.put(TblMchntInfoConstants.AUDIT_RESULT_REENTRY, "待补录");
		mapResult.put(TblMchntInfoConstants.AUDIT_RESULT_REENTRYCHECK, "补录完成待审核");
		mapResult.put(TblMchntInfoConstants.AUDIT_RESULT_REENTRYBACK, "补录审核退回");
	}
	
	public static String statusString(String key){
		if("0".equals(key.substring(0,1))){
			return "正常";
		}
		String v3 = key.substring(2,3);
		v3 = v3.equals("0")? "" : mapResult.get(v3);
		
		String v2 = key.substring(1,2);
		v2 = v2.equals("0")? "" : "（"+mapRank.get(v2)+"）";
		
		return  mapType.get(key.substring(0,1)) + v3 + v2;
	}
	
	/** 操作类型：增加 **/
	public static final String ACTION_TYPE_ADD = "1";
	/** 操作类型：修改 **/
	public static final String ACTION_TYPE_EDIT = "2";
	/** 操作类型：停用 **/
	public static final String ACTION_TYPE_STOP = "3";
	/** 操作类型：恢复 **/
	public static final String ACTION_TYPE_START = "4";
	/** 操作类型：正常（无） **/
	public static final String ACTION_TYPE_NORMAL = "0";
	/** 操作类型：临时保存 **/
	public static final String ACTION_TYPE_TMP_SAVE = "5";
	
	/** 审核级别：分公司 **/
	public static final String AUDIT_RANK_PART = "1";
	/** 审核级别：总公司 **/
	public static final String AUDIT_RANK_HQ = "2";
	/** 审核级别：正常（无） **/
	public static final String AUDIT_RANK_NORMAL = "0";
	
	/** 审核结果：通过 **/
	public static final String AUDIT_RESULT_PASS = "3";
	/** 审核结果：待审核 **/
	public static final String AUDIT_RESULT_SCHECK = "1";
	/** 审核结果：退回 **/
	public static final String AUDIT_RESULT_BACK = "2";
	/** 审核结果：正常（无） **/
	public static final String AUDIT_RESULT_NORMAL = "0";
	/** 审核结果：待补录 **/
	public static final String AUDIT_RESULT_REENTRY = "4";
	/** 审核结果：补录完成待审核**/
	public static final String AUDIT_RESULT_REENTRYCHECK= "5";
	/** 审核结果：补录审核退回 **/
	public static final String AUDIT_RESULT_REENTRYBACK = "6";
	
	/**商户状态-正常*/
	public static final String MCHNT_ST_OK = "0";
	/**商户状态-非正常*/
	public static final String MCHNT_ST_NO_OK = "1";
	/**商户状态-停用*/
	public static final String MCHNT_ST_STOP = "2";
	
	/**专业化服务商户终端**/
	public static final String MCHNT_TYPE_ONE = "1";
	/** 收单商户终端 **/
	public static final String MCHNT_TYPE_ZERO = "0";
	
	/*
	*//**商户状态-可用*//*
	public static final String MCHNT_ST_OK = "0";
	*//**商户状态-(分公司)添加待审核*//*
	public static final String MCHNT_ST_NEW_UNCK = "1";
	*//**商户状态-添加审核退回*//*
	public static final String MCHNT_ST_NEW_UNCK_BACK = "2";
	*//**商户状态-(分公司)修改待审核*//*
	public static final String MCHNT_ST_MODI_UNCK = "3";
	*//**商户状态-修改审核退回*//*
	public static final String MCHNT_ST_MODI_UNCK_BACK = "4";
	*//**商户状态-（分公司）停用待审核*//*
	public static final String MCHNT_ST_STOP_UNCK = "5";
	*//**商户状态-停用*//*
	public static final String MCHNT_ST_STOP = "6";
	*//**商户状态-（分公司）恢复待审核*//*
	public static final String MCHNT_ST_RCV_UNCK = "7";
	*//**商户状态-(总公司)恢复待审核*//*
	public static final String MCHNT_ST_RCV_UNCK_HQ = "H7";
	*//**商户状态-新增未提交*//*
	public static final String MCHNT_ST_RELOAD = "20";
	*//**商户状态-待分公司补录*//*
	public static final String MCHNT_HQ_ST_OK = "23";
	*//**商户状态-添加我方审核*//*
	public static final String MCHNT_ST_OUR_NEW_UNCK = "8";
	*//**商户状态-修改我方审核*//*
	public static final String MCHNT_ST_OUR_MODI_UNCK = "9";
	*//**商户状态-银行审核*//*
	public static final String MCHNT_ST_BANK_UNCK = "10";
	*//**商户状态-银联审核*//*
	public static final String MCHNT_ST_UNION_UNCK = "11";
	*//**商户状态-添加我方退回*//*
	public static final String MCHNT_ST_OUR_NEW_UNCK_BACK = "12";
	*//**商户状态-修改我方退回*//*
	public static final String MCHNT_ST_OUR_MODI_UNCK_BACK = "13";
	*//**商户状态-银行审核退回*//*
	public static final String MCHNT_ST_BANK_UNCK_BACK = "14";
	*//**商户状态-银联审核退回*//*
	public static final String MCHNT_ST_UNION_UNCK_BACK = "15";
	*/
	/**参数状态正常-所属门店无机具**/
	public static final String MCHNT_PARANORMAL_NORMALTERM = "21" ;
	
	/**商户结算方式-日结*/
	public static final String MCHNT_SETTLE_TYPE_DAY = "1";
	/**商户结算方式-月结*/
	public static final String MCHNT_SETTLE_TYPE_MON = "2";
	/**商户手续费结算类型-按商户*/
	public static final String MCHNT_RATE_FLAG_MCHT = "0";
	/**商户手续费结算类型-按卡种*/
	public static final String MCHNT_RATE_FLAG_CARD = "1";
	/**商户结算渠道-本行对公活期账户*/
	public static final String MCHNT_SETTLE_CHN_SELF = "1";
	/**他行帐户*/
	public static final String MCHNT_SETTLE_CHN_OUT = "2";
	/**按分段金额，针对手续费月结商户*/
	public static final String MCHNT_FEE_TYPE_DIV = "1";
	/**按每笔固定金额*/
	public static final String MCHNT_FEE_TYPE_FIX = "2";
	/**按每笔百分比，且有最高上限*/
	public static final String MCHNT_FEE_TYPE_PER = "3";
	
	/**商户临时基本信息*/
	public static final String MCHNT_BASE_INFO_TMP = "MCHT_BASE_INFO_TMP";
	/**商户临时清算信息*/
	public static final String MCHNT_SETTLE_INFO_TMP = "MCHT_SETTLE_INFO_TMP";
	/**商户临时补充信息*/
	public static final String MCHNT_SUPP_INFO_TMP = "MCHT_SUPP_INFO_TMP";
	/**商户补充信息集合*/
	public static final String MCHNT_SUPP_INFO_TMPS = "MCHT_SUPP_INFO_TMPS";
	/**商户基本信息*/
	public static final String MCHNT_BASE_INFO = "MCHT_BASE_INFO";
	/**商户清算信息*/
	public static final String MCHNT_SETTLE_INFO = "MCHT_SETTLE_INFO";
	/**商户补充信息*/
	public static final String MCHNT_SUPP_INFO = "MCHT_SUPP_INFO";
	/**商户分期参数信息*/
	public static final String MCHNT_DIV_NO = "DIV_NO_ARRAY";
	/**商户分期商品代码信息*/
	public static final String MCHNT_PRODUCT = "PRODUCT_ARRAY";
	/**商户分期扣率*/
	public static final String MCHNT_DIV_FEE_TYPE_RATE = "0";
	/**商户分期固定金额*/
	public static final String MCHNT_DIV_FEE_TYPE_FIX = "1";
	/**商户补充信息国内他行卡*/
	public static final String MCHNT_CARD_TYPE_CUPS = "00";
	/**商户补充信息本行借记卡*/
	public static final String MCHNT_CARD_TYPE_DEBITS = "01";
	/**商户补充信息本行一帐通*/
	public static final String MCHNT_CARD_TYPE_CDC = "03";
	/**商户补充信息本行信用卡*/
	public static final String MCHNT_CARD_TYPE_CREDIT = "04";
	/**商户按卡种手续费类型-固定手续费*/
	public static final String MCHNT_CARD_TYPE_FEE_FIX = "2";
	/**商户按卡种手续费类型-按比例*/
	public static final String MCHNT_CARD_TYPE_FEE_RATE = "3";
	
	
	/**Extjs checkbox checked*/
	public static final String EXTJS_CHECKED = "on";
	
}
