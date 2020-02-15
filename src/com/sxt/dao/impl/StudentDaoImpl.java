package com.sxt.dao.impl;

import java.util.List;
import java.util.Map;

import com.sxt.dao.BaseDao;
import com.sxt.dao.IStudentDao;
import com.sxt.pojo.Student;
/**
 * @ClassName: StudentDaoImpl 
 * @Description: 学生数据操作实现类
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:02:31
 */
public class StudentDaoImpl extends BaseDao implements IStudentDao {

	@Override
	public List<Student> selectList(Map<String, Object> params) {
		String sql = "select id as id,st_no as stNo,name as name,sex as sex,age as age,score as score from student where 1=1";
		//如果存在name的key  说明存在 name的条件
		if(params.containsKey("name")) {
			sql = sql + " and name like '%"+params.get("name")+"%' ";
		}
		//根据分数进行排序
		sql = sql + " order by score desc";
		return super.selectList(sql, Student.class);
	}

	@Override
	public Student selectStudent(String stNo) {
		String sql = "select id from student where st_no = ?";
		return super.selectOne(sql, Student.class, stNo);
	}
	
	public Student insert(Student st) {
		String sql = "insert into student value(0,?,?,?,?,?)";
		System.out.println(st);
		int id = super.insert(sql, st.getStNo(),st.getName(),st.getSex(),st.getAge(),st.getScore());
		if(id == 0) {
			System.out.println("学生添加失败:系统错误");
			return null;
		}
		st.setId(id);
		return st;
	}
}
