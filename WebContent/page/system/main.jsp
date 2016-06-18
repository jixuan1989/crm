<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="com.huateng.common.Operator"%>

<%@ include file="/page/system/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
var treeMenuPanel = '${treeMenuPanel}';
//var toolBarStr = Ext.decode('${tool_bar_str}',false);
operator[OPR_ID] = '${operator.oprId}';
operator[OPR_NAME] = '${operator.oprName}';
operator[OPR_BRH_NAME] = '${operator.oprBrhName}'; 
var lastTime = '${OPERATOR_INFO}';
</script>

<script type="text/javascript" src="<%= request.getContextPath()%>/ext-4.2/custom/toolbar.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/ui/mainApp.js"></script>

<title>成都支付通一体化收单平台</title>
<style type="text/css">
        #tab-id .x-tab-bar-body
        {
            height: 40px !important;
        }
        
        #tab-id .x-tab-bar-strip
        {
            top: 38px !important;
        }
    </style>
<link rel="Shortcut Icon" href="<%= request.getContextPath()%>/ext-4.2/resources/images/favicon.ico" type="image/x-icon" />
<style>
	#load-mask {
		position:absolute;
		left:0;
        top:0;
        width:100%;
        height:100%;
        z-index:20000;
        background-color:white;
	}
	#load {
		position:absolute;
		left:45%;
        top:45%;
        z-index:20001;
	}
	#load-text {
        position:absolute;
        z-index:20001;
        width: 200px;
        vertical-align: middle;
        text-align: center;
        left:42%;
        top:70%;
	}
	.key {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/key_16.png) !important;
	}
	.lock {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/lock_16.png) !important;
	}
	.quit {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/quit_16.png) !important;
	}
	.bgimage {
 		background:url(<%= request.getContextPath()%>/ext-4.2/resources/images/inde.png);
 	    -moz-background-size:100% 100%;
	  	background-size:100% 100%;
 	    background-position: center; 
 	} 
 	.insave {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/industry_save.png) !important;
	}
	.inadd {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/industry_add.png) !important;
	}
	.indelete {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/industry_delete.png) !important;
	}
	.home {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/house.png) !important;
	}
	.rece {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/table_auto.gif) !important;
	}
	.auto {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/table_edit.gif) !important;
	}
	.mchnt {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/mchnt_grp_16.png) !important;
	}
	.accept {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/accept_16.png) !important;
	}
	.back {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/back_16.png) !important;
	}
	.refuse {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/refuse_16.png) !important;
	}
	.detail {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/detail_16.png) !important;
	}
	.upload {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/upload_16.png) !important;
	}
	.note {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/notepad_edit.gif) !important;
	}
	.plug {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/plugin_go.png) !important;
	}
	.machine {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/app_home.png) !important;
	}
	.machine_add {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/app_add.png) !important;
	}
	.machine_delete {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/app_delete.png) !important;
	}
	.vcard {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/law.png) !important;
	}
	.sett {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/accept_16.png) !important;
	}
	.upload-icon {
            background: url(<%= request.getContextPath()%>/ext-4.2/resources/images/image_add.png) no-repeat 0 0 !important;
        }
    .btn_2k3 {
	 	BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #002D96 1px solid
	}
</style>

<script type="text/javascript">
	Ext.Ajax.on('requestexception',function(conn, response,options, eOpts){
		if(response.status == 408){
			var winLogin = Ext.create('zft.view.system.winLogin');
			
			winLogin.down('button[action=reset]').on('click',function(){
				winLogin.down('form').getForm().reset();
			});
			
			winLogin.down('button[action=submit]').on('click',function(){
				var form = winLogin.down('form').getForm();
				if(form.isValid()) {
					form.submit({
						url: 'login.asp',
						waitMsg : '登录中，请稍候......',
						success: function(form, action) {
							winLogin.close();
							showSuccessMsg('登陆成功', form);
						},
						failure: function(form, action) {
							showErrorMsg(action.result.msg, form);
						}
					});
				}
			});
		}
	}); 
</script>
<script type="text/javascript">
	var txnCode="";
	<%
	Operator operator = (Operator)request.getSession().getAttribute("operator");
	%>
	var brhId = "<%= operator.getOprBrhId() %>";
	var cupBrhId = "<%= request.getSession().getAttribute("cupBrhId") %>";
	var imagesId;
	var opr = "<%= operator.getOprId()%>";
	function resetImagesId(){
		var date = new Date();
		var full = date.getFullYear().toString() 
			+ date.getMonth().toString() 
			+ date.getDay().toString() 
			+ date.getHours().toString() 
			+ date.getMinutes().toString() 
			+ date.getSeconds().toString() 
			+ date.getUTCMilliseconds();
		imagesId = opr + full;
	}
	resetImagesId();
</script>
</head>
<body>

</body>
</html>