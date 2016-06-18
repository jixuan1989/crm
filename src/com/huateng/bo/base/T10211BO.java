package com.huateng.bo.base;

import java.util.List;

import com.huateng.po.TblOprLog;

/**
 * Title:业务操作记录BO
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
public interface T10211BO {
	public TblOprLog get(String id); // 获取业务操作记录

	public String add(TblOprLog tblOprLog);// 创建业务操作记录

	public String update(TblOprLog tblOprLog); // 更新业务操作记录

	public String delete(String id);// 删除业务操作记录
	
	public String update(List<TblOprLog> tblOprLogList); // 更新业务操作记录
}
