package com.xjy.biz.impl;

import com.xjy.biz.UserBiz;
import com.xjy.dao.UserDao;
import com.xjy.dao.impl.UserDaoImpl;
import com.xjy.entity.User;

public class UserBizImpl implements UserBiz {
	
	private UserDao userDao = null;
	public UserBizImpl(){
		userDao = new UserDaoImpl();
	}
	@Override
	public User login(User user) {
		
		return userDao.queryUser(user);
	}

	@Override
	public int registerUser(User user) {
		if(userDao.queryUser(user)!=null){
			return 1 ;//此用户名已经存在
		}else{
			boolean res = userDao.saveUser(user);
			if(res){
				return 2;//注册成功
			}else{
				return 3;//注册失败
			}
		}
	}

}
