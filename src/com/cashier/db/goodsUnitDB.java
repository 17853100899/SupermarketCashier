package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.cashier.util.goodsUnit;

/**
 * ��Ʒ��λ���ݲ���
 * 
 * @author Administrator
 *
 */
public class goodsUnitDB {
	private baseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	/**
	 * �������ݿ��������
	 */
	public goodsUnitDB() {
		if (basedb == null) {
			basedb = new baseDB();
		}
	}

	/**
	 * ��ѯ������Ʒ��λ��Ϣ
	 * 
	 * @return ��Ʒ��λ���󼯺�
	 */
	public Vector<Vector<String>> getGoodsUnit() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select * from GoodsUnit";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getInt("GUId")));
				v.addElement(rs.getString("GUName"));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ʒ��λ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ������Ʒ��λ��Ϣ����䵽�������������������
	 * 
	 * @return ��Ʒ��λ���󼯺�
	 */
	public Hashtable<Integer, String> getGoodsUnitForCombox() {
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		sql = "select * from GoodsUnit";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				ht.put(rs.getInt("GUId"), rs.getString("GUName"));
			}
			return ht;
		} catch (SQLException e) {
			System.out.println("��Ʒ��λ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����Ʒ��λ
	 * 
	 * @param goodsUnit
	 *            ����Ʒ��λ��Ϣ
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean saveGoodsClass(goodsUnit goodsUnit) {
		try {
			if (goodsUnit != null) {
				// ��֤�˺Ų������ظ�
				sql = "select * from GoodsUnit where guname='" + goodsUnit.getGuname() + "'";
				rs = basedb.executeQuery(sql);

				if (rs != null && rs.next()) {// ˵�������ݿ����Ѿ����ڸ��˺ţ��������
					return false;
				}
				sql = "insert into GoodsUnit values('" + goodsUnit.getGuname() + "')";
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
	 * �޸���Ʒ�����Ϣ
	 * 
	 * @param goodsClass
	 *            �޸ĵĶ���
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean updateGoodsClass(goodsUnit goodsUnit) {
		if (goodsUnit != null) {
			sql = "update goodsUnit set guname='" + goodsUnit.getGuname() + "' where guid=" + goodsUnit.getGuid();
			if (basedb.executeUpdate(sql)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ɾ����Ʒ�����Ϣ
	 * 
	 * @param goodsUnit������ID
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean delGoodsUnit(goodsUnit goodsUnit) {
		int guid = goodsUnit.getGuid();
		sql = "select * from GoodsUnit gc,Goods g where gc.guid=g.guid and gc.guid=" + guid;
		// ����Ƿ��й�������Ʒ
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "delete from GoodsUnit where guid=" + guid;
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	// �����Ʒ��λ���ı���
	public Vector<String> getColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("��λ���");
		vector.add("��λ����");
		return vector;
	}

	// hashtable����
	public static void main(String[] args) {
		goodsUnitDB goodsUnit = new goodsUnitDB();
		Hashtable<Integer, String> ht = goodsUnit.getGoodsUnitForCombox();
		Enumeration<Integer> eu = ht.keys();
		while (eu.hasMoreElements()) {
			Integer index = eu.nextElement();
			System.out.println("---" + ht.get(index));
		}
	}

}
