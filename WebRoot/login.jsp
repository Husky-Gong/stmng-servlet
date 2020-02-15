<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
  <link rel="stylesheet" media="screen" href="resources/css/style.css">
  <link rel="stylesheet" type="text/css" href="resources/css/reset.css"/>
</head>
<body>
<div id="particles-js">
		<!-- 用户注册页面 -->
		<!-- user.do  用户注册的地址 -->
		<!-- 
			每个servlet 默认只能通过service 方法 处理用户具体的请求。
			但是一个资源,可能存在多个请求，如: 登录  注册  修改  删除  查询。等等都是在操作
			用户资源。那么一个service是不够处理，基于这样的情况，则需要创建多个Servlet。
			一个用户资源就要创建5个servlet。这样资源多了,那么servlet也会变多。此时会造成servlet臃肿。
			基于这种情况,可以在设计上弥补servlet本身的不足。
			虽然service方法提供真正具体的服务.但是servie 现在需要既提供登录服务,也要提供注册服务。
			如果service 能提供登录服务也能提供注册服务。 问题时,service方法什么时候提供登录服务,什么时候提供注册服务。
			可以通过,在设计上传输有个需要调用那种服务的标识,servlet中,首先先获取到这种标识值,然后根据这个标识值调用相应的
			服务方法。注意: 标识要是统一的。
				在form表单中,定义一个隐藏的值,用于值标识这个需要调用的服务。
				值传给后台,需要为值定义一个名称,注意此时这个的名称要是统一。
				如果在登录页面：service=login,那么注册页面 service=register/zhuce
				
		
		
		
		 -->
		
		
		<form action="user.do" method="post">
			<!-- 使用隐藏框  存放需要调用的服务的标识值 -->
			<input type="hidden"  name="service" value="login"/>
		<div class="login">
			<div class="login-top">
				登录
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="resources/img/name.png"/></div>
				<div class="login-center-input">
					<input type="text" name="username"  placeholder="请输入您的用户名" />
					<div class="login-center-input-text">用户名</div>
				</div>
			</div>
			
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="resources/img/password.png"/></div>
				<div class="login-center-input">
					<input type="password" name="password" placeholder="请输入您的密码" />
					<div class="login-center-input-text">密码</div>
				</div>
			</div>
			
			<p>验证码:<input type="text" name="checkCode" style="width: 50px"  />
				<img onclick="refreshCode()" src="checkCode.do" style="height: 50px" id="checkCodeImg" />
			</p>
			
			<div class="login-center clearfix">
			<input type="submit" value="登录" class="login-button" style="border: 0px;margin: auto" />
			</div>
			
			<div class="login-center clearfix">
			<input type="button" onclick="location.href='register.jsp';" value="注册" />
			</div>
		</div>
		
		</form>
</div>

<!-- scripts -->
<script src="resources/js/particles.min.js"></script>
<script src="resources/js/app.js"></script>
<script type="text/javascript">
	//刷新验证码
	function refreshCode(){
		console.log("点击了");
		//设置 src的值  值一旦发生改变  浏览器 会重新请求这个url地址
		//浏览器存在缓存策略,当src 后面值一样,浏览器是不会重新请求
		//那么servlet就不会产生新的验证码  所以验证码没有发生改变
		//基于这样的情况  一般会在src 后面的url 拼接一个不可重复的字符 ,一般使用随机数 或者 时间戳
		document.getElementById("checkCodeImg").src = "checkCode.do?"+new Date();
	}
</script>
</body>
</html>