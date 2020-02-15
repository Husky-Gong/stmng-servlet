package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.pojo.Student;

/**
 * @ClassName: IStudentDao 
 * @Description: 学生数据操作接口
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:00:54
 */
public interface IStudentDao {
	/**
	 * @Title: selectList
	 * @author: Mr.T   
	 * @date: 2020年2月13日 下午3:39:41 
	 * @Description: 根据条件获取学生数据
	 * @param params
	 * @return
	 * @return: List<Student>
	 */
	List<Student> selectList(Map<String, Object> params);

	Student selectStudent(String stNo);

	Student insert(Student stu);

}
