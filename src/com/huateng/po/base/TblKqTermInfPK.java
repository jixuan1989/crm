package com.huateng.po.base;

import java.io.Serializable;

public class TblKqTermInfPK implements Serializable  {
	private static final long serialVersionUID = 1L;

	protected int hashCode = Integer.MIN_VALUE;
	private String mchtNo ;
	private String termId;
	public String getMchtNo() {
		return mchtNo;
	}
	public void setMchtNo(String mchtNo) {
		this.mchtNo = mchtNo;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof TblKqTermInfPK)) return false;
		else {
			TblKqTermInfPK mObj = (TblKqTermInfPK) obj;
			if (null != this.getMchtNo() && null != mObj.getMchtNo()) {
				if (!this.getMchtNo().equals(mObj.getMchtNo())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getTermId() && null != mObj.getTermId()) {
				if (!this.getTermId().equals(mObj.getTermId())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getMchtNo()) {
				sb.append(this.getMchtNo().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getTermId()) {
				sb.append(this.getTermId().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}

}
