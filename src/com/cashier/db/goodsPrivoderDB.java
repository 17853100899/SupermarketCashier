package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import com.cashier.util.goodsPrivoder;

public class goodsPrivoderDB {
	private baseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	/**
	 * �������ݿ��������
	 */
	public goodsPrivoderDB() {
		if (basedb == null) {
			basedb = new baseDB();
		}
	}

	/**
	 * ��ѯ������Ʒ��Ӧ����Ϣ
	 * 
	 * @return ��Ʒ��Ӧ�̶��󼯺�
	 */
	public Vector<Vector<String>> getGoodsPrivoder() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select * from GoodsPrivoder";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getInt("GPId")));
				v.addElement(rs.getString("GPName"));
				v.addElement(rs.getString("GPLinkman"));
				v.addElement(rs.getString("GPPhone"));
				v.addElement(rs.getString("GPAddress"));

				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ʒ��Ӧ�̲�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ��Ʒ��Ӧ����Ϣ����������������������
	 * 
	 * @return ��Ʒ��Ӧ�̶��󼯺�
	 */
	public Hashtable<Integer, String> getGoodsPrivoderForCombox() {
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		sql = "select gpid,gpname from GoodsPrivoder";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				ht.put(rs.getInt("GPId"), rs.getString("GPName"));
			}
			return ht;
		} catch (SQLException e) {
			System.out.println("��Ʒ��Ӧ�̲�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����Ʒ��Ӧ��
	 * 
	 * @param goodsClass
	 *            ����Ʒ��Ӧ����Ϣ
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean saveGoodsPrivoder(goodsPrivoder goodsPrivoder) {
		try {
			if (goodsPrivoder != null) {
				// ��֤�˺Ų������ظ�
				sql = "select * from GoodsPrivoder where gpname='" + goodsPrivoder.getGpname() + "'";
				rs = basedb.executeQuery(sql);

				if (rs != null && rs.next()) {// ˵�������ݿ����Ѿ����ڸ��˺ţ��������
					return false;
				}
				sql = "insert into GoodsPrivoder values('" + goodsPrivoder.getGpname() + "','"
						+ goodsPrivoder.getGpphone() + "','" + goodsPrivoder.getGpaddress() + "','"
						+ goodsPrivoder.getGplinkman() + "')";
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
	 * �޸���Ʒ��Ӧ����Ϣ
	 * 
	 * @param goodsClass
	 *            �޸ĵĶ���
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean updateGoodsPrivoder(goodsPrivoder goodsPrivoder) {
		if (goodsPrivoder != null) {
			sql = "update GoodsClass set gpname='" + goodsPrivoder.getGpname() + "',gpphone='"
					+ goodsPrivoder.getGpphone() + "',gpaddress='" + goodsPrivoder.getGpaddress() + "',gplinkman='"
					+ goodsPrivoder.getGplinkman() + "' where gpid=" + goodsPrivoder.getGpid();
			if (basedb.executeUpdate(sql)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ɾ����Ʒ�����Ϣ
	 * 
	 * @param user������ID
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean delGoodsPrivoder(goodsPrivoder goodsPrivoder) {
		int gpid = goodsPrivoder.getGpid();
		sql = "select * from GoodsPrivoder gp,InStore ins where gp.gpid=ins.gpid and gp.gpid=" + gpid;
		// ����Ƿ��й����Ľ�����
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "delete from GoodsPrivoder where gpid=" + gpid;
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ģ������ �����ݵ绰�����ƣ�
	 * 
	 * @return
	 */
	public Vector<Vector<String>> searchGoodsPrivoder(goodsPrivoder goodsPrivoder) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		String name = goodsPrivoder.getGpname();
		String phone = goodsPrivoder.getGpphone();
		sql = "select * from GoodsPrivoder where gpid<>0";
		if (name != null && !name.equals("")) {
			sql = sql + " and gpname like '%" + name + "%'";
		}
		if (phone != null && !phone.equals("")) {
			sql = sql + " and gpphone like '%" + phone + "%'";
		}
		System.out.println(sql);

		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getInt("GPId")));
				v.addElement(rs.getString("GPName"));
				v.addElement(rs.getString("GPLinkman"));
				v.addElement(rs.getString("GPPhone"));
				v.addElement(rs.getString("GPAddress"));

				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ʒ��Ӧ�̲�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// �����Ʒ��Ӧ�̱��ı���
	public Vector<String> getColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("���");
		vector.add("����");
		vector.add("��ϵ��");
		vector.add("�绰");
		vector.add("��ַ");
		return vector;
	}

}
