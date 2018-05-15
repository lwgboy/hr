<%@ page pageEncoding="UTF-8" %>
<%-- 
	<div id="splitBarsDiv">
		<jsp:include page="split_page_plugin_bars.jsp"></jsp:include>
	</div>
 --%>
 
<%	// 防止乱码
	request.setCharacterEncoding("UTF-8") ;
%>
<%
	String url = "" ;
	int currentPage = 1;
	int lineSize = 5 ;
	String column = "ename" ;
	String keyWord = "" ;
	int allRecorders = 0 ;
	int pageSize = 0 ;
	int lsData [] = new int [] {1, 5, 10, 15, 20, 30, 50, 100} ;
	int feed = 2 ;	// 种子数
	String paramName = null ;	// 参数名称
	String paramValue = null ;	// 参数值
%>
<%	// 接收传递的参数
	try {
		currentPage = (Integer) request.getAttribute("currentPage") ;
	} catch (Exception e) {}
	try {
		allRecorders = (Integer) request.getAttribute("allRecorders") ;
	} catch(Exception e) {}
	try {
		lineSize = (Integer) request.getAttribute("lineSize") ;
	} catch (Exception e) {}
	column = (String) request.getAttribute("column") ;
	keyWord = (String) request.getAttribute("keyWord") ;
	url = (String) request.getAttribute("url") ;
	if (request.getAttribute("paramName") != null) {
		paramName = (String) request.getAttribute("paramName") ;
	}
	if (request.getAttribute("paramValue") != null) {
		paramValue = request.getAttribute("paramValue").toString() ;
	}
%>
<%
	if (allRecorders > 0) {
		pageSize = (allRecorders+lineSize-1) / lineSize ;
	} else {
		pageSize =1 ;
	}
%>

<script type="text/javascript">
	function goSplit(cp) {	// 根据外部传递的currentPage进行操作
		var elels = document.getElementById("lsSel").value ;
		try {
			var eleKw = document.getElementById("kw").value ;
			var eleCol = document.getElementById("colSel").value ;
			window.location = "<%=url%>?cp=" + cp + "&ls=" + elels + "&kw=" + eleKw + "&col=" + eleCol + "&<%=paramName%>=<%=paramValue%>" ; 
		} catch (Exception) {
			window.location = "<%=url%>?cp=" + cp + "&ls=" + elels + "&<%=paramName%>=<%=paramValue%>" ;
		}
	}
</script>

<div>
	<input type="button" value="首页" onclick="goSplit(1)" <%=currentPage==1 ? "disabled" : ""%>>
	<input type="button" value="上一页" onclick="goSplit(<%=currentPage-1%>)" <%=currentPage==1 ? "disabled" : ""%>>
	<input type="button" value="下一页" onclick="goSplit(<%=currentPage+1%>)" <%=currentPage==pageSize ? "disabled" : ""%>>
	<input type="button" value="尾页" onclick="goSplit(<%=pageSize%>)" <%=currentPage==pageSize ? "disabled" : ""%>>

	&nbsp;&nbsp;
	<span onclick="goSplit(1)" <%=currentPage==1 ? "style='color:#FF0000'" : ""%>>1</span>&nbsp;&nbsp;
<%
	if (pageSize > 1) {
		if (currentPage > 2+feed) {
%>
			<span>...</span>
<%
		}
		for (int x=currentPage-feed ; x<=currentPage+feed ; ++x) {
			if (x < 2) {
				continue ;
			}
			if (x >= pageSize) {
				break ;
			}
%>
			<a onclick="goSplit(<%=x%>)" <%=currentPage==x ? "style='color:#FF0000'" : ""%>><%=x%></a>&nbsp;&nbsp;
<%
		}
		if (currentPage < pageSize-feed-1) {
%>
			<span>...</span>
<%
		}
%>
		<span onclick="goSplit(<%=pageSize%>)" <%=currentPage==pageSize ? "style='color:#FF0000'" : ""%>><%=pageSize%></span>&nbsp;&nbsp;
<%
	}
%>
	跳转到：<select id="cpSel" onchange="goSplit(this.value)">
	<%
		for (int x=1 ; x<=pageSize ; ++x) {
	%>
			<option value=<%=x%> <%=currentPage==x ? "selected" : ""%>><%=x%></option>
	<%
		}
	%>
	</select>&nbsp;&nbsp;
	每页显示：<select id="lsSel" onchange="goSplit(1)">
	<%
		for (int x=0 ; x<lsData.length ; ++x) {
	%>
			<option value="<%=lsData[x]%>" <%=lsData[x]==lineSize ? "selected" : ""%>><%=lsData[x]%></option>
	<%
		}
	%>
	</select>行记录
</div>