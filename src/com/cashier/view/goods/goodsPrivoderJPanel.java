package com.cashier.view.goods;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.cashier.db.goodsPrivoderDB;
import com.cashier.util.goodsPrivoder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class goodsPrivoderJPanel extends javax.swing.JPanel implements ActionListener, MouseListener {
	private JScrollPane jScrollPane1;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JTextField GPPhone;
	private JTextField GPAddress;
	private JTextField GPLinkman;
	private JTextField GPName;
	private JLabel jLabel3;
	private JLabel jLabel1;
	private JTable jTable1;
	private DefaultTableModel tableModel;
	Vector<Vector<String>> vector = new Vector<Vector<String>>();
	Vector<String> columnVector = new Vector<String>();
	private goodsPrivoderDB goodsPrivoderDAO = new goodsPrivoderDB();
	private int index;
	private int gpid;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JButton jButton1;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new goodsPrivoderJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public goodsPrivoderJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(548, 365));
			this.setLayout(null);
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(20, 27, 495, 211);
				{
					tableModel = new DefaultTableModel();
					tableModel.setDataVector(goodsPrivoderDAO.getGoodsPrivoder(), goodsPrivoderDAO.getColoumn());
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(tableModel);
					jTable1.addMouseListener(this);
				}
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("\u8054\u7cfb\u4eba\uff1a");
				jLabel1.setBounds(320, 255, 69, 15);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("\u7535 \u8bdd\uff1a");
				jLabel2.setBounds(318, 291, 49, 15);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText("\u5730    \u5740\uff1a");
				jLabel3.setBounds(38, 291, 82, 15);
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText("���������ƣ�");
				jLabel4.setBounds(36, 255, 101, 15);
			}
			{
				GPName = new JTextField();
				this.add(GPName);
				GPName.setBounds(122, 251, 181, 22);

			}
			{
				GPLinkman = new JTextField();
				this.add(GPLinkman);
				GPLinkman.setBounds(375, 251, 97, 22);
			}
			{
				GPAddress = new JTextField();
				this.add(GPAddress);
				GPAddress.setBounds(120, 287, 186, 22);
			}
			{
				GPPhone = new JTextField();
				this.add(GPPhone);
				GPPhone.setBounds(373, 287, 102, 22);
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setText("���");
				jButton1.setBounds(94, 321, 63, 22);
				jButton1.addActionListener(this);
			}
			{
				jButton2 = new JButton();
				this.add(jButton2);
				jButton2.setText("�޸�");
				jButton2.setBounds(188, 321, 62, 22);
				jButton2.setEnabled(false);
				jButton2.addActionListener(this);

			}
			{
				jButton3 = new JButton();
				this.add(jButton3);
				jButton3.setText("ɾ��");
				jButton3.setEnabled(false);
				jButton3.setBounds(280, 321, 62, 22);
				jButton3.addActionListener(this);
			}
			{
				jButton4 = new JButton();
				this.add(jButton4);
				jButton4.setText("��ѯ");
				jButton4.setBounds(379, 321, 75, 22);
				jButton4.addActionListener(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		String goodsPrivoderName;
		String goodsPrivoderPhone;
		String goodsPrivoderAddress;
		String goodsPrivoderLinkman;

		// ����
		if (e.getSource() == jButton1) {
			goodsPrivoderName = GPName.getText().trim();
			goodsPrivoderPhone = GPPhone.getText().trim();
			goodsPrivoderAddress = GPAddress.getText().trim();
			goodsPrivoderLinkman = GPLinkman.getText().trim();
			if (goodsPrivoderName.equals("")) {
				JOptionPane.showMessageDialog(this, "�Բ�����Ʒ��Ӧ�����Ʋ���Ϊ�գ�");
				clear();
			} else {
				goodsPrivoder goodsPrivoder = new goodsPrivoder();
				goodsPrivoder.setGpaddress(goodsPrivoderAddress);
				goodsPrivoder.setGplinkman(goodsPrivoderLinkman);
				goodsPrivoder.setGpphone(goodsPrivoderPhone);
				goodsPrivoder.setGpname(goodsPrivoderName);
				if (goodsPrivoderDAO.saveGoodsPrivoder(goodsPrivoder)) {
					JOptionPane.showMessageDialog(this, "����ɹ���");
					tableModel.setDataVector(goodsPrivoderDAO.getGoodsPrivoder(), goodsPrivoderDAO.getColoumn());
					clear();

				} else {
					JOptionPane.showMessageDialog(this, "��Ʒ��Ӧ�������Ѿ����ڣ�����ʧ�ܣ�");
					clear();
				}
			}
		}
		// �޸�
		if (e.getSource() == jButton2) {
			goodsPrivoderName = GPName.getText().trim();
			goodsPrivoderPhone = GPPhone.getText().trim();
			goodsPrivoderAddress = GPAddress.getText().trim();
			goodsPrivoderLinkman = GPLinkman.getText().trim();
			if (goodsPrivoderName.equals("")) {
				JOptionPane.showMessageDialog(this, "�Բ�����Ʒ�����Ϊ�գ�");
				clear();
			} else {
				goodsPrivoder goodsPrivoder = new goodsPrivoder();
				goodsPrivoder.setGpaddress(goodsPrivoderAddress);
				goodsPrivoder.setGplinkman(goodsPrivoderLinkman);
				goodsPrivoder.setGpphone(goodsPrivoderPhone);
				goodsPrivoder.setGpname(goodsPrivoderName);
				goodsPrivoder.setGpid(gpid);
				if (goodsPrivoderDAO.updateGoodsPrivoder(goodsPrivoder)) {
					JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
					tableModel.setDataVector(goodsPrivoderDAO.getGoodsPrivoder(), goodsPrivoderDAO.getColoumn());
					clear();
					jButton1.setEnabled(true);
					jButton2.setEnabled(false);
					jButton3.setEnabled(false);
				}
			}
		}
		// ɾ��
		if (e.getSource() == jButton3) {
			goodsPrivoder goodsPrivoder = new goodsPrivoder();
			goodsPrivoder.setGpid(gpid);
			if (goodsPrivoderDAO.delGoodsPrivoder(goodsPrivoder)) {
				JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
				tableModel.setDataVector(goodsPrivoderDAO.getGoodsPrivoder(), goodsPrivoderDAO.getColoumn());
				clear();
				jButton1.setEnabled(true);
				jButton2.setEnabled(false);
				jButton3.setEnabled(false);

			}
		}
		// ģ������
		if (e.getSource() == jButton4) {
			goodsPrivoderName = GPName.getText().trim();
			goodsPrivoderPhone = GPPhone.getText().trim();
			goodsPrivoder goodsPrivoder = new goodsPrivoder();
			goodsPrivoder.setGpphone(goodsPrivoderPhone);
			goodsPrivoder.setGpname(goodsPrivoderName);
			tableModel.setDataVector(goodsPrivoderDAO.searchGoodsPrivoder(goodsPrivoder),
					goodsPrivoderDAO.getColoumn());
			clear();

		}

	}

	public void mouseClicked(MouseEvent e) {
		jButton1.setEnabled(false);
		jButton2.setEnabled(true);
		jButton3.setEnabled(true);
		index = jTable1.getSelectedRow();
		if (index >= 0 && index < jTable1.getModel().getRowCount()) {
			GPName.setText(jTable1.getValueAt(index, 1) + "");
			gpid = Integer.parseInt(jTable1.getValueAt(index, 0).toString());
			GPAddress.setText(jTable1.getValueAt(index, 4) + "");
			GPLinkman.setText(jTable1.getValueAt(index, 2) + "");
			GPPhone.setText(jTable1.getValueAt(index, 3) + "");

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

	public void clear() {
		GPAddress.setText("");
		GPLinkman.setText("");
		GPName.setText("");
		GPPhone.setText("");
	}

}
