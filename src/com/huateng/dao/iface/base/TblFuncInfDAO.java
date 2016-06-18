package com.huateng.dao.iface.base;

import com.huateng.po.TblFuncInf;

public interface TblFuncInfDAO {
	public TblFuncInf get(Integer key);

	public TblFuncInf load(Integer key);

	public java.util.List<TblFuncInf> findAll ();


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param tblCityCode a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public Integer save(TblFuncInf tblFuncInf);

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param tblCityCode a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(TblFuncInf tblFuncInf);

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tblCityCode a transient instance containing updated state
	 */
	public void update(TblFuncInf tblFuncInf);
	
	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(Integer id);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param tblCityCode the instance to be removed
	 */
	public void delete(TblFuncInf tblCityCode);


}