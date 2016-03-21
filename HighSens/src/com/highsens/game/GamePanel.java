package com.highsens.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -6235582196874360230L;
	public static final int PWIDTH = 1500;
	public static final int PHEIGHT = 800;
	private Animator animator;
	private GameData gameData;
	private GameScreen gamescreen;
	private boolean gameover = false;
	private Graphics graphics;
	private Image dbImage;
	private Image mapImage;
	private Image RegularMonsterImage;
	private Image FastMonsterImage;
	private Image BloonMonsterImage;
	private Image BossImage;

	public GamePanel(Animator animator, GameData gameData, GameScreen gameScreen) {
		///////////////////////////////
		// Use the animator and gamedata from within this class
		this.animator = animator;
		this.gameData = gameData;
		this.gamescreen = gameScreen;
		///////////////////////////////

		// Set global background color
		// setBackground(Color.gray);
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		mapImage = getImage(imagePath + separator + "images" + separator + "map_new.png");
		BloonMonsterImage = getImage(imagePath + separator + "images" + separator + "BlueMonster.png");
		RegularMonsterImage = getImage(imagePath + separator + "images" + separator + "RegularMonster1.png");
		FastMonsterImage = getImage(imagePath + separator + "images" + separator + "fastMonster1.png");
		BossImage = getImage(imagePath + separator + "images" + separator + "boss1.png");

		AudioPlayer.loadStream("background", "sounds/bgmusic.wav");
	}

	public static Image getImage(String fileName) {
		Image image = null;

		// Try to pull the file from a user defined file path
		try {
			image = ImageIO.read(new File(fileName));
			// If that fails to succeed.
		} catch (IOException ioe) {
			// ERROR: either the the file wasn't in the folder or mispelled.
			System.out.println("Error: Cannot open image:" + fileName);
			JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
		}
		// Return the obtained Image Class Instantiation
		return image;
	}
	///////////////////////////////

	///////////////////////////////
	// This function starts the game loop (heart beat) of game
	public void startGame() {
		// Creates a new thread for the animator within this class.
		Thread t = new Thread(animator);
		AudioPlayer.play("background", true);
		// Calls the start class within our Thread Instantiation (starts the
		// loop)
		t.start();
	}
	///////////////////////////////

	///////////////////////////////
	// Renders every image 1 by 1 on the screen
	public void gameRender() {
		// If the Image object: dbImage is null
		if (dbImage == null) {
			dbImage = createImage(PWIDTH, PHEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else {
				graphics = dbImage.getGraphics();
			}
		}

		graphics.clearRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
		synchronized (gameData.figures) {

			// Instantiates class GameFigure
			GameFigure f;

			//

			graphics.drawImage(mapImage, 0, 0, null);
			Graphics2D g2 = (Graphics2D) graphics;
			g2.setColor(Color.RED);
			g2.setFont(new Font("Serif", Font.PLAIN, 20));
			g2.drawString("Lives: " + gameData.getLives(), 375, 60);
			g2.drawString("Gold: " + gameData.getMoney(), 470, 60);
			if (gameData.getMoney() < 50) {
				this.gamescreen.enableArrowToggle(false);
				this.gamescreen.enableBlueToggle(false);
			}

			else if (gameData.getMoney() >= 50 && gameData.getMoney() < 100) {
				this.gamescreen.enableArrowToggle(true);
				this.gamescreen.enableBlueToggle(false);
			}

			else {
				this.gamescreen.enableArrowToggle(true);
				this.gamescreen.enableBlueToggle(true);
			}

			Graphics2D gameStats = (Graphics2D) graphics;
			gameStats.setColor(Color.ORANGE);
			gameStats.fillRect(0, 0, 1000, 100);

			Graphics2D g12 = (Graphics2D) graphics;
			g12.drawImage(RegularMonsterImage, 30, 30, this);

			Graphics2D g13 = (Graphics2D) graphics;
			g13.drawImage(FastMonsterImage, 88, 30, this);

			Graphics2D g14 = (Graphics2D) graphics;
			g14.drawImage(BossImage, 144, 28, this);

			Graphics2D g17 = (Graphics2D) graphics;
			g17.drawImage(BloonMonsterImage, 210, 30, this);

			Graphics2D g15 = (Graphics2D) graphics;
			g15.setColor(Color.RED);
			g15.setFont(new Font("Serif", Font.PLAIN, 20));
			g15.drawString("x" + gameData.getRegularMonsterCount(), 64, 48);
			g15.drawString("x" + gameData.getFastMonsterCount(), 124, 48);
			g15.drawString("x" + gameData.getBossCount(), 180, 48);
			g15.drawString("x" + gameData.getBloonMonsterCount(), 244, 48);
			// END GAME CONDITION
			// If the user has no more lives
			if (gameData.lives <= 0) {
				//
				for (int i = 0; i < gameData.figures.size(); i++) {
					f = (GameFigure) gameData.figures.get(i);
					f.setState(GameFigure.STATE_DONE);
					gameover = true;
				}

				Graphics2D g9 = (Graphics2D) graphics;
				g9.setColor(Color.BLACK);
				g9.fillRect(0, 0, 1000, 1000);

			}
			///////////////////////////////

			for (int i = 0; i < gameData.figures.size(); i++) {

				f = (GameFigure) gameData.figures.get(i);

				// get(i): Function gets the object from the collection of
				// figures (images) within the gamedata

				// Calls render from class GameFigure:
				f.render(graphics);
			}

			for (int j = 0; j < gameData.sellFigures.size(); j++) {

				f = (GameFigure) gameData.sellFigures.get(j);
				f.render(graphics);
			}

			if (gameover) {
				final GameoverScreen gameoverScreen = new GameoverScreen(gameData.getWaves(), gameData.getScore());
				gameoverScreen.setVisible(true);

				gamescreen.setVisible(false);

				// main.dispose();

				gameover = false;
			}

		}

	}
	///////////////////////////////

	///////////////////////////////
	// This class uses active rendering to put the buffered image on-screen
	public void printScreen() {
		// Instantiagtes the Graphics class
		Graphics g;
		// PLEASE WORK
		try {
			// getGraphics() is a function within the Graphics library imported
			// at top
			// g contains the graphics within THIS class
			g = this.getGraphics();
			// If nothing is in the graphics object
			// and nothing is in image object: dbImage
			// and nothing is in image object: mapImage
			if ((g != null) && (dbImage != null) && (mapImage != null)) {
				// drawImage(Image img, int x, int y, ImageObserver observer)
				// Draws as much of the specified image as is currently
				// available.
				g.drawImage(dbImage, 0, 0, null);
			}
			// sync() - sunchronizes the graphics state on some systems
			Toolkit.getDefaultToolkit().sync();
			// Disposes of this graphics context and releases any system
			// resources that it is using.
			g.dispose();
			// Catch any errors
		} catch (Exception e) {
			System.out.println("Graphics error: " + e);
			throw e;
		}
	}
}