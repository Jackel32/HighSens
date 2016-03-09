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
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.JToggleButton;
import java.awt.Label;

import javax.swing.JLabel;

public class GameScreen extends JFrame implements ActionListener, MouseListener, KeyListener {

	private final GamePanel gamePanel;
	private final GameData gameData;
	private final Animator animator;
	private final JButton playButton;
	private final JButton quitButton;
	private final JToggleButton arrowToggle;
	private final JToggleButton blueToggle;
	private final JToggleButton muteButton;
	private final JPanel southPanel;
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
	public static ScreenManager GUI;
	private AudioPlayer sound;
	public ArrayList TowerPosition;
	boolean ArrowPlaceable = false;
	boolean BluePlaceable = false;
	private int sellPosition = 0;
	private Image mute;
	public boolean nextWaveClicked;
	private JButton menuCloseButton;
	int muteCount = 0;
	private JTextField txtReady;
	
	public GameScreen() {
		nextWaveClicked = false;
		TowerPosition = new ArrayList();
		setSize(800, 511);
		Container c = getContentPane();
		animator = new Animator();
		gameData = new GameData();
		gamePanel = new GamePanel(animator, gameData, this);
		gamePanel.setBounds(0, 0, 573, 325);
		animator.setGamePanel(gamePanel);
		animator.setGameData(gameData);
		getContentPane().setLayout(null);
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		
			    southPanel = new JPanel();
			    southPanel.setBounds(25, 95, 531, 131);
			    getContentPane().add(southPanel);
			    southPanel.setBackground(SystemColor.activeCaptionBorder);
			    southPanel.setLayout(null);
			    playButton = new JButton("Play");
			    playButton.setBounds(125, 63, 74, 44);
			    southPanel.add(playButton);
			    quitButton = new JButton("Quit");
			    quitButton.setBounds(337, 63, 69, 44);
			    southPanel.add(quitButton);
			    playButton.setFocusable(false);
			    
			    txtReady = new JTextField();
			    txtReady.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
			    txtReady.setHorizontalAlignment(SwingConstants.CENTER);
			    txtReady.setText("Ready?");
			    txtReady.setBounds(196, 11, 138, 41);
			    southPanel.add(txtReady);
			    txtReady.setColumns(10);
			    
			    playButton.addActionListener(this);
			    quitButton.addActionListener(this);
			    menuCloseButton = new JButton("Cancel");	    

		c.add(gamePanel);

		gamePanel.addMouseListener(this);
		gamePanel.setFocusable(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 331, 573, 131);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		arrowToggle = new JToggleButton("\r\n");
		arrowToggle.setVerticalAlignment(SwingConstants.BOTTOM);
		arrowToggle.setEnabled(false);
		arrowToggle.setIcon(new ImageIcon("C:\\Users\\Sha\\git\\HighSens\\HighSens\\images\\ArrowTower.png"));
		arrowToggle.setBounds(83, 0, 173, 88);
		//redToggle.setIcon(new ImageIcon (imagePath + separator + "images" + separator + "BlueTower.png"));
		arrowToggle.addActionListener(this);
		panel.add(arrowToggle);
		
		blueToggle = new JToggleButton("");
		blueToggle.setEnabled(false);
		blueToggle.setIcon(new ImageIcon("C:\\Users\\Sha\\git\\HighSens\\HighSens\\images\\BlueTower.png"));
		blueToggle.setBounds(307, 0, 173, 88);
		blueToggle.addActionListener(this);
		panel.add(blueToggle);
		
		muteButton = new JToggleButton("");
		muteButton.setBounds(512, 11, 44, 45);
		muteButton.setIcon(new ImageIcon("C:\\Users\\Sha\\git\\HighSens\\HighSens\\images\\mute.jpg"));
		muteButton.addActionListener(this);
		panel.add(muteButton);
		
		JLabel lblNewLabel = new JLabel("<html> Arrow Tower  <br> Cost: 50g </html>\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(101, 87, 155, 31);
		panel.add(lblNewLabel);
		
		JLabel lblBlueTowerCost = new JLabel("<html> Blue Tower  <br> Cost: 100g </html>\r\n");
		lblBlueTowerCost.setVerticalAlignment(SwingConstants.TOP);
		lblBlueTowerCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlueTowerCost.setBounds(317, 87, 155, 31);
		panel.add(lblBlueTowerCost);
		
		
		

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
		RangePanel.setBounds(700, 100, 200, 100);
		LevelPanel.setText("Level: " + abstractTowerGameFigure.getLevel());
		LevelPanel.setBounds(700, 0, 100, 100);

		imageLabel.setText("image: " + abstractTowerGameFigure.getLevel());
		imageLabel.setBounds(600, 0, 100, 100);

		// Image newImage = getImage(imagePath + separator + "images" +
		// separator + "RedTower.png");

		imageLabel2.setText("image2: " + abstractTowerGameFigure.getLevel());
		imageLabel2.setBounds(600, 100, 100, 100);

		imageLabel3.setText("image3: " + abstractTowerGameFigure.getLevel());
		imageLabel3.setBounds(600, 200, 100, 100);

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
			southPanel.setVisible(false);
			southPanel.setEnabled(false);
			blueToggle.setEnabled(true);
			arrowToggle.setEnabled(true);
			gamePanel.startGame();

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
			blueToggle.setSelected(false);
		}
		
		else if (e.getSource() == blueToggle)
		{
			if(blueToggle.isSelected())
			{
				BluePlaceable = true;
				ArrowPlaceable = false;
				arrowToggle.setSelected(false);
				//arrowToggle.setSelected(true);
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
		if (x >= 250 && x <= 350 && y >= 295 && y <= 325) {
			gameData.setWaves(gameData.wave = gameData.wave + 1);
			gameData.resetCreepCount();
		}

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
		if (gameData.money >= 100 && BluePlaceable == true) {
			if (!(x >= 520 && x <= 590 && y >= 250 && y <= 320) && !(x >= 250 && x <= 350 && y >= 295 && y >= 325)
					&& !(x >= 440 && x <= 530 && y >= 250 && y <= 320)) {
				if (BluePlaceable == true) {
					gameData.moneyManager("tower2", gameData.getMoney());
					BlueTower = new BlueTower(x - 25, y - 50, gameData);
					gameData.figures.add(BlueTower);
					BluePlaceable = false;
					blueToggle.setSelected(false);
				}
			}
		} else if (gameData.money >= 50 && ArrowPlaceable == true) {
			if (!(x >= 520 && x <= 590 && y >= 250 && y <= 320) && !(x >= 250 && x <= 350 && y >= 295 && y >= 325)
					&& !(x >= 440 && x <= 530 && y >= 250 && y <= 320)) {
				if (ArrowPlaceable == true) {
					gameData.moneyManager("tower1", gameData.getMoney());
					ArrowTower = new ArrowTower(x - 25, y - 50, gameData);
					gameData.figures.add(ArrowTower);
					ArrowPlaceable = false;
					arrowToggle.setSelected(false);
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

