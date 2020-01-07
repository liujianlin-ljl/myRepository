<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="showInfo" method="post">
	<p>姓名：<input type="text" name="name" /></p>
	<p>性别：<input type="radio" name="sex" value="男" />男
			<input type="radio" name="sex" value="女" />女
	</p>
	<p>爱好：
		<input type="checkbox" name="hobby" value="Read" />Read
		<input type="checkbox" name="hobby" value="Travel" />Travel
		<input type="checkbox" name="hobby" value="Play Ball" />Play Ball
		<input type="checkbox" name="hobby" value="Listening Music" />Listening Music
	</p>
	<p><input type="submit" value="提交"  /></p>
</form>
</body>
</html>