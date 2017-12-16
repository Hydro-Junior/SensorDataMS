package com.xjy.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/sensormng?useSSL=false&useUnicode=true&characterEncoding=utf-8";
	private final String sqluser = "root";
	private final String sqlpwd = "xjy123";
	
	//�����ݿ⽨�����ӣ��������ݿ����Ӷ���
	public Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(URL, sqluser, sqlpwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ͷ���Ӧ����Դ
	 * @param rs
	 * @param pstmt
	 * @param coon
	 */
	public void closeAll(ResultSet rs , PreparedStatement pstmt, Connection coon){
		try {
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(coon != null){
				coon.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �˷������������ɾ�����в���
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean operUpdate(String sql,List<Object> params){ //sql���ʺű�ʾռλ��
		int res = 0;  //Ӱ�������
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn =getConn(); //�������ݿ�����
			pstmt = conn.prepareStatement(sql); //װ��sql���
			if(params != null){
				//�����У�ռλ������ִ��ǰ�滻�ʺ�ռλ��
				for(int i = 0; i<params.size();i++){
					pstmt.setObject(i+1,params.get(i));
				}
			}
			res = pstmt.executeUpdate(); //������ɾ�Ĳ���
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(rs,pstmt,conn);
		}
		return res>0? true:false;
	}
	/**
	 * ʹ�÷��ͷ����ͷ�����ƽ��з�װ,ʵ�ֲ�ѯ
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> operQuery(String sql, List<Object> params,Class<T> cls) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> data = new ArrayList<T>();
		
		try {
			conn = getConn(); //�������ݿ�����
			pstmt = conn.prepareStatement(sql); //װ��sql���
			if(params != null){
				//�����У�ռλ������ִ��ǰ�滻�ʺ�ռλ��
				for(int i = 0; i<params.size();i++){
					pstmt.setObject(i+1,params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			//�Ѳ�ѯ�����ļ�¼��װ�ɶ�Ӧ��ʵ�������
			ResultSetMetaData rsd = rs.getMetaData(); //�õ���¼��Ԫ���ݶ���
			//ͨ���˶�����Եõ���Ľṹ�������������еĸ������е���������
			while(rs.next()){
				T m = cls.newInstance();
				for(int i = 0; i<rsd.getColumnCount();i++){
					String col_name = rsd.getColumnName(i+1); //�������
					Object value = rs.getObject(col_name);//����ж�Ӧ��ֵ
					Field field = cls.getDeclaredField(col_name);
					field.setAccessible(true);//��˽���������ÿɷ���Ȩ
					field.set(m,value);//�������˽�����Ը�ֵ
				}
				data.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(rs,pstmt,conn);
		}
		return data;
	}
	
	
	
	
	
	
	
	
	
	
}
