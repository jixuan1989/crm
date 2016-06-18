package com.huateng.po.base;

public class TblDestGrpInf {
	private int hashCode = Integer.MIN_VALUE;
	private String destIdGrpId ;
	private String descr;
	private String recSt;
	private String sequeuceNo;
	private String lastOperIn;
	private String updUsrId;
	private String recUpdTs;
	private String recCrtTs;	


	public String getDestIdGrpId() {
		return destIdGrpId;
	}

	public void setDestIdGrpId(String destIdGrpId) {
		this.destIdGrpId = destIdGrpId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getRecSt() {
		return recSt;
	}

	public void setRecSt(String recSt) {
		this.recSt = recSt;
	}

	public String getSequeuceNo() {
		return sequeuceNo;
	}

	public void setSequeuceNo(String sequeuceNo) {
		this.sequeuceNo = sequeuceNo;
	}

	public String getLastOperIn() {
		return lastOperIn;
	}

	public void setLastOperIn(String lastOperIn) {
		this.lastOperIn = lastOperIn;
	}

	public String getUpdUsrId() {
		return updUsrId;
	}

	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}

	public String getRecUpdTs() {
		return recUpdTs;
	}

	public void setRecUpdTs(String recUpdTs) {
		this.recUpdTs = recUpdTs;
	}

	public String getRecCrtTs() {
		return recCrtTs;
	}

	public void setRecCrtTs(String recCrtTs) {
		this.recCrtTs = recCrtTs;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof  TblDestGrpInf))
			return false;
		else {
			TblDestGrpInf tblDestGrpInf = ( TblDestGrpInf) obj;
			if (null == this.getDestIdGrpId() || null == tblDestGrpInf.getDestIdGrpId())
				return false;
			else
				return (this.getDestIdGrpId().equals(tblDestGrpInf.getDestIdGrpId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getDestIdGrpId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getDestIdGrpId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

		
}
