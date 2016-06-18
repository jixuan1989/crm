package com.huateng.bo.base;

import java.util.List;

import com.huateng.common.Operator;
import com.huateng.po.base.TblDestGrpInf;
import com.huateng.po.base.TblDestReleInf;

/**
 * Title:通道池管理
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
public interface T10230BO {

	public String add(String descr,List<TblDestReleInf> destReleList,Operator operator);// 新增

	public String edit(String destIdGrpId,String descr,List<TblDestReleInf> destReleList,Operator operator); // 修改
	
	public String delete(String destIdGrpId,Operator operator);//删除
}
