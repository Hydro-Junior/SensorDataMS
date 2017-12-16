package com.xjy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class AdminView extends JFrame {
	private static final long serialVersionUID = 49197746368973685L;
	private JPanel main_panel = null; //�����
	private JPanel wel_panel = null; //��ӭ���
	private JPanel btn_panel = null; //��ť�����
	private JDesktopPane funcDesktop = null;//�������
	
	private JButton btn_querysensor = null;//��������ѯ��ť
	private JButton btn_data = null; //���ݲ�ѯ
	private JButton btn_record = null;//��¼��ť
	private JButton btn_exit = null; //�˳���ť
	private JButton btn_sts = null;  //ͳ�ư�ť
	private JLabel deskLabel = null; //���ͼƬ
	private JLabel lb_welcome = null;//��Ż�ӭ��Ļ
	
	public  AdminView(){
		init();
		registerListener();
	}
	
	private void init(){
		main_panel = new JPanel(new BorderLayout());
		btn_panel = new JPanel(new GridLayout(7,1,0,35));
		btn_querysensor = new JButton("��ѯ������");
		btn_data = new JButton("���ݲ�ѯ");
		btn_record = new JButton("����������"); 
		btn_sts = new JButton("���ݹ���");
		btn_exit = new JButton("�˳�����");
		//�������ı�ǩ�ؼ�
		btn_panel.add(new JLabel());
		
		btn_panel.add(btn_querysensor);
		btn_panel.add(btn_record);
		btn_panel.add(btn_data);
		btn_panel.add(btn_sts);
		
		btn_panel.add(btn_exit);
		btn_panel.add(new JLabel());
		//�������ı߿����
		btn_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(),"��ݹ�����"));
		//��ӭ���
		wel_panel = new JPanel();
		lb_welcome  = new JLabel("��   ӭ   ʹ   ��   ��   ��   ��   ��   ��   ��   ϵ   ͳ");
		lb_welcome.setFont(new Font("����",Font.BOLD,20));
		lb_welcome.setForeground(Color.GREEN);
		wel_panel.add(lb_welcome);
		
		//��ʼ���������
		funcDesktop = new JDesktopPane();
		//ImageIcon image = new ImageIcon("src/image/environment.png"); 
		ImageIcon image = new ImageIcon(
				ClassLoader.getSystemResource("image/environment.png"));
		deskLabel = new JLabel(image);
		deskLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		funcDesktop.add(deskLabel,new Integer(Integer.MIN_VALUE));
		
		//��3�����ŵ������
		main_panel.add(btn_panel,BorderLayout.EAST);
		main_panel.add(wel_panel,BorderLayout.NORTH);
		main_panel.add(funcDesktop,BorderLayout.CENTER);
		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				new Thread(new DynamicThread()).start();
				}
			
		});
		
		this.setTitle("�������ݹ���ϵͳ");
		this.getContentPane().add(main_panel);
		this.setSize(1000,650);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		
	}
	
	private void registerListener(){
		
		btn_querysensor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SensorqueView sqv = new SensorqueView();
				funcDesktop.add(sqv);
				sqv.toFront();
				
			}
		});
		
		btn_data.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DataqueView dtv = new DataqueView();
				funcDesktop.add(dtv);
				dtv.toFront();
				
			}
		}
		);
		
		btn_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateView udv = new UpdateView();
				funcDesktop.add(udv);
				udv.toFront();
				
			}
		});
		
		btn_sts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatisticView stv = new StatisticView();
				funcDesktop.add(stv);
				stv.toFront();
				
			}
		});
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminView.this.setVisible(false);
				System.exit(0);
				}
		});
	}
	
	
	
	
	
	
	
	
	/**
	 * �߳��࣬�����ƶ�label��ǩ
	 * @author zs
	 *
	 */
	private class DynamicThread implements Runnable{

		@Override
		public void run() {
			while(true){
				for(int i = 700;i>-650;i--){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						lb_welcome.setLocation(i,5);
				}
			}
			
		}
		
	}
	
	
	
	
	
	static{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
