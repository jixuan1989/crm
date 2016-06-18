<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.huateng.common.Operator"%>
<%@ page import="com.huateng.common.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate,text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/ext-4.2/resources/css/ext-all-neptune.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/ext-all.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/ext-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/ext-4.2/resources/css/frame.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/ext-4.2/resources/css/icon.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/ext-4.2/resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/ext-4.2/resources/css/UploadDialog.css" />


<script type="text/javascript" src="<%= request.getContextPath()%>/js/common/common.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/examples.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/examples/ux/RowExpander.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/upload/swfupload.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/ext-4.2/examples.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/ext-4.2/resources/css/BoxSelect.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/custom/systemSuport.js"></script> 
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/custom/BoxSelect.js"></script> 
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/custom/soundmanager2.js"></script> 


<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/custom/system.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/custom/systemSuport.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/custom/toolbar.js"></script>


<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/SelectOptionsDWR.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/ChartUtilDWR.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/RemoteTransDWR.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/WarningDwr.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/MenuDwr.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T100100.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T30101.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T20101.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T20100.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T100302.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T10401.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T40202.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/HandleOfBatch.js"></script> 
<%--<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/AuthoriseDwr.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/dwr/interface/T40202.js"></script>
--%>

</head>
<body>
<script type="text/javascript">
Ext.contextPath = '<%= request.getContextPath()%>';
<%-- System[QUERY_RECORD_COUNT] = <%= Constants.QUERY_RECORD_COUNT%>;
System[QUERY_SELECT_COUNT] = <%= Constants.QUERY_SELECT_COUNT%>; --%>
Ext.BLANK_IMAGE_URL = Ext.contextPath + '/ext-4.2/resources/images/default/s.gif';
Ext.chart.Chart.CHART_URL = Ext.contextPath + '/ext-4.2/resources/charts.swf';
var sessionId = "<%= request.getSession().getId() %>";
<% 
	Operator opr = (Operator)request.getSession().getAttribute("operator");
	if (null != opr) {
	%>
		var BRHID = "<%= opr.getOprBrhId() %>";
	<%
	}
%>
	javascript:window.history.forward(1);
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String today = sdf.format(new Date());
	String yesterday = sdf.format(sdf.parse(String.valueOf(Integer.parseInt(today) - 1)));

//银联编号
	String cupBrhId = (String)request.getSession().getAttribute("CUP_BRH_ID");
%>

	var TORDAY = "<%= today %>";
	var YESTERDAY = "<%= yesterday %>";

	var CUPBRHID = "<%= cupBrhId %>";
</script>
</body>
</html>