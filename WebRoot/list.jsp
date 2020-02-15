<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入jstl 标签库 -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生列表</title>
</head>
<body>
<!-- 搜索学生信息的表单
	student 作为处理学生行为控制器 ：
		查询  新增  删除  修改等等
		同样,一个service 不够处理。创建多个。从设计上弥补这个不足
		传递一个业务标识,service 根据业务标识调用不同的方法

 -->
<form action="student.do" method="get">
	<!-- 业务标识  告诉servlet 这个是查询列表 -->
	<input type="hidden" name="service" value="list" />
	<p>学生名称:<input type="text" name="name" value="${name}"  />&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="查询" />&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="add.jsp">添加学生</a>
	</p>
</form>
<hr>
<table>
	<tr>
		<th>ID</th>
		<th>学号</th>
		<th>名称</th>
		<th>年龄</th>
		<th>性别</th>
		<th>分数</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${sts}" var="st">
		<tr>
			<td>${st.id}</td>
			<td>${st.stNo}</td>
			<td>${st.name}</td>
			<td>${st.age}</td>
			<td>${st.sex}</td>
			<td>${st.score}</td>
			<td>删除</td>
		</tr>
	</c:forEach>

</table>




</body>
</html>