package com.huateng.po.base;

public class TblSalTpGrp {
	private int hashCode = Integer.MIN_VALUE;
	private String salTpGrp ;
	private String minTpGrp;
	private String descr;
	private String recSt;
	private String lastOperIn;
	private String updUsrId;
	private String recUpdTs;
	private String recCrtTs;
	public String getMinTpGrp() {
		return minTpGrp;
	}
	public void setMinTpGrp(String minTpGrp) {
		this.minTpGrp = minTpGrp;
	}

	public String getSalTpGrp() {
		return salTpGrp;
	}
	public void setSalTpGrp(String salTpGrp) {
		this.salTpGrp = salTpGrp;
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
		if (!(obj instanceof  TblSalTpGrp))
			return false;
		else {
			TblSalTpGrp tblSalTpGrp = ( TblSalTpGrp) obj;
			if (null == this.getSalTpGrp() || null == tblSalTpGrp.getSalTpGrp())
				return false;
			else
				return (this.getSalTpGrp().equals(tblSalTpGrp.getSalTpGrp()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getSalTpGrp())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getSalTpGrp().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

		
}
