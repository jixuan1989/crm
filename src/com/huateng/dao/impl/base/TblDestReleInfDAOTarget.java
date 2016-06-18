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
import com.huateng.dao.iface.base.TblDestReleInfDAO;
import com.huateng.po.base.TblDestReleInf;
import com.huateng.po.base.TblDestReleInfPK;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-6-27
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author ning.tan
 * 
 * @version 1.0
 */
public class TblDestReleInfDAOTarget extends _RootDAO<TblDestReleInf> implements TblDestReleInfDAO{

	// query name references




	public Class<TblDestReleInf> getReferenceClass () {
		return TblDestReleInf.class;
	}


	/**
	 * Cast the object as a com.huateng.po.TblTxnName
	 */
	public TblDestReleInf cast (Object object) {
		return (TblDestReleInf) object;
	}


	public TblDestReleInf load(TblDestReleInfPK key)
	{
		return (TblDestReleInf) load(getReferenceClass(), key);
	}

	public TblDestReleInf get(TblDestReleInfPK key)
	{
		return (TblDestReleInf) get(getReferenceClass(), key);
	}

	public java.util.List<TblDestReleInf> loadAll()
	{
		return loadAll(getReferenceClass());	
	}
	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.)
	 * @param tblTxnName a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.String save(TblDestReleInf tblDestReleInf)
	{
		return (java.lang.String) super.save(tblDestReleInf);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param tblTxnName a transient instance containing new or updated state
	 */
	public void saveOrUpdate(TblDestReleInf tblDestReleInf)
	{
		super.saveOrUpdate(tblDestReleInf);
	}


	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tblTxnName a transient instance containing updated state
	 */
	public void update(TblDestReleInf tblDestReleInf)
	{
		super.update(tblDestReleInf);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param tblTxnName the instance to be removed
	 */
	public void delete(TblDestReleInf tblDestReleInf)
	{
		super.delete((Object) tblDestReleInf);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param id the instance ID to be removed
	 */
	public void delete(TblDestReleInfPK id)
	{
		super.delete((Object) load(id));
	}
}