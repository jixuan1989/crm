package com.huateng.dao;

import java.util.List;


public interface _IRootDAO {
	/**
	 * 通用按类方法属性查询
	 * @param cls  要查询的实体对象
	 * @param sqlWhe  查询条件 如：and t.machtNo=?
	 * @param params  查询所需的参数组，按查询条件顺序先后
	 * @return	返回实体对象
	 */
	public <T> T queryByField(final Class<T> cls,final String sqlWhe,final String [] params);
	/**
	 * 通用按类方法属性查询
	 * @param cls  要查询的实体对象
	 * @param sqlWhe  查询条件 如：and t.machtNo=?
	 * @param params  查询所需的参数组，按查询条件顺序先后
	 * @return	返回实体对象集合
	 */
	public <T> List<T> queryByFieldList(final Class<T> cls,final String sqlWhe,final String [] params);
}
