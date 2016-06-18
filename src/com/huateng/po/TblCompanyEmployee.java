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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
}
