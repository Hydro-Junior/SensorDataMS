package com.xjy.entity;

public class User {
	private int id ; 
	private String uname;
	private String upwd;
	private int type ;
	
	
	public User() {
		super();
	}


	public User(int id, String uname, String upwd, int type) {
		super();
		this.id = id;
		this.uname = uname;
		this.upwd = upwd;
		this.type = type;
	}
	
	
	public User(String uname, String upwd, int type) {
		super();
		this.uname = uname;
		this.upwd = upwd;
		this.type = type;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
