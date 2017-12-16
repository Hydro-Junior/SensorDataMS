package com.xjy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xjy.dao.UserDao;
import com.xjy.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public boolean saveUser(User user) {
		String sql = "insert into user (uname,upwd,type) values(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpwd());
		params.add(user.getType());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delUser(int id) {
		String sql = "delete from user where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "update user set uname=?,upwd=?,type=? where uid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpwd());
		params.add(user.getType());
		params.add(user.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	public User queryUser(User user) {
		List<User> uList = null;
		String sql = "select uname,upwd,type from user where uname=? and upwd=? and type=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpwd());
		params.add(user.getType());
		try {
			uList = this.operQuery(sql, params, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(uList.size()>0){
			return uList.get(0);
		}
		return null;
	}

}
