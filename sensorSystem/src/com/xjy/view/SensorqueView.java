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
	private JPanel paneltable = null;//用来保存JTable的面板
	private JTable table = null;//声明JTable
	private JPanel panelButton = null;//按钮面板
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
		this.setTitle("传感器信息查询");
		this.setSize(530,410);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		table = new JTable();
		sensorList = new ArrayList<Sensor>();
		//让JTable绑定数据模型呈现数据
		refreshTable(sensorList);
		
		paneltable = new JPanel(new BorderLayout());//创建面板
		
		sensorBiz = new SensorBizImpl();
		//给面板设置边框
		paneltable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"查询信息"));
		
		//paneltable.add(table);
		this.add(paneltable,BorderLayout.CENTER);
		paneltable.add(new JScrollPane(table));
		
		panelButton = new JPanel(new GridLayout(7,1,10,30));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"查询条件"));
		this.add(panelButton,BorderLayout.EAST);
		lb_type = new JLabel("查询类型");
		panelButton.add(lb_type);
		cb_type = new JComboBox<String>(new String[]{"全部传感器","温度传感器","湿度传感器","PM2.5"});
		panelButton.add(cb_type);
		btn_search = new JButton("查询");
		panelButton.add(btn_search);
		btn_modify = new JButton("修改");
		btn_modify.setEnabled(false);
		panelButton.add(btn_modify);
		panelButton.add(new JLabel());
		panelButton.add(new JLabel());
		btn_exit = new JButton("退出窗口");
		panelButton.add(btn_exit);
		this.setVisible(true);
		
		
	}
	
	private void registerListener(){
		btn_search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = cb_type.getSelectedIndex();
				//先清除数据
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

		//列的数据类型
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}

		//显示的列数
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}
		
		//显示的列名
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			if(columnIndex == 0){
				return "传感器ID";
			}else if(columnIndex == 1){
				return "传感器名称";
			}else if(columnIndex == 2){
				return "传感器类型";
			}else if(columnIndex == 3){
				return "采样频率";
			}else if(columnIndex == 4){
				return "数据存储表";
			}else{
				return "出错了！";
			}
		}

		//JTable显示数据的行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return sensorList.size();
		}

		//获取指定行指定列的数据
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
				return "出错";
			}
		}

		//设置单元格是否可编辑
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
	//刷新JTable并显示数据
	private void refreshTable(List<Sensor> sensorList){
		infoTable = new SensorInfoTable(sensorList);
		table.setModel(infoTable);
	}
	
	
}
