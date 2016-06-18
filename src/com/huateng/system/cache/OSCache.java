package com.huateng.system.cache;

import org.apache.log4j.Logger;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class OSCache extends GeneralCacheAdministrator {
	private static Logger LOG = Logger.getLogger(OSCache.class);

	// 添加缓存对象;
	public void put(String key, Object value) {
		this.putInCache(key, value);
	}

	// 根据key值删除特定缓存对象;
	public void remove(String key) {
		this.flushEntry(key);
	}

	// 删除所有缓存对象;
	public void removeAll() {
		this.flushAll();
	}
	
	//根据key值更新缓存对象
	public void update(String key, Object value){
		this.remove(key);
		this.putInCache(key, value);
	}

	// 获取缓存对象;
	public Object get(String key){
		try {
			return this.getFromCache(key);
		} catch (NeedsRefreshException e) {
			LOG.debug("no such cache for the key:[ " + key + " ]");
			return null;
		}
		
	}

}
