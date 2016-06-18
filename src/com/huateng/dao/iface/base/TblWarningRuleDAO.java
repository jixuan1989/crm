package com.huateng.dao.iface.base;

import com.huateng.po.base.TblWarningRule;

public interface TblWarningRuleDAO {
	public TblWarningRule get(java.lang.String key) ;
	public TblWarningRule load(java.lang.String key) ;
	public java.lang.String save(TblWarningRule tblWarningRule) ;
	public void saveOrUpdate(TblWarningRule tblWarningRule) ;
	public void update(TblWarningRule tblWarningRule) ;
	public void delete(java.lang.String id) ;
	public void delete(TblWarningRule tblWarningRule) ;
}