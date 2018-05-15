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
		<script type="text/javascript" src="js/work_list.js"></script>
	</head>
	<body>
		<span class="title">职位修改记录</span>
		<div id="splitSearchDiv">
			<jsp:include page="../../../plugin/split_page_plugin_search.jsp"></jsp:include>
		</div>
		<c:forEach items="${allWorks}" var="work">
			<br>
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td width="15%"><b>编号：</b></td>
					<td width="10%">${work.wid}</td>
					<td width="15%"><b>被操作雇员编号：</b></td>
					<td width="10%">${work.employee.eid}</td>
					<td width="15%"><b>操作管理员编号：</b></td>
					<td width="10%">${work.admin.aid}</td>
					<td width="15%"><b>操作时间：</b></td>
					<td width="10%">${work.cdate}</td>
				</tr>
				<tr id="tr">
					<td><b>更改前部门编号：</b></td>
					<td>${work.olddid}</td>
					<td><b>更改前部门名称：</b></td>
					<td>${work.olddname}</td>
					<td><b>更改前级别编号：</b></td>
					<td>${work.oldjid}</td>
					<td><b>更改前级别名称：</b></td>
					<td>${work.oldjob}</td>
				</tr>
				<tr id="tr">
					<td><b>更改后部门编号：</b></td>
					<td>${work.newdid}</td>
					<td><b>更改后部门名称：</b></td>
					<td>${work.newdname}</td>
					<td><b>更改后级别编号：</b></td>
					<td>${work.newjid}</td>
					<td><b>更改后级别名称：</b></td>
					<td>${work.newjob}</td>
				</tr>
				<tr id="tr">
					<td colspan="4"><b>更改原因：</b></td>
					<td colspan="4"><b>备注：</b></td>
				</tr>
				<tr id="tr">
					<td colspan="4">${work.reason}</td>
					<td colspan="4">${work.note}</td>
				</tr>
			</table>
			<br>
		</c:forEach>
		<div id="splitBarsDiv">
			<jsp:include page="../../../plugin/split_page_plugin_bars.jsp"></jsp:include>
		</div>
	</body>
</html>
