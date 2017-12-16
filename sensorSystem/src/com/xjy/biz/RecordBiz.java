package com.xjy.biz;

import java.util.List;

import com.xjy.entity.Record;

public interface RecordBiz {
	//查看指定用户的修改记录
	public List<Record> queryUserRecords(String uname);
	//查看指定传感器的修改记录
	public List<Record> querySensorRecords(String uname);
	//查看所有传感器的记录
	public List<Record> queryAllRecords();
}
