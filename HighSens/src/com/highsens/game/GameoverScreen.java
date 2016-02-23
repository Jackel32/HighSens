package com.highsens.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameoverScreen extends JFrame {

	private JPanel contentPane;
	private int waves = 0;
	private int score = 0;

	/**
	 * Create the frame.
	 */
	public GameoverScreen(int w, int s) {
		this.waves = w;
		this.score = s;
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lbl = new JLabel("Game over, man!!!");
		lbl.setForeground(new Color(255, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl.setBounds(5, 5, 490, 51);
		contentPane.add(lbl);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(91, 67, 303, 127);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblWaves = new JLabel("Waves survived: " + waves);
		lblWaves.setBounds(22, 21, 255, 22);
		panel.add(lblWaves);
		
		JLabel label = new JLabel("Score: " + score);
		label.setBounds(22, 62, 255, 22);
		panel.add(label);
		
		JButton btnPlayAgain = new JButton("Play again");
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LevelSelectScreen lss = new LevelSelectScreen();
				lss.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnPlayAgain.setForeground(Color.BLACK);
		btnPlayAgain.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnPlayAgain.setBackground(new Color(50, 205, 50));
		btnPlayAgain.setBounds(91, 221, 131, 30);
		contentPane.add(btnPlayAgain);
		btnPlayAgain.setContentAreaFilled(false);
		btnPlayAgain.setOpaque(true);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		btnQuit.setForeground(Color.BLACK);
		btnQuit.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		btnQuit.setBackground(new Color(255, 0, 0));
		btnQuit.setBounds(254, 221, 131, 30);
		contentPane.add(btnQuit);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setOpaque(true);
	}
}
