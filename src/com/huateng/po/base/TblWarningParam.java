package com.huateng.po.base;

import java.io.Serializable;

public class TblWarningParam implements Serializable{	
	private static final long serialVersionUID = 1L;
	private String paramName;
	private String paramKey;
	private String paramValue;
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	
}
