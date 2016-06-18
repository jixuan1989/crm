package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CstSysParamPK implements Serializable {
	
	public CstSysParamPK(String owner2, String key2) {
		this.owner = owner2 ;
		this.key = key2 ;
	}
	private static final long serialVersionUID = 1L;

	protected int hashCode = Integer.MIN_VALUE;

	private String owner;
	private String key;
	
}