package com.cashier.view;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;

public class newJPanel extends javax.swing.JPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new newJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public newJPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
