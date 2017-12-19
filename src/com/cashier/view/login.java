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
 * 登录窗体
 */
public class login extends javax.swing.JFrame implements ActionListener {

	private JLabel jLabel1, jLabel2, jLabel3;
	// 重置 登录
	private JButton reset, submit;
	// 密码输入框
	private JPasswordField Password;
	// 用户名输入框
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

	// 视图构建
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			// 调用任意已注册 WindowListener 的对象后自动隐藏并释放该窗体。但继续运行应用程序，释放了窗体中占用的资源。
			getContentPane().setLayout(null);
			this.setTitle("超市收银管理系统");
			this.setResizable(false);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("用户登录");
				jLabel1.setBounds(164, 56, 224, 32);
				jLabel1.setBackground(new java.awt.Color(128, 128, 192));
				jLabel1.setFont(new java.awt.Font("仿宋_GB2312", 0, 22));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("账号：");
				jLabel2.setBounds(122, 133, 42, 15);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("密码：");
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
				submit.setText("登录");
				submit.setBounds(159, 248, 74, 22);
				submit.addActionListener(this);
			}
			{
				reset = new JButton();
				getContentPane().add(reset);
				reset.setText("重置");
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

	// 按钮监听
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == submit) {
			String username = Username.getText().trim();
			String password = Password.getText().trim();

			if (username.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(this, "用户名或密码不能为空！");
			} else {
				supermarketUser user = new userDB().checkUser(username, password);
				if (user != null) {
					String urole = user.getURole();
					// 将用户信息保存在常量中方便与在后台页面访问
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
					JOptionPane.showMessageDialog(this, "用户名或密码错误，请重新输入");
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
