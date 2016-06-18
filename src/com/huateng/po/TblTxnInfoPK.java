package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TblTxnInfoPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4445689507394082644L;

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.String txnSeqNo;
	private java.lang.String acctDate;

}