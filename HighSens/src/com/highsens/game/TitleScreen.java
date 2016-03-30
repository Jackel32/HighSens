package com.highsens.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TitleScreen extends JFrame {

	private JPanel contentPane;
	private final DemoPanel demo;
	private final DemoAnimator animator;
	private final GameData gameData;

	public TitleScreen() {
		animator = new DemoAnimator();
		gameData = new GameData();
		Container c = getContentPane();
		demo = new DemoPanel(animator, gameData);
		demo.setBounds(0, 0, 573, 325);
		animator.setGameData(gameData);
		animator.setDemoPanel(demo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		demo.setFocusable(true);
		c.add(demo);
		//demo.startGame();
		
	}
	
	public void playGame()
	{
		demo.startGame();
	}
	
	public Image getImage(String fileName) {
		Image image = null;
		try {
			image = ImageIO.read(new File(fileName));
		} catch (Exception ioe) {
			System.out.println("Error: Cannot open image:" + fileName);
			JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
		}
		return image;
	}

}
