package com.huateng.po;

import java.io.Serializable;



public class TblPositionInf implements Serializable {
	
	private static final long serialVersionUID = 1L;

	


	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	
	private String positionName ;
	private String positionDes ;
	private String sortNo; 
	private String createTime ;
	private String updateTime ;
	private String createOpr ;
	private String updateOpr ;
	
	
	
	/**
	 * @return the hashCode
	 */
	public int getHashCode() {
		return hashCode;
	}

	/**
	 * @param hashCode the hashCode to set
	 */
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the positionDes
	 */
	public String getPositionDes() {
		return positionDes;
	}

	/**
	 * @param positionDes the positionDes to set
	 */
	public void setPositionDes(String positionDes) {
		this.positionDes = positionDes;
	}

	/**
	 * @return the sortNo
	 */
	public String getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo the sortNo to set
	 */
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the createOpr
	 */
	public String getCreateOpr() {
		return createOpr;
	}

	/**
	 * @param createOpr the createOpr to set
	 */
	public void setCreateOpr(String createOpr) {
		this.createOpr = createOpr;
	}

	/**
	 * @return the updateOpr
	 */
	public String getUpdateOpr() {
		return updateOpr;
	}

	/**
	 * @param updateOpr the updateOpr to set
	 */
	public void setUpdateOpr(String updateOpr) {
		this.updateOpr = updateOpr;
	}

	/**
	 * 
	 */
	public TblPositionInf() {
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




	/**
	 * Return the value associated with the column: CUP_BRH_ID
	 */
	



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.huateng.po.TblPositionInf)) return false;
		else {
			com.huateng.po.TblPositionInf tblBrhInfo = (com.huateng.po.TblPositionInf) obj;
			if (null == this.getId() || null == tblBrhInfo.getId()) return false;
			else return (this.getId().equals(tblBrhInfo.getId()));
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

	


}