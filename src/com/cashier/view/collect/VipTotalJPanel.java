package com.cashier.view.collect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.cashier.db.TotalDB;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VipTotalJPanel extends javax.swing.JPanel implements ActionListener {
	private JTextField startTime;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTextField endTime;
	Vector<Vector<String>> vector = new Vector<Vector<String>>();

	Vector<String> columnVector = new Vector<String>();
	private JTable jTable1;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new VipTotalJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public VipTotalJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(550, 321));
			this.setLayout(null);
			{
				startTime = new JTextField();
				this.add(startTime);
				startTime.setBounds(181, 25, 226, 22);
			}
			{
				endTime = new JTextField();
				this.add(endTime);
				endTime.setBounds(181, 68, 226, 22);
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("开始时间：");
				jLabel1.setBounds(67, 29, 94, 15);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("结束时间：");
				jLabel2.setBounds(67, 72, 66, 15);
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setText("查询");
				jButton1.setBounds(447, 25, 79, 65);
				jButton1.addActionListener(this);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(34, 116, 492, 180);
				{
					tableModel = new DefaultTableModel();
					tableModel.setDataVector(new TotalDB().getShopVIP(), new TotalDB().getVipColoumn());
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(tableModel);

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
				JOptionPane.showMessageDialog(this, "查询时间不能为空！");
				return;
			}
			if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", start) || !Pattern.matches("\\d{4}-\\d{2}-\\d{2}", end)) {
				JOptionPane.showMessageDialog(this, "日期格式不正确！");
				return;
			}
			tableModel.setDataVector(new TotalDB().getShopVIP(start, end), new TotalDB().getVipColoumn());

		}

	}

}
