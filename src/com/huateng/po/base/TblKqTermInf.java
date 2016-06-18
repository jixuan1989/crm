package com.huateng.po.base;

public class TblKqTermInf {
	protected int hashCode = Integer.MIN_VALUE;
	private TblKqTermInfPK id ;
	private String usageFlag;
	private String crtOpr;
	private String recUpdOpr;
	private String crtTime;
	private String recUpdTime;
	
	public TblKqTermInfPK getId() {
		return id;
	}

	public void setId(TblKqTermInfPK id) {
		this.id = id;
	}

	public String getUsageFlag() {
		return usageFlag;
	}

	public void setUsageFlag(String usageFlag) {
		this.usageFlag = usageFlag;
	}

	public String getCrtOpr() {
		return crtOpr;
	}

	public void setCrtOpr(String crtOpr) {
		this.crtOpr = crtOpr;
	}

	public String getRecUpdOpr() {
		return recUpdOpr;
	}

	public void setRecUpdOpr(String recUpdOpr) {
		this.recUpdOpr = recUpdOpr;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getRecUpdTime() {
		return recUpdTime;
	}

	public void setRecUpdTime(String recUpdTime) {
		this.recUpdTime = recUpdTime;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof  TblKqTermInf))
			return false;
		else {
			TblKqTermInf tblKqTermInf = ( TblKqTermInf) obj;
			if (null == this.getId() || null == tblKqTermInf.getId())
				return false;
			else
				return (this.getId().equals(tblKqTermInf.getId()));
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
