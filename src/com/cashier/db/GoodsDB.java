package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.cashier.util.Goods;
import com.cashier.util.GoodsClass;
import com.cashier.util.InStore;

public class GoodsDB {
	private BaseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	/**
	 * �������ݿ��������
	 */
	public GoodsDB() {
		if (basedb == null) {
			basedb = new BaseDB();
		}
	}

	/**
	 * ��ѯ������Ʒ�����Ϣ
	 * 
	 * @return ��Ʒ�����󼯺�
	 */
	public Vector<Vector<String>> getGoodsClass() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select * from GoodsClass";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getInt("CId")));
				v.addElement(rs.getString("CName"));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ʒ������ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ������Ʒ�����Ϣ,��������������������
	 * 
	 * @return ��Ʒ�����󼯺�
	 */
	public Hashtable<Integer, String> getGoodsClassForCombox() {
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		sql = "select * from GoodsClass";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				ht.put(rs.getInt("CId"), rs.getString("CName"));
			}
			return ht;
		} catch (SQLException e) {
			System.out.println("��Ʒ������ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����Ʒ���
	 * 
	 * @param goodsClass
	 *            ����Ʒ�����Ϣ
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean saveGoodsClass(GoodsClass goodsClass) {
		try {
			if (goodsClass != null) {
				// ��֤�˺Ų������ظ�
				sql = "select * from GoodsClass where cname='" + goodsClass.getCName() + "'";
				rs = basedb.executeQuery(sql);

				if (rs != null && rs.next()) {// ˵�������ݿ����Ѿ����ڸ��˺ţ��������
					return false;
				}
				sql = "insert into GoodsClass values('" + goodsClass.getCName() + "')";
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
	public boolean updateGoodsClass(GoodsClass goodsClass) {
		if (goodsClass != null) {
			sql = "update GoodsClass set cname='" + goodsClass.getCName() + "' where cid=" + goodsClass.getCId();
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
	public boolean delGoodsClass(GoodsClass goodsClass) {
		int cid = goodsClass.getCId();
		sql = "select * from GoodsClass gc,Goods g where gc.cid=g.cid and gc.cid=" + cid;
		// ����Ƿ��й�������Ʒ
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "delete from GoodsClass where cid=" + cid;
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	// �����Ʒ�����ı���
	public Vector<String> getColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("�����");
		vector.add("�������");
		return vector;
	}// end

	/** *****************��ˮ������****************** */
	// ������ˮ��
	public String createInStoreId() {
		String inStoreId = "";
		String year, month, day;
		String tempdate;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		inStoreId = sdf.format(calendar.getTime());// ���ڸ�ʽ���ַ���
		// ���ַ����ԡ�-�����зָ�
		StringTokenizer st = new StringTokenizer(inStoreId, "-");
		year = st.nextToken();// ���
		month = st.nextToken();// �·�
		day = st.nextToken();// ��
		tempdate = year + month + day;
		if (checkDate(tempdate)) {
			Vector<String> vector = getInStoreId(tempdate);
			String k = vector.get(0).substring(8);
			int ad = Integer.valueOf(k);
			if (ad < 10) {
				inStoreId = tempdate + "0" + (Integer.valueOf(k) + 1);

			} else {
				inStoreId = tempdate + (Integer.valueOf(k) + 1);

			}

		} else {
			inStoreId = tempdate + "01";
		}
		return inStoreId;

	}

	// �������Ƿ��Ѿ�������ˮ��
	public boolean checkDate(String datestr) {
		Vector<String> list = getInStoreId(datestr);
		System.out.println(list.size() + "");
		if (list.size() > 0) {
			return true;
		}
		return false;

	}

	/** **************** ������ά�� ************************* */
	/**
	 * ��ѯ���еĽ�������ˮ��
	 * 
	 * @return ��ˮ�ż���
	 */
	public Vector<String> getInStoreId(String str) {
		Vector<String> list = new Vector<String>();
		sql = "select InStoreId from InStore where InStoreId like '" + str + "%' order by InStoreId desc";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				list.add(rs.getString("InStoreId"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * ������������Ƿ���ڸ�������
	 * 
	 * @param gid
	 *            ������
	 * @return ״̬
	 */
	public boolean checkGid(String gid) {
		sql = "select gid from Goods where gid='" + gid + "'";
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
	 * �����Ʒ��Ϣ
	 * 
	 * @param GoodsClass
	 *            ����Ʒ�����Ϣ
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean saveGoods(Goods goods) {
		try {
			if (goods != null) {
				// ��֤��Ʒ���Ʋ������ظ�
				sql = "select * from Goods where gname='" + goods.getGName() + "'";
				System.out.println(sql);
				rs = basedb.executeQuery(sql);

				if (rs != null && rs.next()) {// ˵�������ݿ����Ѿ����ڸ��˺ţ��������
					return false;
				}
				sql = "insert into Goods values('" + goods.getGid() + "','" + goods.getCid() + "','" + goods.getGName()
						+ "','" + goods.getGSpec() + "','" + goods.getGUId() + "','" + goods.getGMinNumber() + "','"
						+ goods.getSalePrice() + "','" + goods.getVipPrice() + "','" + goods.getGAmount() + "')";
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
	 * �����������ѯ��Ʒ��Ϣ
	 * 
	 * @param gid
	 *            ������
	 * @return ��Ʒ��Ϣ
	 */
	public Goods searchGoods(String gid) {
		Goods g;
		if (gid == null || gid.equals("")) {
			return null;
		}
		sql = "select * from Goods where gid='" + gid + "'";
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				g = new Goods();
				g.setGid(rs.getString("GId"));
				g.setCid(rs.getInt("CId"));
				g.setGName(rs.getString("GName"));
				g.setGSpec(rs.getString("GSpec"));
				g.setGUId(rs.getInt("GUId"));
				g.setGMinNumber(rs.getInt("GMinNumber"));
				g.setSalePrice(rs.getFloat("SalePrice"));
				g.setVipPrice(rs.getFloat("VipPrice"));
				g.setGAmount(rs.getInt("GAmount"));
				return g;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}// end

	/**
	 * ������Ʒ���
	 * 
	 * @param gid
	 *            ������
	 * @param amount
	 *            ���
	 * @return ״̬
	 */
	public boolean updateGAmount(String gid, int amount) {
		sql = "update goods set GAmount=GAmount+" + amount + " where gid='" + gid + "'";
		System.out.println(sql);
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ������ⵥ��Ϣ
	 * 
	 * @param instore
	 *            ��ⵥ����
	 * @return ״̬
	 */
	public boolean saveInStore(InStore instore) {
		if (instore != null) {
			sql = "insert into InStore values('" + instore.getInStoreId() + "','" + instore.getGid() + "',"
					+ instore.getGpid() + ",'" + instore.getInStoreTime() + "','" + instore.getInStoreAmount() + "',"
					+ instore.getPurchasePrice() + ")";
			System.out.println(sql);
			if (basedb.executeUpdate(sql)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * ��ѯ������Ʒ��Ϣ
	 * 
	 * @return ��Ʒ���󼯺�
	 */
	public Vector<Vector<String>> getGoods() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select g.GId,gc.cname,g.gname,g.GSpec,gu.guname,GMinNumber,SalePrice,VipPrice,GAmount from goods g,goodsclass gc,goodsunit gu where g.cid=gc.cid and g.guid=gu.guid";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();

				v.addElement(rs.getString(1));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(rs.getString(4));
				v.addElement(rs.getString(5));
				v.addElement(String.valueOf(rs.getInt(6)));
				v.addElement(String.valueOf(rs.getFloat(7)));
				v.addElement(String.valueOf(rs.getFloat(8)));
				v.addElement(String.valueOf(rs.getInt(9)));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ʒ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ������Ʒ��Ϣ
	 * 
	 * @return ��Ʒ���󼯺�
	 */
	public Vector<Vector<String>> getGoods(Goods goods) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select g.GId,gc.cname,g.gname,g.GSpec,gu.guname,GMinNumber,SalePrice,VipPrice,GAmount from goods g,goodsclass gc,goodsunit gu where g.cid=gc.cid and g.guid=gu.guid";
		if (!goods.getGid().equals("")) {
			sql = sql + " and gid like '%" + goods.getGid() + "%'";
		}

		if (!goods.getGName().equals("")) {
			sql = sql + " and gname like '%" + goods.getGName() + "%'";
		}
		if (goods.getCid() > 0) {
			sql = sql + " and g.cid=" + goods.getCid();
		}
		System.out.println(sql);

		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();

				v.addElement(rs.getString(1));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(rs.getString(4));
				v.addElement(rs.getString(5));
				v.addElement(String.valueOf(rs.getInt(6)));
				v.addElement(String.valueOf(rs.getFloat(7)));
				v.addElement(String.valueOf(rs.getFloat(8)));
				v.addElement(String.valueOf(rs.getInt(9)));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ʒ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// �����Ʒ��Ϣ���ı���
	public Vector<String> getGoodsColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("������");
		vector.add("����");
		vector.add("����");
		vector.add("���");
		vector.add("��λ");
		vector.add("������");
		vector.add("�ۼ�");
		vector.add("��Ա��");
		vector.add("���");
		return vector;
	}// end

	/**
	 * �޸���Ʒ��Ϣ
	 * 
	 * @param good
	 *            ��Ʒ����
	 * @return ״̬
	 */
	public boolean updateGoods(Goods good) {
		if (good == null) {
			return false;
		}
		sql = "update goods set CId=" + good.getCid() + ",GName='" + good.getGName() + "',GSpec='" + good.getGSpec()
				+ "',GUId=" + good.getGUId() + ",GMinNumber=" + good.getGMinNumber() + ",SalePrice="
				+ good.getSalePrice() + ",VipPrice=" + good.getVipPrice() + " where GId='" + good.getGid() + "'";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ɾ����Ʒ��Ϣ
	 * 
	 * @param user������ID
	 * @return ����״̬��true���ɹ� false:ʧ�ܣ�
	 */
	public boolean delGoods(String gid) {
		sql = "select * from Goods g,Instore i where g.gid=i.gid and g.gid='" + gid + "'";
		// sql = "select * from SellDetail sd,Goods g,Instore i where
		// sd.gid=g.gid and g.gid=i.gid and g.gid='"+gid+"'";
		// ����Ƿ��й�������Ʒ
		rs = basedb.executeQuery(sql);
		System.out.println(sql);
		try {
			if (rs != null && rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "delete from Goods where gid='" + gid + "'";
		System.out.println(sql);
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Goods god = new Goods();
		god.setGName("ǡǡ����");
		System.out.println(new GoodsDB().saveGoods(god));
	}

}
