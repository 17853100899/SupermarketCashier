package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import com.cashier.util.SaleGoodBean;
import com.cashier.util.Sell;
import com.cashier.util.SellDetail;
import com.cashier.util.ShopVip;

public class SaleDB {
	private BaseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	private String sidd = "";

	/**
	 * �������ݿ��������
	 */
	public SaleDB() {
		if (basedb == null) {
			basedb = new BaseDB();
		}
	}

	/**
	 * �����������ѯ��Ʒ��Ϣ
	 * 
	 * @return ��Ʒ���󼯺�
	 */
	public SaleGoodBean SearchSaleGood(String gid) {
		SaleGoodBean gb = new SaleGoodBean();
		sql = "select g.GId,g.gname,g.GSpec,gu.guname,SalePrice,VipPrice from goods g,goodsclass gc,goodsunit gu where g.cid=gc.cid and g.guid=gu.guid and g.gid='"
				+ gid + "'";

		System.out.println(sql);

		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				gb.setGid(rs.getString(1));
				gb.setGName(rs.getString(2));
				gb.setGSpec(rs.getString(3));
				gb.setGuname(rs.getString(4));
				gb.setGAmount(1);
				gb.setSalePrice(rs.getFloat(5));
				gb.setVipPrice(rs.getFloat(6));

			}
			return gb;
		} catch (SQLException e) {
			System.out.println("��Ʒ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��������Ļ�Ա������ʾ�����������
	 * 
	 */
	public ShopVip SearchShopVip(String vid) {
		ShopVip sv = new ShopVip();
		sql = "select vname,ConsumeScore,VBalance,ConsumeSum,ConsumeCount,ConsumeRate from ShopVip where vid='" + vid
				+ "'";

		System.out.println(sql);

		rs = basedb.executeQuery(sql);
		try {
			if (!rs.next()) {
				return null;
			}
			sv.setVName(rs.getString(1));
			sv.setConsumeScore(Integer.parseInt(rs.getString(2)));
			sv.setVBalance(Float.parseFloat(rs.getString(3)));
			sv.setConsumeSum(Float.parseFloat(rs.getString(4)));
			sv.setConsumeCount(Integer.parseInt(rs.getString(5)));
			sv.setConsumeRate(Float.parseFloat(rs.getString(6)));

			return sv;
		} catch (SQLException e) {
			System.out.println("��Ʒ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���»�Ա��Ϣ
	 * 
	 * @param sv
	 *            ��Ա��Ϣ
	 * @return ״̬
	 */
	public boolean updateSaleInfo(ShopVip sv) {
		if (sv == null) {
			return false;
		}
		sql = "update ShopVip set VBalance=" + sv.getVBalance() + ",ConsumeSum=" + sv.getConsumeSum() + ",ConsumeScore="
				+ sv.getConsumeScore() + ",ConsumeCount=" + sv.getConsumeCount() + " where VId='" + sv.getVId() + "'";
		System.out.println(sql);
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ������ۼ�¼
	 */
	public boolean saveSale(Sell s) {
		if (s == null) {
			return false;
		}
		sidd = createSID();
		sql = "insert into Sell values('" + sidd + "','" + s.getVId() + "','" + s.getSTime() + "'," + s.getSCount()
				+ ",'" + s.getUId() + "'," + s.getSCountPrice() + ")";
		System.out.println(sql);
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	// ���������ϸ��¼
	public boolean saveSellDetail(SellDetail sd) {
		if (sd == null) {
			return false;
		}
		sql = "insert into SellDetail values('" + sidd + "','" + sd.getGId() + "'," + sd.getSQuantity() + ","
				+ sd.getSSalePrice() + ")";
		if (basedb.executeUpdate(sql)) {
			return true;
		}
		return false;
	}

	/**
	 * ********************************************* ��ˮ������ *
	 ************************************************/
	// ������ˮ��
	public String createSID() {
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

	/**
	 * ��ѯ���е����۵���ˮ��
	 * 
	 * @return ��ˮ�ż���
	 */
	public Vector<String> getInStoreId(String str) {
		Vector<String> list = new Vector<String>();
		sql = "select SId from Sell where SId like '" + str + "%' order by SId desc";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				list.add(rs.getString("SId"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
