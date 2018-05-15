<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String insertDoUrl = basePath + "pages/back/admin/dept/DeptServletBack/insert" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/dept_insert.js"></script>
	</head>
	<body>
		<form action="<%=insertDoUrl%>" method="post" onsubmit="return validateInsert()">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="3" class="title">增加部门</td>
				</tr>
				<tr id="tr">
					<td width="20%"><b>部门名称：</b></td>
					<td width="40%"><input type="text" name="dept.dname" id="dept.dname"></td>
					<td width="40%"><span id="dept.dnameMsg"></span></td>
				</tr>
				<tr id="tr">
					<td colspan="3">
						<input type="submit" value="增加">
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
