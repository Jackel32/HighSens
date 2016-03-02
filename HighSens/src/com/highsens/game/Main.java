/*//<<<<<<< HEAD
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.highsens.game.tower.AbstractTower;
import com.highsens.game.tower.ArrowTower;
import com.highsens.game.tower.BlueTower;

public class Main extends JFrame implements ActionListener, MouseListener, KeyListener {

	private final GamePanel gamePanel;
	private final GameData gameData;
	private final Animator animator;
	private final JButton playButton;
	private final JButton quitButton;
	private static ArrowTower ArrowTower;
	private static BlueTower BlueTower;
	public ArrayList TowerPosition;
	boolean ArrowPlaceable = false;
	boolean BluePlaceable = false;

	public Main() {
		TowerPosition = new ArrayList();
		setSize(600, 400);
		Container c = getContentPane();
		animator = new Animator();
		gameData = new GameData();
		gamePanel = new GamePanel(animator, gameData);
		animator.setGamePanel(gamePanel);
		animator.setGameData(gameData);

		c.add(gamePanel, "Center");

		JPanel southPanel = new JPanel();
		playButton = new JButton("Play");
		southPanel.add(playButton);
		quitButton = new JButton("Quit");
		southPanel.add(quitButton);
		c.add(southPanel, "South");

		gamePanel.addMouseListener(this);
		playButton.setFocusable(false);
		gamePanel.setFocusable(true);
		gamePanel.addKeyListener(this);
		playButton.addActionListener(this);
		quitButton.addActionListener(this);

	}

	private void increaseSizeOfTowerRangeWhenOverlapped(int pressedXposition, int pressedYposition) {
		Iterator<GameFigure> iterator = gameData.figures.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			GameFigure gameFigure = iterator.next();
			if (gameFigure instanceof AbstractTower) {
				AbstractTower abstractTowerGameFigure = (AbstractTower) gameFigure;
				if (abstractTowerGameFigure.collision(pressedXposition, pressedYposition)) {
					
					System.out.println("YOU CLICKED ON THE TOWER");
					abstractTowerGameFigure.changeRange();
					String imagePath = System.getProperty("user.dir");
					String separator = System.getProperty("file.separator");
					Image newImage = getImage(imagePath + separator + "images" + separator + "RedTower.png"); //changes tower
					abstractTowerGameFigure.setTowerImage(newImage);
					gameData.sellFigures.clear();
					gameData.sellFigures.add(new SellManager(gameData.figures.get(i).getX(),gameData.figures.get(i).getY()));
					
				}else if(!gameData.sellFigures.isEmpty()){
					
					if(gameData.sellFigures.get(0).collisionManager(pressedXposition,pressedYposition)){
						
						gameData.sellFigures.clear();
						gameData.figures.get(i).setState(0);
						
					}else{
						
						gameData.sellFigures.clear();
					}
				}
				
			}
			i++;
		}
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
			playButton.setEnabled(false);
			gamePanel.startGame();

		} else if (e.getSource() == quitButton) {
			animator.running = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();

		increaseSizeOfTowerRangeWhenOverlapped(x, y);

		// System.out.println("X: " + x);
		// System.out.println("Y: " + y);

		// Limits the clickable range to the button
		if (x >= 250 && x <= 350 && y >= 295 && y <= 325) {
			gameData.setWaves(gameData.wave = gameData.wave + 1);
			gameData.resetCreepCount();
		}

		if (x >= 520 && x <= 590 && y >= 250 && y <= 320) {
			BluePlaceable = true;
		}

		if (x >= 440 && x <= 530 && y >= 250 && y <= 320) {
			ArrowPlaceable = true;
		}

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
				}
			}
		}
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

	public static void main(String[] args) {
		final MainScreen mainScreen = new MainScreen();

		mainScreen.setVisible(true);

		/*
		 * JFrame game = new Main(); game.setTitle("Tower Defence");
		 * game.setResizable(false); game.setLocationRelativeTo(null);
		 * game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * game.setVisible(true);
		 
	}
}
*/
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.highsens.game.tower.AbstractTower;
import com.highsens.game.tower.ArrowTower;
import com.highsens.game.tower.BlueTower;

public class Main extends JFrame implements ActionListener, MouseListener, KeyListener {

	private final GamePanel gamePanel;
	private final GameData gameData;
	private final Animator animator;
	private final JButton playButton;
	private final JButton quitButton;
	private static ArrowTower ArrowTower;
	private static BlueTower BlueTower;
	public static GUIManager GUI;
	private AudioPlayer sound;
	public ArrayList TowerPosition;
	boolean ArrowPlaceable = false;
	boolean BluePlaceable = false;
	private int sellPosition = 0;

	int muteCount = 0;
	
	public Main() {
		TowerPosition = new ArrayList();
		setSize(600, 400);
		Container c = getContentPane();
		animator = new Animator();
		gameData = new GameData();
		gamePanel = new GamePanel(animator, gameData, this);
		animator.setGamePanel(gamePanel);
		animator.setGameData(gameData);

		c.add(gamePanel, "Center");

		JPanel southPanel = new JPanel();
		playButton = new JButton("Play");
		southPanel.add(playButton);
		quitButton = new JButton("Quit");
		southPanel.add(quitButton);
		c.add(southPanel, "South");

		gamePanel.addMouseListener(this);
		playButton.setFocusable(false);
		gamePanel.setFocusable(true);
		gamePanel.addKeyListener(this);
		playButton.addActionListener(this);
		quitButton.addActionListener(this);

	}

	private void increaseSizeOfTowerRangeWhenOverlapped(int pressedXposition, int pressedYposition) {
		Iterator<GameFigure> iterator = gameData.figures.iterator();
		int countPosition = 0;
		boolean isCleanofSellBox = true;
		boolean flag = false;

		while (iterator.hasNext()) {
			GameFigure gameFigure = iterator.next();
			if (gameFigure instanceof AbstractTower) {
				AbstractTower abstractTowerGameFigure = (AbstractTower) gameFigure;
				if (abstractTowerGameFigure.collision(pressedXposition, pressedYposition)) {
					//System.out.println("YOU CLICKED ON THE TOWER");
					abstractTowerGameFigure.changeRange();
					flag = true;
					String imagePath = System.getProperty("user.dir");
					String separator = System.getProperty("file.separator");
					Image newImage = getImage(imagePath + separator + "images" + separator + "BlueTower.png");
					abstractTowerGameFigure.setTowerImage(newImage);
					
					gameData.sellFigures.clear();
					gameData.sellFigures.add(new SellManager(gameData.figures.get(countPosition).getX(),gameData.figures.get(countPosition).getY()));
					sellPosition = countPosition;
					isCleanofSellBox = false;
					
				}else if (!gameData.sellFigures.isEmpty() && isCleanofSellBox){
					
					if (gameData.sellFigures.get(0).collisionManager(pressedXposition,pressedYposition)){
						
						gameData.figures.get(sellPosition).setState(0);
						gameData.sellFigures.clear();

						
					}else{
						
						gameData.sellFigures.clear();
					}
					
				}
			}
			
			if(gameFigure instanceof ArrowMissile && flag){
				ArrowMissile abstractArrowMissileFigure = (ArrowMissile) gameFigure;
				abstractArrowMissileFigure.setUNIT_TRAVEL_DISTANCE();
				flag = false;
			}
			else if(gameFigure instanceof Missile && flag){
				Missile abstractMissileFigure = (Missile) gameFigure;
				abstractMissileFigure.setUNIT_TRAVEL_DISTANCE();
				flag = false;
			}
			
			countPosition++;
		}

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
			playButton.setEnabled(false);
			gamePanel.startGame();

		} else if (e.getSource() == quitButton) {
			animator.running = false;
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

		if (x >= 520 && x <= 590 && y >= 250 && y <= 320) {
			BluePlaceable = true;
		}

		if (x >= 440 && x <= 530 && y >= 250 && y <= 320) {
			ArrowPlaceable = true;
		}

		if(x >= 10 && x <= 40 && y >= 295 && y <= 320) {
			muteCount++;
			if(muteCount % 2 != 0){
				sound.stop("background");
			} else {
				AudioPlayer.play("background", true);
			}
		}
		
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
				}
			}
		}
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

	public static void main(String[] args) {
		final Splash splash = new Splash();
		splash.showSplash();
		final MainScreen mainScreen = new MainScreen();
		mainScreen.setVisible(true);

		/*
		 * JFrame game = new Main(); game.setTitle("Tower Defence");
		 * game.setResizable(false); game.setLocationRelativeTo(null);
		 * game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * game.setVisible(true);
		 */
	}
}
//>>>>>>> origin/master
