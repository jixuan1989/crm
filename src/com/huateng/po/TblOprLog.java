package com.huateng.po;

import java.io.Serializable;



public class TblOprLog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String oprId;
	private java.lang.String oprType;
	private java.lang.String oprDate;


	
	
	/**
	 * 
	 */
	public TblOprLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="BRH_ID"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.huateng.po.TblOprLog)) return false;
		else {
			com.huateng.po.TblOprLog tblOprLog = (com.huateng.po.TblOprLog) obj;
			if (null == this.getId() || null == tblOprLog.getId()) return false;
			else return (this.getId().equals(tblOprLog.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

	/**
	 * @return the oprId
	 */
	public java.lang.String getOprId() {
		return oprId;
	}

	/**
	 * @param oprId the oprId to set
	 */
	public void setOprId(java.lang.String oprId) {
		this.oprId = oprId;
	}

	/**
	 * @return the oprType
	 */
	public java.lang.String getOprType() {
		return oprType;
	}

	/**
	 * @param oprType the oprType to set
	 */
	public void setOprType(java.lang.String oprType) {
		this.oprType = oprType;
	}

	/**
	 * @return the oprDate
	 */
	public java.lang.String getOprDate() {
		return oprDate;
	}

	/**
	 * @param oprDate the oprDate to set
	 */
	public void setOprDate(java.lang.String oprDate) {
		this.oprDate = oprDate;
	}

	

}