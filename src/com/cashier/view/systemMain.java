package com.cashier.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cashier.util.constant;
import com.cashier.view.collect.intoWareTotalJPanel;
import com.cashier.view.collect.saleTotalJPanel;
import com.cashier.view.collect.turnOverJPanel;
import com.cashier.view.collect.vipTotalJPanel;
import com.cashier.view.goods.goodsInStoreJPanel;
import com.cashier.view.goods.goodsJPanel;
import com.cashier.view.goods.goodsOpertionJPanel;
import com.cashier.view.goods.goodsPrivoderJPanel;
import com.cashier.view.goods.goodsUnitJPanel;
import com.cashier.view.sale.saleJPanel;
import com.cashier.view.system.updatePasswordJPanel;
import com.cashier.view.vip.vipJPanel;

public class systemMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenu jMenu1 = null;

	private JMenu jMenu3 = null;

	private JMenu jMenu4 = null;

	private JMenuItem jMenuItem = null;

	private JMenuItem jMenuItem5;
	private JMenu jMenu2;

	private JMenuItem jMenuItem2 = null;

	private JMenuItem jMenuItem3 = null;

	private JMenuItem jMenuItem4 = null;

	private JMenuItem jMenuItem7 = null;

	private JMenuItem jMenuItem8 = null;

	private JMenuItem jMenuItem9 = null;

	// ��Ʒ���
	// private goodsInStoreJPanel goodsInStoreJPanel_IL;

	private JMenuItem jMenuItem10 = null;

	private JMenuItem jMenuItem11 = null;

	private JMenuItem jMenuItem12 = null;

	private JMenuItem jMenuItem13 = null;

	// �����
	private newJPanel newJPanel_IL;

	private JMenuItem jMenuItem16 = null;

	private JPanel jPanel = null;

	private JMenuItem jMenuItem14;

	private JMenuItem jMenuItem6;

	private CardLayout jPanelLayout;

	public systemMain() {
		super();
		initialize();
	}

	private void initialize() {
		this.setTitle("������������ϵͳ");
		this.setLocation(new Point(300, 100));
		this.setSize(710, 479);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJPanel());
		addCard();
		this.setResizable(false);
		this.setJMenuBar(getJJMenuBar());
		this.jPanelLayout.show(this.jPanel, "sale1");
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
			jJMenuBar.add(getJMenu3());
			jJMenuBar.add(getJMenu4());
			jJMenuBar.add(getJMenu2());
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu("��Ϣ����");
			jMenu.setPreferredSize(new Dimension(80, 25));
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem2());
		}
		return jMenu;
	}

	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu("��Ʒ����");
			jMenu1.setPreferredSize(new Dimension(80, 25));
			jMenu1.setText("��Ʒ����");
			menuShow(jMenu1);
			jMenu1.add(getJMenuItem3());
			jMenu1.add(getJMenuItem4());
			jMenu1.add(getJMenuItem6());
			jMenu1.add(getJMenuItem14());
		}
		return jMenu1;
	}

	private JMenu getJMenu3() {
		if (jMenu3 == null) {
			jMenu3 = new JMenu("��ѯͳ��");
			jMenu3.setPreferredSize(new Dimension(80, 25));
			menuShow(jMenu3);
			jMenu3.add(getJMenuItem7());
			jMenu3.add(getJMenuItem8());
			jMenu3.add(getJMenuItem9());
			jMenu3.add(getJMenuItem10());
			jMenu3.add(getJMenuItem11());
		}
		return jMenu3;
	}

	private JMenu getJMenu4() {
		if (jMenu4 == null) {
			jMenu4 = new JMenu("ϵͳ����");
			jMenu4.setPreferredSize(new Dimension(80, 25));
			jMenu4.add(getJMenuItem12());
			jMenu4.add(getJMenuItem13());
			jMenu4.add(getJMenuItem16());
		}
		return jMenu4;
	}

	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem("��Ӧ�̹���");
			jMenuItem.setIcon(new ImageIcon(getClass().getResource("/com/image/GysGuanLi.png")));
			menuItemShow(jMenuItem);
			jMenuItem.addActionListener(this);

		}
		return jMenuItem;
	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem("��Ա����");
			jMenuItem2.setIcon(new ImageIcon(getClass().getResource("/com/image/KeHuGuanLi.png")));
			jMenuItem2.setText("��Ա����");
			jMenuItem2.addActionListener(this);
		}
		return jMenuItem2;
	}

	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem("������  ");
			jMenuItem3.setPreferredSize(new Dimension(93, 30));
			jMenuItem3.setIcon(new ImageIcon(getClass().getResource("/com/image/JinHuoDan.png")));
			jMenuItem3.setText("������");
			jMenuItem3.addActionListener(this);
		}
		return jMenuItem3;
	}

	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem("��λ����");
			jMenuItem4.setPreferredSize(new Dimension(93, 30));
			jMenuItem4.setIcon(new ImageIcon(getClass().getResource("/com/image/XiaoShouDan.png")));
			jMenuItem4.setText("��λ����");
			jMenuItem4.addActionListener(this);

		}
		return jMenuItem4;
	}

	/**
	 * This method initializes jMenuItem7
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem7() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem("�ͻ���ѯ");
			jMenuItem7.setPreferredSize(new Dimension(93, 30));
			jMenuItem7.setIcon(new ImageIcon(getClass().getResource("/com/image/KeHuChaXun.png")));
			jMenuItem7.setText("���ͳ��");
			jMenuItem7.addActionListener(this);
		}
		return jMenuItem7;
	}

	/**
	 * This method initializes jMenuItem8
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem8() {
		if (jMenuItem8 == null) {
			jMenuItem8 = new JMenuItem("��Ʒ��ѯ");
			jMenuItem8.setPreferredSize(new Dimension(93, 30));
			jMenuItem8.setIcon(new ImageIcon(getClass().getResource("/com/image/ShangPinChaXun.png")));
			jMenuItem8.setText("����ͳ��");
			jMenuItem8.addActionListener(this);
		}
		return jMenuItem8;
	}

	/**
	 * This method initializes jMenuItem9
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem9() {
		if (jMenuItem9 == null) {
			jMenuItem9 = new JMenuItem("��Ӧ�̲�ѯ");
			jMenuItem9.setIcon(new ImageIcon(getClass().getResource("/com/image/GongYingShangChaXun.png")));
			jMenuItem9.setPreferredSize(new Dimension(129, 30));
			jMenuItem9.setText("Ӫҵͳ��");

		}
		return jMenuItem9;
	}

	/**
	 * This method initializes jMenuItem10
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem10() {
		if (jMenuItem10 == null) {
			jMenuItem10 = new JMenuItem("���۲�ѯ");
			jMenuItem10.setPreferredSize(new Dimension(93, 30));
			jMenuItem10.setIcon(new ImageIcon(getClass().getResource("/com/image/XiaoShouChaXun.png")));
			jMenuItem10.setText("����Աͳ��");
			jMenuItem10.addActionListener(this);
		}
		return jMenuItem10;
	}

	/**
	 * This method initializes jMenuItem11
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem11() {
		if (jMenuItem11 == null) {
			jMenuItem11 = new JMenuItem("����ѯ");
			jMenuItem11.setIcon(new ImageIcon(getClass().getResource("/com/image/RuKuChaXun.png")));
			jMenuItem11.setPreferredSize(new Dimension(129, 30));
			jMenuItem11.setText("��Ա��ֵͳ��");
			jMenuItem11.addActionListener(this);
		}
		return jMenuItem11;
	}

	/**
	 * This method initializes jMenuItem12
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem12() {
		if (jMenuItem12 == null) {
			jMenuItem12 = new JMenuItem("ϵͳ�û�����");
			jMenuItem12.setIcon(new ImageIcon(getClass().getResource("/com/image/CzyGL.png")));
			menuItemShow(jMenuItem12);
			jMenuItem12.addActionListener(this);
		}
		return jMenuItem12;
	}

	/**
	 * This method initializes jMenuItem13
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem13() {
		if (jMenuItem13 == null) {
			jMenuItem13 = new JMenuItem("�����û�����");

			jMenuItem13.setIcon(new ImageIcon(getClass().getResource("/com/image/GengGaiMiMa.png")));
			jMenuItem13.addActionListener(this);

		}
		return jMenuItem13;
	}

	/**
	 * This method initializes jMenuItem16
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem16() {
		if (jMenuItem16 == null) {
			jMenuItem16 = new JMenuItem("�˳�ϵͳ");
			jMenuItem16.setIcon(new ImageIcon(getClass().getResource("/com/image/exitIcon.png")));
			jMenuItem16.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳���", "�˳�",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						System.exit(0);
					}

				}
			});

		}
		return jMenuItem16;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanelLayout = new CardLayout();
			jPanel.setLayout(jPanelLayout);
		}
		return jPanel;
	}

	private JMenuItem getJMenuItem6() {
		if (jMenuItem6 == null) {
			jMenuItem6 = new JMenuItem();
			jMenuItem6.setText("��������");
			jMenuItem6.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/image/JinHuoDan.png")));
			jMenuItem6.addActionListener(this);
		}
		return jMenuItem6;
	}

	private JMenuItem getJMenuItem14() {
		if (jMenuItem14 == null) {
			jMenuItem14 = new JMenuItem();
			jMenuItem14.setText("��Ϣ����");
			jMenuItem14.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/image/ShangPinGuanLi.png")));
			jMenuItem14.addActionListener(this);
		}
		return jMenuItem14;
	}

	/**
	 * ������е��Ӵ���
	 *
	 */
	private void addCard() {
		this.jPanel.add(getNewJPanel_IL(), "about");
		this.jPanel.add(new updatePasswordJPanel(), "updatePassword");
		this.jPanel.add(new systemUserJPanel(), "systemUserManager");
		this.jPanel.add(new goodsJPanel(), "GoodsManager");
		this.jPanel.add(new goodsUnitJPanel(), "goodsUnit");
		this.jPanel.add(new goodsPrivoderJPanel(), "goodsPrivoder");
		this.jPanel.add(new goodsInStoreJPanel(), "goodsInStore");
		this.jPanel.add(new goodsOpertionJPanel(), "goodsOpertion");
		this.jPanel.add(new vipTotalJPanel(), "VipTotal");
		this.jPanel.add(new turnOverJPanel(), "Turnover");
		this.jPanel.add(new intoWareTotalJPanel(), "intoWareTotal");
		this.jPanel.add(new saleTotalJPanel(), "saleTotal");
		this.jPanel.add(new vipJPanel(), "vip");
		this.jPanel.add(new saleJPanel(), "sale1");
	}

	public void actionPerformed(ActionEvent e) {
		// ��Ʒ��Ӧ�̹���
		if (e.getSource() == jMenuItem) {
			this.jPanelLayout.show(this.jPanel, "goodsPrivoder");
		}
		// ��Ա����
		if (e.getSource() == jMenuItem2) {
			this.jPanelLayout.show(this.jPanel, "vip");
		}
		// ��������
		if (e.getSource() == jMenuItem5) {
			this.jPanelLayout.show(this.jPanel, "sale1");
		}

		// ��������
		if (e.getSource() == jMenuItem6) {
			this.jPanelLayout.show(this.jPanel, "goodsInStore");
		}
		// //���ͳ��
		if (e.getSource() == jMenuItem7) {
			this.jPanelLayout.show(this.jPanel, "intoWareTotal");
		}
		// ����ͳ��
		if (e.getSource() == jMenuItem8) {
			this.jPanelLayout.show(this.jPanel, "saleTotal");
		}
		// ��Ա��ֵ��ϸ
		if (e.getSource() == jMenuItem10) {
			this.jPanelLayout.show(this.jPanel, "Turnover");
		}
		// ��Ա��ֵ��ϸ
		if (e.getSource() == jMenuItem11) {
			this.jPanelLayout.show(this.jPanel, "VipTotal");
		}
		// �޸��û�����
		if (e.getSource() == jMenuItem12) {
			this.jPanelLayout.show(this.jPanel, "systemUserManager");
		}
		// ϵͳ�û�����
		if (e.getSource() == jMenuItem13) {
			this.jPanelLayout.show(this.jPanel, "updatePassword");
		}
		// ��Ʒ������
		if (e.getSource() == jMenuItem3) {
			this.jPanelLayout.show(this.jPanel, "GoodsManager");
		}
		// ��Ʒ��Ϣ����
		if (e.getSource() == jMenuItem14) {
			this.jPanelLayout.show(this.jPanel, "goodsOpertion");
		}
		// ��Ʒ��λ����
		if (e.getSource() == jMenuItem4) {
			this.jPanelLayout.show(this.jPanel, "goodsUnit");
		}

	}

	public JPanel getCardPanel() {
		return this.jPanel;
	}

	public CardLayout getCardLayout() {
		return this.jPanelLayout;
	}

	/**
	 * �˵���Ȩ�޿���
	 * 
	 * @param menu
	 */
	public void menuShow(JMenu menu) {
		if (constant.urole.equals("����Ա")) {
			menu.setEnabled(false);
		} else {
			menu.setEnabled(true);
		}
	}

	/**
	 * �˵���Ȩ�޿���
	 * 
	 * @param menuItem
	 */
	public void menuItemShow(JMenuItem menuItem) {
		if (constant.urole.equals("����Ա")) {
			menuItem.setEnabled(false);
		} else {
			menuItem.setEnabled(true);
		}
	}

	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setText("��������");
			jMenu2.add(getJMenuItem5());

		}
		return jMenu2;
	}

	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("����");
			jMenuItem5.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/image/CzyGL.png")));
			jMenuItem5.addActionListener(this);
		}
		return jMenuItem5;
	}

	// private goodsInStoreJPanel getGoodsInStoreJPanel_IL() {
	// if (goodsInStoreJPanel_IL == null) {
	// goodsInStoreJPanel_IL = new goodsInStoreJPanel();
	// goodsInStoreJPanel_IL.setPreferredSize(new java.awt.Dimension(632, 385));
	// }
	// return goodsInStoreJPanel_IL;
	// }

	// ����������������
	private newJPanel getNewJPanel_IL() {
		if (newJPanel_IL == null) {
			newJPanel_IL = new newJPanel();
			newJPanel_IL.setPreferredSize(new java.awt.Dimension(581, 382));
		}
		return newJPanel_IL;
	}

}
