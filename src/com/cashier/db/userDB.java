package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.cashier.util.supermarketUser;

public class userDB {

	private baseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	private supermarketUser user = null;

	/**
	 * �������ݿ��������
	 */
	public userDB() {
		if (basedb == null) {
			basedb = new baseDB();
		}
	}

	/**
	 * ��ѯ��һ�û���Ϣ
	 * 
	 * @param uName
	 *            �û���
	 * @param uPassword
	 *            ����
	 * @return �û�����
	 */
	public supermarketUser checkUser(String uid, String uPassword) {

		sql = "select * from SuperMarketUser where UId='" + uid + "' and UPassword='" + uPassword + "'";
		System.out.println(sql);
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				user = new supermarketUser();
				user.setUId(rs.getString("UId"));
				user.setUName(rs.getString("UName"));
				user.setUPassword(rs.getString("UPassword"));
				user.setURole(rs.getString("URole"));
				return user;
			}
		} catch (SQLException e) {
			System.out.println("�û����ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ����ϵͳ�û���Ϣ
	 * 
	 * @return �û����󼯺�
	 */
	public Vector<Vector<String>> getUsers() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select * from SuperMarketUser";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(rs.getString("UName"));
				v.addElement(rs.getString("UId"));
				v.addElement(rs.getString("UPassword"));
				v.addElement(rs.getString("URole"));

				vector.addElement(v);

			}
			return vector;
		} catch (SQLException e) {
			System.out.println("�û����ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ɾ���û���Ϣ
	 * 
	 * @param user������ID
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean delUser(supermarketUser user) {
		sql = "delete from SuperMarketUser where UId='" + user.getUId() + "'";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param user
	 *            �޸ĵĶ���
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean updateUser(supermarketUser user) {
		if (user != null) {
			sql = "update SuperMarketUser set UName='" + user.getUName() + "' ,UPassword='" + user.getUPassword()
					+ "',URole='" + user.getURole() + "' where UId='" + user.getUId() + "'";
			if (basedb.executeUpdate(sql)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ����û�
	 * 
	 * @param user
	 *            ���û���Ϣ
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean saveUser(supermarketUser user) {
		try {
			if (user != null) {
				// ��֤�˺Ų������ظ�
				sql = "select * from SuperMarketUser where uid='" + user.getUId() + "'";
				System.out.println(sql);
				rs = basedb.executeQuery(sql);

				if (rs != null && rs.next()) {// ˵�������ݿ����Ѿ����ڸ��˺ţ��������
					System.out.println("+++++++++++++++++");
					return false;
				}
				sql = "insert into SuperMarketUser values('" + user.getUId() + "','" + user.getUName() + "','"
						+ user.getUPassword() + "','" + user.getURole() + "')";
				System.out.println(sql);

				if (basedb.executeUpdate(sql)) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("���ݲ���ʧ�ܣ�");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �޸�����
	 * 
	 * @param uid
	 *            �˺�
	 * @param upassword
	 *            ������
	 * @return ����״̬
	 */
	public boolean updatePass(String uid, String upassword) {
		if (upassword == null || upassword.equals("")) {
			return false;
		}
		sql = "update SuperMarketUser set upassword='" + upassword + "' where uid='" + uid + "'";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;

	}

	// ����û�table�ı���
	public Vector<String> getColoumn() {

		Vector<String> vector = new Vector<String>();
		vector.add("��ʵ����");
		vector.add("�˺�");
		vector.add("����");
		vector.add("��ɫ");
		return vector;

	}

}
