<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String updateUrl = basePath + "pages/back/admin/jobs/JobsServletBack/updatePre" ;
String deleteDoUrl = basePath + "pages/back/admin/jobs/JobsServletBack/delete?p=p" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/jobs_list.js"></script>
	</head>
	<body>
		<form action="<%=updateUrl%>" method="post">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="5" class="title">职位列表</td>
				</tr>
				<tr id="tr">
					<td width="10%"><input type="checkbox" id="selall"></td>
					<td width="15%"><b>职位编号</b></td>
					<td width="15%"><b>职位名称</b></td>
					<td width="50%"><b>详细信息</b></td>
					<td width="10%"><b>操作</b></td>
				</tr>
				<c:forEach items="${allJobs}" var="jobs">
					<tr id="tr">
						<td><input type="checkbox" id="jid" value="${jobs.jid}"></td>
						<td>${jobs.jid}</td>
						<td>${jobs.title}</td>
						<td>${jobs.note}</td>
						<td><a href="<%=updateUrl%>?jid=${jobs.jid}" class="button">修改</a></td>
					</tr>
				</c:forEach>
				<tr id="tr">
					<td colspan="5">
						<input type="button" value="删除选中职位" onclick="deleteAll('<%=deleteDoUrl%>','jids','jid')">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
