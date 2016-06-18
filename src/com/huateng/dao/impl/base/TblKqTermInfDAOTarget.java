package com.huateng.dao.impl.base;

import com.huateng.dao._RootDAO;
import com.huateng.dao.iface.base.TblKqTermInfDAO;
import com.huateng.po.base.TblKqTermInf;
import com.huateng.po.base.TblKqTermInfPK;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2014-6-11
 * 
 * @author ning.tan
 * 
 * @version 1.0
 */
public class TblKqTermInfDAOTarget extends _RootDAO<TblKqTermInf> implements TblKqTermInfDAO{

	// query name references




	public Class<TblKqTermInf> getReferenceClass () {
		return TblKqTermInf.class;
	}


	/**
	 * Cast the object as a com.huateng.po.TblTxnName
	 */
	public TblKqTermInf cast (Object object) {
		return (TblKqTermInf) object;
	}


	public TblKqTermInf load(TblKqTermInfPK key)
	{
		return (TblKqTermInf) load(getReferenceClass(), key);
	}

	public TblKqTermInf get(TblKqTermInfPK key)
	{
		return (TblKqTermInf) get(getReferenceClass(), key);
	}

	public java.util.List<TblKqTermInf> loadAll()
	{
		return loadAll(getReferenceClass());
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.)
	 * @param tblTxnName a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.String save(TblKqTermInf tblKqTermInf)
	{
		return (java.lang.String) super.save(tblKqTermInf);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param tblTxnName a transient instance containing new or updated state
	 */
	public void saveOrUpdate(TblKqTermInf tblKqTermInf)
	{
		super.saveOrUpdate(tblKqTermInf);
	}


	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tblTxnName a transient instance containing updated state
	 */
	public void update(TblKqTermInf tblKqTermInf)
	{
		super.update(tblKqTermInf);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param tblTxnName the instance to be removed
	 */
	public void delete(TblKqTermInf tblKqTermInf)
	{
		super.delete((Object) tblKqTermInf);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param id the instance ID to be removed
	 */
	public void delete(TblKqTermInfPK id)
	{
		super.delete((Object) load(id));
	}
}
