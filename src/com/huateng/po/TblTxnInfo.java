package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblTxnInfo implements Serializable {
	private static final long serialVersionUID = 2354730459825384273L;
	public static String REF = "TblTxnInfo";
	public static String PROP_IP_ADDR = "IpAddr";
	public static String PROP_TXN_DATE = "TxnDate";
	public static String PROP_TXN_CODE = "TxnCode";
	public static String PROP_TXN_TIME = "TxnTime";
	public static String PROP_ORG_CODE = "OrgCode";
	public static String PROP_OPR_ID = "OprId";
	public static String PROP_ERR_MSG = "ErrMsg";
	public static String PROP_ID = "Id";
	public static String PROP_TXN_NAME = "TxnName";
	public static String PROP_SUB_TXN_CODE = "SubTxnCode";
	public static String PROP_TXN_STA = "TxnSta";

	// primary key
	private com.huateng.po.TblTxnInfoPK id;

	// fields
	private java.lang.String txnDate;
	private java.lang.String txnTime;
	private java.lang.String txnCode;
	private java.lang.String subTxnCode;
	private java.lang.String oprId;
	private java.lang.String orgCode;
	private java.lang.String ipAddr;
	private java.lang.String txnName;
	private java.lang.String txnSta;
	private java.lang.String errMsg;

}