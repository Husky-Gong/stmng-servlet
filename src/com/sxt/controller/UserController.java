package com.sxt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxt.pojo.User;
import com.sxt.service.IUserService;
import com.sxt.service.impl.UserServiceImpl;
/**
 * @ClassName: UserController 
 * @Description: 用于处理用户的行为的控制器
 * @author: Mr.T
 * @date: 2020年2月13日 上午11:56:41
 */
public class UserController  extends HttpServlet{
	static IUserService userService = new UserServiceImpl();
	private static final long serialVersionUID = 7723086583204133716L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取业务标识值,根据标识调用相关方法
		String service = req.getParameter("service");
		System.out.println(service);
		// 标识此时是登录请求
		if("login".equals(service)) {
			//处理登录请求
			login(req,resp);
		}else if("register".equals(service)) {
			//处理注册请求
			register(req,resp);
		}
	}
	
	/**
	 * @Title: login
	 * @author: Mr.T   
	 * @date: 2020年2月13日 下午2:48:09 
	 * @Description: 提供登录服务
	 * @param req
	 * @param resp
	 * @return: void
	 * @throws IOException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// User input code
		String checkCode = req.getParameter("checkCode");
		System.out.println(checkCode);
		// Get code from session
		Object code = req.getSession().getAttribute("code");
		// Test code
		System.out.println(code.toString());
		if(checkCode == null || !checkCode.equals(code.toString())) {
			System.out.println("验证码错误");
			resp.sendRedirect("login.jsp");
			return;
		}
		
		//获取用户名和密码
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//以上代码  controller 已经拿到view 层的数据
		// controller  调用 model 层中信息 将数据交给model 层处理
		
		// model 层 登录方法
		boolean flag = userService.login(username,password);
		//如果flag 为 为true 说明账号密码正确
		//跳转学生数据列表页面  ----> 要有学生数据  
		if(flag) {
			System.out.println("=========登录成功   后面去学生列表数据页面===========");
			//获取学生数据的地址  注意  要传递业务标识  标识要获取学生数据
			resp.sendRedirect("student.do?service=list");
			return;
		}
		//不为true 则登录失败  返回登录页面
		resp.sendRedirect("login.jsp");
		
		
	}
	/**
	 * @Title: resgiter
	 * @author: Mr.T   
	 * @date: 2020年2月13日 下午2:48:20 
	 * @Description: 提供注册服务
	 * @param req
	 * @param resp
	 * @return: void
	 * @throws IOException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String realName = req.getParameter("realName");
		
		User user = userService.getUser(username, password, realName);
		
		boolean flag = userService.register(user);
		System.out.println(user);
		if(flag) {
			resp.sendRedirect("login.jsp");
		}else {
			resp.sendRedirect("register.jsp");
		}
	}

	

	
	
}
