<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + 
					request.getServerName() + ":" + 
					request.getServerPort() + path + "/" ;
String dataUrl = "" ;
String indexUrl = basePath + "pages/front/index.jsp" ;
String logoutUrl = basePath + "AdminLogin/logout" ;
String changePasswordUrl = basePath + "pages/admin_change_password.jsp" ;
String empListUrl = basePath + "pages/front/emp/EmployeeServletFront/list" ;
String empStatusInListUrl = basePath + "pages/front/emp/EmployeeServletFront/listStatus?status=1" ;
String empStatusOutListUrl = basePath + "pages/front/emp/EmployeeServletFront/listStatus?status=0" ;
String empInsertUrl = basePath + "pages/front/emp/EmployeeServletFront/insertPre" ;
String workUpdateUrl = basePath + "pages/front/work/WorkServletFront/selectPre" ;
String salaryUpdateUrl = basePath + "pages/front/salary/SalaryServletFront/selectPre" ;

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
			<span>设置</span>
			<ul>
				<li>全局设置</li>
				<li><a href="<%=changePasswordUrl%>" class="button">安全设置</a></li>
			</ul>
			<span>员工管理</span>
			<ul>
				<li><a href="<%=indexUrl%>?dataUrl=<%=empInsertUrl%>" class="button">员工入职</a></li>
				<li><a href="<%=indexUrl%>?dataUrl=<%=empListUrl%>" class="button">员工列表</a></li>
				<li><a href="<%=indexUrl%>?dataUrl=<%=empStatusInListUrl%>" class="button">在职员工列表</a></li>
				<li><a href="<%=indexUrl%>?dataUrl=<%=empStatusOutListUrl%>" class="button">离职员工列表</a></li>
				<li><a href="<%=indexUrl%>?dataUrl=<%=workUpdateUrl%>" class="button">职位变更</a></li>
				<li><a href="<%=indexUrl%>?dataUrl=<%=salaryUpdateUrl%>" class="button">待遇变更</a></li>
			</ul>
			<span>员工培训</span>
			<ul>
				<li>培训管理</li>
				<li>发布课程</li>
				<li>课程管理</li>
			</ul>
			</div>
			<div class="rightDiv">
				<iframe src="<%=dataUrl%>" width="100%" height="100%"></iframe>
			</div>
		</div>
	</body>	
</html>
