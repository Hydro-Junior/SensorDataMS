package com.xjy.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.xjy.biz.UserBiz;
import com.xjy.biz.impl.UserBizImpl;
import com.xjy.entity.User;

public class LoginView extends JFrame {
	private static final long serialVersionUID = -5626855053582276614L;
	private UserBiz userBiz ;
	private JPanel panel_main = null; //主面板
	private JPanel panel_left = null; //左面板
	private JPanel panel_right = null;//右面版
	
	private JLabel lb_uname = null; //用户标签
	private JLabel lb_upwd = null;//密码标签
	private JLabel lb_type = null;//登录类型标签
	
	private JTextField tf_uname = null; //用户文本框
	private JPasswordField pf_pass = null;//密码文本框
	
	private JLabel lb_img = null; //显示图片的标签
	
	private JButton btn_login = null; //登录按钮
	private JButton btn_register = null; //注册按钮
	
	private JComboBox<String> cb_type = null;//登录类型下拉列表框
	
	public LoginView(){
		userBiz = new UserBizImpl();
		init();
		registerListener();
	}
	
	//初始化控件的方法
	private void init(){
		this.setSize(320,220);//设置窗体大小
		this.setResizable(false);//不可拖动改变窗体大小
		this.setLocationRelativeTo(null); //窗体居中显示
		this.setTitle("登录窗口");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//初始化面板
		panel_main = new JPanel(new GridLayout(1,2));
		panel_left = new JPanel();
		panel_right = new JPanel(new GridLayout(4,2,0,10));
		
		//初始化控件
		tf_uname = new JTextField(8);
		pf_pass = new JPasswordField(8);
		cb_type = new JComboBox<String>(new String[]{"普通用户","管理员"});
		btn_login = new JButton("登录");
		btn_register = new JButton("注册");
		lb_uname = new JLabel("用   户：",JLabel.CENTER);
		lb_upwd = new JLabel("密   码：",JLabel.CENTER);
		lb_type = new JLabel("类   型：",JLabel.CENTER);
		lb_img = new JLabel(new ImageIcon(
				ClassLoader.getSystemResource("image/user.png")));
		
		//把相应的控件放到相应的面板中
		panel_left.add(lb_img);
		panel_right.add(lb_uname);
		panel_right.add(tf_uname);
		panel_right.add(lb_upwd);
		panel_right.add(pf_pass);
		panel_right.add(lb_type);
		panel_right.add(cb_type);
		panel_right.add(btn_login);
		panel_right.add(btn_register);
		
		//主面板中放左右两个面板
		panel_main.add(panel_left);
		panel_main.add(panel_right);
		
		//主面板放到窗体中
		this.getContentPane().add(panel_main);
		this.pack();
		this.setVisible(true);
	}
	
	private void registerListener(){
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取用户名和密码
				String uname = tf_uname.getText().trim();
				String upwd = new String(pf_pass.getPassword());
				int type = cb_type.getSelectedIndex()+1;
				if(uname.equals("")){
					JOptionPane.showMessageDialog(LoginView.this,"用户名不能为空！");
					return;
				}else if(upwd.equals("")){
					JOptionPane.showMessageDialog(LoginView.this,"密码不能为空！");
					return;
				}
				User user = new User(uname,upwd,type);
			    user = userBiz.login(user);
			    if(user != null){
			    	//普通用户
			    	if(user.getType()==1){
			    		new UserMainView();
			    	}else{
			    		new AdminView();
			    	}
			    	LoginView.this.dispose();
			    	
			    }else{
			    	JOptionPane.showMessageDialog(LoginView.this,"用户名或密码出错！");
			    }
				
			}
		});
	}
	
	static{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
