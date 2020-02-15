<%@page import="com.sxt.dao.*" %>
<%@page import="com.sxt.pojo.*" %>
<%@page import="com.sxt.service.*" %>
<%@page import="com.sxt.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增学生</title>
</head>
<body>
<form action="student.do" method="post">
	<!-- 使用隐藏框  存放需要调用的服务的标识值 -->
	<input type="hidden"  name="service" value="add"/>

	<p>学号:<input type="text" name="st_no"/></p>
	<p>姓名:<input type="text" name="name"/></p>
	<p>性别:<input type="text" name="sex"/></p>
	<p>年龄:<input type="text" name="age"/></p>
	<p>分数:<input type="text" name="score"/></p>
	<p>
		<input type="submit" value="添加"/>
		<input type="reset" value="重置"/>
	</p>
</form>
</body>
</html>