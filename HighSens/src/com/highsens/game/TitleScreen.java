package com.highsens.game;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.highsens.game.tower.AbstractTower;
import com.highsens.game.tower.ArrowTower;
import com.highsens.game.tower.BlueTower;
import com.highsens.game.tower.Landmine;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.JToggleButton;
import java.awt.Label;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JToolBar;
import java.awt.GridLayout;

public class TitleScreen extends JFrame {

	private final DemoPanel demoPanel;
	private final GameData gameData;
	private final DemoAnimator animator;

	private boolean menuVisible;
	private String imagePath = System.getProperty("user.dir");
	private String separator = System.getProperty("file.separator");
	
	Label LevelPanel = new Label();
	Label RangePanel = new Label();
	Label TowerTypePanel = new Label();
	Label LivesPanel = new Label();
	Label MoneyPanel = new Label();
	Label RegularMonsterPanel = new Label();
	Label FastMonsterPanel = new Label();
	Label BossMonsterPanel = new Label();
	Label BloonMonsterPanel = new Label();
	
	private static ArrowTower ArrowTower;
	private static BlueTower BlueTower;
	private static Landmine Landmine;
	public static ScreenManager GUI;
	public ArrayList TowerPosition;
	boolean ArrowPlaceable = false;
	boolean BluePlaceable = false;
	boolean LandminePlaceable = false;
	boolean lightingPlaceable = false;
	private int sellPosition = 0;
	private Image mute;
	public boolean nextWaveClicked;
	public boolean isActive;
	private JButton menuCloseButton;
	int muteCount = 0;
	private JTextField txtReady;

	public TitleScreen() {
		nextWaveClicked = false;
		TowerPosition = new ArrayList();
		setSize(1300, 600);
		Container c = getContentPane();
		getContentPane().setLayout(null);
		
		animator = new DemoAnimator();
		gameData = new GameData();
		animator.setGameData(gameData);
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		demoPanel = new DemoPanel(animator, gameData);
		demoPanel.setBounds(0, 0, 1300, 460);
		animator.setDemoPanel(demoPanel);
		
				c.add(demoPanel);
				demoPanel.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBackground(Color.GREEN);
				panel.setBounds(0, 460, 1284, 102);
				getContentPane().add(panel);
				
				JButton btnNewButton_1 = new JButton("New button");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenManager.hideTitleScreen();
					}
				});
				panel.add(btnNewButton_1);
				
				JButton btnNewButton = new JButton("New button");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScreenManager.hideTitleScreen();
						System.exit(0);
					}
				});
				panel.add(btnNewButton);
		gameData.setWaves(gameData.wave = gameData.wave + 1);
		gameData.resetCreepCount();
		

	}
	
	public void playGame()
	{
		demoPanel.startGame();
	}
	
	public void dispatch()
	{
		this.dispose();
		//gameData.dispose();
		demoPanel.dispose();
	}
	}

