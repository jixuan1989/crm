package com.huateng.system.cache;

public class CacheFactory {
	private static OSCache instance;

	public static OSCache getInstance() {
		if (instance == null) {
			instance = new OSCache();
		}
		return instance;
	}
}
