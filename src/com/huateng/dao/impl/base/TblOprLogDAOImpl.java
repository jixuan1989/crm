package com.huateng.dao.impl.base;

import java.util.List;

import com.huateng.dao._RootDAO;
import com.huateng.po.TblOprLog;


public class TblOprLogDAOImpl extends _RootDAO<com.huateng.po.TblOprLog> implements com.huateng.dao.iface.base.TblOprLogDAO{

public TblOprLogDAOImpl () {}

/* (non-Javadoc)
 * @see com.huateng.dao.iface.TblOprInfoDAO#findAll()
 */
public List<TblOprLog> findAll() {
	// TODO Auto-generated method stub
	return null;
}

public Class<com.huateng.po.TblOprLog> getReferenceClass () {
	return com.huateng.po.TblOprLog.class;
}


/**
 * Cast the object as a com.huateng.po.TblOprInfo
 */
public com.huateng.po.TblOprLog cast (Object object) {
	return (com.huateng.po.TblOprLog) object;
}


public com.huateng.po.TblOprLog load(java.lang.String key)
{
	return (com.huateng.po.TblOprLog) load(getReferenceClass(), key);
}

public com.huateng.po.TblOprLog get(java.lang.String key)
{
	return (com.huateng.po.TblOprLog) get(getReferenceClass(), key);
}

@SuppressWarnings("unchecked")
public java.util.List<com.huateng.po.TblOprLog> loadAll()
{
	return loadAll(getReferenceClass());
}
/**
 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
 * of the identifier property if the assigned generator is used.)
 * @param tblOprInfo a transient instance of a persistent class
 * @return the class identifier
 */
public java.lang.String save(com.huateng.po.TblOprLog tblOprLog)
{
	return (java.lang.String) super.save(tblOprLog);
}

/**
 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
 * identifier property mapping.
 * @param tblOprInfo a transient instance containing new or updated state
 */
public void saveOrUpdate(com.huateng.po.TblOprLog tblOprLog)
{
	super.saveOrUpdate(tblOprLog);
}


/**
 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
 * instance with the same identifier in the current session.
 * @param tblOprInfo a transient instance containing updated state
 */
public void update(com.huateng.po.TblOprLog tblOprLog)
{
	super.update(tblOprLog);
}

/**
 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
 * Session or a transient instance with an identifier associated with existing persistent state.
 * @param tblOprInfo the instance to be removed
 */
public void delete(com.huateng.po.TblOprLog tblOprLog)
{
	super.delete((Object) tblOprLog);
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