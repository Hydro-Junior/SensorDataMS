package com.xjy.entity;

public class Hmddata {
	private int idhmdData;
	private int hmddeviceID;
	private String time;
	private String value;
	
	public Hmddata(int idhmdData, int hmddeviceID, String time, String value) {
		super();
		this.idhmdData = idhmdData;
		this.hmddeviceID = hmddeviceID;
		this.time = time;
		this.value = value;
	}
	public Hmddata(int hmddeviceID, String time, String value) {
		super();
		this.hmddeviceID = hmddeviceID;
		this.time = time;
		this.value = value;
	}
	public int getIdhmdData() {
		return idhmdData;
	}
	public void setIdhmdData(int idhmdData) {
		this.idhmdData = idhmdData;
	}
	public int getHmddeviceID() {
		return hmddeviceID;
	}
	public void setHmddeviceID(int hmddeviceID) {
		this.hmddeviceID = hmddeviceID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
