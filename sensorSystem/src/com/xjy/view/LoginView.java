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
	private JPanel panel_main = null; //�����
	private JPanel panel_left = null; //�����
	private JPanel panel_right = null;//�����
	
	private JLabel lb_uname = null; //�û���ǩ
	private JLabel lb_upwd = null;//�����ǩ
	private JLabel lb_type = null;//��¼���ͱ�ǩ
	
	private JTextField tf_uname = null; //�û��ı���
	private JPasswordField pf_pass = null;//�����ı���
	
	private JLabel lb_img = null; //��ʾͼƬ�ı�ǩ
	
	private JButton btn_login = null; //��¼��ť
	private JButton btn_register = null; //ע�ᰴť
	
	private JComboBox<String> cb_type = null;//��¼���������б��
	
	public LoginView(){
		userBiz = new UserBizImpl();
		init();
		registerListener();
	}
	
	//��ʼ���ؼ��ķ���
	private void init(){
		this.setSize(320,220);//���ô����С
		this.setResizable(false);//�����϶��ı䴰���С
		this.setLocationRelativeTo(null); //���������ʾ
		this.setTitle("��¼����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʼ�����
		panel_main = new JPanel(new GridLayout(1,2));
		panel_left = new JPanel();
		panel_right = new JPanel(new GridLayout(4,2,0,10));
		
		//��ʼ���ؼ�
		tf_uname = new JTextField(8);
		pf_pass = new JPasswordField(8);
		cb_type = new JComboBox<String>(new String[]{"��ͨ�û�","����Ա"});
		btn_login = new JButton("��¼");
		btn_register = new JButton("ע��");
		lb_uname = new JLabel("��   ����",JLabel.CENTER);
		lb_upwd = new JLabel("��   �룺",JLabel.CENTER);
		lb_type = new JLabel("��   �ͣ�",JLabel.CENTER);
		lb_img = new JLabel(new ImageIcon(
				ClassLoader.getSystemResource("image/user.png")));
		
		//����Ӧ�Ŀؼ��ŵ���Ӧ�������
		panel_left.add(lb_img);
		panel_right.add(lb_uname);
		panel_right.add(tf_uname);
		panel_right.add(lb_upwd);
		panel_right.add(pf_pass);
		panel_right.add(lb_type);
		panel_right.add(cb_type);
		panel_right.add(btn_login);
		panel_right.add(btn_register);
		
		//������з������������
		panel_main.add(panel_left);
		panel_main.add(panel_right);
		
		//�����ŵ�������
		this.getContentPane().add(panel_main);
		this.pack();
		this.setVisible(true);
	}
	
	private void registerListener(){
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȡ�û���������
				String uname = tf_uname.getText().trim();
				String upwd = new String(pf_pass.getPassword());
				int type = cb_type.getSelectedIndex()+1;
				if(uname.equals("")){
					JOptionPane.showMessageDialog(LoginView.this,"�û�������Ϊ�գ�");
					return;
				}else if(upwd.equals("")){
					JOptionPane.showMessageDialog(LoginView.this,"���벻��Ϊ�գ�");
					return;
				}
				User user = new User(uname,upwd,type);
			    user = userBiz.login(user);
			    if(user != null){
			    	//��ͨ�û�
			    	if(user.getType()==1){
			    		new UserMainView();
			    	}else{
			    		new AdminView();
			    	}
			    	LoginView.this.dispose();
			    	
			    }else{
			    	JOptionPane.showMessageDialog(LoginView.this,"�û������������");
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
