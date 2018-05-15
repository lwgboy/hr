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
		<script type="text/javascript" src="js/emp_list.js"></script>
	</head>
	<body>
		<br>
		<div id="splitSearchDiv">
			<jsp:include page="../../../plugin/split_page_plugin_search.jsp"></jsp:include>
		</div>
		<br>
		<div>
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="11" class="title">雇员列表</td>
				</tr>
				<tr id="tr">
					<td width="10%"><input type="checkbox" id="selall"></td>
					<td width="10%"><b>雇员编号</b></td>
					<td width="10%"><b>雇员姓名</b></td>
					<td width="10%"><b>性别</b></td>
					<td width="10%"><b>学历</b></td>
					<td width="10%"><b>部门</b></td>
					<td width="10%"><b>职位</b></td>
					<td width="10%"><b>工资</b></td>
					<td width="10%"><b>入职时间</b></td>
					<td width="10%"><b>状态</b></td>
				</tr>
				<c:forEach items="${allEmps}" var="emp">
					<tr id="tr">
						<td><input type="checkbox" value="${emp.eid}" id="eid" name="eid"></td>
						<td>${emp.eid}</td>
						<td>${emp.ename}</td>
						<td>${emp.sex}</td>
						<td>${emp.edu}</td>
						<td>${emp.dname}</td>
						<td>${emp.job}</td>
						<td>${emp.sal}</td>
						<td>${emp.indate}</td>
						<td>${emp.status==1 ? "在职" : "离职"}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<div id="splitBarsDiv">
			<jsp:include page="../../../plugin/split_page_plugin_bars.jsp"></jsp:include>
		</div>
	</body>
</html>
