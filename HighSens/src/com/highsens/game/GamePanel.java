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
	public static final int PWIDTH = 600;
	public static final int PHEIGHT = 400;
	private Animator animator;
	private GameData gameData;
	private GameScreen gamescreen;
	private boolean gameover = false;
	private Graphics graphics;
	private Image dbImage;
	private Image mapImage;
	private Image ArrowTowerImage;
	private Image BlueTowerImage;
	private Image RegularMonsterImage;
	private Image FastMonsterImage;
	private Image BloonMonsterImage;
	private Image BossImage;
	private Image mute;

	public GamePanel(Animator animator, GameData gameData, GameScreen gameScreen) {
		///////////////////////////////
		// Use the animator and gamedata from within this class
		this.animator = animator;
		this.gameData = gameData;
		this.gamescreen = gameScreen;
		///////////////////////////////

		// Set global background color
		setBackground(Color.gray);
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		mapImage = getImage(imagePath + separator + "images" + separator + "map.png");

		// Adds the Regular Tower image from the images director with a distinct
		// seperator and path
		//ArrowTowerImage = getImage(imagePath + separator + "images" + separator + "ArrowTower.png");
		// Adds the Blue Tower image from the images director with a distinct
		// seperator and path
		//BlueTowerImage = getImage(imagePath + separator + "images" + separator + "BlueTower.png");


		RegularMonsterImage = getImage(imagePath + separator + "images" + separator + "RegularMonster1.png");
		FastMonsterImage = getImage(imagePath + separator + "images" + separator + "fastMonster1.png");
		BossImage = getImage(imagePath + separator + "images" + separator + "boss1.png");
		mute = getImage(imagePath + separator + "images" + separator + "mute.jpg");

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
			if(gameData.getMoney() < 50)
			{
				this.gamescreen.enableArrowToggle(false);
				this.gamescreen.enableBlueToggle(false);
			}
			
			else if(gameData.getMoney() >= 50 && gameData.getMoney() < 100)
			{
				this.gamescreen.enableArrowToggle(true);
				this.gamescreen.enableBlueToggle(false);
			}
			
			else
			{
				this.gamescreen.enableArrowToggle(true);
				this.gamescreen.enableBlueToggle(true);
			}

			///////////////////////////////
			// Creates white box. Puts the image of the Regular Tower in it
			/* BUTTON
			Graphics2D g3 = (Graphics2D) graphics;
			g3.setColor(Color.WHITE);
			g3.fillRect(440, 250, 70, 70);
			g3.drawImage(ArrowTowerImage, 450, 250, this);
			/////////////////////////////*/

			///////////////////////////////
			// Creates white box. Puts the image of the Blue Tower in it
			/* BUTTON
			Graphics2D g4 = (Graphics2D) graphics;
			g4.setColor(Color.WHITE);
			g4.fillRect(520, 250, 70, 70);
			g4.drawImage(BlueTowerImage, 530, 250, this);
			/////////////////////////////*/

			///////////////////////////////
			// Creates Red Text with the name and price for the Regular Tower
			/* Info Text
			Graphics2D g5 = (Graphics2D) graphics;
			g5.setColor(Color.RED);
			g5.setFont(new Font("Serif", Font.PLAIN, 16));
			g5.drawString("Tower 1", 450, 240);
			g5.drawString("50 Gold", 450, 330);
			/////////////////////////////*/

			///////////////////////////////
			// Creates Red Text with the name and price for the Regular Tower
			/* Info Text
			Graphics2D g6 = (Graphics2D) graphics;
			g6.setColor(Color.RED);
			g6.setFont(new Font("Serif", Font.PLAIN, 16));
			g6.drawString("Tower 2", 530, 240);
			g6.drawString("100 Gold", 530, 330);
			/////////////////////////////*/

			///////////////////////////////
			// Creates a Yellow box
			// BUTTON
			Graphics2D g7 = (Graphics2D) graphics;
			g7.setColor(Color.yellow);
			g7.fillRect(250, 295, 100, 30);
			///////////////////////////////

			///////////////////////////////
			// Creates text for the Yellow Button
			Graphics2D g8 = (Graphics2D) graphics;
			g8.setColor(Color.BLACK);
			g8.setFont(new Font("Serif", Font.PLAIN, 20));
			//if(main.nextWaveClicked == false) {g8.drawString("Start Game", 255, 317); }
			//else {g8.drawString("Next Wave", 255, 317);}
			///////////////////////////////

			///////////////////////////////
			// Creates the mute button
			Graphics2D g16 = (Graphics2D) graphics;
			g16.drawImage(mute, 10, 295, this);
			///////////////////////////////

			//////////////////////////////
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

				///////////////////////////////
				// Fills the screen from the origin (Top Left Corner) to (1000,
				/////////////////////////////// 1000) with black
				Graphics2D g9 = (Graphics2D) graphics;
				g9.setColor(Color.BLACK);
				g9.fillRect(0, 0, 1000, 1000);
				///////////////////////////////
				/*
				 * /////////////////////////////// // Renders Red Text in the
				 * specified Font to print GAME OVER
				 * /////////////////////////////// MAN!!! Graphics2D g10 =
				 * (Graphics2D) graphics; g10.setColor(Color.RED);
				 * g10.setFont(new Font("Serif", Font.PLAIN, 50));
				 * g10.drawString("GAME OVER, MAN!!!", 75, 100);
				 * ///////////////////////////////
				 * 
				 * /////////////////////////////// // Renders White Text in the
				 * specified Font to print the amount
				 * /////////////////////////////// of waves and the score
				 * Graphics2D g11 = (Graphics2D) graphics;
				 * g11.setColor(Color.WHITE); g11.setFont(new Font("Serif",
				 * Font.PLAIN, 25)); g11.drawString("Waves Completed: " +
				 * gameData.wave, 200, 150); g11.drawString("Score: " +
				 * gameData.score, 200, 200); ///////////////////////////////
				 */
			}
			///////////////////////////////

			// int i = 0.
			// increment i by 1 each time i is
			// less than the size of the figure object within the gameData
			// object
			for (int i = 0; i < gameData.figures.size(); i++) {

				// get(i): Function gets the object from the collection of
				// figures (images) within the gamedata
				f = (GameFigure) gameData.figures.get(i);

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
				//main.dispose();

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
		}
	}
}