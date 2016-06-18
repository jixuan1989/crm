package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblOprInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String REF = "TblOprInfo";
	public static String PROP_BRH_ID = "BrhId";
	public static String PROP_OPR_DEGREE = "OprDegree";
	public static String PROP_OPR_STA = "OprSta";
	public static String PROP_LAST_UPD_TS = "LastUpdTs";
	public static String PROP_LAST_UPD_TXN_ID = "LastUpdTxnId";
	public static String PROP_PWD_WR_TM = "PwdWrTm";
	public static String PROP_OPR_PWD = "OprPwd";
	public static String PROP_OPR_NAME = "OprName";
	public static String PROP_PWD_OUT_DATE = "PwdOutDate";
	public static String PROP_REGISTER_DT = "RegisterDt";
	public static String PROP_OPR_GENDER = "OprGender";
	public static String PROP_OPR_TEL = "OprTel";
	public static String PROP_OPR_MOBILE = "OprMobile";
	public static String PROP_PWD_WR_TM_TOTAL = "PwdWrTmTotal";
	public static String PROP_ID = "Id";
	public static String PROP_LAST_UPD_OPR_ID = "LastUpdOprId";
	public static String OPR_EMAIL = "oprEmail";
	public static String OPR_DEGREE_RSC = "oprDegreeRsc";
	public static String OPR_LOG_STA = "oprLogSta";
	public static String SET_OPR_ID= "setOprId";
	


	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String brhId;
	private java.lang.String oprDegree;
	private java.lang.String oprName;
	private java.lang.String oprGender;
	private java.lang.String registerDt;
	private java.lang.String oprPwd;
	private java.lang.String oprTel;
	private java.lang.String oprMobile;
	private java.lang.String pwdWrTm;
	private java.lang.String pwdWrTmTotal;
	private java.lang.String pwdWrLastDt;
	private java.lang.String pwdOutDate;
	private java.lang.String oprSta;
	private java.lang.String lastUpdOprId;
	private java.lang.String lastUpdTxnId;
	private java.lang.String lastUpdTs;
	private java.lang.String oprEmail;
	private java.lang.String oprDegreeRsc;
	private java.lang.String oprLogSta;
	private java.lang.String setOprId;
	private java.lang.String stepment;
	private java.lang.String isValMsg;
	
}