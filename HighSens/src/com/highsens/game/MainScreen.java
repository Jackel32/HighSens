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
	public MainScreen() {
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(70, 130, 180));
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		
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
				final LevelSelectScreen levelSelectScreen = new LevelSelectScreen();
				levelSelectScreen.setVisible(true);
				setVisible(false);
			}
		});
		btnStart.setBounds(50, 127, 112, 46);
		btnStart.setOpaque(true);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnQuit.setBackground(new Color(255, 0, 0));
		btnQuit.setBounds(386, 278, 112, 46);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setOpaque(true);
		
		JButton btnStore = new JButton("Store");
		btnStore.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnStore.setBackground(new Color(153, 50, 204));
		btnStore.setBounds(50, 278, 112, 46);
		btnStore.setContentAreaFilled(false);
		btnStore.setOpaque(true);
		btnStore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				final StoreScreen storeScreen = new StoreScreen();
				storeScreen.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnAbout.setBackground(new Color(255, 215, 0));
		btnAbout.setBounds(386, 127, 112, 46);
		btnAbout.setContentAreaFilled(false);
		btnAbout.setOpaque(true);
		
		getContentPane().add(btnStart);
		getContentPane().add(btnQuit);
		getContentPane().add(btnStore);
		getContentPane().add(btnAbout);
		
	}
}