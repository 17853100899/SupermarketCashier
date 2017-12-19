package com.cashier.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

	private static Connection conn = null;

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String username = "sa";

	private static String password = "suger007..";
	// 数据库地址；数据库名。
	private static String url = "jdbc:sqlserver://120.79.43.219:1433;DatabaseName=saleshop;";

	static {
		try {
			Class.forName(driver);
			System.out.println("JDBC驱动加载成功！");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC驱动加载失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return 数据库连接对象
	 */
	public static Connection getConn() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				System.out.println("数据库连接成功！");
			} catch (SQLException e) {
				System.out.println("数据库连接失败！");
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * 测试数据库连接是否成功
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		connectionDB db = new connectionDB();
		db.getConn();
	}

}
