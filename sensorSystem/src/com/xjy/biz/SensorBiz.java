package com.xjy.biz;

import java.util.List;

import com.xjy.entity.Sensor;

public interface SensorBiz {
	public boolean addSensor(Sensor sensor);//��Ӵ�����
	public boolean delSensor(int sid);//ɾ��������
	public boolean modifySensor(Sensor sensor);//�޸Ĵ�����
	public List<Sensor> queryAllSensors();//��ѯ���еĴ�������Ϣ
	public List<Sensor> querySensorByName(String sname);//���ݴ���������������ѯ
	public List<Sensor> querySensorByType(String stype);//���ݴ���������������ѯ
	
}
