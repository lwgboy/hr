<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c" %>
<%
String path = request.getContextPath() ;
String basePath = request.getScheme() + "://" + 
					request.getServerName() + ":" + 
					request.getServerPort() + path ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/style.css">
		<script type="text/javascript" src="<%=basePath%>/js/tools.js"></script>
	</head>
	<body>
	</body>
	<script type="text/javascript">
		window.alert("${msg}") ;
		window.location = "<%=basePath%>${url}" ;
	</script>
</html>
