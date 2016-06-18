package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblBrhInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static String REF = "TblBrhInfo";
	public static String PROP_CUP_BRH_ID = "CupBrhId";
	public static String PROP_LAST_UPD_TS = "LastUpdTs";
	public static String PROP_LAST_UPD_TXN_ID = "LastUpdTxnId";
	public static String PROP_RESV1 = "Resv1";
	public static String PROP_REG_DT = "RegDt";
	public static String PROP_BRH_LEVEL = "BrhLevel";
	public static String PROP_BRH_CONT_NAME = "BrhContName";
	public static String PROP_BRH_STA = "BrhSta";
	public static String PROP_BRH_TEL_NO = "BrhTelNo";
	public static String PROP_RESV2 = "Resv2";
	public static String PROP_BRH_NAME = "BrhName";
	public static String PROP_BRH_ADDR = "BrhAddr";
	public static String PROP_UP_BRH_ID = "UpBrhId";
	public static String PROP_POST_CD = "PostCd";
	public static String PROP_ID = "Id";
	public static String PROP_LAST_UPD_OPR_ID = "LastUpdOprId";


	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String cupBrhId;
	private java.lang.String brhLevel;
	private java.lang.String brhType;
	private java.lang.String brhSta;
	private java.lang.String upBrhId;
	private java.lang.String regDt;
	private java.lang.String postCd;
	private java.lang.String brhAddr;
	private java.lang.String brhName;
	private java.lang.String brhTelNo;
	private java.lang.String brhContName;
	private java.lang.String resv1;
	private java.lang.String resv2;
	private java.lang.String lastUpdOprId;
	private java.lang.String lastUpdTxnId;
	private java.lang.String lastUpdTs;

}