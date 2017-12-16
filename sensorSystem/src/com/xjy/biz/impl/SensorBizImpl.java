package com.xjy.biz.impl;

import java.util.List;

import com.xjy.biz.SensorBiz;
import com.xjy.dao.SensorDao;
import com.xjy.dao.impl.SensorDaoImpl;
import com.xjy.entity.Sensor;

public class SensorBizImpl implements SensorBiz {
	private SensorDao sensordao = null;
	public SensorBizImpl(){
		sensordao = new SensorDaoImpl();
	}
	@Override
	public boolean addSensor(Sensor sensor) {
		// TODO Auto-generated method stub
		return sensordao.saveSensor(sensor);
	}

	@Override
	public boolean delSensor(int sid) {
		// TODO Auto-generated method stub
		return sensordao.delSensor(sid);
	}

	@Override
	public boolean modifySensor(Sensor sensor) {
		// TODO Auto-generated method stub
		return sensordao.updateSensor(sensor);
	}

	@Override
	public List<Sensor> queryAllSensors() {
		// TODO Auto-generated method stub
		return sensordao.querySensors();
	}

	@Override
	public List<Sensor> querySensorByName(String sname) {
		// TODO Auto-generated method stub
		return sensordao.querySensorsByname(sname);
	}

	@Override
	public List<Sensor> querySensorByType(String stype) {
		// TODO Auto-generated method stub
		return sensordao.querySensorsBytype(stype);
	}

}
