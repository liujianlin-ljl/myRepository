<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息</title>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
window.onload=function(){
	var msg="<%=session.getAttribute("msg")%>";
	if(msg!="null"){
		alert(msg);
		<%session.removeAttribute("msg");%>
	}
}
</script>
</head>
<body>
	<table width="600" align="center" border="1" cellspacing="1" cellpadding="5">
		<caption>
			<h2>学生信息</h2>
		</caption>
		<thead>
			<tr align="center">
				<th>序号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>班级</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty stuList }">
				<c:forEach items="${stuList }" var="student" varStatus="s">
					<tr align="center">
						<td>${s.count }</td>
						<td>${student.s_no }</td>
						<td>${student.s_name }</td>
						<td>${student.s_sex }</td>
						<td>${student.s_age }</td>
						<td>${student.classes.c_name }</td>
						<td>
							<span><a href="student?action=del&id=${student.s_no }" onclick="return confirm('确定删除该学生吗？');">删除</a></span>&nbsp;&nbsp;
							<span><a href="student?action=toUpdate&id=${student.s_no }">修改</a></span>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty stuList }">
				<tr>
					<td colspan="6">暂无学生数据</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<table align="center" width="600">
		<tr>
			<td>
				<form action="student?action=findById">
					<span>按学号查询：</span> <input type="text" placeholder="请输入学生学号"
						name="id" value="${id }" /> <input type="hidden" name="action"
						value="findById" /> <input type="submit" value="查询" />&nbsp;&nbsp;
					<a href="student?action=toUpdate" style="float:right;">新增学生</a>
				</form>
			</td>
		</tr>
	</table>
	<table align="center" width="600">
		<tr>
			<td>
				<form action="student">
					<span>按姓名查询：</span> 
					<input type="text" placeholder="请输入学生姓名" name="name" value="${name }" /> 
					<input type="hidden" name="action" value="findByName" /> 
					<input type="submit" value="查询" />
				</form>
			</td>
		</tr>
	</table>
</body>
</html>