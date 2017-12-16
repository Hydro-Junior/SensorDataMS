package com.xjy.dao;

import java.util.List;

import com.xjy.entity.Record;

public interface RecordDao {
	public Record queryRecordByid(int id); //查看指定id的传感器修改记录
	public boolean saveRecord(Record record); //保存
	public boolean updateRecord(Record record);//更新
	
	public List<Record> queryAllRecord();  //查询所有传感器修改记录
	
	public List<Record> queryRecordByUname(String uname); //查询指定用户的修改记录
	public List<Record> queryRecordBySname(String sname); //查看指定传感器的修改记录
	
	
	
}
