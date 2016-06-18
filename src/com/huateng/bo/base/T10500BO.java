/* @(#)
 *
 * Project:spdb
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2011-10-17       first release
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
package com.huateng.bo.base;

import java.util.List;

import com.huateng.po.TblCompanyStepment;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-10-17
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author pengcheng.xie
 * 
 * @version 1.0
 */
public interface T10500BO {
	/**
	 * 查询信息
	 * @param    
	 * @return
	 */
	public TblCompanyStepment get(String id);
	/**
	 * 添加部门信息
	 * @param 
	 * @return
	 */
	public String add(TblCompanyStepment tblCompanyStepment);
	/**
	 * 删除部门信息
	 * @param 
	 * @return
	 */
	public String delete(String id);
	/**
	 * 更新部门信息列表
	 * @param 
	 * @return
	 */
	public String update(List<TblCompanyStepment> tblCompanyStepmentList);
}
