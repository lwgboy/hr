<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String randUrl = basePath + "pages/plugin/codeImage.jsp" ;
String loginUrl = basePath + "AdminLogin/login" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
	</head>
	<body>
		<form action="<%=loginUrl%>" method="post" onsubmit="return validateLogin()">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="3" class="title">管理员登录</td>
				</tr>
				<tr id="tr">
					<td width="20%"><b>账号：</b></td>
					<td width="40%"><input type="text" id="admin.aid" name="admin.aid" class="init"></td>
					<td width="40%"><span id="admin.aidMsg"></span></td>
				</tr>
				<tr id="tr">
					<td><b>密码：</b></td>
					<td><input type="password" id="admin.password" name="admin.password" class="init"></td>
					<td><span id="admin.passwordMsg"></span></td>
				</tr>
				<tr>
					<td><b>验证码：</b></td>
					<td>
						<input type="text" id="code" name="code" maxlength="4" class="init">
						<img src="<%=randUrl%>" id="codeImage">
						<a id="changeCode" class="buttonA"><font size="1px" color="#0000FF">看不清，换一张</font></a>
					</td>
					<td><span id="codeMsg"></span></td>
				</tr>
				<tr id="tr">
					<td colspan="3">
						<input type="submit" value="登录">
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
