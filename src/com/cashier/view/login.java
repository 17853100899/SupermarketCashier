package com.cashier.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.cashier.db.userDB;
import com.cashier.util.Constant;
import com.cashier.util.supermarketUser;

/**
 * ��¼����
 */
public class login extends javax.swing.JFrame implements ActionListener {

	private JLabel jLabel1, jLabel2, jLabel3;
	// ���� ��¼
	private JButton reset, submit;
	// ���������
	private JPasswordField Password;
	// �û��������
	private JTextField Username;

	// private SystemMain systemMain;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				login inst = new login();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});

	}

	public login() {
		super();
		initGUI();
	}

	// ��ͼ����
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			// ����������ע�� WindowListener �Ķ�����Զ����ز��ͷŸô��塣����������Ӧ�ó����ͷ��˴�����ռ�õ���Դ��
			getContentPane().setLayout(null);
			this.setTitle("������������ϵͳ");
			this.setResizable(false);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("�û���¼");
				jLabel1.setBounds(164, 56, 224, 32);
				jLabel1.setBackground(new java.awt.Color(128, 128, 192));
				jLabel1.setFont(new java.awt.Font("����_GB2312", 0, 22));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("�˺ţ�");
				jLabel2.setBounds(122, 133, 42, 15);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("���룺");
				jLabel3.setBounds(122, 187, 42, 15);
			}
			{
				Username = new JTextField();
				getContentPane().add(Username);
				Username.setBounds(192, 129, 174, 22);
				Username.setText("admin");

			}
			{
				Password = new JPasswordField();
				getContentPane().add(Password);
				Password.setBounds(192, 174, 174, 22);
				Password.setText("123");
			}
			{
				submit = new JButton();
				getContentPane().add(submit);
				submit.setText("��¼");
				submit.setBounds(159, 248, 74, 22);
				submit.addActionListener(this);
			}
			{
				reset = new JButton();
				getContentPane().add(reset);
				reset.setText("����");
				reset.setBounds(259, 248, 77, 22);
				reset.addActionListener(this);
			}
			pack();
			setSize(500, 400);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	// ��ť����
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == submit) {
			String username = Username.getText().trim();
			String password = Password.getText().trim();

			if (username.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(this, "�û��������벻��Ϊ�գ�");
			} else {
				supermarketUser user = new userDB().checkUser(username, password);
				if (user != null) {
					String urole = user.getURole();
					// ���û���Ϣ�����ڳ����з������ں�̨ҳ�����
					Constant.uid = username;
					Constant.uname = user.getUName();
					Constant.upassword = password;
					Constant.urole = user.getURole();
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// systemMain = new SystemMain();
							// systemMain.setLocationRelativeTo(null);
							// systemMain.setVisible(true);
						}
					});
					this.dispose();

				} else {
					JOptionPane.showMessageDialog(this, "�û����������������������");
					Username.setText("");
					Password.setText("");
				}
			}

		}
		if (arg0.getSource() == reset) {
			Username.setText("");
			Password.setText("");
		}

	}

}
