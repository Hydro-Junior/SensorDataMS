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
	
	//与数据库建立连接，返回数据库连接对象
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
	 * 释放相应的资源
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
	 * 此方法可以完成增删改所有操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean operUpdate(String sql,List<Object> params){ //sql中问号表示占位符
		int res = 0;  //影响的行数
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn =getConn(); //建立数据库连接
			pstmt = conn.prepareStatement(sql); //装载sql语句
			if(params != null){
				//假如有？占位符，在执行前替换问号占位符
				for(int i = 0; i<params.size();i++){
					pstmt.setObject(i+1,params.get(i));
				}
			}
			res = pstmt.executeUpdate(); //进行增删改操作
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(rs,pstmt,conn);
		}
		return res>0? true:false;
	}
	/**
	 * 使用泛型方法和反射机制进行封装,实现查询
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
			conn = getConn(); //建立数据库连接
			pstmt = conn.prepareStatement(sql); //装载sql语句
			if(params != null){
				//假如有？占位符，在执行前替换问号占位符
				for(int i = 0; i<params.size();i++){
					pstmt.setObject(i+1,params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			//把查询出来的记录封装成对应的实体类对象
			ResultSetMetaData rsd = rs.getMetaData(); //得到记录集元数据对象
			//通过此对象可以得到表的结构，包括列名、列的个数、列的数据类型
			while(rs.next()){
				T m = cls.newInstance();
				for(int i = 0; i<rsd.getColumnCount();i++){
					String col_name = rsd.getColumnName(i+1); //获得列名
					Object value = rs.getObject(col_name);//获得列对应的值
					Field field = cls.getDeclaredField(col_name);
					field.setAccessible(true);//给私有属性设置可访问权
					field.set(m,value);//给对象的私有属性赋值
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
