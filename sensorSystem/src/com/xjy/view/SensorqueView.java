package com.xjy.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.xjy.biz.SensorBiz;
import com.xjy.biz.impl.SensorBizImpl;
import com.xjy.entity.Sensor;

public class SensorqueView extends JInternalFrame {
	private JPanel paneltable = null;//��������JTable�����
	private JTable table = null;//����JTable
	private JPanel panelButton = null;//��ť���
	private JButton btn_search = null;
	private JButton btn_modify = null;
	private JButton btn_exit = null;
	private JComboBox<String> cb_type = null;
	private JLabel lb_type = null;
	private SensorBiz sensorBiz=null;
	private List<Sensor> sensorList = null;
	private SensorInfoTable infoTable = null;
	public SensorqueView (){
		init();
		registerListener();
	}
	
	
	private void init(){
		this.setTitle("��������Ϣ��ѯ");
		this.setSize(530,410);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		table = new JTable();
		sensorList = new ArrayList<Sensor>();
		//��JTable������ģ�ͳ�������
		refreshTable(sensorList);
		
		paneltable = new JPanel(new BorderLayout());//�������
		
		sensorBiz = new SensorBizImpl();
		//��������ñ߿�
		paneltable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"��ѯ��Ϣ"));
		
		//paneltable.add(table);
		this.add(paneltable,BorderLayout.CENTER);
		paneltable.add(new JScrollPane(table));
		
		panelButton = new JPanel(new GridLayout(7,1,10,30));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"��ѯ����"));
		this.add(panelButton,BorderLayout.EAST);
		lb_type = new JLabel("��ѯ����");
		panelButton.add(lb_type);
		cb_type = new JComboBox<String>(new String[]{"ȫ��������","�¶ȴ�����","ʪ�ȴ�����","PM2.5"});
		panelButton.add(cb_type);
		btn_search = new JButton("��ѯ");
		panelButton.add(btn_search);
		btn_modify = new JButton("�޸�");
		btn_modify.setEnabled(false);
		panelButton.add(btn_modify);
		panelButton.add(new JLabel());
		panelButton.add(new JLabel());
		btn_exit = new JButton("�˳�����");
		panelButton.add(btn_exit);
		this.setVisible(true);
		
		
	}
	
	private void registerListener(){
		btn_search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = cb_type.getSelectedIndex();
				//���������
				if(sensorList != null){
					sensorList.clear();
				}
				if(index==0){
					sensorList = sensorBiz.queryAllSensors();
				}else if(index == 1){
					sensorList = sensorBiz.querySensorByType("tmp");
				}else if(index == 2){
					sensorList = sensorBiz.querySensorByType("hmd");
				}else{
					sensorList = sensorBiz.querySensorByType("pm2.5");
				}
				refreshTable(sensorList);
			}
			
		});
	}
	
	private class SensorInfoTable implements TableModel{
		private List<Sensor> sensorList = null;
		
		public SensorInfoTable (List<Sensor> sensorList){
			this.sensorList = sensorList;
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		//�е���������
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}

		//��ʾ������
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}
		
		//��ʾ������
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			if(columnIndex == 0){
				return "������ID";
			}else if(columnIndex == 1){
				return "����������";
			}else if(columnIndex == 2){
				return "����������";
			}else if(columnIndex == 3){
				return "����Ƶ��";
			}else if(columnIndex == 4){
				return "���ݴ洢��";
			}else{
				return "�����ˣ�";
			}
		}

		//JTable��ʾ���ݵ�����
		public int getRowCount() {
			// TODO Auto-generated method stub
			return sensorList.size();
		}

		//��ȡָ����ָ���е�����
		public Object getValueAt(int rowIndex, int columnIndex) {
			Sensor sensor = sensorList.get(rowIndex);
			if(columnIndex == 0 ){
				return sensor.getSid();
			}else if(columnIndex == 1){
				return sensor.getSname();
			}else if (columnIndex == 2){
				return sensor.getStype();
			}else if (columnIndex == 3){
				return sensor.getSampleRate();
			}else if (columnIndex == 4){
				return sensor.getStorageTable();}
			else{
				return "����";
			}
		}

		//���õ�Ԫ���Ƿ�ɱ༭
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}
		
	}
	//ˢ��JTable����ʾ����
	private void refreshTable(List<Sensor> sensorList){
		infoTable = new SensorInfoTable(sensorList);
		table.setModel(infoTable);
	}
	
	
}
