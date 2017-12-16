package com.xjy.biz;

import java.util.List;

import com.xjy.entity.Sensor;

public interface SensorBiz {
	public boolean addSensor(Sensor sensor);//添加传感器
	public boolean delSensor(int sid);//删除传感器
	public boolean modifySensor(Sensor sensor);//修改传感器
	public List<Sensor> queryAllSensors();//查询所有的传感器信息
	public List<Sensor> querySensorByName(String sname);//根据传感器的名字来查询
	public List<Sensor> querySensorByType(String stype);//根据传感器的类型来查询
	
}
