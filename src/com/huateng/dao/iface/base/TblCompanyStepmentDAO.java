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
package com.huateng.dao.iface.base;


/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-10-17
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public interface TblCompanyStepmentDAO {
	
	public com.huateng.po.TblCompanyStepment get(java.lang.String key) ;
	
	public com.huateng.po.TblCompanyStepment load(java.lang.String key);
	
	public void save(com.huateng.po.TblCompanyStepment tblCompanyStepment);
	
	public void delete(String id);
	
	public void update(com.huateng.po.TblCompanyStepment tblCompanyStepment);
}
