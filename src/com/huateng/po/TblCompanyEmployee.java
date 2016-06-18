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
public class TblCompanyEmployee  implements Serializable{
	private static final long serialVersionUID = 1L;
	private java.lang.String id;
	private java.lang.String employeeId;
	private java.lang.String name;
	private java.lang.String sex;
	private java.lang.String telephone;
	private java.lang.String mobiephone;
	private java.lang.String email;
	private java.lang.String job;
	private java.lang.String stepment;
	private java.lang.String entryTime;
	private java.lang.String rotationTime;
	private java.lang.String levels;
	private java.lang.String edution;
	private java.lang.String professional;
	private java.lang.String graduate;
	private java.lang.String birthday;
	private java.lang.String address;
	private java.lang.String permanentAddress;
	private java.lang.String identityNum;
	private java.lang.String contact;
	private java.lang.String contactTelephone;
	private java.lang.String overtime;
	private java.lang.String fax;
	private java.lang.String parentId;
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
	public TblCompanyEmployee() {
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
	 * @return the employeeId
	 */
	public java.lang.String getEmployeeId() {
		return employeeId;
	}



	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(java.lang.String employeeId) {
		this.employeeId = employeeId;
	}



	/**
	 * @return the sex
	 */
	public java.lang.String getSex() {
		return sex;
	}



	/**
	 * @param sex the sex to set
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}



	/**
	 * @return the telephone
	 */
	public java.lang.String getTelephone() {
		return telephone;
	}



	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(java.lang.String telephone) {
		this.telephone = telephone;
	}



	/**
	 * @return the mobiephone
	 */
	public java.lang.String getMobiephone() {
		return mobiephone;
	}



	/**
	 * @param mobiephone the mobiephone to set
	 */
	public void setMobiephone(java.lang.String mobiephone) {
		this.mobiephone = mobiephone;
	}



	/**
	 * @return the job
	 */
	public java.lang.String getJob() {
		return job;
	}



	/**
	 * @param job the job to set
	 */
	public void setJob(java.lang.String job) {
		this.job = job;
	}



	/**
	 * @return the stepment
	 */
	public java.lang.String getStepment() {
		return stepment;
	}



	/**
	 * @param stepment the stepment to set
	 */
	public void setStepment(java.lang.String stepment) {
		this.stepment = stepment;
	}



	/**
	 * @return the entryTime
	 */
	public java.lang.String getEntryTime() {
		return entryTime;
	}



	/**
	 * @param entryTime the entryTime to set
	 */
	public void setEntryTime(java.lang.String entryTime) {
		this.entryTime = entryTime;
	}



	/**
	 * @return the rotationTime
	 */
	public java.lang.String getRotationTime() {
		return rotationTime;
	}



	/**
	 * @param rotationTime the rotationTime to set
	 */
	public void setRotationTime(java.lang.String rotationTime) {
		this.rotationTime = rotationTime;
	}



	/**
	 * @return the levels
	 */
	public java.lang.String getLevels() {
		return levels;
	}



	/**
	 * @param levels the levels to set
	 */
	public void setLevels(java.lang.String levels) {
		this.levels = levels;
	}



	/**
	 * @return the edution
	 */
	public java.lang.String getEdution() {
		return edution;
	}



	/**
	 * @param edution the edution to set
	 */
	public void setEdution(java.lang.String edution) {
		this.edution = edution;
	}



	/**
	 * @return the professional
	 */
	public java.lang.String getProfessional() {
		return professional;
	}



	/**
	 * @param professional the professional to set
	 */
	public void setProfessional(java.lang.String professional) {
		this.professional = professional;
	}



	/**
	 * @return the graduate
	 */
	public java.lang.String getGraduate() {
		return graduate;
	}



	/**
	 * @param graduate the graduate to set
	 */
	public void setGraduate(java.lang.String graduate) {
		this.graduate = graduate;
	}



	/**
	 * @return the birthday
	 */
	public java.lang.String getBirthday() {
		return birthday;
	}



	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(java.lang.String birthday) {
		this.birthday = birthday;
	}



	/**
	 * @return the address
	 */
	public java.lang.String getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}



	/**
	 * @return the permanentAddress
	 */
	public java.lang.String getPermanentAddress() {
		return permanentAddress;
	}



	/**
	 * @param permanentAddress the permanentAddress to set
	 */
	public void setPermanentAddress(java.lang.String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}



	/**
	 * @return the identityNum
	 */
	public java.lang.String getIdentityNum() {
		return identityNum;
	}



	/**
	 * @param identityNum the identityNum to set
	 */
	public void setIdentityNum(java.lang.String identityNum) {
		this.identityNum = identityNum;
	}



	/**
	 * @return the contact
	 */
	public java.lang.String getContact() {
		return contact;
	}



	/**
	 * @param contact the contact to set
	 */
	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}



	/**
	 * @return the contactTelephone
	 */
	public java.lang.String getContactTelephone() {
		return contactTelephone;
	}



	/**
	 * @param contactTelephone the contactTelephone to set
	 */
	public void setContactTelephone(java.lang.String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}



	/**
	 * @return the fax
	 */
	public java.lang.String getFax() {
		return fax;
	}



	/**
	 * @param fax the fax to set
	 */
	public void setFax(java.lang.String fax) {
		this.fax = fax;
	}



	/**
	 * @return the overtime
	 */
	public java.lang.String getOvertime() {
		return overtime;
	}



	/**
	 * @param overtime the overtime to set
	 */
	public void setOvertime(java.lang.String overtime) {
		this.overtime = overtime;
	}

	



	/**
	 * @return the email
	 */
	public java.lang.String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}



	/**
	 * @return the parentId
	 */
	public java.lang.String getParentId() {
		return parentId;
	}



	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.huateng.po.TblCompanyEmployee)) return false;
		else {
			com.huateng.po.TblCompanyEmployee tblCompanyEmployee = (com.huateng.po.TblCompanyEmployee) obj;
			if (null == this.getId() || null == tblCompanyEmployee.getId()) return false;
			else return (this.getId().equals(tblCompanyEmployee.getId()));
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
