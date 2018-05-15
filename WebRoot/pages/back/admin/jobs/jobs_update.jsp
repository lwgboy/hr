<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String updateDoUrl = basePath + "pages/back/admin/jobs/JobsServletBack/update" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/jobs_update.js"></script>
		<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
	</head>
	<body>
		<form action="<%=updateDoUrl%>" method="post" onsubmit="return validateUpdate()">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="3" class="title">修改职位</td>
				</tr>
				<tr id="tr">
					<td width="20%"><b>职位名称：</b></td>
					<td width="40%"><input type="text" name="jobs.title" id="jobs.title" value="${jobs.title}"></td>
					<td width="40%"><span id="jobs.titleMsg"></span></td>
				</tr>
				<tr id="tr">
					<td colspan="3"><b>详细信息：</b></td>
				</tr>
				<tr>
					<td colspan="3"><textarea class="ckeditor" id="editor1" name="jobs.note">${jobs.note}</textarea></td>
				</tr>
				<tr id="tr">
					<td colspan="3">
						<input type="submit" value="确定修改">
						<input type="reset" value="重置">
						<input type="text" value="${jobs.jid}" name="jobs.jid">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
