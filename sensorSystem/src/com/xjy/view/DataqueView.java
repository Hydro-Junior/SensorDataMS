package com.xjy.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.xjy.util.DataUtil;

public class DataqueView extends JInternalFrame {

	private JPanel topPanel = null;
	private JPanel tablePanel = null;
	private JPanel spePanel = null;
	private JLabel label1 = null;
	private JLabel label2 = null;
	private JLabel labelT = null;
	private JComboBox<String> cb_table = null;
	private JTextField tf_sname = null;
	private JButton bt_quedata = null;
	private JButton bt_value = null;
	private JTextField tf_time = null;
	private JTextField tf_value = null;
	private JTextArea ta_data = null;
	private JScrollPane sp = null;

	
	public DataqueView(){
		init();
		registerListener();
	}
	
	public void init(){
		this.setTitle("数据信息查询");
		this.setSize(530,400);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ta_data = new JTextArea(15,55);
		topPanel = new JPanel(new FlowLayout());
		tablePanel = new JPanel();
		spePanel = new JPanel(new GridLayout(8,1));
		sp = new JScrollPane();
		
		
		label1 = new JLabel("请选择数据存储表：");
		cb_table = new JComboBox<String>(new String[]{"温 度","湿 度","PM2.5"});
		label2 = new JLabel("请输入传感器id：");
		tf_sname = new JTextField(8);
		bt_quedata = new JButton("查 询");
		labelT = new JLabel("  请输入具体时刻 ");
		tf_time = new JTextField(6);
		tf_value = new JTextField(8);
		bt_value = new JButton("快速查询数值");
		
		topPanel.add(label1);
		topPanel.add(cb_table);
		topPanel.add(new JLabel());
		topPanel.add(label2);
		topPanel.add(tf_sname);
		topPanel.add(bt_quedata);
		
		spePanel.add(new JLabel());
		spePanel.add(labelT);
		spePanel.add(tf_time );
		spePanel.add(new JLabel());
		spePanel.add(bt_value);
		spePanel.add(new JLabel("时刻监测值："));
		spePanel.add(tf_value);
		spePanel.add(new JLabel());
		ta_data.setLineWrap(true);
		sp.setViewportView(ta_data);
		//sp.setBounds(5, 5, 100, 65);
		sp.setWheelScrollingEnabled(true);
		
		tablePanel.add(sp);
		//tablePanel.add(new JScrollPane(ta_data));
		
		this.add(topPanel,BorderLayout.NORTH);
	    this.add(tablePanel,BorderLayout.CENTER);
	    this.add(spePanel, BorderLayout.EAST);
		this.setVisible(true);
			
	}
	
	private void registerListener(){
		bt_quedata.addActionListener(new ActionListener() {
			
			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MongoClient mongoClient = new MongoClient("localhost",27001);
					// 连接到数据库
					MongoDatabase db = mongoClient.getDatabase("sensor_data");
					int key = 0;
				    String info = tf_sname.getText().trim();
				    if(info.equals("")){
				    	JOptionPane.showInternalMessageDialog(DataqueView.this, "搜索条件不能为空！");
				    	return;
				    }
				    if(!DataUtil.isNumber(info)){
				    	JOptionPane.showInternalMessageDialog(DataqueView.this, "按id查询只能输入整数！");
				    	return;
				    }
					key = Integer.parseInt(info);
					BasicDBObject cond = new BasicDBObject();
					cond.put("sensorID", key);
					int index = cb_table.getSelectedIndex();
					String table_info = new String();
					if(index==0){
						table_info = "tmpdata";
					}else if(index == 1){
						table_info = "hmddata";
					}else{
						table_info = "pmtdata";
					}
					MongoCollection<Document> col = db.getCollection(table_info);

					try {
						MongoCursor<Document> cursor = col.find(cond).iterator();
						
						List<Document> all = new ArrayList<Document>();
						while(cursor.hasNext()){
							all.add(cursor.next());
						}
						MongoCollection<Document> res= db.getCollection("result");
						res.drop();   //先清空结果集
						res.insertMany(all);   //把文档插入结果集
						BasicDBObject condition = new BasicDBObject("$project",new BasicDBObject("_id",0).append("sensorName",1).append("sensorID", 1).append("time", 1)
								.append("value", 1));  //选择显示的查询条件
						List<BasicDBObject> docs = new ArrayList<BasicDBObject>();
						docs.add(condition);
						MongoCursor<Document> cursor3 = res.aggregate(docs).iterator();
						while(cursor3.hasNext()){
							Document doc = cursor3.next();
							ta_data.append(doc.toString()) ;
							ta_data.append("\n");
						}
					} catch (MongoException  e1) {
						
						JOptionPane.showInternalMessageDialog(DataqueView.this, "错误！极有可能是搜索条件超出了范围。");
						e1.printStackTrace();
					}
					mongoClient.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		bt_value.addActionListener(new ActionListener() {
			
			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MongoClient mongoClient = new MongoClient("localhost",27001);
					MongoDatabase db = mongoClient.getDatabase("sensor_data");
					String info = tf_sname.getText().trim();
					String time = tf_time.getText().trim();
					if(info.equals("")||time.equals("")){
						JOptionPane.showInternalMessageDialog(DataqueView.this, "搜索条件不能为空！");
						return;
					}
					if(!DataUtil.isNumber(info)||!DataUtil.isNumber(time)){
						JOptionPane.showInternalMessageDialog(DataqueView.this, "id和具体时刻只能输入整数！");
						return;
					}
					int key = 0;
					int t = 0;
					key = Integer.parseInt(info);
					t = Integer.parseInt(time);
					BasicDBObject cond = new BasicDBObject();
					cond.put("sensorID", key);
					cond.put("time", t);
					int index = cb_table.getSelectedIndex();
					String table_info = new String();
					if(index==0){
						table_info = "tmpdata";
					}else if(index == 1){
						table_info = "hmddata";
					}else{
						table_info = "pmtdata";
					}
					MongoCollection<Document> col = db.getCollection(table_info);
					MongoCursor<Document> cursor = col.find(cond).iterator();
					List<Document> all = new ArrayList<Document>();
					while(cursor.hasNext()){
						all.add(cursor.next());
						}
					MongoCollection<Document> res= db.getCollection("result");
					res.drop();   //先清空结果集
					res.insertMany(all);   //把文档插入结果集
					BasicDBObject condition = new BasicDBObject("$project",new BasicDBObject("_id",0).append("sensorID", 1).append("time", 1)
							.append("value", 1));
					List<BasicDBObject> docs = new ArrayList<BasicDBObject>();
					docs.add(condition);
					MongoCursor<Document> cursor3 = res.aggregate(docs).iterator();
					while(cursor3.hasNext()){
						Document doc = cursor3.next();
						tf_value.setText(doc.get("value").toString());
					}
					mongoClient.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showInternalMessageDialog(DataqueView.this, "错误！极有可能是搜索条件超出了范围。");
					e1.printStackTrace();
				}
			}
		});
	}
}
