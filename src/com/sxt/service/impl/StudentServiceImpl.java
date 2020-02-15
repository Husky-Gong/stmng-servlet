package com.sxt.service.impl;

import java.util.List;
import java.util.Map;

import com.sxt.dao.IStudentDao;
import com.sxt.dao.impl.StudentDaoImpl;
import com.sxt.pojo.Student;
import com.sxt.service.IStudentService;
/**
 * @ClassName: StudentServiceImpl 
 * @Description: 处理学生业务的实现类
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:04:53
 */
public class StudentServiceImpl implements IStudentService {

	static IStudentDao studentDao = new StudentDaoImpl();
	@Override
	public List<Student> queryList(Map<String, Object> params) {
		//创建dao 对象 调用先关方法获取数据
		
		//获取学生数据
		List<Student> students = studentDao.selectList(params);
		return students;
	}

	@Override
	public Student getStudent(String st_no, String name, String sex, int age, String score) {
		Student stu = new Student();
		stu.setStNo(st_no);
		stu.setName(name);
		stu.setSex(sex);
		stu.setAge(age);
		stu.setScore(score);
		return stu;
	}

	@Override
	public boolean addStudent(Student stu) {
		//校验学号是否重复
		Student student = studentDao.selectStudent(stu.getStNo());
		if(student != null) {
			System.out.println("添加学生失败: 学号已被使用");
			return false;
		}
		stu = studentDao.insert(stu);
		return stu == null?false:true;
	}
}
