<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/salary_list.js"></script>
	</head>
	<body>
		<span class="title">职位修改记录</span>
		<div id="splitSearchDiv">
			<jsp:include page="../../../plugin/split_page_plugin_search.jsp"></jsp:include>
		</div>
		<c:forEach items="${allSalarys}" var="salary">
			<br>
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td width="15%"><b>编号：</b></td>
					<td width="10%">${salary.sid}</td>
					<td width="15%"><b>被操作雇员编号：</b></td>
					<td width="10%">${salary.employee.eid}</td>
					<td width="15%"><b>操作管理员编号：</b></td>
					<td width="10%">${salary.admin.aid}</td>
					<td width="15%"><b>操作时间：</b></td>
					<td width="10%">${salary.cdate}</td>
				</tr>
				<tr id="tr">
					<td><b>更改工资等级：</b></td>
					<td>${salary.oldlid}</td>
					<td><b>更改后工资等级：</b></td>
					<td>${salary.newlid}</td>
					<td><b>更改前工资(元)：</b></td>
					<td>${salary.oldsal}</td>
					<td><b>更改后工资(元)：</b></td>
					<td>${salary.newsal}</td>
				</tr>
				<tr id="tr">
					<td colspan="4"><b>更改原因：</b></td>
					<td colspan="4"><b>备注：</b></td>
				</tr>
				<tr id="tr">
					<td colspan="4">${salary.reason}</td>
					<td colspan="4">${salary.note}</td>
				</tr>
			</table>
			<br>
		</c:forEach>
		<div id="splitBarsDiv">
			<jsp:include page="../../../plugin/split_page_plugin_bars.jsp"></jsp:include>
		</div>
	</body>
</html>
