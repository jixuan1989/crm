package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblRoleFuncMap implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String REF = "TblRoleFuncMap";
	public static String PROP_REC_UPD_OPR = "RecUpdOpr";
	public static String PROP_REC_CRT_TS = "RecCrtTs";
	public static String PROP_ID = "Id";
	public static String PROP_REC_UPD_TS = "RecUpdTs";
	public static String ROLE_FLAG = "RoleFlag";

	// primary key
	private com.huateng.po.TblRoleFuncMapPK id;

	// fields
	private java.lang.String recUpdOpr;
	private java.lang.String recCrtTs;
	private java.lang.String recUpdTs;
	private java.lang.String roleFlag;
	private java.lang.String valueId;
	
}