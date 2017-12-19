package com.cashier.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.cashier.util.goods;
import com.cashier.util.goodsClass;
import com.cashier.util.inStore;

public class goodsDB {
	private baseDB basedb = null;

	private ResultSet rs = null;

	private String sql = "";

	/**
	 * 创建数据库操作对象
	 */
	public goodsDB() {
		if (basedb == null) {
			basedb = new baseDB();
		}
	}

	/**
	 * 查询所有商品类别信息
	 * 
	 * @return 商品类别对象集合
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
			System.out.println("商品类别数据查询失败");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有商品类别信息,用来填充进货单的下拉框
	 * 
	 * @return 商品类别对象集合
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
			System.out.println("商品类别数据查询失败");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加商品类别
	 * 
	 * @param goodsClass
	 *            新商品类别信息
	 * @return 操作状态（true：成功 false:失败）
	 */
	public boolean saveGoodsClass(goodsClass goodsClass) {
		try {
			if (goodsClass != null) {
				// 验证账号不允许重复
				sql = "select * from GoodsClass where cname='" + goodsClass.getCName() + "'";
				rs = basedb.executeQuery(sql);

				if (rs != null && rs.next()) {// 说明该数据库中已经存在该账号，不能添加
					return false;
				}
				sql = "insert into GoodsClass values('" + goodsClass.getCName() + "')";
				if (basedb.executeUpdate(sql)) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("数据插入失败！");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 修改商品类别信息
	 * 
	 * @param goodsClass
	 *            修改的对象
	 * @return 操作状态（true：成功 false:失败）
	 */
	public boolean updateGoodsClass(goodsClass goodsClass) {
		if (goodsClass != null) {
			sql = "update GoodsClass set cname='" + goodsClass.getCName() + "' where cid=" + goodsClass.getCId();
			if (basedb.executeUpdate(sql)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 删除商品类别信息
	 * 
	 * @param user的主键ID
	 * @return 操作状态（true：成功 false:失败）
	 */
	public boolean delGoodsClass(goodsClass goodsClass) {
		int cid = goodsClass.getCId();
		sql = "select * from GoodsClass gc,Goods g where gc.cid=g.cid and gc.cid=" + cid;
		// 检查是否有关联的商品
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

	// 填充商品类别表格的标题
	public Vector<String> getColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("类别编号");
		vector.add("类别名称");
		return vector;
	}// end

	/** *****************流水号生成****************** */
	// 生成流水号
	public String createInStoreId() {
		String inStoreId = "";
		String year, month, day;
		String tempdate;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		inStoreId = sdf.format(calendar.getTime());// 日期格式化字符串
		// 将字符串以‘-’进行分割
		StringTokenizer st = new StringTokenizer(inStoreId, "-");
		year = st.nextToken();// 年份
		month = st.nextToken();// 月份
		day = st.nextToken();// 日
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

	// 检查改天是否已经生成流水号
	public boolean checkDate(String datestr) {
		Vector<String> list = getInStoreId(datestr);
		System.out.println(list.size() + "");
		if (list.size() > 0) {
			return true;
		}
		return false;

	}

	/** **************** 进货单维护 ************************* */
	/**
	 * 查询所有的进货单流水号
	 * 
	 * @return 流水号集合
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
	 * 根据条件检查是否存在该条形码
	 * 
	 * @param gid
	 *            条形码
	 * @return 状态
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
	 * 添加商品信息
	 * 
	 * @param goodsClass
	 *            新商品类别信息
	 * @return 操作状态（true：成功 false:失败）
	 */
	public boolean saveGoods(goods goods) {
		try {
			if (goods != null) {
				// 验证商品名称不允许重复
				sql = "select * from Goods where gname='" + goods.getGName() + "'";
				System.out.println(sql);
				rs = basedb.executeQuery(sql);

				if (rs != null && rs.next()) {// 说明该数据库中已经存在该账号，不能添加
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
			System.out.println("数据插入失败！");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据条形码查询商品信息
	 * 
	 * @param gid
	 *            条形码
	 * @return 商品信息
	 */
	public goods searchGoods(String gid) {
		goods g;
		if (gid == null || gid.equals("")) {
			return null;
		}
		sql = "select * from Goods where gid='" + gid + "'";
		rs = basedb.executeQuery(sql);
		try {
			if (rs != null && rs.next()) {
				g = new goods();
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
	 * 更新商品库存
	 * 
	 * @param gid
	 *            条形码
	 * @param amount
	 *            库存
	 * @return 状态
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
	 * 保存入库单信息
	 * 
	 * @param instore
	 *            入库单对象
	 * @return 状态
	 */
	public boolean saveInStore(inStore instore) {
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
	 * 查询所有商品信息
	 * 
	 * @return 商品对象集合
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
			System.out.println("商品数据查询失败");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有商品信息
	 * 
	 * @return 商品对象集合
	 */
	public Vector<Vector<String>> getGoods(goods goods) {
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
			System.out.println("商品数据查询失败");
			e.printStackTrace();
		}
		return null;
	}

	// 填充商品信息表格的标题
	public Vector<String> getGoodsColoumn() {
		Vector<String> vector = new Vector<String>();
		vector.add("条形码");
		vector.add("种类");
		vector.add("名称");
		vector.add("规格");
		vector.add("单位");
		vector.add("报警数");
		vector.add("售价");
		vector.add("会员价");
		vector.add("库存");
		return vector;
	}// end

	/**
	 * 修改商品信息
	 * 
	 * @param good
	 *            商品对象
	 * @return 状态
	 */
	public boolean updateGoods(goods good) {
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
	 * 删除商品信息
	 * 
	 * @param user的主键ID
	 * @return 操作状态（true：成功 false:失败）
	 */
	public boolean delGoods(String gid) {
		sql = "select * from Goods g,Instore i where g.gid=i.gid and g.gid='" + gid + "'";
		// sql = "select * from SellDetail sd,Goods g,Instore i where
		// sd.gid=g.gid and g.gid=i.gid and g.gid='"+gid+"'";
		// 检查是否有关联的商品
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
		goods god = new goods();
		god.setGName("恰恰瓜子");
		System.out.println(new goodsDB().saveGoods(god));
	}

}
