package com.sxt.dao;

import com.sxt.pojo.User;

/**
 * @ClassName: IUserDao 
 * @Description: 用户数据操作接口
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:00:27
 */
public interface IUserDao {
	/**
	 * @Title: selectUser
	 * @author: Mr.T   
	 * @date: 2020年2月13日 下午2:53:41 
	 * @Description: 根据用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return
	 * @return: User
	 */
	User selectUser(String username, String password);

	User selectUser(String username);

	User insert(User user);
	

}
