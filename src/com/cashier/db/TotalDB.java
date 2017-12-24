package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class TotalDB {
	private BaseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	/**
	 * �������ݿ��������
	 */
	public TotalDB() {
		if (basedb == null) {
			basedb = new BaseDB();
		}
	}

	/**
	 * ��ѯָ��ʱ��εĻ�Ա��ֵ��ϸ
	 * 
	 * @return ��Ա��ֵ��ϸ����
	 */
	public Vector<Vector<String>> getShopVIP(String startTime, String endTime) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select vsm.VTime,sv.VId,sv.VName,vsm.VMoney,u.UName  from shopvip sv,VipSaveMoney vsm,SuperMarketUser u where sv.vid=vsm.vid and vsm.uid=u.uid and vtime between '"
				+ startTime + "' and '" + endTime + "'";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getDate(1)));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(String.valueOf(rs.getFloat(4)));
				v.addElement(rs.getString(5));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��Ա��ֵ��ϸ
	 * 
	 * @return
	 */
	public Vector<Vector<String>> getShopVIP() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select vsm.VTime,sv.VId,sv.VName,vsm.VMoney,u.UName  from shopvip sv,VipSaveMoney vsm,SuperMarketUser u where sv.vid=vsm.vid and vsm.uid=u.uid";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getDate(1)));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(String.valueOf(rs.getFloat(4)));
				v.addElement(rs.getString(5));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// ����Ա��ֵ��ϸ���ı���
	public Vector<String> getVipColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("��ֵʱ��");
		vector.add("��Ա����");
		vector.add("��Ա����");
		vector.add("��ֵ���");
		vector.add("��ֵԱ");
		return vector;
	}// end

	/**
	 * ��ѯָ��ʱ��ε�����Ա�������ܽ��
	 * 
	 * @return ��Ա��ֵ��ϸ����
	 */
	public Vector<Vector<String>> getTurnover(String startTime, String endTime) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select UName,sum(SQuantity*SSalePrice) as MoneySum from superMarketUser smu,Sell s,SellDetail sd where sd.SId=s.SId and smu.UId=s.UId and STime between '"
				+ startTime + "' and '" + endTime + "' group by UName";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(rs.getString(1));
				v.addElement(String.valueOf(rs.getFloat(2)));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}//

	/**
	 * ��ѯ����Ա�������ܽ��
	 * 
	 * @return ����Ա�������ܽ���
	 */
	public Vector<Vector<String>> getTurnover() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select UName,sum(SQuantity*SSalePrice) as MoneySum from superMarketUser smu,Sell s,SellDetail sd where sd.SId=s.SId and smu.UId=s.UId group by UName";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(rs.getString(1));
				v.addElement(String.valueOf(rs.getFloat(2)));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// ����Ա��ֵ��ϸ���ı���
	public Vector<String> getTurnoverColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("����Ա����");
		vector.add("Ӫҵ�ܶ�");
		return vector;
	}// end

	/**
	 * ��ѯָ��ʱ����ڵĽ����ܽ������ܽ��
	 * 
	 * @param startTime
	 *            ��ʼʱ��
	 * @param endTime
	 *            ����ʱ��
	 * @return �����
	 */
	public ArrayList<String> getSaleTotal(String startTime, String endTime) {
		ArrayList<String> list = new ArrayList<String>();

		sql = "select sum(InStoreAmount*PurchasePrice) from InStore where InStoreTime between '" + startTime + "' and '"
				+ endTime + "'";
		// �����ܽ��
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				list.add(String.valueOf(rs.getFloat(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �����ܽ��
		sql = "select sum(SQuantity*SSalePrice) from SellDetail sd,Sell s where s.SId=sd.SId and STime between '"
				+ startTime + "' and '" + endTime + "'";

		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				list.add(String.valueOf(rs.getFloat(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ��ѯ��ʾָ��ʱ����ڵĽ�����ϸ
	 * 
	 * @return ������ϸ����
	 */
	public Vector<Vector<String>> getInfoWare(String startTime, String endTime) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select InStoreTime,InStoreId,g.GId,GName,GSpec,GUName,CName,InStoreAmount,PurchasePrice,PurchasePrice*InStoreAmount as MoneySum,GPName from GoodsPrivoder gp,GoodsClass gc,GoodsUnit gu,Goods g,InStore ist where ist.GId=g.GId and ist.GPId=gp.GPId and g.GUId=gu.GUId and g.CId=gc.CId and InStoreTime between '"
				+ startTime + "' and '" + endTime + "'";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getDate(1)));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(rs.getString(4));
				v.addElement(rs.getString(5));
				v.addElement(rs.getString(6));
				v.addElement(rs.getString(7));
				v.addElement(String.valueOf(rs.getInt(8)));
				v.addElement(String.valueOf(rs.getFloat(9)));
				v.addElement(String.valueOf(rs.getFloat(10)));
				v.addElement(rs.getString(11));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ��ʾָ��ʱ����ڵĽ�����ϸ
	 * 
	 * @return ������ϸ����
	 */
	public Vector<Vector<String>> getInfoWare() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select InStoreTime,InStoreId,g.GId,GName,GSpec,GUName,CName,InStoreAmount,PurchasePrice,PurchasePrice*InStoreAmount as MoneySum,GPName from GoodsPrivoder gp,GoodsClass gc,GoodsUnit gu,Goods g,InStore ist where ist.GId=g.GId and ist.GPId=gp.GPId and g.GUId=gu.GUId and g.CId=gc.CId";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getDate(1)));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(rs.getString(4));
				v.addElement(rs.getString(5));
				v.addElement(rs.getString(6));
				v.addElement(rs.getString(7));
				v.addElement(String.valueOf(rs.getInt(8)));
				v.addElement(String.valueOf(rs.getFloat(9)));
				v.addElement(String.valueOf(rs.getFloat(10)));
				v.addElement(rs.getString(11));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// ����Ա��ֵ��ϸ���ı���
	public Vector<String> getIntoWareColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("����ʱ��");
		vector.add("��ˮ��");
		vector.add("������");
		vector.add("����");
		vector.add("���");
		vector.add("��λ");
		vector.add("��Ʒ����");
		vector.add("��������");
		vector.add("��������");
		vector.add("�������");
		vector.add("������");
		return vector;
	}// end

	/**
	 * ��ѯ��������
	 * 
	 * @return �������ܼ���
	 */
	public Vector<Vector<String>> getInfoTotal() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select g.GId,GName,GSpec,GUName,CName,sum(InStoreAmount) as AmountSum,sum(PurchasePrice*InStoreAmount) as MoneySum from GoodsClass gc,GoodsUnit gu,Goods g,InStore ist where ist.GId=g.GId and g.GUId=gu.GUId and g.CId=gc.CId group by g.GId,GName,GSpec,GUName,CName";
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
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("�����������ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @return �������ܼ���
	 */
	public Vector<Vector<String>> getInfoTotal(String startTime, String endTime) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select g.GId,GName,GSpec,GUName,CName,sum(InStoreAmount) as AmountSum,sum(PurchasePrice*InStoreAmount) as MoneySum from GoodsClass gc,GoodsUnit gu,Goods g,InStore ist where ist.GId=g.GId and g.GUId=gu.GUId and g.CId=gc.CId and InStoreTime between '"
				+ startTime + "' and '" + endTime + "' group by g.GId,GName,GSpec,GUName,CName";
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
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("�����������ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// ���������ܱ��ı���
	public Vector<String> getIntoTotalColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("������");
		vector.add("����");
		vector.add("���");
		vector.add("��λ");
		vector.add("��Ʒ����");
		vector.add("����������");
		vector.add("�ܽ��");
		return vector;
	}// end

	/**
	 * ��ѯ��ʾָ��ʱ����ڵ�������ϸ
	 * 
	 * @return ������ϸ����
	 */
	public Vector<Vector<String>> getSaleWare() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select STime,sd.SId,sd.GId,GName,GSpec,GUName,CName,SQuantity,SSalePrice,SQuantity*SSalePrice as MoneySum,VId,UName from Sell s,SellDetail sd,Goods g,GoodsUnit gu,GoodsClass gc,superMarketUser smu where sd.SId=s.SId and sd.GId=g.GId and s.UId=smu.UId and g.CId=gc.CId and g.GUId=gu.GUId";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getDate(1)));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(rs.getString(4));
				v.addElement(rs.getString(5));
				v.addElement(rs.getString(6));
				v.addElement(rs.getString(7));
				v.addElement(String.valueOf(rs.getInt(8)));
				v.addElement(String.valueOf(rs.getFloat(9)));
				v.addElement(String.valueOf(rs.getFloat(10)));
				v.addElement(rs.getString(11));
				v.addElement(rs.getString(12));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ��ʾָ��ʱ����ڵ�������ϸ
	 * 
	 * @return ������ϸ����
	 */
	public Vector<Vector<String>> getSaleWare(String startTime, String endTime) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select STime,sd.SId,sd.GId,GName,GSpec,GUName,CName,SQuantity,SSalePrice,SQuantity*SSalePrice as MoneySum,VId,UName from Sell s,SellDetail sd,Goods g,GoodsUnit gu,GoodsClass gc,superMarketUser smu where sd.SId=s.SId and sd.GId=g.GId and s.UId=smu.UId and g.CId=gc.CId and g.GUId=gu.GUId and STime between '"
				+ startTime + "' and '" + endTime + "'";
		System.out.println(sql);
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();
				v.addElement(String.valueOf(rs.getDate(1)));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(rs.getString(4));
				v.addElement(rs.getString(5));
				v.addElement(rs.getString(6));
				v.addElement(rs.getString(7));
				v.addElement(String.valueOf(rs.getInt(8)));
				v.addElement(String.valueOf(rs.getFloat(9)));
				v.addElement(String.valueOf(rs.getFloat(10)));
				v.addElement(rs.getString(11));
				v.addElement(rs.getString(12));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("��Ա��ֵ���ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// ���������ϸ���ı���
	public Vector<String> getSaleColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("����ʱ��");
		vector.add("��ˮ��");
		vector.add("������");
		vector.add("����");
		vector.add("���");
		vector.add("��λ");
		vector.add("��Ʒ����");
		vector.add("��������");
		vector.add("���۵���");
		vector.add("���۽��");
		vector.add("��Ա����");
		vector.add("����Ա");
		return vector;
	}// end

	/**
	 * ��ѯ���ۻ���
	 * 
	 * @return ���ۻ��ܼ���
	 */
	public Vector<Vector<String>> getSaleTotal() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select STime,sd.SId,sd.GId,GName,GSpec,GUName,CName,SQuantity,SSalePrice,SQuantity*SSalePrice as MoneySum,VId,UName from Sell s,SellDetail sd,Goods g,GoodsUnit gu,GoodsClass gc,superMarketUser smu where sd.SId=s.SId and sd.GId=g.GId and s.UId=smu.UId and g.CId=gc.CId and g.GUId=gu.GUId";
		rs = basedb.executeQuery(sql);
		try {
			while (rs != null && rs.next()) {
				Vector<String> v = new Vector<String>();

				v.addElement(rs.getString(1));
				v.addElement(rs.getString(2));
				v.addElement(rs.getString(3));
				v.addElement(rs.getString(4));
				v.addElement(rs.getString(5));
				v.addElement(rs.getString(6));
				v.addElement(String.valueOf(rs.getFloat(7)));
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("�����������ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ���ۻ���
	 * 
	 * @return ���ۻ��ܼ���
	 */
	public Vector<Vector<String>> getSaletotal(String startTime, String endTime) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		sql = "select sd.GId,GName,GSpec,GUName,CName,sum(SQuantity) as AmountSum,sum(SQuantity*SSalePrice) as MoneySum from Sell s,SellDetail sd,Goods g,GoodsUnit gu,GoodsClass gc where sd.SId=s.SId and sd.GId=g.GId and g.CId=gc.CId and g.GUId=gu.GUId and STime between '"
				+ startTime + "' and '" + endTime + "' group by sd.GId,GName,GSpec,GUName,CName";
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
				vector.addElement(v);
			}
			return vector;
		} catch (SQLException e) {
			System.out.println("�����������ݲ�ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}

	// ������ۻ��ܱ��ı���
	public Vector<String> getSaleTotalColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("������");
		vector.add("����");
		vector.add("���");
		vector.add("��λ");
		vector.add("��Ʒ����");
		vector.add("����������");
		vector.add("�����ܽ��");
		return vector;
	}// end

}
