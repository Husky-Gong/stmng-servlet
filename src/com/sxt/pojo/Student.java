package com.sxt.pojo;

/**
 * @ClassName: Student
 * @Description: 学生实体类
 * @author: Mr.T
 * @date: 2020年2月13日 上午11:59:47
 */
public class Student {
	/**
	 * 学生ID
	 */
	private Integer id;
	/**
	 * 学号
	 */
	private String stNo;
	/**
	 * 学生名称
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 分数
	 */
	private String score;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStNo() {
		return stNo;
	}
	public void setStNo(String stNo) {
		this.stNo = stNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
	

}
