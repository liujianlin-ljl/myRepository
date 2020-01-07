package com.ljl.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;

public class JdbcUtil {
	
	private static String config="src/main/resources/jdbc-mysql.properties";
	private static String className="";
	private static String url="";
	private static String user="";
	private static String password="";
	
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	public static Connection getConnection() {
		return getConnection(config);
	}
	
	/**
	 * 获取数据库连接，通过properties文件获取数据库连接的参数
	 * @param file 配置文件的路径
	 * @return 连接对象
	 * @throws Exception 
	 */
	public static Connection getConnection(String conf){
		try {
			/**
			 * 创建输入流，读取配置文件，获取数据库连接参数
			 */
			InputStream in=new FileInputStream(conf);
			Properties pro=new Properties();
			pro.load(in);
			className=pro.getProperty("driverClass");
			url=pro.getProperty("url");
			user=pro.getProperty("user");
			password=pro.getProperty("password");
			//注册驱动
			Class.forName(className);
			//获取数据库连接
			con=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getConnection(InputStream in){
		try {
			Properties pro=new Properties();
			pro.load(in);
			className=pro.getProperty("driverClass");
			url=pro.getProperty("url");
			user=pro.getProperty("user");
			password=pro.getProperty("password");
			//注册驱动
			Class.forName(className);
			//获取数据库连接
			con=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 更新的通用方法，执行后自动关闭连接，无需手动关闭
	 * @param sql 更新的sql语句(update/insert/delete)
	 * @param params sql语句中占位符对应的参数(如果没有占位符，传入null)
	 */
	public static int update(String sql,Object[] params) {
		int row=0;//影响行数
		try {
			//创建预处理
			ps=con.prepareStatement(sql);
			//设置占位符参数
			//判断是否有参数
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			System.out.println(sql);
			//执行sql语句，更新操作
			row=ps.executeUpdate();
			//释放资源
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	/**
	 * 查询的通用方法，使用BeanUtils工具对查询的数据进行封装，执行查询后自动关闭连接，无需手动关闭
	 * 适用于mysql，此方法不能用于oracle数据库（不能封装数据）
	 * @param sql 查询的SQL语句
	 * @param params 占位符的参数，没有参数传入null
	 * @param clazz 要封装的类的类型
	 * @return 放回对应类型的list集合
	 */
	public static <T> List<T> query(String sql,Object[] params,Class<T> clazz){
		//创建返回的集合
		List<T> list=null;
		try {
			list = new ArrayList<T>();
			//创建对象
			T t=null;
			//创建预处理语句对象
			ps=con.prepareStatement(sql);
			//设置占位符参数
			if(params!=null && params.length!=0) {
				for(int i=0;i<params.length;i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			System.out.println(sql);
			//执行SQL查询
			rs=ps.executeQuery();
			//获取结果集元数据
			ResultSetMetaData rsmt=rs.getMetaData();
			//获取列的个数
			int columCount=rsmt.getColumnCount();
			//遍历结果集
			while(rs.next()) {
				//获取要封装的对象
				t=clazz.newInstance();
				//遍历每一行的每一列
				for(int i=0;i<columCount;i++) {
					//获取每一列的名称
					String columnName=rsmt.getColumnName(i+1);
					//获取每一列的名称对应的值
					Object value=rs.getObject(columnName);
					// 封装： 设置到t对象的属性中  【BeanUtils组件】
					BeanUtils.copyProperty(t, columnName, value);
				}
				//将封装好的对象放入list集合中
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	/**
	 * 查询通用方法，注意，此方法执行后不会自动关闭资源，需要调用者手动关闭
	 * @param sql 查询SQL语句
	 * @param params 占位符参数
	 * @return 结果集
	 */
	public static ResultSet query(String sql,Object[] params) {
		try {
			//获取预处理语句
			ps=con.prepareStatement(sql);
			//设置占位符参数
			if(params!=null && params.length!=0) {
				for(int i=0;i<params.length;i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			System.out.println(sql);
			//执行SQL查询
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 释放资源
	 */
	public static void close() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("资源关闭失败！");
			e.printStackTrace();
		}
	}
	
	

}
