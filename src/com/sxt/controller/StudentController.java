package com.sxt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxt.pojo.Student;
import com.sxt.service.IStudentService;
import com.sxt.service.impl.StudentServiceImpl;
/**
 * @ClassName: StudentController 
 * @Description: 学生类行为的控制器
 * @author: Mr.T
 * @date: 2020年2月13日 上午11:57:29
 */
public class StudentController  extends HttpServlet{

	private static final long serialVersionUID = -4598754262279053571L;
	
	static IStudentService studentService = new StudentServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取业务标识
		String service = req.getParameter("service");
		//根据业务标识提供不同的服务
		if("list".equals(service)) {
			//调用查询学生列表数据服务
			list(req,resp);
		}
		else if("add".equals(service)) {
			add(req,resp);
		}
		
	}
	/**
	 * @Title: list
	 * @author: Mr.T   
	 * @date: 2020年2月13日 下午3:30:34 
	 * @Description: 查询学生列表数据服务
	 * @param req
	 * @param resp
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取name 查询条件
		String name = req.getParameter("name");
		// 创建容器 装查询条件
		Map<String,Object> params = new HashMap<>();
		// name = null 
		// name = ""
		// name = "    " ---> "" 
		//name.trim() : 去掉字符串中首位空格
		if(name != null && name.trim().length() != 0) {
			//放入作用域中 便于查询数据回显
			req.setAttribute("name", name);
			//放入查询条件容器中
			params.put("name", name);
		}
		//调用 model 层中的方法  获取相关业务数据
		
		//根据查询条件获取数据
		List<Student> sts = studentService.queryList(params);
		//将数据放入到作用域  便于内部转发 传递给页面
		req.setAttribute("sts", sts);
		//使用内部转发跳转到页面
		req.getRequestDispatcher("list.jsp").forward(req, resp);
	}
	
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//Receive data from VIEW
		String st_no = req.getParameter("st_no");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		int age = Integer.parseInt(req.getParameter("age"));
		String score = req.getParameter("score");
		
		// Generate a new student object
		Student stu = studentService.getStudent(st_no, name, sex, age, score);
		
		boolean flag = studentService.addStudent(stu);
		
		System.out.println(flag);
		if(flag) {
			resp.sendRedirect("http://127.0.0.1:8080/stmng-servlet/student.do?service=list");
		}
		else {
			resp.sendRedirect("studentAdd.jsp");
		}
	}
	
}
