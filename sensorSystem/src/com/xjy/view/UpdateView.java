package com.xjy.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xjy.biz.SensorBiz;
import com.xjy.biz.impl.SensorBizImpl;
import com.xjy.entity.Sensor;
import com.xjy.util.DataUtil;

public class UpdateView extends JInternalFrame {
	private JPanel top = null;
	private JPanel delp =null;
	private JPanel buttom1 = null;
	private JPanel buttom2 = null;
	private JLabel lb_name = null;
	private JLabel lb_type = null;
	private JLabel lb_freq = null;
	private JLabel lb_table = null;
	private JLabel lb_del = null;
	private JTextField tf_name = null;
	private JTextField tf_type = null;
	private JTextField tf_freq = null;
	private JTextField tf_table = null;
	private JTextField tf_del = null;
	private JButton btn_add = null;
	private JButton btn_del = null;
	private SensorBiz sensorBiz =null;
	
	public UpdateView(){
		sensorBiz = new SensorBizImpl(); 
		init();
		registerListener();
	}
	
	private void init(){
		this.setTitle("数据信息查询");
		this.setSize(530,400);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		top = new JPanel(new GridLayout(2,4));
		delp = new JPanel(new GridLayout(1,2));
		buttom1 = new JPanel();
		buttom2 = new JPanel();
		lb_name = new JLabel("   传感器名称:");
		lb_type = new JLabel("   传感器类型:");
		lb_freq = new JLabel("   采样频率:");
		lb_table = new JLabel("   数据存储表:");
		lb_del = new JLabel("   请输入要删除的传感器的ID:  ");
		tf_name = new JTextField(16);
		tf_type = new JTextField(16);
		tf_freq = new JTextField(16);
		tf_table = new JTextField(16);
		tf_del = new JTextField(8);
		btn_add = new JButton("添加");
		btn_del = new JButton("删除");
		
		top.add(lb_name);
		top.add(tf_name);
		top.add(lb_type);
		top.add(tf_type);
		top.add(lb_freq);
		top.add(tf_freq);
		top.add(lb_table);
		top.add(tf_table);
		buttom1.add(btn_add);
		delp.add(lb_del);
		delp.add(tf_del);
		buttom2.add(btn_del);
		
		this.setLayout(new GridLayout(4, 1));
		this.add(top);
		this.add(buttom1);
		this.add(delp);
		this.add(buttom2);
        
		this.pack();
		this.setVisible(true);
		
	}
	
	private void registerListener(){
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sname = tf_name.getText().trim();
				String stype = tf_type.getText().trim();
				String sfreq = tf_freq.getText().trim();
				String stable = tf_table.getText().trim();
				if(sname.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "传感器名字不能为空！");
					return;
				}
				if(stype.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "传感器类型不能为空！");
					return;
				}
				if(sfreq.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "采样频率不能为空！");
					return;
				}
				if(stable.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "数据存储表不能为空！");
					return;
				}
				
				int flag=JOptionPane.showInternalConfirmDialog(UpdateView.this, "是否确定添加传感器？","确认信息",JOptionPane.YES_NO_OPTION);
				if(flag == JOptionPane.YES_OPTION){
					boolean res = sensorBiz.addSensor(new Sensor(sname,stype,sfreq,stable));
					if(res){
						JOptionPane.showInternalMessageDialog(UpdateView.this, "添加成功！");
					}else{
						JOptionPane.showInternalMessageDialog(UpdateView.this, "添加失败，请联系管理员！");
					}
				}
			}
		});
		
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sid = tf_del.getText().trim();
				if(sid.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "id信息不能为空！");
					return;
				}
				if(!DataUtil.isNumber(sid)){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "传感器id必须是整数！");
					return;
				}
				int flag = JOptionPane.showInternalConfirmDialog(UpdateView.this, "是否确定删除该传感器？","确认信息",JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION){
					boolean res = sensorBiz.delSensor(Integer.parseInt(sid));
					if(res){
						JOptionPane.showInternalMessageDialog(UpdateView.this, "删除成功！");
					}else{
						JOptionPane.showInternalMessageDialog(UpdateView.this, "删除失败，可能不存在拥有这个id的传感器！");
					}
				}
				
				
			}
		});
		
	}
	
}
