package com.cashier.view.goods;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.cashier.db.GoodsDB;
import com.cashier.db.GoodsUnitDB;
import com.cashier.util.Goods;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GoodsOpertionJPanel extends javax.swing.JPanel implements ActionListener, MouseListener {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField GMinNumber;
	private JLabel jLabel11;
	private JLabel jLabel9;
	private JTextField VipPrice;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JComboBox cname;
	private JTextField gname;
	private JLabel jLabel8;
	private JButton jButton3;
	private JButton jButton2;
	private JLabel jLabel12;
	private JLabel jLabel7;
	private JLabel jLabel4;
	private JLabel jLabel6;
	private JTextField GId;
	private JTextField GName;
	private JComboBox guname;
	private JComboBox CName;
	private JLabel jLabel5;
	private JTextField GSpec;
	private JTextField GAmount;
	private JLabel jLabel10;
	private JTextField SalePrice;
	private JTextField gid;
	private JLabel jLabel3;
	private DefaultTableModel tableModel;
	private GoodsDB goodsdb = new GoodsDB();

	private Hashtable<Integer, String> goodsClassHt = new Hashtable<Integer, String>();

	private Hashtable<Integer, String> goodsUnitHt = new Hashtable<Integer, String>();

	Vector<Vector<String>> vector = new Vector<Vector<String>>();

	Vector<String> columnVector = new Vector<String>();

	private int numId;
	private String nullahNumber;// ������

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new GoodsOpertionJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public GoodsOpertionJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(610, 436));
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("�����룺");
				jLabel1.setBounds(52, 19, 76, 15);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("��Ʒ���ƣ�");
				jLabel2.setBounds(36, 61, 114, 15);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText("��Ʒ���");
				jLabel3.setBounds(333, 19, 89, 15);
			}
			{
				gid = new JTextField();
				this.add(gid);
				gid.setBounds(120, 15, 132, 22);
			}
			{
				gname = new JTextField();
				this.add(gname);
				gname.setBounds(120, 57, 132, 22);
			}
			{
				Vector<String> v = getGoodsClass(1);
				cname = new JComboBox(v);
				this.add(cname);

				cname.setBounds(468, 15, 108, 22);
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setText("\u7efc\u5408��ѯ");
				jButton1.setBounds(339, 52, 232, 32);
				jButton1.addActionListener(this);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(27, 96, 571, 168);
				{
					tableModel = new DefaultTableModel();
					tableModel.setDataVector(goodsdb.getGoods(), goodsdb.getGoodsColoumn());
					jTable1 = new JTable();

					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(tableModel);
					jTable1.addMouseListener(this);
				}
			}
			{
				VipPrice = new JTextField();
				this.add(VipPrice);
				VipPrice.setText("");
				VipPrice.setEnabled(true);
				VipPrice.setBounds(248, 381, 65, 22);
			}
			{
				jLabel9 = new JLabel();
				this.add(jLabel9);
				jLabel9.setText("��Ա�ۣ�");
				jLabel9.setBounds(180, 385, 96, 15);
			}
			{
				GMinNumber = new JTextField();
				this.add(GMinNumber);
				GMinNumber.setText("");
				GMinNumber.setEnabled(true);
				GMinNumber.setBounds(391, 381, 74, 22);
			}
			{
				jLabel11 = new JLabel();
				this.add(jLabel11);
				jLabel11.setText("��������");
				jLabel11.setBounds(329, 385, 62, 15);
			}
			{
				SalePrice = new JTextField();
				this.add(SalePrice);
				SalePrice.setText("");
				SalePrice.setEnabled(true);
				SalePrice.setBounds(101, 381, 63, 22);
			}
			{
				jLabel8 = new JLabel();
				this.add(jLabel8);
				jLabel8.setText("���ۼۣ�");
				jLabel8.setBounds(27, 380, 102, 15);
			}
			{
				jLabel10 = new JLabel();
				this.add(jLabel10);
				jLabel10.setText("���������");
				jLabel10.setBounds(254, 354, 107, 15);
			}
			{
				GAmount = new JTextField();
				this.add(GAmount);
				GAmount.setText("");
				GAmount.setEnabled(true);
				GAmount.setBounds(331, 350, 136, 22);
			}
			{
				GSpec = new JTextField();
				this.add(GSpec);
				GSpec.setText("");
				GSpec.setEnabled(true);
				GSpec.setBounds(101, 350, 128, 22);
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText("��Ʒ���");
				jLabel4.setBounds(19, 354, 108, 15);
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5);
				jLabel5.setText("��Ʒ���");
				jLabel5.setBounds(19, 320, 97, 15);
			}
			{
				Vector<String> v = getGoodsClass(0);
				CName = new JComboBox(v);
				this.add(CName);

				CName.setEnabled(true);
				CName.setEditable(true);
				CName.setSelectedIndex(0);
				CName.setBounds(100, 320, 128, 22);
			}
			{
				Vector<String> v = getGoodsUnit();
				guname = new JComboBox(v);
				this.add(guname);

				guname.setEnabled(true);
				guname.setEditable(true);
				guname.setSelectedIndex(0);
				guname.setBounds(328, 320, 139, 22);
			}
			{
				GName = new JTextField();
				this.add(GName);
				GName.setText("");
				GName.setEnabled(true);
				GName.setBounds(327, 285, 141, 22);
			}
			{
				GId = new JTextField();
				this.add(GId);
				GId.setText("");
				GId.setEnabled(true);
				GId.setBounds(100, 285, 131, 22);

			}
			{
				jLabel6 = new JLabel();
				this.add(jLabel6);
				jLabel6.setText("�����룺");
				jLabel6.setBounds(31, 289, 78, 15);
			}
			{
				jLabel7 = new JLabel();
				this.add(jLabel7);
				jLabel7.setText("��Ʒ���ƣ�");
				jLabel7.setBounds(254, 289, 87, 15);
			}
			{
				jLabel12 = new JLabel();
				this.add(jLabel12);
				jLabel12.setText("��Ʒ��λ��");
				jLabel12.setBounds(248, 324, 106, 15);
			}
			{
				jButton2 = new JButton();
				this.add(jButton2);
				jButton2.setText("�޸�");
				jButton2.setBounds(480, 271, 110, 58);
				jButton2.setEnabled(false);
				jButton2.addActionListener(this);
			}
			{
				jButton3 = new JButton();
				this.add(jButton3);
				jButton3.setText("ɾ��");
				jButton3.setBounds(479, 350, 111, 53);
				jButton3.setEnabled(false);
				jButton3.addActionListener(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// �ۺϲ�ѯ
		if (e.getSource() == jButton1) {
			JOptionPane.showMessageDialog(this, "---------");
			String id = gid.getText().trim();
			String name = gname.getText().trim();
			String tempcname = cname.getSelectedItem().toString();
			int index = 0;
			Enumeration<Integer> enu = goodsClassHt.keys();
			while (enu.hasMoreElements()) {
				index = enu.nextElement();
				if (goodsClassHt.get(index).equals(tempcname)) {
					break;
				}
			}
			if (tempcname.equals("")) {
				index = 0;
			}
			Goods goods = new Goods();
			goods.setGName(name);
			goods.setCid(index);
			goods.setGid(id);
			tableModel.setDataVector(new GoodsDB().getGoods(goods), new GoodsDB().getGoodsColoumn());

		}
		// �޸�
		if (e.getSource() == jButton2) {
			String gname = GName.getText().trim();
			float vipPrice = Float.valueOf(VipPrice.getText().trim());
			float salePrice = Float.valueOf(SalePrice.getText().trim());
			String gsp = GSpec.getText().trim();
			int aminnumber = Integer.valueOf(GMinNumber.getText().trim());
			// �õ���Ʒ���ID
			String aaa = CName.getSelectedItem().toString();
			int goodsClassindex = 0;// ��Ʒ���ID
			if (goodsClassHt.contains(aaa)) {
				Enumeration<Integer> en = goodsClassHt.keys();
				while (en.hasMoreElements()) {
					goodsClassindex = (int) en.nextElement();
					if (goodsClassHt.get(goodsClassindex).equals(aaa)) {
						break;
					}
				}
			}
			// �õ���Ʒ��λ��ID
			int goodUnitIndex = 0;// ��Ʒ��λ��ID
			String ccc = guname.getSelectedItem().toString();
			System.out.println(ccc + "*********");
			if (goodsUnitHt.contains(ccc)) {
				Enumeration<Integer> en = goodsUnitHt.keys();
				while (en.hasMoreElements()) {
					goodUnitIndex = (int) en.nextElement();
					if (goodsUnitHt.get(goodUnitIndex).equals(ccc)) {
						break;
					}
				}
			}
			// ����׼�����
			Goods g = new Goods();
			g.setGid(nullahNumber);
			g.setGName(gname);
			g.setCid(goodsClassindex);
			g.setGMinNumber(aminnumber);
			g.setGUId(goodUnitIndex);
			g.setSalePrice(salePrice);
			g.setVipPrice(vipPrice);
			g.setGSpec(gsp);
			if (new GoodsDB().updateGoods(g)) {
				jButton2.setEnabled(false);
				jButton3.setEnabled(false);
				JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
				tableModel.setDataVector(goodsdb.getGoods(), goodsdb.getGoodsColoumn());
			}
		}
		// ɾ��
		if (e.getSource() == jButton3) {
			if (nullahNumber != null && !nullahNumber.equals("")) {
				if (new GoodsDB().delGoods(nullahNumber)) {
					jButton2.setEnabled(false);
					jButton3.setEnabled(false);
					JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
					tableModel.setDataVector(goodsdb.getGoods(), goodsdb.getGoodsColoumn());
				} else {
					JOptionPane.showMessageDialog(this, "����Ʒ�й�����¼��ɾ��ʧ�ܣ�");
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		GAmount.setEditable(false);
		GId.setEnabled(false);
		jButton2.setEnabled(true);
		jButton3.setEnabled(true);
		numId = jTable1.getSelectedRow();
		if (numId >= 0 && numId < jTable1.getModel().getRowCount()) {
			// ���ʱ�������뱣�浽��Ա�����У��޸ĺ�ɾ��ʱ��������ȡֵ
			nullahNumber = jTable1.getValueAt(numId, 0).toString();
			GId.setText(jTable1.getValueAt(numId, 0) + "");
			CName.setSelectedItem(jTable1.getValueAt(numId, 1));
			GName.setText(jTable1.getValueAt(numId, 2) + "");
			GSpec.setText(jTable1.getValueAt(numId, 3) + "");
			guname.setSelectedItem(jTable1.getValueAt(numId, 4));
			GMinNumber.setText(jTable1.getValueAt(numId, 5) + "");
			SalePrice.setText(jTable1.getValueAt(numId, 6) + "");
			VipPrice.setText(jTable1.getValueAt(numId, 7) + "");
			GAmount.setText(jTable1.getValueAt(numId, 8) + "");

		}

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// ��ȡ��Ʒ��λ
	public Vector<String> getGoodsUnit() {
		Vector<String> vector = new Vector<String>();
		goodsUnitHt = new GoodsUnitDB().getGoodsUnitForCombox();
		Enumeration<Integer> eu = goodsUnitHt.keys();
		while (eu.hasMoreElements()) {
			int index = eu.nextElement();
			vector.add(goodsUnitHt.get(index));
		}
		return vector;
	}

	// ��ȡ��Ʒ���
	public Vector<String> getGoodsClass(int i) {

		Vector<String> vector = new Vector<String>();
		if (i == 1) {
			vector.add("");// ���һ����������ѯ
		}

		goodsClassHt = new GoodsDB().getGoodsClassForCombox();
		Enumeration<Integer> eu = goodsClassHt.keys();
		while (eu.hasMoreElements()) {
			int index = eu.nextElement();
			vector.add(goodsClassHt.get(index));
		}
		return vector;
	}

}
