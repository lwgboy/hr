<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.baidu.com/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" 
					+ request.getServerName() + ":" 
					+ request.getServerPort() + path+ "/" ;
String insertUrl = basePath + "pages/front/emp/EmployeeServletFront/insert" ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人事管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript" src="js/emp_insert.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
	</head>
	<body>
		<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2">
				<tr id="tr">
					<td colspan="6" class="title">雇员增加</td>
				</tr>
				<tr id="tr">
					<td width="12%"><b>编号：</b></td>
					<td width="23%"><input type="text" id="emp.eid" name="emp.eid" class="init"></td>
					<td width="20%"><span id="emp.eidMsg"></span></td>
					<td width="10%"><b>雇员照片：</b></td>
					<td width="20%"><input type="file" name="emp.photo" id="emp.photo" class="init"></td>
					<td width="15%" rowspan="7"><div id="preview"></div></td>
				</tr>
				<tr id="tr">
					<td width="10%"><b>姓名：</b></td>
					<td width="20%"><input type="text" id="emp.ename" name="emp.ename" class="init"></td>
					<td width="20%"><span id="emp.enameMsg"></span></td>
					<td width="10%"><b>性别：</b></td>
					<td width="20%">
						<input type="radio" id="emp.sex" name="emp.sex" value="男" checked>男
						<input type="radio" id="emp.sex" name="emp.sex" value="女">女
					</td>
				</tr>
				<tr id="tr">
					<td><b>身份证号：</b></td>
					<td><input type="text" id="emp.idcard" name="emp.idcard" maxlength="18" class="init"></td>
					<td><span id="emp.idcardMsg"></span></td>
					<td><b>出生日期：</b></td>
					<td><input type="text" id="emp.birthday" name="emp.birthday" class="Wdate" 
								onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr id="tr">
					<td><b>毕业院校：</b></td>
					<td><input type="text" id="emp.school" name="emp.school" class="init"></td>
					<td><span id="emp.schoolMsg"></span></td>
					<td><b>学历：</b></td>
					<td>
						<select id="emp.edu" name="emp.edu">
							<option>专科以下</option>
							<option>专科</option>
							<option selected>本科</option>
							<option>硕士</option>
							<option>博士</option>
						</select>
					</td>
				</tr>
				<tr id="tr">
					<td><b>所学专业：</b></td>
					<td><input type="text" id="emp.profession" name="emp.profession" class="init"></td>
					<td><span id="emp.professionMsg"></span></td>
					<td><b>毕业日期：</b></td>
					<td><input type="text" id="emp.grad" name="emp.grad" class="Wdate" 
								onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr id="tr">
					<td><b>每月工资：</b></td>
					<td><input type="text" id="emp.sal" name="emp.sal" class="init">元</td>
					<td><span id="emp.salMsg"></span></td>
					<td><b>工资级别：</b></td>
					<td>
						<select id="emp.level.lid" name="emp.level.lid">
							<c:forEach items="${allLevels}" var="level">
								<option value="${level.lid}">${level.title}（${level.losal}~${level.hisal}元）</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="tr">
					<td><b>部门/职位：</b></td>
					<td>
						<select id="emp.dept.did" name="emp.dept.did">
							<c:forEach items="${allDepts}" var="dept">
								<option value="${dept.did}">${dept.dname}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;
						<select id="emp.jobs.jid" name="emp.jobs.jid">
							<c:forEach items="${allJobs}" var="jobs">
								<option value="${jobs.jid}">${jobs.title}</option>
							</c:forEach>
						</select>
					</td>
					<td><span id="emp.jobs.jidMsg"></span></td>
					<td><b>是否在职：</b></td>
					<td>
						<input type="radio" id="emp.status" name="emp.status" value="1" checked>在职
						<input type="radio" id="emp.status" name="emp.status" value="0">离职
					</td>
				</tr>
				<tr id="tr">
					<td colspan="6">雇员简介</td>
				</tr>
				<tr id="tr">
					<td colspan="6"><textarea class="ckeditor" id="editor1" name="emp.note"></textarea></td>
				</tr>
				<tr id="tr">
					<td colspan="6">
						<input type="submit" value="增加">
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
