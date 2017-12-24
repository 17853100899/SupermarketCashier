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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TurnOverJPanel extends javax.swing.JPanel implements ActionListener {
	private JScrollPane jScrollPane1;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JTextField startTime;
	private JButton jButton1;
	private JTextField endTime;
	private JTable jTable1;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TurnOverJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public TurnOverJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(613, 322));
			this.setLayout(null);
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(59, 106, 492, 180);
				{
					tableModel = new DefaultTableModel();
					tableModel.setDataVector(new TotalDB().getTurnover(), new TotalDB().getTurnoverColoumn());
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(tableModel);
				}
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("结束时间：");
				jLabel2.setBounds(50, 76, 66, 15);
			}
			{
				endTime = new JTextField();
				this.add(endTime);
				endTime.setBounds(150, 72, 226, 22);
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setText("查询");
				jButton1.setBounds(472, 29, 79, 65);
				jButton1.addActionListener(this);
			}
			{
				startTime = new JTextField();
				this.add(startTime);
				startTime.setBounds(150, 26, 226, 22);
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("开始时间：");
				jLabel1.setBounds(50, 30, 94, 15);
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
			tableModel.setDataVector(new TotalDB().getTurnover(start, end), new TotalDB().getTurnoverColoumn());

		}

	}

}
