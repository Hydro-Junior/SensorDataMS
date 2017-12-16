package com.xjy.dao;

import com.xjy.entity.User;

public interface UserDao {
	public boolean saveUser(User user);  //添加用户
	public boolean delUser(int id);  //删除用户
	public boolean updateUser(User user); //更新用户
	public User queryUser(User user);  //查询用户
	
}
