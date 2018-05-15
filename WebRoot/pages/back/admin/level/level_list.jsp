<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String updateDoUrl = basePath + "pages/back/admin/level/LevelServletBack/update?" ;
String deleteDoUrl = basePath + "pages/back/admin/level/LevelServletBack/delete?p=p" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/level_list.js"></script>
	</head>
	<body>
		<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
			<tr id="tr">
				<td colspan="6" class="title">级别列表</td>
			</tr>
			<tr id="tr">
				<td width="10%"><input type="checkbox" id="selall"></td>
				<td width="20%"><b>级别编号</b></td>
				<td width="20%"><b>级别名称</b></td>
				<td width="20%"><b>最低工资（元）</b></td>
				<td width="20%"><b>最高工资（元）</b></td>
				<td width="10%"><b>操作</b></td>
			</tr>
			<c:forEach items="${allLevels}" var="level">
				<tr id="tr">
					<td><input type="checkbox" id="lid" value="${level.lid}"></td>
					<td>${level.lid}</td>
					<td><input type="text" name="title-${level.lid}" id="title-${level.lid}" value="${level.title}"></td>
					<td><input type="text" name="losal-${level.lid}" id="losal-${level.lid}" value="${level.losal}"></td>
					<td><input type="text" name="hisal-${level.lid}" id="hisal-${level.lid}" value="${level.hisal}"></td>
					<td><input type="button" value="修改" onclick="update('${level.lid}')"></td>
				</tr>
			</c:forEach>
			<tr id="tr">
				<td colspan="6">
					<input type="button" value="删除选中级别" onclick="deleteAll('<%=deleteDoUrl%>','lids','lid')">
				</td>
			</tr>
		</table>
		<form action="<%=updateDoUrl%>" method="post" id="levelForm">
			<input type="hidden" name="level.lid" id="level.lid">
			<input type="hidden" name="level.title" id="level.title">
			<input type="hidden" name="level.losal" id="level.losal">
			<input type="hidden" name="level.hisal" id="level.hisal">
		</form>
	</body>
</html>
