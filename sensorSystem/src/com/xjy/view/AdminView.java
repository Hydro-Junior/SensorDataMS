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
	private JPanel main_panel = null; //主面板
	private JPanel wel_panel = null; //欢迎面板
	private JPanel btn_panel = null; //按钮组面板
	private JDesktopPane funcDesktop = null;//桌面面板
	
	private JButton btn_querysensor = null;//传感器查询按钮
	private JButton btn_data = null; //数据查询
	private JButton btn_record = null;//记录按钮
	private JButton btn_exit = null; //退出按钮
	private JButton btn_sts = null;  //统计按钮
	private JLabel deskLabel = null; //存放图片
	private JLabel lb_welcome = null;//存放欢迎字幕
	
	public  AdminView(){
		init();
		registerListener();
	}
	
	private void init(){
		main_panel = new JPanel(new BorderLayout());
		btn_panel = new JPanel(new GridLayout(7,1,0,35));
		btn_querysensor = new JButton("查询传感器");
		btn_data = new JButton("数据查询");
		btn_record = new JButton("传感器管理"); 
		btn_sts = new JButton("数据管理");
		btn_exit = new JButton("退出窗口");
		//用来填充的标签控件
		btn_panel.add(new JLabel());
		
		btn_panel.add(btn_querysensor);
		btn_panel.add(btn_record);
		btn_panel.add(btn_data);
		btn_panel.add(btn_sts);
		
		btn_panel.add(btn_exit);
		btn_panel.add(new JLabel());
		//设置面板的边框外观
		btn_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(),"快捷功能区"));
		//欢迎面板
		wel_panel = new JPanel();
		lb_welcome  = new JLabel("欢   迎   使   用   环   境   数   据   管   理   系   统");
		lb_welcome.setFont(new Font("宋体",Font.BOLD,20));
		lb_welcome.setForeground(Color.GREEN);
		wel_panel.add(lb_welcome);
		
		//初始化桌面面板
		funcDesktop = new JDesktopPane();
		//ImageIcon image = new ImageIcon("src/image/environment.png"); 
		ImageIcon image = new ImageIcon(
				ClassLoader.getSystemResource("image/environment.png"));
		deskLabel = new JLabel(image);
		deskLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		funcDesktop.add(deskLabel,new Integer(Integer.MIN_VALUE));
		
		//把3个面板放到主面板
		main_panel.add(btn_panel,BorderLayout.EAST);
		main_panel.add(wel_panel,BorderLayout.NORTH);
		main_panel.add(funcDesktop,BorderLayout.CENTER);
		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				new Thread(new DynamicThread()).start();
				}
			
		});
		
		this.setTitle("环境数据管理系统");
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
	 * 线程类，用于移动label标签
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
