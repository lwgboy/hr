<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String insertUrl = basePath + "pages/front/salary/SalaryServletFront/insert" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/salary_step2.js"></script>
		<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
	</head>
	<body>
		<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="4" class="title">雇员工资变更</td>
				</tr>
				<tr id="tr">
					<td width="25%">雇员编号：</td>
					<td width="25%">${emp.eid}</td>
					<td width="25%">姓名：</td>
					<td width="25%">${emp.ename}</td>
				</tr>
				<tr id="tr">
					<td width="25%">原工资等级：</td>
					<td width="25%">${emp.level.title}</td>
					<td width="25%">新工资等级：</td>
					<td width="25%">
						<select name="salary.newlid">
							<c:forEach items="${allLevels}" var="level">
								<option value="${level.lid}" ${emp.level.lid==level.lid ? "selected" : ""}>${level.title}（${level.losal}~${level.hisal}元）</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="tr">
					<td width="25%">原工资：</td>
					<td width="25%">${emp.sal}元</td>
					<td width="25%">新工资：</td>
					<td width="25%"><input type="text" id="salary.newsal" name="salary.newsal" value="${emp.sal}" class="init">元</td>
				</tr>
				<tr id="tr">
					<td colspan="2">原因：</td>
					<td colspan="2">备注：</td>
				</tr>
				<tr id="tr">
					<td colspan="2"><textarea class="ckeditor" id="editor1" name="salary.reason"></textarea></td>
					<td colspan="2"><textarea class="ckeditor" id="editor1" name="salary.note"></textarea></td>
				</tr>
				<tr id="tr">
					<td colspan="4">
						<input type="submit" value="确定">
						<input type="reset" value="重置">
						<input type="hidden" name="salary.employee.eid" value="${emp.eid}">
						<input type="hidden" name="salary.oldlid" value="${emp.level.lid}">
						<input type="hidden" name="salary.oldsal" value="${emp.sal}">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
