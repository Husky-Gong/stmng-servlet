package com.sxt.service;

import com.sxt.pojo.User;

/**
 * @ClassName: IUserService 
 * @Description: 处理用户的业务规则的接口
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:03:06
 */
public interface IUserService {
	/**
	 * @Title: login
	 * @author: Mr.T   
	 * @date: 2020年2月13日 下午2:52:10 
	 * @Description: 用户登录的业务方法
	 * @param username
	 * @param password
	 * @return
	 * @return: boolean
	 */
	boolean login(String username, String password);

	User getUser(String username, String password, String realName);

	boolean register(User user);

}
