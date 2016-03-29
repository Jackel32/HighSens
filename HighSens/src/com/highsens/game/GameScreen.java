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

public class GameScreen extends JFrame implements ActionListener, MouseListener, KeyListener {

	private final GamePanel gamePanel;
	private final GameData gameData;
	private final Animator animator;
	private final JButton playButton;
	private final JButton quitButton;
	private final JButton btnWave;
	private final JButton btnStore;
	private final JButton btnQuit;
	private final JToggleButton blueToggle;
	private final JToggleButton arrowToggle;
	private final JToggleButton redToggle;
	private final JToggleButton landmineToggle;
	private final JToggleButton muteButton;
	private final JPanel northPanel;
	private final JToggleButton lighting_spell;

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
	private AudioPlayer sound;
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
	ImageIcon arrowTowerIcon = createImageIcon("ArrowTower.png");
	ImageIcon blueTowerIcon = createImageIcon("BlueTower.png");
	ImageIcon landmineIcon = createImageIcon("Landmine.png");
	ImageIcon muteButtonIcon = createImageIcon("mute.png");
	ImageIcon lighting_spellIcon = createImageIcon("lighting_spell.png");
	private JLabel lblNewLabel;

	public GameScreen() {
		nextWaveClicked = false;
		TowerPosition = new ArrayList();
		setSize(1500, 800);
		Container c = getContentPane();
		animator = new Animator();
		gameData = new GameData();
		gamePanel = new GamePanel(animator, gameData, this);
		//gamePanel.setBounds(0, 0, 573, 325);
		gamePanel.setBounds(0, 0, 1500, 584);
		animator.setGamePanel(gamePanel);
		animator.setGameData(gameData);
		getContentPane().setLayout(null);
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		JPanel southPanel = new JPanel();
		
		
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
		
		
		//panel.setBounds(0, 336, 573, 131);
		southPanel.setBounds(0, 621, 585, 110);
		getContentPane().add(southPanel);
		southPanel.setLayout(null);

		
		landmineToggle = new JToggleButton("", landmineIcon);
		landmineToggle.setEnabled(false);
		landmineToggle.setBounds(381, 0, 94, 77);
		landmineToggle.addActionListener(this);
		southPanel.add(landmineToggle);

		
		lighting_spell = new JToggleButton("", lighting_spellIcon);
		lighting_spell.setEnabled(false);
		lighting_spell.setBounds(498, 0, 77, 77);
		lighting_spell.addActionListener(this);
		southPanel.add(lighting_spell);
		
		JLabel lblLandmineCost = new JLabel("<html> Landmine  <br> Cost: 200g </html>\r\n");
		lblLandmineCost.setVerticalAlignment(SwingConstants.TOP);
		lblLandmineCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblLandmineCost.setBounds(352, 75, 155, 31);
		southPanel.add(lblLandmineCost);
		

		muteButton = new JToggleButton("", muteButtonIcon);
		muteButton.setBounds(0, 0, 44, 45);
		southPanel.add(muteButton);
		
		JLabel lblLightSpell = new JLabel("<html> Light Spell  <br></html>\r\n");
		lblLightSpell.setVerticalAlignment(SwingConstants.TOP);
		lblLightSpell.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightSpell.setBounds(485, 75, 100, 31);
		southPanel.add(lblLightSpell);
		
		JPanel towerPanel = new JPanel();
		towerPanel.setBounds(54, 11, 317, 88);
		southPanel.add(towerPanel);
		towerPanel.setLayout(new GridLayout(2, 3, 0, 0));
		
		blueToggle = new JToggleButton("", blueTowerIcon);
		blueToggle.setVerticalAlignment(SwingConstants.BOTTOM);
		blueToggle.setEnabled(false);
		blueToggle.addActionListener(this);
		towerPanel.add(blueToggle);
		
		arrowToggle = new JToggleButton("", arrowTowerIcon);
		arrowToggle.setEnabled(false);
		arrowToggle.addActionListener(this);
		towerPanel.add(arrowToggle);
		
		
		redToggle = new JToggleButton("", (Icon) null);
		towerPanel.add(redToggle);
		
		JLabel label_1 = new JLabel("<html> Blue Tower  <br> Cost: 100g </html>\r\n");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		towerPanel.add(label_1);
		
		JLabel label = new JLabel("<html> Arrow Tower  <br> Cost: 50g </html>\r\n");
		towerPanel.add(label);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel = new JLabel("<html> Red Tower <br> Cost: 200g </html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		towerPanel.add(lblNewLabel);
		muteButton.addActionListener(this);
		
		btnQuit = new JButton("Quit to main menu\r\n");
		btnQuit.setBounds(439, 589, 146, 21);
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
		btnStore.setBounds(284, 589, 103, 21);
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
		btnWave.setBounds(140, 589, 103, 21);
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
		RangePanel.setBounds(1300, 0, 300, 25);

		LevelPanel.setText("Level: " + abstractTowerGameFigure.getLevel());
		LevelPanel.setBounds(1300, 50, 300, 25);

		TowerTypePanel.setText("Tower type: " + abstractTowerGameFigure.getLevel());
		TowerTypePanel.setBounds(1300, 100, 300, 25);

		LivesPanel.setText("Lives remaining: " + gameData.getLives());
		LivesPanel.setBounds(1300, 150, 300, 25);

		MoneyPanel.setText("Money remaining: " + gameData.getMoney());
		MoneyPanel.setBounds(1300, 200, 300, 25);

		RegularMonsterPanel.setText("Regular Monsters active: " + gameData.getRegularMonsterCount());
		RegularMonsterPanel.setBounds(1300, 250, 300, 25);

		FastMonsterPanel.setText("Fast Monsters active: " + gameData.getFastMonsterCount());
		FastMonsterPanel.setBounds(1300, 300, 300, 25);

		BossMonsterPanel.setText("Bosses active: " + gameData.getBossCount());
		BossMonsterPanel.setBounds(1300, 350, 300, 25);

		BloonMonsterPanel.setText("Balloon Monsters active: " + gameData.getBloonMonsterCount());
		BloonMonsterPanel.setBounds(1300, 400, 300, 25);

		gamePanel.add(RangePanel);
		gamePanel.add(LevelPanel);
		gamePanel.add(TowerTypePanel);
		gamePanel.add(LivesPanel);
		gamePanel.add(MoneyPanel);
		gamePanel.add(RegularMonsterPanel);
		gamePanel.add(FastMonsterPanel);
		gamePanel.add(BossMonsterPanel);
		gamePanel.add(BloonMonsterPanel);
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
			lighting_spell.setEnabled(true);
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
			lightingPlaceable = false;
			ArrowPlaceable = true;
			BluePlaceable = false;
			LandminePlaceable = false;
			blueToggle.setSelected(false);
			landmineToggle.setSelected(false);
			lighting_spell.setSelected(false);
		}
		
		else if (e.getSource() == blueToggle)
		{
			if(blueToggle.isSelected())
			{
				lightingPlaceable = false;
				BluePlaceable = true;
				ArrowPlaceable = false;
				LandminePlaceable = false;
				arrowToggle.setSelected(false);
				lighting_spell.setSelected(false);
				landmineToggle.setSelected(false);
			}
		}
		
		else if (e.getSource() == lighting_spell)
		{
			/*if(lighting_spell.isSelected())
			{
				for (int i = 0; i < gameData.figures.size(); i++) {
					if(gameData.figures.get(i) instanceof RegularMonster || gameData.figures.get(i) instanceof FastMonster ||
							gameData.figures.get(i) instanceof BloonMonster || gameData.figures.get(i) instanceof Boss ){
						gameData.figures.remove(i);
						gameData.update();
					}
				}
			
				gameData.moneyManager("LightingSpell", gameData.getMoney());
			}*/
			gameData.figures.clear();
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
			if (!(x >= 0 && x <= 1300 && y >= 160 && y <= 270)) {
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
			if (!(x >= 0 && x <= 1300 && y >= 160 && y <= 270)) {
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
			if ((x >= 0 && x <= 1300 && y >= 160 && y <= 270)) {
				if (LandminePlaceable == true) {
					gameData.moneyManager("Landmine", gameData.getMoney());
					Landmine = new Landmine(x - 50  , 180, gameData);
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
	
	public void enableLightingSpell(boolean b)
	{
		this.lighting_spell.setEnabled(b);
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

