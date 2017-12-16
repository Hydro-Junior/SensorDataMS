package com.xjy.dao;

import java.util.List;

import com.xjy.entity.Sensor;

public interface SensorDao {
	public boolean saveSensor(Sensor sensor);  //添加传感器
	public boolean updateSensor(Sensor sensor); //更新传感器
	public boolean delSensor(int sid);  //删除传感器
	public List<Sensor> querySensors();//查询所有传感器
	public List<Sensor> querySensorsByname(String sname); //按名字查询
	public Sensor querySensorById(int id);
	public List<Sensor> querySensorsBytype(String stype);//按类型查询
	 
}
