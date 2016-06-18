package com.huateng.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import com.huateng.log.Log;
import com.huateng.system.util.ContextUtil;
/**
 * 
 * Title: 报表生成类
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
public class ReportCreator {
	/**报表对象*/
	private JasperReport reportObject = null;
	/**报表打印对象*/
	private JasperPrint jasperPrint = null;
	/**报表类型*/
	private String reportType = "";
	/**数据库连接*/
	//private static Connection connection = null;
	/**数据库执行句柄*/
	private PreparedStatement preparedStatement = null;
	/**结果集*/
	private ResultSet resultSet = null;
	
	public ReportCreator() {
		//if(connection == null)
//			try {
//				connection = ((DataSource)ContextUtil.getBean("dataSourceQuery")).getConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				log("报表生成类初始化错误：" + e.getMessage());
//			}
	}
	
	/**
	 * 初始化报表模版
	 * @param reportStream
	 * @param parameters
	 * @param dataSource
	 * @param reportType
	 * @throws JRException
	 * 2010-8-27下午02:42:12
	 */
	public void initReportData(InputStream reportStream, Map<String, String> parameters,
	        JRDataSource dataSource, String reportType) throws JRException {
		reportObject = (JasperReport) JRLoader.loadObject(reportStream);
		jasperPrint = JasperFillManager.fillReport(reportObject, parameters,
		        dataSource);
		this.reportType = reportType;
	}
	
    /**
     * 孔典 
     * 初始化报表模版  数据库连接填充
     * 
     * @param reportStream
     * @param parameters
     * @param connection
     * @param reportType
     * @throws JRException
     */
    public void initReportData(InputStream reportStream,
	    Map<String, String> parameters, Connection connection,
	    String reportType) throws JRException {
	reportObject = (JasperReport) JRLoader.loadObject(reportStream);
	jasperPrint = JasperFillManager.fillReport(reportObject, parameters,
		connection);
	this.reportType = reportType;
    }
	/**
	 * 初始化报表模版
	 * @param reportStream
	 * @param parameters
	 * @param reportType
	 * @throws JRException
	 * 2010-8-27下午02:44:03
	 */
	public void initReportNoData(InputStream reportStream, Map<String, String> parameters,
	        String reportType) throws JRException {
		reportObject = (JasperReport) JRLoader.loadObject(reportStream);
		jasperPrint = JasperFillManager.fillReport(reportObject, parameters);
		this.reportType = reportType;
	}
	
	/**
	 * 导出报表
	 * @param outputStream
	 * @param name
	 * @throws JRException
	 * @throws IOException
	 * 2010-8-27下午02:44:12
	 */
	public void exportReport(OutputStream outputStream,String name) throws JRException, IOException{
		if (reportType.equals(Constants.REPORT_PDFMODE)) {
			pdfReportExporter(outputStream);
		} else if (reportType.equals(Constants.REPORT_RTFMODE)) {
			rtfReportExporter(outputStream);
		} else if (reportType.equals(Constants.REPORT_HTMLMODE)) {
	        htmlReportExporter(outputStream);
	    } else if (reportType.equals(Constants.REPORT_TXTMODE)){
	        txtReportExporter(outputStream);
	    } else if(reportType.equals(Constants.REPORT_EXCELMODE)) {
	    	xlsReportExporter(outputStream,name);
	    }
	}
	
	/**
	 * 导出PDF报表
	 * @param outputStream
	 * @throws JRException
	 * @throws IOException
	 * 2010-8-27下午02:44:21
	 */
	private void pdfReportExporter(OutputStream outputStream)
			throws JRException, IOException {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				this.jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
		outputStream.close();
		outputStream = null;
		exporter = null;
	}
	
	/**
	 * 导出RTF报表
	 * @param outputStream
	 * @throws JRException
	 * @throws IOException
	 * 2010-8-27下午02:44:31
	 */
	private void rtfReportExporter(OutputStream outputStream)
			throws JRException, IOException {
		JRRtfExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "gb2312");
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				this.jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
		outputStream.close();
		outputStream = null;
		exporter = null;
	}
	
	/**
	 * 导出HTML报表
	 * @param outputStream
	 * @throws JRException
	 * @throws IOException
	 * 2010-8-27下午02:44:42
	 */
    private void htmlReportExporter(OutputStream outputStream)
            throws JRException, IOException {
        JRHtmlExporter exporter = new JRHtmlExporter();
        exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "gb2312");
        exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT,
                this.jasperPrint);
        exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        exporter.exportReport();
        outputStream.close();
        outputStream = null;
		exporter = null;
    }
    
    /**
     * 导出TXT报表
     * @param outputStream
     * @throws JRException
     * @throws IOException
     * 2010-8-27下午02:44:52
     */
    private void txtReportExporter(OutputStream outputStream)
            throws JRException, IOException {
        JRTextExporter exporter = new JRTextExporter();
        exporter.setParameter(JRTextExporterParameter.CHARACTER_ENCODING, "GBK");
        exporter.setParameter(JRTextExporterParameter.JASPER_PRINT,
                this.jasperPrint);
        exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(200));
        exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(200));
        exporter.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT,
                System.getProperty("line.separator") + System.getProperty("line.separator"));
        exporter.setParameter(JRTextExporterParameter.LINE_SEPARATOR, System.getProperty("line.separator"));
        exporter.exportReport();
        outputStream.close();
        outputStream = null;
		exporter = null;
    }
    
    /**
     * 导出XLS报表
     * @param outputStream
     * @param name
     * @throws JRException
     * @throws IOException
     * 2010-8-27下午02:45:01
     */
    private void xlsReportExporter(OutputStream outputStream,String name)
            throws JRException, IOException {
    	JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				this.jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        String[] sheetNames ={"Sheet1"};
        exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
        		sheetNames);
        exporter.exportReport();
		outputStream.close();
		outputStream = null;
		exporter = null;
    }
    
    
    /**
     * 导出多表单XLS报表
     * @param jasperPrintList
     * @param outputStream
     * @param names
     * @throws JRException
     * @throws IOException
     * 2010-8-27下午02:45:14
     */
    public void multXlsReportExporter(List<JasperPrint> jasperPrintList,OutputStream outputStream,String[] names)
            throws JRException, IOException {
    	JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
				jasperPrintList);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,
                "UTF-8");
        exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
        		names);
        exporter.exportReport();
		outputStream.close();
		outputStream = null;
		exporter = null;
    }
    
    /**
     * 导出多页面RTF报表
     * @param jasperPrintList
     * @param outputStream
     * @throws JRException
     * @throws IOException
     * 2010-8-27下午02:45:27
     */
    public void multRtfReportExporter(List<JasperPrint> jasperPrintList,OutputStream outputStream) 
    				throws JRException, IOException {
    	JRRtfExporter exporter = new JRRtfExporter();
    	exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
				jasperPrintList);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
		outputStream.close();
		outputStream = null;
		exporter = null;
    }
    /**
     * 对T30406待领机报表生成数据处理
     * @param sql
     * @param title
     * @return
     * @throws SQLException
     * 2010-8-27下午02:46:11
     */
	public Object[][] processT30406(String sql,String[] title)throws SQLException {
		Object[][] dataTmp;
		Object[][] data;
		Object[][] dataSingle;
		log(sql);
		Connection connection = ((DataSource)ContextUtil.getBean("dataSourceQuery")).getConnection();
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		if(resultSet == null)
			return null;
		resultSet.last();
		int len = resultSet.getRow();
		dataTmp = new Object[resultSet.getRow()][title.length];
		dataSingle = new Object[1][title.length];
		resultSet.beforeFirst();
		int i=0,j = 0,m=1;
		resultSet.next();
		while(i < title.length) {
			dataTmp[m-1][i] = resultSet.getObject((i + 1));
			i++;
		}
		while(resultSet.next()) {
			i = 0;
			while(i < title.length) {
				dataSingle[0][i] = resultSet.getObject((i + 1));
				i++;
			}
			//在此处循环dataTmp数组判断是否存在商户号、终端号机具型号相同的，循环数组长度为m
			boolean flag =false;
			for(int n=0;n<m;n++){
				if(dataTmp[n][0].toString().trim().equals(dataSingle[0][0].toString().trim())&&dataTmp[n][2].toString().trim().equals(dataSingle[0][2].toString().trim())&&dataTmp[n][3].toString().trim().equals(dataSingle[0][3].toString().trim())){
					//改行商户号、终端号、机具类型与前一行相同   合并
					dataTmp[n][4]=(Object)(dataTmp[n][4].toString()+"、"+dataSingle[0][4].toString());
					flag = true;
				}
			}
			if(flag == false){
				i=0;
				while(i<title.length){
					dataTmp[m][i]=dataSingle[0][i];
					i++;
				}
				m++;
			}
		}
		//获取总数组长度为m、将dataTmp放入data数组中
		data = new Object[m][title.length];
		for(int k=0;k<m;k++){
			i=0;
			while(i<title.length){
				data[k][i]=dataTmp[k][i];
				i++;
			}
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
		return data;
	}
    /**
     * 生成数据
     * @param sql
     * @param title
     * @return
     * @throws SQLException
     * 2010-8-27下午02:46:11
     */
	public Object[][] process(String sql,String[] title)throws SQLException {
		Object[][] data;
		log(sql);
		Connection connection = ((DataSource)ContextUtil.getBean("dataSourceQuery")).getConnection();
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		if(resultSet == null)
			return null;
		resultSet.last();
		data = new Object[resultSet.getRow()][title.length];
		resultSet.beforeFirst();
		int i,j = 0;
		while(resultSet.next()) {
			i = 0;
			while(i < title.length) {
				data[j][i] = resultSet.getObject((i + 1));
				i++;
			}
			j++;
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
		return data;
	}
	/**
     * 生成数据
     * @param sql
     * @param title
     * @return
     * @throws SQLException
     * 2010-8-27下午02:46:11
     */
	public Object[][] processRemote(String sql,String[] title)throws SQLException {
		Object[][] data;
		log(sql);
		Connection connection = ((DataSource)ContextUtil.getBean("dataSourceRemote")).getConnection();
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		if(resultSet == null)
			return null;
		resultSet.last();
		data = new Object[resultSet.getRow()][title.length];
		resultSet.beforeFirst();
		int i,j = 0;
		while(resultSet.next()) {
			i = 0;
			while(i < title.length) {
				data[j][i] = resultSet.getObject((i + 1));
				i++;
			}
			j++;
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
		return data;
	}
	public Object[][] confirmDePositProcess(String sql,String[] title)throws SQLException{
		Object[][] data;
		log(sql);
		Connection connection = ((DataSource)ContextUtil.getBean("dataSourceQuery")).getConnection();
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		if(resultSet == null)
			return null;
		resultSet.last();
		data = new Object[resultSet.getRow()][title.length];
		resultSet.beforeFirst();
		int i,j = 0;
		while(resultSet.next()) {
			i = 0;
			while(i < title.length) {
				String temp = resultSet.getObject((i + 1))+"" ;
				if(i == 6){
					if("0".equals(temp)){
						data[j][i]="台式" ;
					}else if("1".equals(temp)){
						data[j][i]="移动" ;
					}
				}else if(i == 7){
					
					if("0".equals(temp)){
						data[j][i]="未完成" ;
					}else if("1".equals(temp)){
						data[j][i]="已删除" ;
					}else if("2".equals(temp)){
						data[j][i]="已完成";
					}else if("3".equals(temp)){
						data[j][i]="已失败";
					}
				}else{
					data[j][i] = resultSet.getObject((i + 1));
				}
				i++;
			}
			j++;
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
		return data;
	}
	
	
	/**
     * 生成数据
     * @param sql
     * @param title
     * @return
     * @throws SQLException
     * 2010-8-27下午02:46:11
     */
	public Object[][] process(String sql,String[] title,boolean flag)throws SQLException {
		Object[][] data;
		log(sql);
		Connection connection = ((DataSource)ContextUtil.getBean("dataSourceQuery")).getConnection();
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		if(resultSet == null)
			return null;
		resultSet.last();
		data = new Object[resultSet.getRow()][title.length];
		resultSet.beforeFirst();
		int i,j = 0;
		while(resultSet.next()) {
			i = 0;
			while(i < title.length) {
				if(i == 8){
					String temp = resultSet.getObject((i + 1))+"" ;
					if("0".equals(temp)){
						data[j][i]="扣率过高" ;
					}else if("1".equals(temp)){
						data[j][i]="房租到期" ;
					}else if("2".equals(temp)){
						data[j][i]="不再经营";
					}else if("3".equals(temp)){
						data[j][i]="银行冻结";
					}else if("4".equals(temp)){
						data[j][i]="银行撬单";
					}else if("5".equals(temp)){
						data[j][i]="其它("+resultSet.getObject((i + 2))+")";
					}
				}else{
					data[j][i] = resultSet.getObject((i + 1));
				}
				i++;
			}
			j++;
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
		return data;
	}
	
	
	
	/**
	 * 记录日志
	 * @param info
	 * 2010-8-27下午02:46:24
	 */
	private void log(String info) {
		Log.log(info);
	}
}
