package com.sxt.pojo;

/**
 * @ClassName: User
 * @Description: 用户实体类
 * @author: Mr.T
 * @date: 2020年2月13日 上午11:59:29
 */
public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", realName=" + realName + "]";
	}
	/**
	 *  用户ID
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户真实姓名
	 */
	private String realName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
	
}
