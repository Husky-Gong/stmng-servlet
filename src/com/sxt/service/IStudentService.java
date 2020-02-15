package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.pojo.Student;

/**
 * @ClassName: IStudentService 
 * @Description: 处理学生业务规则接口
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:03:37
 */
public interface IStudentService {
	/**
	 * @Title: queryList
	 * @author: Mr.T   
	 * @date: 2020年2月13日 下午3:37:49 
	 * @Description: 根据条件查询数据
	 * @param params
	 * @return
	 * @return: List<Student>
	 */
	List<Student> queryList(Map<String, Object> params);

	Student getStudent(String st_no, String name, String sex, int age, String score);

	boolean addStudent(Student stu);
	
	
		
}
