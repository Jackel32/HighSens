package com.highsens.game;

import java.awt.Container;
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

public class GameScreen extends JFrame implements ActionListener, MouseListener, KeyListener {

	private final GamePanel gamePanel;
	private final GameData gameData;
	private final Animator animator;
	private final JButton playButton;
	private final JButton quitButton;
	private final JButton btnWave;
	private final JButton btnStore;
	private final JButton btnQuit;
	private final JToggleButton arrowToggle;
	private final JToggleButton blueToggle;
	private final JToggleButton landmineToggle;
	private final JToggleButton muteButton;
	private final JPanel northPanel;
	private boolean menuVisible;
	private String imagePath = System.getProperty("user.dir");
	private String separator = System.getProperty("file.separator");
	Label LevelPanel = new Label();
	Label RangePanel = new Label();
	Label imageLabel = new Label();
	Label imageLabel2 = new Label();
	Label imageLabel3 = new Label();
	private static ArrowTower ArrowTower;
	private static BlueTower BlueTower;
	private static Landmine Landmine;
	public static ScreenManager GUI;
	private AudioPlayer sound;
	public ArrayList TowerPosition;
	boolean ArrowPlaceable = false;
	boolean BluePlaceable = false;
	boolean LandminePlaceable = false;
	private int sellPosition = 0;
	private Image mute;
	public boolean nextWaveClicked;
	private JButton menuCloseButton;
	int muteCount = 0;
	private JTextField txtReady;
	ImageIcon arrowTowerIcon = createImageIcon("ArrowTower.png");
	ImageIcon blueTowerIcon = createImageIcon("BlueTower.png");
	ImageIcon landmineIcon = createImageIcon("Landmine.png");
	ImageIcon muteButtonIcon = createImageIcon("mute.png");
	
	public GameScreen() {
		nextWaveClicked = false;
		TowerPosition = new ArrayList();
		setSize(1500, 800);
		Container c = getContentPane();
		animator = new Animator();
		gameData = new GameData();
		gamePanel = new GamePanel(animator, gameData, this);
		//gamePanel.setBounds(0, 0, 573, 325);
		gamePanel.setBounds(0, 0, 1500, 590);
		animator.setGamePanel(gamePanel);
		animator.setGameData(gameData);
		getContentPane().setLayout(null);
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		
			    northPanel = new JPanel();
			    northPanel.setBounds(25, 95, 531, 131);
			    getContentPane().add(northPanel);
			    northPanel.setBackground(SystemColor.activeCaptionBorder);
			    northPanel.setLayout(null);
			    playButton = new JButton("Play");
			    playButton.setBounds(125, 63, 74, 44);
			    northPanel.add(playButton);
			    quitButton = new JButton("Quit");
			    quitButton.setBounds(337, 63, 69, 44);
			    northPanel.add(quitButton);
			    playButton.setFocusable(false);
			    
			    txtReady = new JTextField();
			    txtReady.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
			    txtReady.setHorizontalAlignment(SwingConstants.CENTER);
			    txtReady.setText("Ready?");
			    txtReady.setBounds(196, 11, 138, 41);
			    northPanel.add(txtReady);
			    txtReady.setColumns(10);
			    
			    playButton.addActionListener(this);
			    quitButton.addActionListener(this);
			    menuCloseButton = new JButton("Cancel");	    

		c.add(gamePanel);

		gamePanel.addMouseListener(this);
		gamePanel.setFocusable(true);
		
		JPanel southPanel = new JPanel();
		//panel.setBounds(0, 336, 573, 131);
		southPanel.setBounds(0, 600, 573, 131);
		getContentPane().add(southPanel);
		southPanel.setLayout(null);
		
		arrowToggle = new JToggleButton("", arrowTowerIcon);
		arrowToggle.setVerticalAlignment(SwingConstants.BOTTOM);
		arrowToggle.setEnabled(false);
		//arrowToggle.setIcon(new ImageIcon("C:\\Users\\Sha\\git\\HighSens\\HighSens\\images\\ArrowTower.png"));
		arrowToggle.setBounds(80, 24, 91, 77);
		//redToggle.setIcon(new ImageIcon (imagePath + separator + "images" + separator + "BlueTower.png"));
		arrowToggle.addActionListener(this);
		southPanel.add(arrowToggle);
		
		blueToggle = new JToggleButton("", blueTowerIcon);
		blueToggle.setEnabled(false);
		//blueToggle.setIcon(new ImageIcon("C:\\Users\\Sha\\git\\HighSens\\HighSens\\images\\BlueTower.png"));
		blueToggle.setBounds(301, 24, 77, 77);
		blueToggle.addActionListener(this);
		southPanel.add(blueToggle);

		
		landmineToggle = new JToggleButton("", landmineIcon);
		landmineToggle.setEnabled(false);
		//landmineToggle.setIcon(new ImageIcon("C:\\Users\\Sha\\git\\HighSens\\HighSens\\images\\landmine1.png"));
		landmineToggle.setBounds(400, 24, 100, 80);
		landmineToggle.addActionListener(this);
		southPanel.add(landmineToggle);
		
		JLabel lblNewLabel = new JLabel("<html> Arrow Tower  <br> Cost: 50g </html>\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(50, 100, 155, 31);
		southPanel.add(lblNewLabel);
		
		JLabel lblBlueTowerCost = new JLabel("<html> Blue Tower  <br> Cost: 100g </html>\r\n");
		lblBlueTowerCost.setVerticalAlignment(SwingConstants.TOP);
		lblBlueTowerCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlueTowerCost.setBounds(203, 100, 155, 31);
		southPanel.add(lblBlueTowerCost);
		
		JLabel lblLandmineCost = new JLabel("<html> Landmine  <br> Cost: 200g </html>\r\n");
		lblLandmineCost.setVerticalAlignment(SwingConstants.TOP);
		lblLandmineCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblLandmineCost.setBounds(410, 100, 155, 31);
		southPanel.add(lblLandmineCost);
		
		btnQuit = new JButton("Quit to main menu\r\n");
		btnQuit.setBounds(687, 697, 127, 24);
		getContentPane().add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.hideGameScreen();
				ScreenManager.displayMainScreen();
				AudioPlayer.stop("background");
			}
		});
		btnQuit.setEnabled(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBackground(Color.RED);
		btnQuit.setOpaque(true);
		
		btnStore = new JButton("Go to Store");
		btnStore.setBounds(688, 645, 126, 23);
		getContentPane().add(btnStore);
		btnStore.setEnabled(false);
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenManager.displayStoreScreen();
			}
		});
		btnStore.setContentAreaFilled(false);
		btnStore.setBackground(Color.YELLOW);
		btnStore.setOpaque(true);
		
		btnWave = new JButton("Start Wave");
		btnWave.setBounds(687, 601, 127, 23);
		getContentPane().add(btnWave);
		btnWave.setEnabled(false);
		btnWave.setContentAreaFilled(false);
		btnWave.setBackground(Color.GREEN);
		btnWave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameData.setWaves(gameData.wave = gameData.wave + 1);
				gameData.resetCreepCount();
				btnWave.setText("Next Wave");
			}
		});
		btnWave.setOpaque(true);
		

		muteButton = new JToggleButton("", muteButtonIcon);
		muteButton.setBounds(881, 612, 44, 45);
		getContentPane().add(muteButton);
		//muteButton.setIcon(new ImageIcon("C:\\Users\\Sha\\git\\HighSens\\HighSens\\images\\mute.jpg"));
		muteButton.addActionListener(this);

		
		
		

	}

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = GameScreen.class.getResource(path);
	    return new ImageIcon(imgURL);
	}

	private synchronized void increaseSizeOfTowerRangeWhenOverlapped(int pressedXposition, int pressedYposition) {
		synchronized (gameData.figures) {
			Iterator<GameFigure> iterator = gameData.figures.iterator();
			int countPosition = 0;
			boolean isCleanofSellBox = true;
			boolean flag = false;

			while (iterator.hasNext()) {
				GameFigure gameFigure = iterator.next();
				if (gameFigure instanceof AbstractTower) {
					AbstractTower abstractTowerGameFigure = (AbstractTower) gameFigure;

					if (abstractTowerGameFigure.collision(pressedXposition, pressedYposition)) {
						if (!menuVisible) {
							drawMenuForTower(abstractTowerGameFigure);
						} else {
							menuVisible = true;
						}

						abstractTowerGameFigure.upgradeTower();

						Image newImage = getImage(imagePath + separator + "images" + separator + "RedTower.png");

						abstractTowerGameFigure.setTowerImage(newImage);

						gameData.sellFigures.clear();
						gameData.sellFigures.add(new SellManager(gameData.figures.get(countPosition).getX(),
								gameData.figures.get(countPosition).getY()));
						sellPosition = countPosition;
						isCleanofSellBox = false;
						flag = true;
					} else if (!gameData.sellFigures.isEmpty() && isCleanofSellBox) {

						if (gameData.sellFigures.get(0).collisionManager(pressedXposition, pressedYposition)) {

							gameData.figures.get(sellPosition).setState(0);
							gameData.sellFigures.clear();

						} else {

							gameData.sellFigures.clear();
						}

					}
				}

				if (gameFigure instanceof ArrowMissile && flag) {
					ArrowMissile abstractArrowMissileFigure = (ArrowMissile) gameFigure;
					abstractArrowMissileFigure.setUNIT_TRAVEL_DISTANCE();
					flag = false;
				} else if (gameFigure instanceof Missile && flag) {
					Missile abstractMissileFigure = (Missile) gameFigure;
					abstractMissileFigure.setUNIT_TRAVEL_DISTANCE();
					flag = false;
				}
				countPosition++;
			}
		}
	}

	public void drawMenuForTower(AbstractTower abstractTowerGameFigure) {

		RangePanel.setText("Range: " + abstractTowerGameFigure.getRange());
		RangePanel.setBounds(1400, 100, 100, 100);
		LevelPanel.setText("Level: " + abstractTowerGameFigure.getLevel());
		LevelPanel.setBounds(1400, 0, 100, 100);

		imageLabel.setText("image: " + abstractTowerGameFigure.getLevel());
		imageLabel.setBounds(1300, 0, 100, 100);

		// Image newImage = getImage(imagePath + separator + "images" +
		// separator + "RedTower.png");

		imageLabel2.setText("image2: " + abstractTowerGameFigure.getLevel());
		imageLabel2.setBounds(1300, 100, 100, 100);

		imageLabel3.setText("image3: " + abstractTowerGameFigure.getLevel());
		imageLabel3.setBounds(1300, 200, 100, 100);

		gamePanel.add(imageLabel);
		gamePanel.add(imageLabel2);
		gamePanel.add(imageLabel3);
		gamePanel.add(RangePanel);
		gamePanel.add(LevelPanel);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			northPanel.setVisible(false);
			northPanel.setEnabled(false);
			blueToggle.setEnabled(true);
			arrowToggle.setEnabled(true);
			landmineToggle.setEnabled(true);
			gamePanel.startGame();
			btnWave.setEnabled(true);
			btnStore.setEnabled(true);
			btnQuit.setEnabled(true);

		} else if (e.getSource() == quitButton) {
			animator.running = false;
		}
		
		if (e.getSource() == menuCloseButton) {
			Label closed = new Label("ZZZZZZZZZZZZZZZZZ");
			closed.setBounds(0, 0, 600, 400);
		}
			
		
		else if(e.getSource() == arrowToggle)
		{
			ArrowPlaceable = true;
			BluePlaceable = false;
			LandminePlaceable = false;
			blueToggle.setSelected(false);
			landmineToggle.setSelected(false);
		}
		
		else if (e.getSource() == blueToggle)
		{
			if(blueToggle.isSelected())
			{
				BluePlaceable = true;
				ArrowPlaceable = false;
				LandminePlaceable = false;
				arrowToggle.setSelected(false);
				landmineToggle.setSelected(false);
			}
		}
		
		else if (e.getSource() == landmineToggle)
		{
			if(landmineToggle.isSelected())
			{
				LandminePlaceable = true;
				BluePlaceable = false;
				ArrowPlaceable = false;
				blueToggle.setSelected(false);
				arrowToggle.setSelected(false);
				
			}
		}

		
		else if(e.getSource() == muteButton)
		{
			if(muteButton.isSelected())
			{
				AudioPlayer.stop("background");
			}
			 
			else {
				AudioPlayer.play("background", true);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();
	

		increaseSizeOfTowerRangeWhenOverlapped(x, y);

		 //System.out.println("X: " + x);
		 //System.out.println("Y: " + y);

		// Limits the clickable range to the button
		//if (x >= 250 && x <= 350 && y >= 295 && y <= 325) {
		//	gameData.setWaves(gameData.wave = gameData.wave + 1);
		//	gameData.resetCreepCount();
		//}

		//if (x >= 520 && x <= 590 && y >= 250 && y <= 320) { //originally for blueTower placement


		//if (x >= 440 && x <= 530 && y >= 250 && y <= 320) { //originally for arrowTower placement
			
		
		
		

		/*Commented out for new JToggle muteButton
		if(x >= 10 && x <= 40 && y >= 295 && y <= 320) {
			muteCount++;
			if(muteCount % 2 != 0){
				sound.stop("background");
			} else {
				AudioPlayer.play("background", true);
			}
		}
		*/
		
		// Only allow the placement of towers if we have enough money and have
		// clicked the tower
		// Additionally only allow the placement of towers on any buttons.
		
		if (gameData.money >= 100 && BluePlaceable == true ) {
			if (!(x >= 0 && x <= 1300 && y >= 270 && y <= 380)) {
				if (BluePlaceable == true) {
					gameData.moneyManager("BlueTower", gameData.getMoney());
					BlueTower = new BlueTower(x - 25, y - 50, gameData);
					gameData.figures.add(BlueTower);
					BluePlaceable = false;
					blueToggle.setSelected(false);
				}
				if (LandminePlaceable == true) {
					gameData.moneyManager("Landmine", gameData.getMoney());
					Landmine = new Landmine(x - 50  , 278 , gameData);
					gameData.armsFigures.add(Landmine);
					LandminePlaceable = false;
					landmineToggle.setSelected(false);
					
				}
			}
		} else if (gameData.money >= 50 && ArrowPlaceable == true) {
			if (!(x >= 0 && x <= 1300 && y >= 270 && y <= 380)) {
				if (ArrowPlaceable == true) {
					gameData.moneyManager("RegularTower", gameData.getMoney());
					ArrowTower = new ArrowTower(x - 25, y - 50, gameData);
					gameData.figures.add(ArrowTower);
					ArrowPlaceable = false;
					arrowToggle.setSelected(false);
				}
				if (BluePlaceable == true) {
					gameData.moneyManager("BlueTower", gameData.getMoney());
					BlueTower = new BlueTower(x - 25, y - 50, gameData);
					gameData.figures.add(BlueTower);
					BluePlaceable = false;
					blueToggle.setSelected(false);
				}
			}
		} else if (gameData.money >= 200 && LandminePlaceable == true) {
			if ((x >= 0 && x <= 1300 && y >= 270 && y <= 380)) {
				if (LandminePlaceable == true) {
					gameData.moneyManager("Landmine", gameData.getMoney());
					Landmine = new Landmine(x - 50  , 278 , gameData);
					gameData.armsFigures.add(Landmine);
					LandminePlaceable = false;
					landmineToggle.setSelected(false);
					
				}
				
			}
		}
	}
	
	public void enableArrowToggle(boolean b)
	{
		this.arrowToggle.setEnabled(b);
	}
	
	public void enableBlueToggle(boolean b)
	{
		this.blueToggle.setEnabled(b);
	}
	
	public void enableLandmineToggle(boolean b)
	{
		this.landmineToggle.setEnabled(b);
	}

	@Override
	public void keyPressed(KeyEvent ke) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	public void mouseDragged(MouseEvent me) {

	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyReleased(KeyEvent ke) {
	}
	}

