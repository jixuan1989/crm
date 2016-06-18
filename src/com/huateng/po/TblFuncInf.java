package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblFuncInf implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String REF = "TblFuncInf";
	public static String PROP_FUNC_TYPE = "FuncType";
	public static String PROP_PAGE_NAME = "PageName";
	public static String PROP_PAGE_TARGET = "PageTarget";
	public static String PROP_FUNC_PARENT_ID = "FuncParentId";
	public static String PROP_REC_UPD_TS = "RecUpdTs";
	public static String PROP_FUNC_NAME = "FuncName";
	public static String PROP_PAGE_URL = "PageUrl";
	public static String PROP_MISC2 = "Misc2";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_MISC1 = "Misc1";
	public static String PROP_REC_UPD_OPR = "RecUpdOpr";
	public static String PROP_REC_CRT_TS = "RecCrtTs";
	public static String PROP_FUNC_ID = "FuncId";
	protected void initialize () {}	
	

	// primary key
	private java.lang.Integer funcId;

	// fields
	private java.lang.Integer funcParentId;
	private java.lang.String funcType;
	private java.lang.String funcName;
	private java.lang.String pageName;
	private java.lang.String pageUrl;
	private java.lang.String iconPath;
	private java.lang.String misc1;
	private java.lang.String misc2;
	private java.lang.String description;
	private java.lang.String recUpdOpr;
	private java.lang.String recCrtTs;
	private java.lang.String recUpdTs;

}