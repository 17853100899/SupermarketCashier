package com.cashier.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

	private static Connection conn = null;

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String username = "sa";

	private static String password = "suger007..";
	// ���ݿ��ַ�����ݿ�����
	private static String url = "jdbc:sqlserver://120.79.43.219:1433;DatabaseName=saleshop;";

	static {
		try {
			Class.forName(driver);
			System.out.println("JDBC�������سɹ���");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC��������ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return ���ݿ����Ӷ���
	 */
	public static Connection getConn() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				System.out.println("���ݿ����ӳɹ���");
			} catch (SQLException e) {
				System.out.println("���ݿ�����ʧ�ܣ�");
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * �������ݿ������Ƿ�ɹ�
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		connectionDB db = new connectionDB();
		db.getConn();
	}

}
