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
		<title>微商城</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/style.css">
		<script type="text/javascript" src="<%=basePath%>/js/tools.js"></script>
	</head>
	<body>
		<h1>对不起，程序出现错误，请于管理员联系</h1>
		<c:forEach items="${errors}" var="error">
			<ul>
				<li><h2>${error}</h2></li>
			</ul>
		</c:forEach>
		<br>
		<h1><a href="<%=basePath%>">返回首页</a></h1>
	</body>
</html>
