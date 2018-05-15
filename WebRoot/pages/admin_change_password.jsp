<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String changePasswordDoUrl = basePath + "AdminLogin/changePassword" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/admin_change_password.js"></script>
	</head>
	<body>
		<form action="<%=changePasswordDoUrl%>" method="post" onsubmit="return validateChangePassword()">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="3" class="title">修改密码</td>
				</tr>
				<tr id="tr">
					<td width="20%">原始密码：</td>
					<td width="40%"><input type="password" id="oldPassword" name="oldPassword" class="init"></td>
					<td width="40%"><span id="oldPasswordMsg"></span>
				</tr>
				<tr id="tr">
					<td>新密码：</td>
					<td><input type="password" id="newPassword1" name="newPassword1" class="init"></td>
					<td><span id="newPassword1Msg"></span>
				</tr>
				<tr id="tr">
					<td>再次输入：</td>
					<td><input type="password" id="newPassword2" name="newPassword2" class="init"></td>
					<td><span id="newPassword2Msg"></span>
				</tr>
				<tr id="tr">
					<td colspan="3">
						<input type="submit" value="提交">
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
