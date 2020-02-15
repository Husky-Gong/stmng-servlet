<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册页面</title>
</head>
<body>
<form action="user.do" method="post">
<input type="hidden" name="service" value="register"/>
	<p>用户名:<input type="text" name="username" /></p>
		<p>密码:<input type="text" name="password" /></p>
		<p>真实姓名:<input type="text" name="realName" /></p>
		<p>
			<input type="submit"   value="注册" /> 
			<input type="reset"   value="重置" /> 
		</p>
</form>
</body>
</html>