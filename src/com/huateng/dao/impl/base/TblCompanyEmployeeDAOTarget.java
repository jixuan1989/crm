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
package com.huateng.dao.impl.base;

import com.huateng.dao._RootDAO;

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
public class TblCompanyEmployeeDAOTarget  extends _RootDAO<com.huateng.po.TblCompanyEmployee> implements com.huateng.dao.iface.base.TblCompanyEmployeeDAO {

	@Override
	protected Class<com.huateng.po.TblCompanyEmployee> getReferenceClass() {
		return com.huateng.po.TblCompanyEmployee.class;
	}


	public void delete(String id) {
		super.delete((Object)load(id));
	}

	public com.huateng.po.TblCompanyEmployee get(String key) {
		return (com.huateng.po.TblCompanyEmployee) get(getReferenceClass(), key);
	}

	public com.huateng.po.TblCompanyEmployee load(String key) {
		return (com.huateng.po.TblCompanyEmployee) load(getReferenceClass(), key);
	}

	public void save(com.huateng.po.TblCompanyEmployee tblCompanyEmployee) {
		super.save(tblCompanyEmployee);
	}

	public void update(com.huateng.po.TblCompanyEmployee tblCompanyEmployee) {
		super.update(tblCompanyEmployee);
	}

}
