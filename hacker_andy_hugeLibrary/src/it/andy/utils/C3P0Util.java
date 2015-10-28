package it.andy.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//C3P0数据源工具类
public class C3P0Util {
	
	//创建数据源对象
	private static ComboPooledDataSource ds = new ComboPooledDataSource();//使用默认配置（default-config）
	//private static ComboPooledDataSource ds = new ComboPooledDataSource("day18");//使用命名配置（name-config  name属性名就是方法的参数值）
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	
	/**
	 * 获取连接的方法
	 * @return
	 */
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 释放资源的方法
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();//这个方法已经被带了，放回连接池
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
