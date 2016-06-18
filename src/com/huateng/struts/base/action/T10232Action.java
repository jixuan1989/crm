package com.huateng.struts.base.action;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.huateng.common.Constants;
import com.huateng.common.StringUtil;
import com.huateng.common.SysParamConstants;
import com.huateng.struts.system.action.ReportBaseAction;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.InformationUtil;
import com.huateng.system.util.SysParamUtil;


public class T10232Action extends ReportBaseAction{

	private static final long serialVersionUID = 1L;

	@Override
	protected void reportAction() throws Exception {
		
		reportType = "EXCEL";
		
		title = InformationUtil.createTitles("V_", 7);
		
		data = reportCreator.process(genSql(), title);		
		
		if(data.length == 0) {
			writeNoDataMsg("没有找到符合条件的记录");
			return;
		}
		
		reportModel.setData(data);
		reportModel.setTitle(title);
		parameters.put("P_1", startDate+"-"+endDate);	
		parameters.put("P_2", instCode==null?"-":instCode);	
		reportCreator.initReportData(getJasperInputSteam("T10232.jasper"), parameters,reportModel.wrapReportDataSource(), getReportType());
		
		
		fileName = SysParamUtil.getParam(SysParamConstants.TEMP_FILE_DISK) + "RN10232RN_" + operator.getOprId() + "_" + CommonFunction.getCurrentDateTime() + ".xls";
		
		
		outputStream = new FileOutputStream(fileName);
		
		reportCreator.exportReport(outputStream, SysParamUtil.getParam("RN10232RN"));
		
		writeUsefullMsg(fileName);
		return;
	}
	@Override
	protected String genSql() {
		// TODO Auto-generated method stub
		StringBuffer whereSql = new StringBuffer();		
		if (StringUtil.isNotEmpty(instCode)) {
			whereSql.append(" AND INST_CODE ='"+instCode+"'");
		}
		if (StringUtil.isNotEmpty(startDate)) {
			whereSql.append(" AND DATE_TIME>='"+startDate+"000000'");
		}
		if (StringUtil.isNotEmpty(endDate)) {
			whereSql.append(" AND DATE_TIME<='"+endDate+"595959'");
		}		
		if(whereSql.length()==0){
			whereSql.append(" AND DATE_TIME LIKE TO_CHAR(SYSDATE,'YYYYMMDD')||'%' ");
		}
		StringBuffer sSql = new StringBuffer();
		sSql.append(" SELECT TO_CHAR(TO_DATE(DATE_TIME,'YYYYMMDDHHMISS'),'YYYY-MM-DD HH:MI:SS'),INST_CODE,TOT_DAY_AMT,RISK_DAY_AMT_BL,TOT_DAY_NUM,RISK_DAY_NUM_BL,DAY_MAX_AMT FROM RPT_DEST_WARN_INF WHERE 1=1 ");
		sSql.append(whereSql);
		sSql.append(" ORDER BY DATE_TIME DESC,INST_CODE ");
		return sSql.toString();
	}
	private String instCode;
	private String startDate;
	private String endDate;

	public String getInstCode() {
		return instCode;
	}
	public void setInstCode(String instCode) {
		this.instCode = instCode;
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
	

}
