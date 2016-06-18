package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseTblRoleFuncMapId implements Serializable {

	private static final long serialVersionUID = 6499951232837321653L;

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.Long keyId;
	private java.lang.Long valueId;

}