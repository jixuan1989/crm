package com.huateng.dao.impl.base;

import java.util.List;

import com.huateng.dao._RootDAO;
import com.huateng.po.TblPositionInf;


public class TblPositionInfDAOTarget extends _RootDAO<com.huateng.po.TblPositionInf> implements com.huateng.dao.iface.base.TblPositionInfDAO {

	public TblPositionInfDAOTarget () {}
	
	/* (non-Javadoc)
	 * @see com.huateng.dao.iface.TblBrhInfoDAO#findAll()
	 */
	public List<TblPositionInf> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<com.huateng.po.TblPositionInf> getReferenceClass () {
		return com.huateng.po.TblPositionInf.class;
	}


	/**
	 * Cast the object as a com.huateng.po.brh.TblPositionInf
	 */
	public com.huateng.po.TblPositionInf cast (Object object) {
		return (com.huateng.po.TblPositionInf) object;
	}


	public com.huateng.po.TblPositionInf load(java.lang.String key)
	{
		return (com.huateng.po.TblPositionInf) load(getReferenceClass(), key);
	}

	public com.huateng.po.TblPositionInf get(java.lang.String key)
	{
		return (com.huateng.po.TblPositionInf) get(getReferenceClass(), key);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.)
	 * @param tblPositionInf a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.String save(com.huateng.po.TblPositionInf tblPositionInf)
	{
		return (java.lang.String) super.save(tblPositionInf);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param tblPositionInf a transient instance containing new or updated state
	 */
	public void saveOrUpdate(com.huateng.po.TblPositionInf tblPositionInf)
	{
		super.saveOrUpdate(tblPositionInf);
	}


	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tblPositionInf a transient instance containing updated state
	 */
	public void update(com.huateng.po.TblPositionInf tblPositionInf)
	{
		super.update(tblPositionInf);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param tblPositionInf the instance to be removed
	 */
	public void delete(com.huateng.po.TblPositionInf tblPositionInf)
	{
		super.delete((Object) tblPositionInf);
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