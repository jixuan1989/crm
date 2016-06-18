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
import com.huateng.dao.iface.base.TblDestGrpInfNDAO;
import com.huateng.po.base.TblDestGrpInfN;

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
public class TblDestGrpInfNDAOTarget extends _RootDAO<TblDestGrpInfN> implements TblDestGrpInfNDAO{

	// query name references




	public Class<TblDestGrpInfN> getReferenceClass () {
		return TblDestGrpInfN.class;
	}


	/**
	 * Cast the object as a com.huateng.po.TblTxnName
	 */
	public TblDestGrpInfN cast (Object object) {
		return (TblDestGrpInfN) object;
	}


	public TblDestGrpInfN load(java.lang.String key)
	{
		return (TblDestGrpInfN) load(getReferenceClass(), key);
	}

	public TblDestGrpInfN get(java.lang.String key)
	{
		return (TblDestGrpInfN) get(getReferenceClass(), key);
	}

	public java.util.List<TblDestGrpInfN> loadAll()
	{
		return loadAll(getReferenceClass());	
	}
	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.)
	 * @param tblTxnName a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.String save(TblDestGrpInfN tblDestGrpInf)
	{
		return (java.lang.String) super.save(tblDestGrpInf);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param tblTxnName a transient instance containing new or updated state
	 */
	public void saveOrUpdate(TblDestGrpInfN tblDestGrpInf)
	{
		super.saveOrUpdate(tblDestGrpInf);
	}


	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tblTxnName a transient instance containing updated state
	 */
	public void update(TblDestGrpInfN tblDestGrpInf)
	{
		super.update(tblDestGrpInf);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param tblTxnName the instance to be removed
	 */
	public void delete(TblDestGrpInfN tblDestGrpInf)
	{
		super.delete((Object) tblDestGrpInf);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.String id)
	{
		super.delete((Object) load(id));
	}
}