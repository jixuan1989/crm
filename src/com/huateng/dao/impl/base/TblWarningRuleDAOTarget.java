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
import com.huateng.dao.iface.base.TblWarningRuleDAO;
import com.huateng.po.base.TblWarningRule;

public class TblWarningRuleDAOTarget extends _RootDAO<TblWarningRule> implements TblWarningRuleDAO{

	@Override
	public String save(TblWarningRule tblWarningRule) {
		return (String) super.save(tblWarningRule);
	}

	@Override
	public void saveOrUpdate(TblWarningRule tblWarningRule) {
		super.saveOrUpdate(tblWarningRule);
	}

	@Override
	public void update(TblWarningRule tblWarningRule) {
		super.update(tblWarningRule);
	}

	@Override
	public void delete(String id) {
		super.delete(id);
	}

	@Override
	public void delete(TblWarningRule tblWarningRule) {
		super.delete(tblWarningRule);
	}

	@Override
	protected Class getReferenceClass() {
		return TblWarningRule.class;
	}

	@Override
	public TblWarningRule get(String key) {
		return (TblWarningRule) super.get(getReferenceClass(), key);
	}

	@Override
	public TblWarningRule load(String key) {

		return null;
	}
	
}
