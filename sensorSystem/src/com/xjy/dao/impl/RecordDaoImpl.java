package com.xjy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xjy.dao.RecordDao;
import com.xjy.entity.Record;
import com.xjy.entity.Sensor;

public class RecordDaoImpl extends BaseDao implements RecordDao {

	@Override
	public Record queryRecordByid(int id) {
		String sql = "select idrecord,uname,sname,updatetime from record where idrecord=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Record> rList = null;
		try {
			rList = this.operQuery(sql, params, Record.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rList.size()>0){
		return rList.get(0);
		}
		return null;
	}

	@Override
	public boolean saveRecord(Record record) {
		String sql = "insert into record(uname,sname,updatetime) values(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUname());
		params.add(record.getSname());
		params.add(record.getUpdatetime());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateRecord(Record record) {
		String sql = "update record set uname=?,sname=?,updatetime=? where idrecord=?";//暂时不考虑名字可能相同的情况
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUname());
		params.add(record.getSname());
		params.add(record.getUpdatetime());
		params.add(record.getIdrecord());
		return this.operUpdate(sql, params);
	}

	@Override
	public List<Record> queryAllRecord() {
		List<Record> data = null;
		String sql = "select idrecord，uname,sname,updatetime from record";
		try {
			data = this.operQuery(sql, null, Record.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<Record> queryRecordByUname(String uname) {
		List<Record> data = null;
		String sql = "select idrecord，uname,sname,updatetime from record where uname=?";
		List<Object> params = new ArrayList<Object>(); 
		params.add(uname);
		try {
			data = this.operQuery(sql, params, Record.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<Record> queryRecordBySname(String sname) {
		List<Record> data = null;
		String sql = "select idrecord，uname,sname,updatetime from record where sname=?";
		List<Object> params = new ArrayList<Object>(); 
		params.add(sname);
		try {
			data = this.operQuery(sql, params, Record.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
