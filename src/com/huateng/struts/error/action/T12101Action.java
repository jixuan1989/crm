/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-8-27       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2010 Huateng Software, Inc. All rights reserved.
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
package com.huateng.struts.error.action;

import com.huateng.common.Constants;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.struts.system.action.BaseSupport;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.ContextUtil;
import com.huateng.system.util.JSONBean;

/**
 * Title: 机构对账不平
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-8-27
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class T12101Action extends BaseSupport {
	private ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
	public String edit(){
		JSONBean jsonBean=new JSONBean();
		jsonBean.parseJSONArrayData(handlingList);
		int len = jsonBean.getArray().size();
		for (int i = 0; i < len; i++) {
			mchtNo = jsonBean.getJSONDataAt(i).getString("mchtNo");
			termId = jsonBean.getJSONDataAt(i).getString("termId");
			instDate = jsonBean.getJSONDataAt(i).getString("instDate");
			settleDate = jsonBean.getJSONDataAt(i).getString("settleDate");
			String sql = "update bth_dtl_err_gc set handling = '" + handling + "',remark = '" + remark + "',flag='01',LST_UPD_TIME='" + CommonFunction.getCurrentDateTime() + "' where acq_mcht_cd = '" + mchtNo + "' and acq_term_id = '" 
			+ termId + "' and trans_date_time = '" + instDate + "' and date_settlmt = '" + settleDate + "'";
			commQueryDAO.excute(sql);
			sql = "update report_t80104_02 set handling = '" + handling + "',remark = '" + remark + "',flag='01',UPD_DATE='" + CommonFunction.getCurrentDateTime() + "' where acq_mcht_cd = '" + mchtNo + "' and acq_term_id = '" 
					+ termId + "' and trans_date_time = '" + instDate + "' and date_settlmt = '" + settleDate + "'";
			commQueryDAO.excute(sql);
		}
		return returnService(Constants.SUCCESS_CODE);
	}
	public String delete(){
		JSONBean jsonBean=new JSONBean();
		jsonBean.parseJSONArrayData(handlingList);
		int len = jsonBean.getArray().size();
		for (int i = 0; i < len; i++) {
			mchtNo = jsonBean.getJSONDataAt(i).getString("mchtNo");
			termId = jsonBean.getJSONDataAt(i).getString("termId");
			instDate = jsonBean.getJSONDataAt(i).getString("instDate");
			settleDate = jsonBean.getJSONDataAt(i).getString("settleDate");
			String sql = "update bth_dtl_err_gc set flag = '05',LST_UPD_TIME='" + CommonFunction.getCurrentDateTime() + "' where acq_mcht_cd = '" + mchtNo + "' and acq_term_id = '" 
			+ termId + "' and trans_date_time = '" + instDate + "' and date_settlmt = '" + settleDate + "'";
			commQueryDAO.excute(sql);
			sql = "update report_t80104_02  set flag = '05',UPD_DATE='" + CommonFunction.getCurrentDateTime() + "' where acq_mcht_cd = '" + mchtNo + "' and acq_term_id = '" 
					+ termId + "' and trans_date_time = '" + instDate + "' and date_settlmt = '" + settleDate + "'";
					commQueryDAO.excute(sql);
		}
		return returnService(Constants.SUCCESS_CODE);
	}
	public String check(){
		JSONBean jsonBean=new JSONBean();
		jsonBean.parseJSONArrayData(handlingList);
		int len = jsonBean.getArray().size();
		for (int i = 0; i < len; i++) {
			mchtNo = jsonBean.getJSONDataAt(i).getString("mchtNo");
			termId = jsonBean.getJSONDataAt(i).getString("termId");
			instDate = jsonBean.getJSONDataAt(i).getString("instDate");
			settleDate = jsonBean.getJSONDataAt(i).getString("settleDate");
			String sql = "update bth_dtl_err_gc set flag='" + status + "',LST_UPD_TIME='" + CommonFunction.getCurrentDateTime() + "',remark='"+ remark +"' where acq_mcht_cd = '" + mchtNo + "' and acq_term_id = '" 
			+ termId + "' and trans_date_time = '" + instDate + "' and date_settlmt = '" + settleDate + "'";
			commQueryDAO.excute(sql);
			sql = "update report_t80104_02 set flag='" + status + "',UPD_DATE='" + CommonFunction.getCurrentDateTime() + "',remark='"+ remark +"' where acq_mcht_cd = '" + mchtNo + "' and acq_term_id = '" 
					+ termId + "' and trans_date_time = '" + instDate + "' and date_settlmt = '" + settleDate + "'";
			commQueryDAO.excute(sql);
		}
		return returnService(Constants.SUCCESS_CODE);
	}
	@Override
	public String getMsg() {
		return msg;
	}
	
	@Override
	public boolean isSuccess() {
		return success;
	}
	
	private String startDate;
	private String endDate;
	private String mchtNo;
	private String termId;
	private String pan;
	private String flag;
	private String remark;
	private String handling;
	private String instDate;
	private String settleDate;
	private String handlingList;
	private String status;
	public String getInstDate() {
		return instDate;
	}
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHandlingList() {
		return handlingList;
	}
	public void setHandlingList(String handlingList) {
		this.handlingList = handlingList;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
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
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHandling() {
		return handling;
	}
	public void setHandling(String handling) {
		this.handling = handling;
	}
	
	
	
	
		
}