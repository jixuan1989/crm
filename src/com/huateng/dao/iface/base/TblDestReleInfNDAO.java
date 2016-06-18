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
package com.huateng.dao.iface.base;

import com.huateng.po.base.TblDestReleInfN;
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
public interface TblDestReleInfNDAO {

	// query name references

	public Class<TblDestReleInfN> getReferenceClass ();

	/**
	 * Cast the object as a com.huateng.po.TblTxnName
	 */
	public TblDestReleInfN cast (Object object);

	public TblDestReleInfN load(TblDestReleInfPK key);

	public TblDestReleInfN get(TblDestReleInfPK key);

	public java.util.List<TblDestReleInfN> loadAll();

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.)
	 * @param tblTxnName a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.String save(TblDestReleInfN tblDestReleInf);

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param tblTxnName a transient instance containing new or updated state
	 */
	public void saveOrUpdate(TblDestReleInfN tblDestReleInf);


	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tblTxnName a transient instance containing updated state
	 */
	public void update(TblDestReleInfN tblDestReleInf);
	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param tblTxnName the instance to be removed
	 */
	public void delete(TblDestReleInfN tblDestReleInf);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param id the instance ID to be removed
	 */
	public void delete(TblDestReleInfPK id);

}
