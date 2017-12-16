package com.xjy.dao;

import java.util.List;

import com.xjy.entity.Record;

public interface RecordDao {
	public Record queryRecordByid(int id); //�鿴ָ��id�Ĵ������޸ļ�¼
	public boolean saveRecord(Record record); //����
	public boolean updateRecord(Record record);//����
	
	public List<Record> queryAllRecord();  //��ѯ���д������޸ļ�¼
	
	public List<Record> queryRecordByUname(String uname); //��ѯָ���û����޸ļ�¼
	public List<Record> queryRecordBySname(String sname); //�鿴ָ�����������޸ļ�¼
	
	
	
}
