package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CstSysParam implements Serializable{
	private static final long serialVersionUID = 1L;


	public static String REF = "CstSysParam";
	public static String PROP_TYPE = "Type";
	public static String PROP_DESCR = "Descr";
	public static String PROP_VALUE = "Value";
	public static String PROP_ID = "Id";
	public static String PROP_RESERVE = "Reserve";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.huateng.po.CstSysParamPK id;

	// fields
	private java.lang.String type;
	private java.lang.String value;
	private java.lang.String descr;
	private java.lang.String reserve;

}