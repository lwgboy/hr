<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String insertUrl = basePath + "pages/front/work/WorkServletFront/insert" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/work_step2.js"></script>
		<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
	</head>
	<body>
		<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="4" class="title">雇员职位变更</td>
				</tr>
				<tr id="tr">
					<td width="25%">雇员编号：</td>
					<td width="25%">${emp.eid}</td>
					<td width="25%">姓名：</td>
					<td width="25%">${emp.ename}</td>
				</tr>
				<tr id="tr">
					<td width="25%">原部门：</td>
					<td width="25%">${emp.dname}</td>
					<td width="25%">新部门：</td>
					<td width="25%">
						<select name="work.newdid">
							<c:forEach items="${allDepts}" var="dept">
								<option value="${dept.did}" ${emp.dept.did==dept.did ? "selected" : ""}>${dept.dname}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="tr">
					<td width="25%">原职位：</td>
					<td width="25%">${emp.job}</td>
					<td width="25%">新职位：</td>
					<td width="25%">
						<select name="work.newjid">
							<c:forEach items="${allJobs}" var="jobs">
								<option value="${jobs.jid}" ${emp.jobs.jid==jobs.jid ? "selected" : ""}>${jobs.title}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="tr">
					<td colspan="2">原因：</td>
					<td colspan="2">备注：</td>
				</tr>
				<tr id="tr">
					<td colspan="2"><textarea class="ckeditor" id="editor1" name="work.reason"></textarea></td>
					<td colspan="2"><textarea class="ckeditor" id="editor1" name="work.note"></textarea></td>
				</tr>
				<tr id="tr">
					<td colspan="4">
						<input type="submit" value="确定">
						<input type="reset" value="重置">
						<input type="hidden" name="work.employee.eid" value="${emp.eid}">
						<input type="hidden" name="work.olddid" value="${emp.dept.did}">
						<input type="hidden" name="work.olddname" value="${emp.dname}">
						<input type="hidden" name="work.oldjid" value="${emp.jobs.jid}">
						<input type="hidden" name="work.oldjob" value="${emp.job}">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
