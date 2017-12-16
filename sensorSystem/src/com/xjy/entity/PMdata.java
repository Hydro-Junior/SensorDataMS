package com.xjy.entity;

public class PMdata {
	private int idpmData;
	private int pmdeviceID;
	private String time;
	private String value;
	
	
	public PMdata(int pmdeviceID, String time, String value) {
		super();
		this.pmdeviceID = pmdeviceID;
		this.time = time;
		this.value = value;
	}
	public PMdata(int idpmData, int pmdeviceID, String time, String value) {
		super();
		this.idpmData = idpmData;
		this.pmdeviceID = pmdeviceID;
		this.time = time;
		this.value = value;
	}
	public int getIdpmData() {
		return idpmData;
	}
	public void setIdpmData(int idpmData) {
		this.idpmData = idpmData;
	}
	public int getPmdeviceID() {
		return pmdeviceID;
	}
	public void setPmdeviceID(int pmdeviceID) {
		this.pmdeviceID = pmdeviceID;
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
