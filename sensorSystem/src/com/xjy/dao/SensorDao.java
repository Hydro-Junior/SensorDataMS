package com.xjy.dao;

import java.util.List;

import com.xjy.entity.Sensor;

public interface SensorDao {
	public boolean saveSensor(Sensor sensor);  //��Ӵ�����
	public boolean updateSensor(Sensor sensor); //���´�����
	public boolean delSensor(int sid);  //ɾ��������
	public List<Sensor> querySensors();//��ѯ���д�����
	public List<Sensor> querySensorsByname(String sname); //�����ֲ�ѯ
	public Sensor querySensorById(int id);
	public List<Sensor> querySensorsBytype(String stype);//�����Ͳ�ѯ
	 
}
