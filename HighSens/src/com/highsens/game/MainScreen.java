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
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnStart.setForeground(new Color(0, 0, 0));
		btnStart.setBackground(new Color(50, 205, 50));
		btnStart.setContentAreaFilled(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenManager.displayGameScreen();
				ScreenManager.hideMainScreen();
			}
		});
		btnStart.setBounds(50, 94, 151, 60);
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
		btnQuit.setBounds(386, 202, 151, 60);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setOpaque(true);
		
		JButton btnStore = new JButton("Store");
		btnStore.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnStore.setBackground(new Color(153, 50, 204));
		btnStore.setBounds(50, 202, 151, 60);
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
		btnAbout.setBounds(386, 94, 151, 60);
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
		btnLogin.setBounds(218, 284, 151, 60);
		getContentPane().add(btnLogin);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblUser.setBounds(204, 55, 180, 23);
		getContentPane().add(lblUser);
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
		lblNewLabel.setBounds(171, 11, 241, 48);
		getContentPane().add(lblNewLabel);
		
		/*JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenManager.displayTitleScreen();
			}
		});
		btnNewButton.setBounds(237, 123, 89, 23);
		getContentPane().add(btnNewButton);
		*/
		
		
	}
	public void setUserLabel()
	{
		lblUser.setText("Current user: "+  CurrentPlayer.getName());
		lblUser.setVisible(true);
		
	}
}