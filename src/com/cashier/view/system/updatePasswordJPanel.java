package com.cashier.view.system;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.cashier.db.userDB;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * �޸��û����봰��
 */
public class updatePasswordJPanel extends javax.swing.JPanel implements ActionListener {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JPasswordField twonewpassword;
	private JButton jButton2;
	private JButton jButton1;
	private JPasswordField newpassword;
	private JPasswordField oldpassword;
	private JTextField uid;
	private JLabel jLabel3;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new updatePasswordJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public updatePasswordJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			this.setToolTipText("�޸�����");
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("�˺ţ�");
				jLabel1.setBounds(61, 62, 87, 15);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("�����룺");
				jLabel2.setBounds(61, 109, 73, 15);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText("�����룺");
				jLabel3.setBounds(61, 162, 73, 15);
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText("ȷ�����룺");
				jLabel4.setBounds(61, 206, 87, 15);
			}
			{
				uid = new JTextField();
				this.add(uid);
				uid.setBounds(175, 58, 149, 22);
				uid.setEditable(false);
				uid.setText("admin");

				// uid.setText(Constant.uid);
			}
			{
				oldpassword = new JPasswordField();
				this.add(oldpassword);
				oldpassword.setBounds(175, 105, 149, 22);
			}
			{
				newpassword = new JPasswordField();
				this.add(newpassword);
				newpassword.setBounds(175, 158, 149, 22);
			}
			{
				twonewpassword = new JPasswordField();
				this.add(twonewpassword);
				twonewpassword.setBounds(175, 202, 149, 22);
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setText("�޸�");
				jButton1.setBounds(108, 244, 75, 22);
				jButton1.addActionListener(this);
			}
			{
				jButton2 = new JButton();
				this.add(jButton2);
				jButton2.setText("����");
				jButton2.setBounds(223, 244, 81, 22);
				jButton2.addActionListener(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		String uidValue = null;
		String newValue = null;
		if (e.getSource() == jButton1) {
			uidValue = uid.getText().trim();
			String old = oldpassword.getText().trim();
			newValue = newpassword.getText().trim();
			String twonewValue = twonewpassword.getText().trim();
			if (old.equals("") || newValue.equals("") || twonewValue.equals("")) {
				JOptionPane.showMessageDialog(this, "�Բ���ѡ���Ϊ�գ�");
			} else {
				if (!newValue.equals(twonewValue)) {
					JOptionPane.showMessageDialog(this, "�������벻һ�£���������д!");
					newpassword.setText("");
					twonewpassword.setText("");
				} else {
					userDB userdb = new userDB();
					if (userdb.updatePass(uidValue, newValue)) {
						JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
					} else {
						JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
					}
				}
			}
		}
		if (e.getSource() == jButton2) {
			oldpassword.setText("");
			newpassword.setText("");
			twonewpassword.setText("");

		}

	}

}
