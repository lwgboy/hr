<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path + "/" ;
String dataUrl = "" ;
String indexUrl = basePath + "pages/back/index.jsp" ;
String logoutUrl = basePath + "AdminLogin/logout" ;
String changePasswordUrl = basePath + "pages/admin_change_password.jsp" ;
%>
<%
	if (request.getParameter("dataUrl") != null) {
		dataUrl = request.getParameter("dataUrl") ;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">   
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<style>
			.upDiv {height: 15% ; background: #F5DEB3 ;}
			.downDiv {height: 85% ;}
			.leftDiv {width: 15% ; background: #F5DEB3 ; float: left ;}
			.rightDiv {width: 85% ; float: right ;}
		</style>
	</head>
	<body>	
		<div class="upDiv">		
			<span class="title">人事管理系统</span>
			<div align="right">
				<span>最近登录时间：${admin.lastdate}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span>最近登录ip：${admin.ip}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span>当前账号：${admin.aid}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=logoutUrl%>" class="button">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<div class="downDiv">
			<div class="leftDiv">
				<span>公共操作</span>
				<ul>
					<li><a href="<%=changePasswordUrl%>">修改密码</a></li>
					<li><a href="<%=logoutUrl%>">登录注销</a></li>
				</ul>
				<c:if test="${admin!=null}" var="res">
					<c:forEach items="${admin.role.groups}" var="group">
						<span>${group.title}</span>
						<ul>
							<c:forEach items="${group.action}" var="action">
								<li><a href="<%=indexUrl%>?dataUrl=<%=basePath%>${action.url}" class="button">${action.title}</a></li>
							</c:forEach>
						</ul>
					</c:forEach>
				</c:if>
			</div>
			<div class="rightDiv">
				<iframe src="<%=dataUrl%>" width="100%" height="100%"></iframe>
			</div>
		</div>
	</body>	
</html>
