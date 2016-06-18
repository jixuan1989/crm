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
package com.huateng.bo.impl.base;

import java.util.List;

import com.huateng.bo.base.T10500BO;
import com.huateng.common.Constants;
import com.huateng.dao.iface.base.TblCompanyStepmentDAO;
import com.huateng.po.TblCompanyStepment;

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
public class T10500BOTarget implements T10500BO {

	private TblCompanyStepmentDAO tblCompanyStepmentDAO;

	/**
	 * @return the tblCompanyStepmentDAO
	 */
	public TblCompanyStepmentDAO getTblCompanyStepmentDAO() {
		return tblCompanyStepmentDAO;
	}

	/**
	 * @param tblCompanyStepmentDAO
	 *            the tblCompanyStepmentDAO to set
	 */
	public void setTblCompanyStepmentDAO(
			TblCompanyStepmentDAO tblCompanyStepmentDAO) {
		this.tblCompanyStepmentDAO = tblCompanyStepmentDAO;
	}

	public String add(TblCompanyStepment tblCompanyStepment) {
		tblCompanyStepmentDAO.save(tblCompanyStepment);
		return Constants.SUCCESS_CODE;
	}

	public String delete(String id) {
		tblCompanyStepmentDAO.delete(id);
		return Constants.SUCCESS_CODE;
	}

	public TblCompanyStepment get(String id) {
		return tblCompanyStepmentDAO.get(id);
	}

	public String update(List<TblCompanyStepment> tblCompanyStepmentList) {
		for (TblCompanyStepment tblCompanyStepment : tblCompanyStepmentList) {
			tblCompanyStepmentDAO.update(tblCompanyStepment);
		}
		return Constants.SUCCESS_CODE;
	}

}
