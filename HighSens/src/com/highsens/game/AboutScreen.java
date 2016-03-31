package com.highsens.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class AboutScreen extends JFrame {

	/**
	 * Create the frame.
	 */
	public AboutScreen() {
		setBounds(100, 100, 568, 384);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 539, 270);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Rules\r\n", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 534, 242);
		panel.add(scrollPane);
		
		JTextPane txtpnTest = new JTextPane();
		scrollPane.setViewportView(txtpnTest);
		txtpnTest.setContentType("text/html");
		txtpnTest.setText("\r\n\r\n<html> \r\n<head>\r\n<style>\r\np {\r\n    text-indent: 10px;\r\n}\r\n</style>\r\n</head>\r\n\r\n<b>*  Overview: </b> \r\n<br>\r\n<p>This game is based on tower defense. The player's goal is to survive as many waves of monsters as they can utilizing allotted resources. On each map, monsters navigate from the start to the end of a path. Each time a monster reaches the end of the path,  the player loses a life. Player try to prevent monsters from reaching the end by purchasing and building towers along the path that shoot and destroys monsters. </p>\r\n<br>\r\n<b>*Monsters </b>\r\n<br>\r\n<p>There are a variety of different monsters, each with their own characteristics. A healthbar is displayed above each monster; when it is depleted, the monster is destroyed. When a monster's health reaches a certain point, they may become angry and receive a boost in health and speed. Each monster destroyed nets the player a small amount of gold. </p>\r\n<br>\r\n<b>*Towers</b> <br>\r\n<p>Towers are purchased with in-game currency and placed along the monster path. Each type of tower has their own set of statics, including firepower and range. Firepower is a measure of the strength of the towers' missiles; the higher the firepower, the more damage that is dealt to the monsters. The range is a measure of the towers' line-of-sight radius; a tower will fire upon any monster within its range. Towers can be upgraded with in-game currency to increase firepower and range. They also may be sold for a partial refund in gold.</p> <br>\r\n\r\n<b>*Store</b> <br>\r\n<p>The store is used purchase upgrades and other items via the Main Menu or GameScreen between waves.</p><br>\r\n\r\n<b>*User accounts</b> <br>\r\n<p>Players are able to register and login to their accounts in order to retain their progress and currency. Players can login via the login screen from the main menu. </p> <br>\r\n\t\r\n\r\n</html>\t");
		
		JButton btnBack = new JButton("Go back to Main Menu");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.displayMainScreen();
				ScreenManager.hideAboutScreen();
			}
		});
		btnBack.setBounds(0, 301, 539, 34);
		getContentPane().add(btnBack);

	}
}
