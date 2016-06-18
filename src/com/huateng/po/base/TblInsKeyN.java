package com.huateng.po.base;

import java.io.Serializable;
import java.math.BigDecimal;

public class TblInsKeyN implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	private int hashCode = Integer.MIN_VALUE;
	/**
	 * 
	 */
	public TblInsKeyN() {
		super();
	}
	
	 private String insId;   
	 private String fwdInsId; 
	 private String useFlag;   
	 private String insKeyIdx;
	 private String tmkKey;   
	 private String insMacKeyLen;
	 private String insMac1;   
	 private String insMac2;
	 private String macChkVal;   
	 private String insPinKeyLen;
	 private String insPin1;   
	 private String insPin2;
	 private String pinChkVal;   
	 private String insTrkFlag;
	 private String insTrkKeyLen;   
	 private String insTrk1;
	 private String insTrk2;   
	 private String trkChkVal;
	 private String recOprId;   
	 private String recCrtTs;
	 private String recUpdOpr;   
	 private String recUpdTs;
	public int getHashCode() {
		return hashCode;
	}
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
	public String getInsId() {
		return insId;
	}
	public void setInsId(String insId) {
		this.insId = insId;
	}
	public String getFwdInsId() {
		return fwdInsId;
	}
	public void setFwdInsId(String fwdInsId) {
		this.fwdInsId = fwdInsId;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public String getInsKeyIdx() {
		return insKeyIdx;
	}
	public void setInsKeyIdx(String insKeyIdx) {
		this.insKeyIdx = insKeyIdx;
	}
	public String getTmkKey() {
		return tmkKey;
	}
	public void setTmkKey(String tmkKey) {
		this.tmkKey = tmkKey;
	}
	public String getInsMacKeyLen() {
		return insMacKeyLen;
	}
	public void setInsMacKeyLen(String insMacKeyLen) {
		this.insMacKeyLen = insMacKeyLen;
	}
	public String getInsMac1() {
		return insMac1;
	}
	public void setInsMac1(String insMac1) {
		this.insMac1 = insMac1;
	}
	public String getInsMac2() {
		return insMac2;
	}
	public void setInsMac2(String insMac2) {
		this.insMac2 = insMac2;
	}
	public String getMacChkVal() {
		return macChkVal;
	}
	public void setMacChkVal(String macChkVal) {
		this.macChkVal = macChkVal;
	}
	public String getInsPinKeyLen() {
		return insPinKeyLen;
	}
	public void setInsPinKeyLen(String insPinKeyLen) {
		this.insPinKeyLen = insPinKeyLen;
	}
	public String getInsPin1() {
		return insPin1;
	}
	public void setInsPin1(String insPin1) {
		this.insPin1 = insPin1;
	}
	public String getInsPin2() {
		return insPin2;
	}
	public void setInsPin2(String insPin2) {
		this.insPin2 = insPin2;
	}
	public String getPinChkVal() {
		return pinChkVal;
	}
	public void setPinChkVal(String pinChkVal) {
		this.pinChkVal = pinChkVal;
	}
	public String getInsTrkFlag() {
		return insTrkFlag;
	}
	public void setInsTrkFlag(String insTrkFlag) {
		this.insTrkFlag = insTrkFlag;
	}
	public String getInsTrkKeyLen() {
		return insTrkKeyLen;
	}
	public void setInsTrkKeyLen(String insTrkKeyLen) {
		this.insTrkKeyLen = insTrkKeyLen;
	}
	public String getInsTrk1() {
		return insTrk1;
	}
	public void setInsTrk1(String insTrk1) {
		this.insTrk1 = insTrk1;
	}
	public String getInsTrk2() {
		return insTrk2;
	}
	public void setInsTrk2(String insTrk2) {
		this.insTrk2 = insTrk2;
	}
	public String getTrkChkVal() {
		return trkChkVal;
	}
	public void setTrkChkVal(String trkChkVal) {
		this.trkChkVal = trkChkVal;
	}
	public String getRecOprId() {
		return recOprId;
	}
	public void setRecOprId(String recOprId) {
		this.recOprId = recOprId;
	}
	public String getRecCrtTs() {
		return recCrtTs;
	}
	public void setRecCrtTs(String recCrtTs) {
		this.recCrtTs = recCrtTs;
	}
	public String getRecUpdOpr() {
		return recUpdOpr;
	}
	public void setRecUpdOpr(String recUpdOpr) {
		this.recUpdOpr = recUpdOpr;
	}
	public String getRecUpdTs() {
		return recUpdTs;
	}
	public void setRecUpdTs(String recUpdTs) {
		this.recUpdTs = recUpdTs;
	}
	 
}