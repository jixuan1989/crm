/* @(#)
 *
 * Project:spdb
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2011-10-17       first release
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
 * Copyright: Copyright (c) 2011-10-17
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class TblCompanyStepment  implements Serializable{
	private static final long serialVersionUID = 1L;
	private java.lang.String id;
	private java.lang.String stepId;
	private java.lang.String name;
	private java.lang.String brhId;
	

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
	public TblCompanyStepment() {
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
		this.hashCode = Integer.MIN_VALUE;
	}
	
	/**
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}



	/**
	 * @return the stepId
	 */
	public java.lang.String getStepId() {
		return stepId;
	}



	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(java.lang.String stepId) {
		this.stepId = stepId;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.huateng.po.TblCompanyStepment)) return false;
		else {
			com.huateng.po.TblCompanyStepment tblCompanyStepment = (com.huateng.po.TblCompanyStepment) obj;
			if (null == this.getId() || null == tblCompanyStepment.getId()) return false;
			else return (this.getId().equals(tblCompanyStepment.getId()));
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
	 * @return the brhId
	 */
	public String getBrhId() {
		return brhId;
	}



	/**
	 * @param brhId the brhId to set
	 */
	public void setBrhId(String brhId) {
		this.brhId = brhId;
	}
}
