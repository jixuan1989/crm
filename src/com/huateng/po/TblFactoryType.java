/* @(#)
 *
 * Project:spdb
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2011-10-9       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2011 Huateng Software, Inc. All rights reserved.
 *
 *       This software is the confidential and proprietary information of
 *       Shanghai HUATENG Software Co., Ltd. ("Confidential Information").
 *       You shall not disclose such Confidential Information and shall use it
 *       only in accordance with the terms of the license agreement you entered
 *       into with Huateng.
 *
 * Warning:
 * =============================================================================
 *
 */
package com.huateng.po;

import java.io.Serializable;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-10-9
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class TblFactoryType implements Serializable{
	private static final long serialVersionUID = 1L;
	private java.lang.String id;
	private java.lang.String factoryNo; //厂商名称
	private java.lang.String typeName;  //型号编号
	private java.lang.String posType;   //台式/移动
	private java.lang.String key;       //是否存在密码键盘
	

	// primary key
	protected void initialize () {}



	/**
	 * @param id
	 * @param branchCd
	 * @param branchArea
	 * @param branchAddr
	 * @param branchSvrLvl
	 * @param branchContMan
	 * @param branchTel
	 * @param branchFax
	 * @param oprNm
	 * @param custTel
	 * @param misc
	 */

	private int hashCode = Integer.MIN_VALUE;


	/**
	 * 
	 */
	public TblFactoryType() {
		initialize();
	}

	

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}
	

	/**
	 * @return the factoryNo
	 */
	public java.lang.String getFactoryNo() {
		return factoryNo;
	}



	/**
	 * @param factoryNo the factoryNo to set
	 */
	public void setFactoryNo(java.lang.String factoryNo) {
		this.factoryNo = factoryNo;
	}

	/**
	 * @return the posType
	 */
	public java.lang.String getPosType() {
		return posType;
	}



	/**
	 * @param posType the posType to set
	 */
	public void setPosType(java.lang.String posType) {
		this.posType = posType;
	}



	/**
	 * @return the key
	 */
	public java.lang.String getKey() {
		return key;
	}



	/**
	 * @param key the key to set
	 */
	public void setKey(java.lang.String key) {
		this.key = key;
	}



	/**
	 * @return the typeName
	 */
	public java.lang.String getTypeName() {
		return typeName;
	}



	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}



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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.huateng.po.TblFactoryType)) return false;
		else {
			com.huateng.po.TblFactoryType tblPosMangamentTmp = (com.huateng.po.TblFactoryType) obj;
			if (null == this.getId() || null == tblPosMangamentTmp.getId()) return false;
			else return (this.getId().equals(tblPosMangamentTmp.getId()));
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
