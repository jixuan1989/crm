package com.huateng.po.base;

public class TblDestReleInf {
	private int hashCode = Integer.MIN_VALUE;
	private TblDestReleInfPK id ;
	private String sequeuceNo;
	private String lastOperIn;
	private String updUsrId;
	private String recUpdTs;
	private String recCrtTs;	
	public TblDestReleInfPK getId() {
		return id;
	}

	public void setId(TblDestReleInfPK id) {
		this.id = id;
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
		if (!(obj instanceof  TblDestReleInf))
			return false;
		else {
			TblDestReleInf tblDestReleInf = ( TblDestReleInf) obj;
			if (null == this.getId() || null == tblDestReleInf.getId())
				return false;
			else
				return (this.getId().equals(tblDestReleInf.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

		
}
