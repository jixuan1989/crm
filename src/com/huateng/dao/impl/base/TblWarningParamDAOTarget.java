/* @(#)
 *
 * Project:PFConsole
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2011-6-27       first release
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
import com.huateng.dao.iface.base.TblWarningParamDAO;
import com.huateng.po.base.TblWarningParam;

public class TblWarningParamDAOTarget extends _RootDAO<TblWarningParam> implements TblWarningParamDAO{

	@Override
	public TblWarningParam get(String key) {
		return (TblWarningParam) super.get(getReferenceClass(), key);
	}

	@Override
	public TblWarningParam load(String key) {
		
		return (TblWarningParam) super.load(getReferenceClass(), key);
	}

	@Override
	public String save(TblWarningParam tblWarningParam) {
		return (String) super.save(tblWarningParam);
	}

	@Override
	public void saveOrUpdate(TblWarningParam tblWarningParam) {
		super.saveOrUpdate(tblWarningParam);
	}

	@Override
	public void update(TblWarningParam tblWarningParam) {
		super.update(tblWarningParam);
	}

	@Override
	public void delete(String id) {
		super.delete(id);
	}

	@Override
	public void delete(TblWarningParam tblWarningParam) {
		super.delete(tblWarningParam);
	}

	@Override
	protected Class getReferenceClass() {
		
		return TblWarningParam.class;
	}
	
}
