package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblCityCode implements Serializable {
	private static final long serialVersionUID = 1L;


	public static String REF = "CstCityCode";
	public static String PROP_INIT_OPR_ID = "InitOprId";
	public static String PROP_MCHT_ADDR = "MchtAddr";
	public static String PROP_CITY_NAME = "CityName";
	public static String PROP_INIT_TIME = "InitTime";
	public static String PROP_CITY_CODE_NEW = "cupCityCode";
	public static String PROP_CITY_CODE_OLD = "mchtCityCode";
	public static String PROP_MODI_OPR_ID = "ModiOprId";
	public static String PROP_CITY_FLAG = "CityFlag";
	public static String PROP_MODI_TIME = "ModiTime";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String cupCityCode;

	// fields
	private java.lang.String mchtCityCode;
	private java.lang.String cityFlag;
	private java.lang.String mchtAddr;
	private java.lang.String cityName;
	private java.lang.String initOprId;
	private java.lang.String modiOprId;
	private java.lang.String initTime;
	private java.lang.String modiTime;


}