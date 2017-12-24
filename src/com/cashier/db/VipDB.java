package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.cashier.util.ShopVip;
import com.cashier.util.VipSaveMoney;

public class VipDB {
	private BaseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	public VipDB() {
		if (basedb == null) {
			basedb = new BaseDB();
		}
	}

	/**
	 * ��Ա��ֵ 1. ��ӳ�ֵ��¼ 2. �޸Ļ�Ա���
	 */
	public boolean updateMoney(VipSaveMoney vm) {
		if (vm == null) {
			return false;
		}
		sql = "select * from ShopVip where vid='" + vm.getVid() + "'";
		rs = basedb.executeQuery(sql);
		try {
			if (!rs.next()) {
				return false;
			} else {
				sql = "insert into VipSaveMoney values(" + vm.getVid() + ",'" + vm.getVtime() + "'," + vm.getVMoney()
						+ ",'" + vm.getUId() + "') ";
				System.out.println("��Ա��ֵ��䣺" + sql);
				if (basedb.executeUpdate(sql)) {
					sql = "update ShopVip set VBalance=VBalance+" + vm.getVMoney() + " where VId='" + vm.getVid() + "'";
					System.out.println("�޸Ļ�Ա��" + sql);
					if (basedb.executeUpdate(sql)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}// end

	/**
	 * �޸Ļ������ֵ
	 * 
	 * @return
	 */
	public boolean encashIntegral(String vid, int score) {
		sql = "update ShopVip set ConsumeScore=ConsumeScore-" + score + " where vid='" + vid + "'";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ����Ա�Ƿ����
	 * 
	 * @param vid
	 *            ��Ա�˺�
	 * @return ״̬
	 */
	public boolean checkVid(String vid) {
		if (vid.equals("") || vid == null) {
			return false;
		}
		sql = "select * from ShopVip where vid='" + vid + "'";
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ���ݻ�Ա�˺ŷ��ػ���
	 * 
	 * @param vid
	 *            ��Ա�˺�
	 * @return ����
	 */
	public String searchIntegral(String vid) {
		String consumeScore = null;
		if (vid.equals("") || vid == null) {
			return null;
		}
		if (!checkVid(vid)) {
			return null;
		}
		sql = "select ConsumeScore from ShopVip where VId='" + vid + "'";
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				consumeScore = String.valueOf(rs.getInt(1));
				return consumeScore;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ��Ա��ϸ��Ϣ
	 * 
	 * @return ��Ա��ϸ��Ϣ����
	 */
	public Vector<Vector<String>> getShopVIP(ShopVip vip) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select * from ShopVip where vid is not null";
		String vid = vip.getVId();
		String name = vip.getVName();
		String phone = vip.getVPhone();
		if (vip != null) {
			if (!vid.equals("")) {
				sql = sql + " and vid like '%" + vid + "%'";
			}
			if (!name.equals("")) {
				sql = sql + " and VName like '%" + name + "%'";
			}
			if (!phone.equals("")) {
				sql = sql + " and VPhone like '%" + phone + "%'";
			}
		}

		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(rs.getString(1));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(String.valueOf(rs.getFloat(4)));
				v.addElement(String.valueOf(rs.getInt(5)));
				v.addElement(String.valueOf(rs.getInt(6)));
				v.addElement(String.valueOf(rs.getFloat(7)));
				v.addElement(String.valueOf(rs.getDate(8)));
				v.addElement(String.valueOf(rs.getFloat(9)));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// ����Ա��Ϣ���ı���
	public Vector<String> getVipColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("��Ա����");
		vector.add("����");
		vector.add("�绰");
		vector.add("�����ܽ��");
		vector.add("����");
		vector.add("���Ѵ���");
		vector.add("������");
		vector.add("����");
		vector.add("���");
		return vector;
	}// end

	/**
	 * ��ӻ�Ա
	 * 
	 * @param vip
	 *            ��Ա����
	 * @return ״̬
	 */
	public boolean saveShopVip(ShopVip vip) {
		if (vip == null) {
			return false;
		}
		if (checkVid(vip.getVId())) {
			return false;
		}
		sql = "insert into ShopVip values('" + vip.getVId() + "','" + vip.getVName() + "','" + vip.getVPhone() + "',"
				+ vip.getConsumeSum() + "," + vip.getConsumeScore() + "," + vip.getConsumeCount() + ","
				+ vip.getConsumeRate() + ",'" + vip.getVBirthday() + "'," + vip.getVBalance() + ")";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * �޸Ļ�Ա��Ϣ
	 * 
	 * @param v
	 * @return
	 */
	public boolean updateVip(ShopVip v) {
		if (v == null) {
			return false;
		}
		sql = "update shopvip set vname='" + v.getVName() + "',vphone='" + v.getVPhone() + "',vbirthday='"
				+ v.getVBirthday() + "',ConsumeScore=" + v.getConsumeScore() + ",VBalance=" + v.getVBalance()
				+ ",ConsumeRate=" + v.getConsumeRate() + " where vid='" + v.getVId() + "'";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ɾ����Ա�������Ѽ�¼����ɾ��
	 * 
	 * @param vid
	 * @return
	 */
	public boolean delVip(String vid) {
		if (vid == null || vid.equals("")) {
			return false;
		}
		sql = "select * from shopvip s,vipsavemoney v where s.vid=v.vid and s.vid='" + vid + "'";
		rs = basedb.executeQuery(sql);
		try {
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "delete from shopvip where vid='" + vid + "'";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new VipDB().searchIntegral("56071"));
	}
}
