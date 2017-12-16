package com.xjy.entity;

public class Sensor {
	private int sid ; 
	private String sname;
	private String stype;
	private String sampleRate;
	private String storageTable;
	
	
	public Sensor(int sid, String sname, String stype, String sampleRate, String storageTable) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.stype = stype;
		this.sampleRate = sampleRate;
		this.storageTable = storageTable;
	}
	public Sensor(String sname, String stype, String sampleRate, String storageTable) {
		super();
		this.sname = sname;
		this.stype = stype;
		this.sampleRate = sampleRate;
		this.storageTable = storageTable;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getSampleRate() {
		return sampleRate;
	}
	public void setSampleRate(String sampleRate) {
		this.sampleRate = sampleRate;
	}
	public String getStorageTable() {
		return storageTable;
	}
	public void setStorageTable(String storageTable) {
		this.storageTable = storageTable;
	}
	public Sensor() {
		super();
	}
	
}