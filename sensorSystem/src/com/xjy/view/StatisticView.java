package com.xjy.view;

import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.xjy.util.DataUtil;

public class StatisticView extends JInternalFrame {
	private JPanel pn_info = null;
	private JPanel pn_time = null;
	private JPanel pn_info2 = null;
	private JPanel pn_time2 = null;
	private JPanel pn_avg = null;
	private JPanel pn_del = null;
	private JPanel avg_panel = null;
	private JPanel del_panel = null;
	
	private JLabel lb_table = null;
	private JLabel lb_table2 = null;
	private JLabel lb_id = null;
	private JLabel lb_id2 = null;
	private JLabel lb_time = null;
	private JLabel lb_signal = null;
	private JLabel lb_del1 = null;
	private JLabel lb_del2 = null;
	private JComboBox<String> cb_table = null;
	private JComboBox<String> cb_table2 = null;
	private JTextField tf_id = null;
	private JTextField tf_id2 = null;
	private JTextField tf_t1 = null;
	private JTextField tf_t2 = null;
	private JTextField tf_avg = null;
	private JTextField tf_del = null;
	private JButton btn_avg = null;
	private JButton btn_del = null;
	
	public StatisticView(){
		init();
		registerListener();
	}
	
	private void init(){
		
		this.setTitle("环境数据管理");
		this.setSize(530,410);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pn_info = new JPanel(new GridLayout(2,2));
		pn_info2 = new JPanel(new GridLayout(2,2));
		pn_time = new JPanel();
		pn_time2 = new JPanel();
		pn_avg = new JPanel();
		pn_del = new JPanel();
		avg_panel = new JPanel(new GridLayout(3, 1));
		del_panel = new JPanel(new GridLayout(2, 1));
		lb_table = new JLabel("  请选择数据表：");
		lb_table2 = new JLabel("  请选择数据表：");
		lb_id = new JLabel("  请输入传感器ID：");
		lb_id2 = new JLabel("  请输入传感器ID：");
		lb_time = new JLabel("请输入时间范围：");
		lb_del1 = new JLabel("  删除时间戳");
		lb_del2 = new JLabel("之前的数据 ");
		lb_signal = new JLabel("至");	
		lb_signal.setFont(new Font("宋体",Font.BOLD,15));
		cb_table = new JComboBox<String>(new String[]{"温 度","湿 度","PM2.5"});
		cb_table2 = new JComboBox<String>(new String[]{"温 度","湿 度","PM2.5"});
		tf_id = new JTextField(16);
		tf_id2 = new JTextField(16);
		tf_t1 = new JTextField(16);
		tf_t2 = new JTextField(16);
		tf_avg = new JTextField(50);
		tf_del = new JTextField(16);
		btn_avg = new JButton("返回平均值记录");
		btn_del = new JButton(" 删除数据 ");
		
		pn_info.add(lb_table);
		pn_info2.add(lb_table2);
		pn_info.add(cb_table);
		pn_info2.add(cb_table2);
		pn_info.add(lb_id);
		pn_info2.add(lb_id2);
		pn_info.add(tf_id);
		pn_info2.add(tf_id2);
		
		pn_time.add(lb_time);
		pn_time.add(tf_t1);
		pn_time.add(lb_signal);
		pn_time.add(tf_t2);
		
		pn_avg.add(btn_avg);
		pn_avg.add(tf_avg);
		
		pn_del.add(lb_del1);
		pn_del.add(tf_del);
		pn_del.add(lb_del2);
		pn_del.add(btn_del);
		
		avg_panel.add(pn_info);
		avg_panel.add(pn_time);
		avg_panel.add(pn_avg);
		
		del_panel.add(pn_info2);
		del_panel.add(pn_del);
		
		avg_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(),"统计平均值模块"));
		del_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(),"删除数据模块"));
		
		this.setLayout(new GridLayout(2, 1));
		//this.add(pn_info);
		//this.add(pn_time);
		//this.add(pn_avg);
		this.add(avg_panel);
		this.add(del_panel);
		
		this.pack();
		this.setVisible(true);
			
	}
	
	 private void registerListener(){
		 btn_avg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					MongoClient mongoClient = new MongoClient("localhost",27001);
					// 连接到数据库
					MongoDatabase db = mongoClient.getDatabase("sensor_data");
					// 获取对应的集合
					int index = cb_table.getSelectedIndex();
					String table_info = new String();
					if(index==0){
						table_info = "tmpdata";
					}else if(index == 1){
						table_info = "hmddata";
					}else{
						table_info = "pmtdata";
					}
					MongoCollection col = db.getCollection(table_info);
					
					String info = tf_id.getText().trim();
					String st1 = tf_t1.getText().trim();
					String st2 = tf_t2.getText().trim();
					if(info.equals("")||st1.equals("")||st2.equals("")){
						JOptionPane.showInternalMessageDialog(StatisticView.this, "条件不能为空！");
						return;
					}
					if((!DataUtil.isNumber(info))||(!DataUtil.isNumber(st1))||(!DataUtil.isNumber(st2))){
						JOptionPane.showInternalMessageDialog(StatisticView.this, "请输入整数！");
						return;
					}
					int id = Integer.parseInt(info);
					int t1 = Integer.parseInt(st1);
					int t2 = Integer.parseInt(st2);
					/**
					 * 统计局部平均值分两步，第一步：创建一个结果集，把按时间范围和id找到的结果放到结果集中。
					 */
					BasicDBObject cond = new BasicDBObject();
					cond.put("time",new BasicDBObject("$gte",t1).append("$lte",t2));
					cond.put("sensorID", id);
					List<Document> all = new ArrayList<Document>();  //文档表，用来暂时存放找出来的文档对象
					MongoCursor<Document> cursor = col.find(cond).iterator();
					while(cursor.hasNext()){
						all.add(cursor.next());
					}
					MongoCollection<Document> res= db.getCollection("result");
					res.drop();   //先清空结果集
					res.insertMany(all);   //把文档插入结果集
					
					/**
					 * 统计局部平均值第二步：利用group和aggregate整理出平均值
					 */
					List<BasicDBObject> docs = new ArrayList<BasicDBObject>();
					 BasicDBObject cond2 = new BasicDBObject("$group",new BasicDBObject("_id","$sensorID")
					         .append("时长",new BasicDBObject("$sum",1)).append("平均值",new BasicDBObject("$avg","$digvalue")));
					docs.add(cond2);
					MongoCursor<Document> cursor3 = res.aggregate(docs).iterator();
					while(cursor3.hasNext()){
						Document doc = cursor3.next();
						tf_avg.setText(doc.toString());
					}
					
					mongoClient.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showInternalMessageDialog(StatisticView.this, "错误！极有可能是搜索条件超出了范围。");
					e1.printStackTrace();
				}
			}
		});
		 
		 btn_del.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MongoClient mongoClient = new MongoClient("localhost",27001);
					// 连接到数据库
					MongoDatabase db = mongoClient.getDatabase("sensor_data");
					// 获取对应的集合
					int index = cb_table2.getSelectedIndex();
					String table_info = new String();
					if(index==0){
						table_info = "tmpdata";
					}else if(index == 1){
						table_info = "hmddata";
					}else{
						table_info = "pmtdata";
					}
					MongoCollection col = db.getCollection(table_info);
					String info = tf_id2.getText().trim();
					String del_info = tf_del.getText().trim();
					
					if(del_info.equals("")||info.equals("")){
						JOptionPane.showInternalMessageDialog(StatisticView.this, "传感id和时间戳不能为空！");
						return;
					}
					if((!DataUtil.isNumber(del_info))||(!DataUtil.isNumber(info))){
						JOptionPane.showInternalMessageDialog(StatisticView.this, "请输入整数！");
						return;
					}
					int id = Integer.parseInt(info);
					int delT = Integer.parseInt(del_info);
					BasicDBObject cond = new BasicDBObject("sensorID",id);
					cond.put("time", new BasicDBObject("$lt",delT));
					
					
					int flag=JOptionPane.showInternalConfirmDialog(StatisticView.this, "是否确定删除这么多数据？","确认信息",JOptionPane.YES_NO_OPTION);
					
					if (flag == JOptionPane.YES_OPTION){
						DeleteResult result = col.deleteMany(cond);
						if(result.getDeletedCount()>0){
							JOptionPane.showInternalMessageDialog(StatisticView.this, "删除成功！");
						}else{
							JOptionPane.showInternalMessageDialog(StatisticView.this, "删除失败，可能是时间戳范围错误！");
						
						}
					}
					
					
				}
			}
			);
	 }
	
	
}
