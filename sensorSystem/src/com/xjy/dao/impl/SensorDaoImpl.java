package com.xjy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xjy.dao.SensorDao;
import com.xjy.entity.Sensor;

public class SensorDaoImpl extends BaseDao implements SensorDao {

	@Override
	public boolean saveSensor(Sensor sensor) {
		String sql = "insert into sensor(sname,stype,sampleRate,storageTable) values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(sensor.getSname());
		params.add(sensor.getStype());
		params.add(sensor.getSampleRate());
		params.add(sensor.getStorageTable());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateSensor(Sensor sensor) {
		String sql = "update sensor set sname=?,stype=?,sampleRate=?,storageTable=? where sid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(sensor.getSname());
		params.add(sensor.getStype());
		params.add(sensor.getSampleRate());
		params.add(sensor.getStorageTable());
		params.add(sensor.getSid());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delSensor(int sid) {
		String sql = "delete from sensor where sid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(sid);
		return this.operUpdate(sql, params);
	}

	@Override
	public List<Sensor> querySensors() {
		String sql = "select sid,sname,stype,sampleRate,storageTable from sensor";
		List<Sensor> sList = null;
		try {
			sList = this.operQuery(sql, null, Sensor.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;
	}

	@Override
	public List<Sensor> querySensorsByname(String sname) {
		String sql = "select sid,sname,stype,sampleRate,storageTable from sensor where sname=?";
		List<Object> params = new ArrayList<Object>();
		params.add(sname);
		List<Sensor> sList = null;
		try {
			sList = this.operQuery(sql, params, Sensor.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;
		
	}

	@Override
	public Sensor querySensorById(int id) {
		String sql = "select sid,sname,stype,sampleRate,storageTable from sensor where sid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Sensor> sList = null;
		try {
			sList = this.operQuery(sql, params, Sensor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sList.size() > 0){
		return sList.get(0);
		}
		return null;
	}
	@Override
	public List<Sensor> querySensorsBytype(String stype) {
		String sql = "select sid,sname,stype,sampleRate,storageTable from sensor where stype=?";
		List<Object> params = new ArrayList<Object>();
		params.add(stype);
		List<Sensor> sList = null;
		try {
			sList = this.operQuery(sql, params, Sensor.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;
		
	}

}
