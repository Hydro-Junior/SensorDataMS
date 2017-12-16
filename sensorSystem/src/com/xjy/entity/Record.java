package com.xjy.entity;

public class Record {
	private int idrecord ;
	private String uname;
	private String sname;
	private String updatetime;
	
	
	public Record(String uname, String sname, String updatetime) {
		super();
		this.uname = uname;
		this.sname = sname;
		this.updatetime = updatetime;
	}
	public Record(int idrecord, String uname, String sname, String updatetime) {
		super();
		this.idrecord = idrecord;
		this.uname = uname;
		this.sname = sname;
		this.updatetime = updatetime;
	}
	public int getIdrecord() {
		return idrecord;
	}
	public void setIdrecord(int idrecord) {
		this.idrecord = idrecord;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Record() {
		super();
	}
}