<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String selectUrl = basePath + "pages/front/salary/SalaryServletFront/select" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/salary_step1.js"></script>
	</head>
	<body>
		<form action="<%=selectUrl%>" method="post" onsubmit="return validateSelect()">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="3" class="title">雇员工资变更</td>
				</tr>
				<tr id="tr">
					<td width="30%">要变更的雇员编号：</td>
					<td width="35%"><input type="text" id="salary.employee.eid" name="salary.employee.eid" class="init"></td>
					<td width="35%"><span id="salary.employee.eidMsg"></span></td>
				</tr>
				<tr id="tr">
					<td colspan="3">
						<input type="submit" value="查询">
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
