package com.sxt.service.impl;

import com.sxt.dao.IUserDao;
import com.sxt.dao.impl.UserDaoImpl;
import com.sxt.pojo.User;
import com.sxt.service.IUserService;
/**
 * @ClassName: UserServiceImpl 
 * @Description: 处理用户业务的实现类
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:04:15
 */
public class UserServiceImpl implements IUserService {
	//model 层要进行具体的数据操作
	static IUserDao userDao = new UserDaoImpl();
	@Override
	public boolean login(String username, String password) {
		
		//根据用户名和密码查询用户
		User user = userDao.selectUser(username,password);
		return user != null ? true:false;
	}
	
	@Override
	public User getUser(String username, String password, String realName) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRealName(realName);
		return user;
	}

	@Override
	public boolean register(User user) {
		User u = userDao.selectUser(user.getUsername());
		
		if(u != null) {
			System.out.println("注册失败：用户名已被占用");
			return false;
		}
		user = userDao.insert(user);
		
		if(user == null) {
			System.out.println("注册失败：系统错误");
			return false;
		}
		System.out.println("注册成功");
		return true;
	}
}
