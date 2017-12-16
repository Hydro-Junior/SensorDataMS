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
		this.setTitle("������Ϣ��ѯ");
		this.setSize(530,400);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		top = new JPanel(new GridLayout(2,4));
		delp = new JPanel(new GridLayout(1,2));
		buttom1 = new JPanel();
		buttom2 = new JPanel();
		lb_name = new JLabel("   ����������:");
		lb_type = new JLabel("   ����������:");
		lb_freq = new JLabel("   ����Ƶ��:");
		lb_table = new JLabel("   ���ݴ洢��:");
		lb_del = new JLabel("   ������Ҫɾ���Ĵ�������ID:  ");
		tf_name = new JTextField(16);
		tf_type = new JTextField(16);
		tf_freq = new JTextField(16);
		tf_table = new JTextField(16);
		tf_del = new JTextField(8);
		btn_add = new JButton("���");
		btn_del = new JButton("ɾ��");
		
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
					JOptionPane.showInternalMessageDialog(UpdateView.this, "���������ֲ���Ϊ�գ�");
					return;
				}
				if(stype.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "���������Ͳ���Ϊ�գ�");
					return;
				}
				if(sfreq.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "����Ƶ�ʲ���Ϊ�գ�");
					return;
				}
				if(stable.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "���ݴ洢����Ϊ�գ�");
					return;
				}
				
				int flag=JOptionPane.showInternalConfirmDialog(UpdateView.this, "�Ƿ�ȷ����Ӵ�������","ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
				if(flag == JOptionPane.YES_OPTION){
					boolean res = sensorBiz.addSensor(new Sensor(sname,stype,sfreq,stable));
					if(res){
						JOptionPane.showInternalMessageDialog(UpdateView.this, "��ӳɹ���");
					}else{
						JOptionPane.showInternalMessageDialog(UpdateView.this, "���ʧ�ܣ�����ϵ����Ա��");
					}
				}
			}
		});
		
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sid = tf_del.getText().trim();
				if(sid.equals("")){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "id��Ϣ����Ϊ�գ�");
					return;
				}
				if(!DataUtil.isNumber(sid)){
					JOptionPane.showInternalMessageDialog(UpdateView.this, "������id������������");
					return;
				}
				int flag = JOptionPane.showInternalConfirmDialog(UpdateView.this, "�Ƿ�ȷ��ɾ���ô�������","ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION){
					boolean res = sensorBiz.delSensor(Integer.parseInt(sid));
					if(res){
						JOptionPane.showInternalMessageDialog(UpdateView.this, "ɾ���ɹ���");
					}else{
						JOptionPane.showInternalMessageDialog(UpdateView.this, "ɾ��ʧ�ܣ����ܲ�����ӵ�����id�Ĵ�������");
					}
				}
				
				
			}
		});
		
	}
	
}
