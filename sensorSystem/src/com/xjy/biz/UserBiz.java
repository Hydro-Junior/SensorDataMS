package com.xjy.biz;

import com.xjy.entity.User;

public interface UserBiz {
	//�û���¼�������û���¼����Ϣ������
	public User login(User user);
	//ע���û�
	public int registerUser(User user);
}
