package com.huateng.dao.iface.base;

import com.huateng.po.base.TblWarningParam;



public interface TblWarningParamDAO {
	public TblWarningParam get(java.lang.String key) ;
	public TblWarningParam load(java.lang.String key) ;
	public java.lang.String save(TblWarningParam tblWarningParam) ;
	public void saveOrUpdate(TblWarningParam tblWarningParam) ;
	public void update(TblWarningParam tblWarningParam) ;
	public void delete(java.lang.String id) ;
	public void delete(TblWarningParam tblWarningParam) ;
}