package com.xjy.biz;

import com.xjy.entity.User;

public interface UserBiz {
	//用户登录，返回用户登录的信息（对象）
	public User login(User user);
	//注册用户
	public int registerUser(User user);
}
