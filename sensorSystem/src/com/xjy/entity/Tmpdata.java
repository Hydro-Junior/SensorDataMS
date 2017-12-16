package com.xjy.entity;

public class Tmpdata {
	private int idtmpData;
	private int tmpdeviceID;
	private String time;
	private String value;
	
	
	public Tmpdata(int idtmpData, int tmpdeviceID, String time, String value) {
		super();
		this.idtmpData = idtmpData;
		this.tmpdeviceID = tmpdeviceID;
		this.time = time;
		this.value = value;
	}
	public Tmpdata(int tmpdeviceID, String time, String value) {
		super();
		this.tmpdeviceID = tmpdeviceID;
		this.time = time;
		this.value = value;
	}
	
	public int getIdtmpData() {
		return idtmpData;
	}
	public void setIdtmpData(int idtmpData) {
		this.idtmpData = idtmpData;
	}
	public int getTmpdeviceID() {
		return tmpdeviceID;
	}
	public void setTmpdeviceID(int tmpdeviceID) {
		this.tmpdeviceID = tmpdeviceID;
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
