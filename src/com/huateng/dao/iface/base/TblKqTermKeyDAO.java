package com.huateng.dao.iface.base;

import com.huateng.po.base.TblKqTermInfPK;
import com.huateng.po.base.TblKqTermKey;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2014-6-11
 * 
 * 
 * @author ning.tan
 * 
 * @version 1.0
 */
public interface TblKqTermKeyDAO {

	// query name references




	public Class<TblKqTermKey> getReferenceClass ();


	/**
	 * Cast the object as a com.huateng.po.TblTxnName
	 */
	public TblKqTermKey cast (Object object);

	public TblKqTermKey load(TblKqTermInfPK key);

	public TblKqTermKey get(TblKqTermInfPK key);

	public java.util.List<TblKqTermKey> loadAll();





	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.)
	 * @param tblTxnName a transient instance of a persistent class
	 * @return the class identifier
	 */
	public String save(TblKqTermKey tblKqTermKey);

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param tblTxnName a transient instance containing new or updated state
	 */
	public void saveOrUpdate(TblKqTermKey tblKqTermKey);


	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tblTxnName a transient instance containing updated state
	 */
	public void update(TblKqTermKey tblKqTermKey);
	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param tblTxnName the instance to be removed
	 */
	public void delete(TblKqTermKey tblKqTermKey);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param id the instance ID to be removed
	 */
	public void delete(TblKqTermInfPK id);

}