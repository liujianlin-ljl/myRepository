<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="student">
		<table align="center" width="360">
			<caption>
			<c:if test="${not empty student }">
				<h2>修改学生信息</h2>
			</c:if>
			<c:if test="${empty student }">
				<h2>添加学生信息</h2>
			</c:if>
			</caption>
			<tr>
				<td>学号：</td>
				<td>
					<input type="text" name="s_no" value="${student.s_no }" <c:if test="${not empty student }">readonly</c:if>  />
				</td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="s_name" value="${student.s_name }" /></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td><input type="text" name="s_sex" value="${student.s_sex }" /></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="s_age" value="${student.s_age }" /></td>
			</tr>
			<tr>
				<td>班级：</td>
				<td>
					<select name="c_no">
						<option>请选择班级</option>
						<c:forEach items="${claList }" var="cla">
							<option value="${cla.c_no }" <c:if test="${cla.c_no == student.classes.c_no }">selected="selected"</c:if>>${cla.c_name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr align="center">
				<td align="center">
					<c:if test="${empty student }">
						<input type="hidden" name="action" value="add"  />
					</c:if>
					<c:if test="${not empty student }">
						<input type="hidden" name="action" value="update"  />
					</c:if>
					<input type="submit" value="提交" />
				</td>
				<td align="left"><a href="student?action=allStudent">返回</a></td>
			</tr>
		</table>
	</form>
</body>
</html>