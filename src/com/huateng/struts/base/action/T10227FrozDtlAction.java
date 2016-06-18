package com.huateng.struts.base.action;

import java.io.FileOutputStream;

import com.huateng.common.Constants;
import com.huateng.common.StringUtil;
import com.huateng.common.SysParamConstants;
import com.huateng.struts.system.action.ReportBaseAction;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.InformationUtil;
import com.huateng.system.util.SysParamUtil;


/**
 * 冻结明细报表
 * @author lhj
 *
 */
public class T10227FrozDtlAction extends ReportBaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private String mchtNo ;
	private String frozenStartDate;
	private String frozenEndDate;
	private String actUnFrozenStartDate;
	private String actUnFrozenEndDate ; 

	@Override
	protected String genSql() {
		String whereSql = " where 1=1 " ;
		
		if(StringUtil.isNotEmpty(mchtNo)){
			whereSql+=" AND a.mcht_no='"+mchtNo+"'";
		}
		
		if (StringUtil.isNotEmpty(frozenStartDate)){
			whereSql+=" AND a.frozen_date>='"+frozenStartDate+"'";
		}
		 
		if(StringUtil.isNotEmpty(frozenEndDate)){
			whereSql+=" AND a.frozen_date <='"+frozenEndDate+"'";
		}
		
		if(StringUtil.isNotEmpty(actUnFrozenStartDate)){
			whereSql+=" AND a.act_frozen_date>='"+actUnFrozenStartDate+"'";
		}
		
		if(StringUtil.isNotEmpty(actUnFrozenEndDate)){
			whereSql+=" AND a.act_frozen_date<='"+actUnFrozenEndDate+"'";
		}
		
		String sql = " select a.mcht_no," +
				"getmchtname(a.mcht_no)," +
				"a.frozen_date," +
				"a.act_frozen_date," +
				"a.frozen_amt," +
				"a.rec_upd_id," +
				"a.rec_upd_ts from TBL_FROZEN_DTL  a  " +whereSql.toString()+" order by a.frozen_date, a.frozen_amt ";
		return sql.toString();
	}

	@Override
	protected void reportAction() throws Exception {
		reportType="EXCEL";
		title=InformationUtil.createTitles("V_",7);
		
		data=reportCreator.process(genSql(), title);
		
		if(data.length == 0 ){
			writeNoDataMsg("没有找到符合条件的记录");
			return;
		}
		
		reportModel.setData(data);
		reportModel.setTitle(title);
	 
		reportCreator.initReportData(getJasperInputSteam("T10227FroDtl.jasper"), parameters,reportModel.wrapReportDataSource(), getReportType());
		if(Constants.REPORT_EXCELMODE.equals(reportType)){
			fileName = SysParamUtil.getParam(SysParamConstants.TEMP_FILE_DISK)+"_"+"RN1022701RN"+operator.getOprId()+"_" + CommonFunction.getCurrentDateTime() + ".xls";
		}		
		outputStream = new FileOutputStream(fileName);
		reportCreator.exportReport(outputStream, SysParamUtil.getParam("RN1022701RN"));
		writeUsefullMsg(fileName);
		return;
	}

	public String getMchtNo() {
		return mchtNo;
	}

	public void setMchtNo(String mchtNo) {
		this.mchtNo = mchtNo;
	}

	public String getFrozenStartDate() {
		return frozenStartDate;
	}

	public void setFrozenStartDate(String frozenStartDate) {
		this.frozenStartDate = frozenStartDate;
	}

	public String getFrozenEndDate() {
		return frozenEndDate;
	}

	public void setFrozenEndDate(String frozenEndDate) {
		this.frozenEndDate = frozenEndDate;
	}

	public String getActUnFrozenStartDate() {
		return actUnFrozenStartDate;
	}

	public void setActUnFrozenStartDate(String actUnFrozenStartDate) {
		this.actUnFrozenStartDate = actUnFrozenStartDate;
	}

	public String getActUnFrozenEndDate() {
		return actUnFrozenEndDate;
	}

	public void setActUnFrozenEndDate(String actUnFrozenEndDate) {
		this.actUnFrozenEndDate = actUnFrozenEndDate;
	}

	
	
	
}
