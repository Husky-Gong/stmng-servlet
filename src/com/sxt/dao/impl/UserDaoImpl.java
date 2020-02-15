package com.sxt.dao.impl;

import com.sxt.dao.BaseDao;
import com.sxt.dao.IUserDao;
import com.sxt.pojo.User;
/**
 * @ClassName: UserDaoImpl 
 * @Description: 用户操作实现类
 * @author: Mr.T
 * @date: 2020年2月13日 下午12:01:56
 */
public class UserDaoImpl extends BaseDao implements IUserDao {

	@Override
	public User selectUser(String username, String password) {
		String sql = "select id as id,username as username,password as password,real_name as realName from user where username = ? and password = ?";
		return super.selectOne(sql, User.class, username,password);
	}

	@Override
	public User selectUser(String username) {
		String sql = "select id from user where username = ?";
		return super.selectOne(sql, User.class, username);
	}

	@Override
	public User insert(User user) {
		String sql = "insert into user value(0,?,?,?)";
		int id = super.insert(sql, user.getUsername(),user.getPassword(),user.getRealName());
		if(id == 0) {return null;}
		user.setId(id);
		
		return user;
	}

}
