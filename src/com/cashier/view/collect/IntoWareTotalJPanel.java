package com.cashier.view.collect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.cashier.db.TotalDB;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class IntoWareTotalJPanel extends javax.swing.JPanel implements ActionListener {
	private JTabbedPane jTabbedPane1;
	private JPanel jPanel1;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTextField endTime;
	private JLabel jLabel3;
	private JTextField startTime;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel jPanel2;
	private DefaultTableModel tableModel;
	private JLabel jLabel4;
	private JTable jTable2;
	private JScrollPane jScrollPane2;
	private JButton jButton2;
	private JTextField endDate;
	private JTextField startDate;
	private DefaultTableModel tableModel2;
	private TotalDB totaldb = new TotalDB();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new IntoWareTotalJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public IntoWareTotalJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(705, 362));
			this.setLayout(null);
			{
				jTabbedPane1 = new JTabbedPane();
				this.add(jTabbedPane1);
				jTabbedPane1.setBounds(12, 18, 681, 318);
				{
					jPanel1 = new JPanel();
					jTabbedPane1.addTab("������ϸ��ѯ", null, jPanel1, null);
					jPanel1.setLayout(null);
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("��ʼʱ�䣺");
						jLabel1.setBounds(82, 23, 84, 15);
					}
					{
						startTime = new JTextField();
						jPanel1.add(startTime);
						startTime.setBounds(195, 19, 203, 22);
					}
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3);
						jLabel3.setText("����ʱ�䣺");
						jLabel3.setBounds(82, 60, 84, 15);
					}
					{
						endTime = new JTextField();
						jPanel1.add(endTime);
						endTime.setBounds(195, 56, 203, 22);
					}
					{
						jButton1 = new JButton();
						jPanel1.add(jButton1);
						jButton1.setText("��ѯ");
						jButton1.setBounds(453, 19, 119, 59);
						jButton1.addActionListener(this);
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel1.add(jScrollPane1);
						jScrollPane1.setBounds(5, 90, 666, 201);
						{
							tableModel = new DefaultTableModel();
							tableModel.setDataVector(totaldb.getInfoWare(), totaldb.getIntoWareColoumn());
							jTable1 = new JTable();
							jScrollPane1.setViewportView(jTable1);
							jTable1.setModel(tableModel);
						}
					}
				}
				{
					jPanel2 = new JPanel();
					jTabbedPane1.addTab("�������ܲ�ѯ", null, jPanel2, null);
					jPanel2.setLayout(null);
					{
						jLabel2 = new JLabel();
						jPanel2.add(jLabel2);
						jLabel2.setText("����ʱ�䣺");
						jLabel2.setBounds(76, 56, 107, 15);
					}
					{
						jLabel4 = new JLabel();
						jPanel2.add(jLabel4);
						jLabel4.setText("��ʼʱ�䣺");
						jLabel4.setBounds(76, 16, 90, 15);
					}
					{
						startDate = new JTextField();
						jPanel2.add(startDate);
						startDate.setBounds(189, 12, 227, 22);
					}
					{
						endDate = new JTextField();
						jPanel2.add(endDate);
						endDate.setBounds(189, 56, 227, 22);
					}
					{
						jButton2 = new JButton();
						jPanel2.add(jButton2);
						jButton2.setText("��ѯ");
						jButton2.setBounds(464, 12, 113, 65);
						jButton2.addActionListener(this);
					}
					{
						jScrollPane2 = new JScrollPane();
						jPanel2.add(jScrollPane2);
						jScrollPane2.setBounds(0, 83, 676, 201);
						{
							tableModel2 = new DefaultTableModel();
							tableModel2.setDataVector(totaldb.getInfoTotal(), totaldb.getIntoTotalColoumn());
							jTable2 = new JTable();
							jScrollPane2.setViewportView(jTable2);
							jTable2.setModel(tableModel2);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton1) {
			String start = startTime.getText().trim();
			String end = endTime.getText().trim();
			if (start.equals("") || end.equals("")) {
				JOptionPane.showMessageDialog(this, "��ѯʱ�䲻��Ϊ�գ�");
				return;
			}
			if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", start) || !Pattern.matches("\\d{4}-\\d{2}-\\d{2}", end)) {
				JOptionPane.showMessageDialog(this, "���ڸ�ʽ����ȷ��");
				return;
			}
			tableModel.setDataVector(totaldb.getInfoWare(start, end), totaldb.getIntoWareColoumn());

		}
		if (e.getSource() == jButton2) {
			String start = startDate.getText().trim();
			String end = endDate.getText().trim();
			if (start.equals("") || end.equals("")) {
				JOptionPane.showMessageDialog(this, "��ѯʱ�䲻��Ϊ�գ�");
				return;
			}
			if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", start) || !Pattern.matches("\\d{4}-\\d{2}-\\d{2}", end)) {
				JOptionPane.showMessageDialog(this, "���ڸ�ʽ����ȷ��");
				return;
			}
			tableModel.setDataVector(totaldb.getInfoTotal(start, end), totaldb.getIntoWareColoumn());

		}

	}

}
