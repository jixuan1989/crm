<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/system/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>成都支付通一体化收单平台</title>
<link rel="Shortcut Icon" href="<%= request.getContextPath()%>/ext-4.2/resources/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%= request.getContextPath()%>/js/system/login.js"></script>
<style type="text/css">

.button, .button:visited {
	<%-- background: #222 url(<%= request.getContextPath()%>/ext-4.2/resources/images/blue.png) repeat-x; --%>
	display: inline-block;
	padding: 5px 10px 2px;
	color: #fff;
	text-decoration: none;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-box-shadow: 0 1px 3px rgba(0,0,0,0.6);
	-webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.6);
	text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
	border-bottom: 1px solid rgba(0,0,0,0.25);
	position: relative;
	cursor: pointer
}
	body{    
      background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/loginbg.png);   
      background-position: center;
      background-repeat: no-repeat;
	  background-attachment: fixed;
	  filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
   	 -moz-background-size:100% 100%;
	  background-size:100% 100%;
	  background-color:#b4b8bc;
 	}  
	.key {
		background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/key_16.png) !important;
	}
	.login {
		 background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/user_login.png) !important;
	     background-repeat:no-repeat;   
	     background-position:right; 
	}
	.passwd {
		 background-image: url(<%= request.getContextPath()%>/ext-4.2/resources/images/lock.gif) !important;
	     background-repeat:no-repeat;   
	     background-position:right; 
	}
</style>
</head>
<body style="background:url(<%= request.getContextPath()%>/ext-4.2/resources/images/loginbg.png) no-repeat;background-position: center;background-repeat: no-repeat;background-attachment: fixed;-moz-background-size:100% 100%;background-size:100% 100%;">
<!-- <div id="top_div" style= "position: absolute; left:37%; Top:40%; margin-top: -68px; z-index: 1; margin-left: -150px; width: 800px;">
	<span style="FILTER: shadow(color=#133984); WIDTH: 100%; COLOR: #133984; LINE-HEIGHT: 130%; FONT-FAMILY: 华文行楷; font-size:45pt;">成都支付通收单管理系统</span>
</div> -->
<div id="login_div" style= "position: absolute; left:50%; Top:60%; margin-top: -68px; z-index: 1; margin-left: -150px; width: 300px;">

</div>
<div id="bottom_div" style= "position: absolute; left:35%; Top:105%; margin-top: -68px; z-index: 1; margin-left: -150px; width: 800px;">
	All rights reserved©2011.Chengdu PayExpress New Information Technology Services Ltd.Co 成都支付通新信息技术服务有限公司
</div>
</body>
</html>