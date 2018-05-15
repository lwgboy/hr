<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String updateDoUrl = basePath + "pages/back/admin/dept/DeptServletBack/update" ;
String deleteDoUrl = basePath + "pages/back/admin/dept/DeptServletBack/delete?p=p" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/dept_list.js"></script>
	</head>
	<body>
		<form action="<%=updateDoUrl%>" method="post">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="4" class="title">部门列表</td>
				</tr>
				<tr id="tr">
					<td width="10%"><input type="checkbox" id="selall"></td>
					<td width="30%"><b>部门编号</b></td>
					<td width="30%"><b>部门名称</b></td>
					<td width="30%"><b>部门人数</b></td>
				</tr>
				<c:forEach items="${allDepts}" var="dept">
					<tr id="tr">
						<td><input type="checkbox" id="did" value="${dept.did}"></td>
						<td>${dept.did}</td>
						<td><input type="text" name="${dept.did}" value="${dept.dname}"></td>
						<td>${dept.current}</td>
					</tr>
				</c:forEach>
				<tr id="tr">
					<td colspan="4">
						<input type="submit" value="确认修改">
						<input type="button" value="删除选中部门" onclick="deleteAll('<%=deleteDoUrl%>','dids','did')">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
