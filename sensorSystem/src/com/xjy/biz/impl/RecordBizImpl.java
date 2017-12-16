package com.xjy.biz.impl;

import java.util.List;

import com.xjy.biz.RecordBiz;
import com.xjy.dao.RecordDao;
import com.xjy.dao.impl.RecordDaoImpl;
import com.xjy.entity.Record;

public class RecordBizImpl implements RecordBiz {
	private  RecordDao recordDao = null;
	
	public RecordBizImpl(){
		recordDao = new RecordDaoImpl();
	}

	public List<Record> queryUserRecords(String uname) {
		
		return recordDao.queryRecordByUname(uname);
	}

	@Override
	public List<Record> querySensorRecords(String sname) {
		
		return recordDao.queryRecordBySname(sname);
	}

	@Override
	public List<Record> queryAllRecords() {
		
		return recordDao.queryAllRecord();
	}

}
