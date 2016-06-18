package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblRoleInf implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String REF = "TblRoleInf";
	public static String PROP_MISC2 = "Misc2";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_ROLE_TYPE = "RoleType";
	public static String PROP_MISC1 = "Misc1";
	public static String PROP_REC_UPD_OPR = "RecUpdOpr";
	public static String PROP_ROLE_NAME = "RoleName";
	public static String PROP_REC_CRT_TS = "RecCrtTs";
	public static String PROP_ROLE_ID = "RoleId";
	public static String PROP_REC_UPD_TS = "RecUpdTs";

	// primary key
	private java.lang.Integer roleId;

	// fields
	private java.lang.String roleName;
	private java.lang.String roleType;
	private java.lang.String misc1;
	private java.lang.String misc2;
	private java.lang.String description;
	private java.lang.String recUpdOpr;
	private java.lang.String recCrtTs;
	private java.lang.String recUpdTs;

}