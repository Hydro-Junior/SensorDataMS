package com.xjy.biz;

import java.util.List;

import com.xjy.entity.Record;

public interface RecordBiz {
	//�鿴ָ���û����޸ļ�¼
	public List<Record> queryUserRecords(String uname);
	//�鿴ָ�����������޸ļ�¼
	public List<Record> querySensorRecords(String uname);
	//�鿴���д������ļ�¼
	public List<Record> queryAllRecords();
}
