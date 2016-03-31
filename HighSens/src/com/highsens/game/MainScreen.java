package com.highsens.game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class MainScreen extends JFrame {
	private JLabel lblUser = new JLabel("");
	
	public MainScreen() {
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(70, 130, 180));
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		this.setLocationRelativeTo(null);
		
		JLabel lblTowerDefense = new JLabel("Tower Defense");
		lblTowerDefense.setBounds(0, 0, 535, 36);
		lblTowerDefense.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
		lblTowerDefense.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTowerDefense);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnStart.setForeground(new Color(0, 0, 0));
		btnStart.setBackground(new Color(50, 205, 50));
		btnStart.setContentAreaFilled(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenManager.displayLevelSelectScreen();
				ScreenManager.hideMainScreen();
			}
		});
		btnStart.setBounds(40, 81, 112, 46);
		btnStart.setOpaque(true);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenManager.disposeAll();
				System.exit(0);
			}
		});
		btnQuit.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnQuit.setBackground(new Color(255, 0, 0));
		btnQuit.setBounds(420, 190, 112, 46);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setOpaque(true);
		
		JButton btnStore = new JButton("Store");
		btnStore.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnStore.setBackground(new Color(153, 50, 204));
		btnStore.setBounds(40, 190, 112, 46);
		btnStore.setContentAreaFilled(false);
		btnStore.setOpaque(true);
		StoreAnimator animator = new StoreAnimator();
		btnStore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//GameData gamedata = new GameData();
				//final StorePanel store = new StorePanel(animator, gamedata);
				//store.setVisible(true);
				//setVisible(false);
				//final StoreScreen storeScreen = new StoreScreen();
				//storeScreen.setVisible(true);
				//setVisible(false);
				ScreenManager.displayStoreScreen();
			}
		});
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.displayAboutScreen();
				ScreenManager.hideMainScreen();
			}
		});
		
		btnAbout.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnAbout.setBackground(new Color(255, 215, 0));
		btnAbout.setBounds(420, 81, 112, 46);
		btnAbout.setContentAreaFilled(false);
		btnAbout.setOpaque(true);
		
		getContentPane().add(btnStart);
		getContentPane().add(btnQuit);
		getContentPane().add(btnStore);
		getContentPane().add(btnAbout);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.displayLoginScreen();
				ScreenManager.hideMainScreen();
			}
		});
		
		btnLogin.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnLogin.setContentAreaFilled(false);
		btnLogin.setOpaque(true);
		btnLogin.setBackground(new Color(0, 255, 255));
		btnLogin.setBounds(234, 273, 112, 46);
		getContentPane().add(btnLogin);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblUser.setBounds(181, 47, 180, 23);
		getContentPane().add(lblUser);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenManager.displayTitleScreen();
			}
		});
		btnNewButton.setBounds(234, 93, 89, 23);
		getContentPane().add(btnNewButton);
		
		
	}
	public void setUserLabel()
	{
		lblUser.setText("Current user: "+ ScreenManager.getUser());
		lblUser.setVisible(true);
	}
}