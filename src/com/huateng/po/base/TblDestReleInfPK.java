package com.huateng.po.base;

import java.io.Serializable;

public class TblDestReleInfPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hashCode = Integer.MIN_VALUE;
	private String destIdGrpId ;
	private String destId;
	public String getDestIdGrpId() {
		return destIdGrpId;
	}
	public void setDestIdGrpId(String destIdGrpId) {
		this.destIdGrpId = destIdGrpId;
	}
	public String getDestId() {
		return destId;
	}
	public void setDestId(String destId) {
		this.destId = destId;
	}	
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof TblDestReleInfPK)) return false;
		else {
			TblDestReleInfPK mObj = (TblDestReleInfPK) obj;
			if (null != this.getDestIdGrpId() && null != mObj.getDestIdGrpId()) {
				if (!this.getDestIdGrpId().equals(mObj.getDestIdGrpId())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getDestId()&& null != mObj.getDestId()) {
				if (!this.getDestId().equals(mObj.getDestId())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}
	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getDestIdGrpId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getDestIdGrpId().hashCode()+this.getDestId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

}
