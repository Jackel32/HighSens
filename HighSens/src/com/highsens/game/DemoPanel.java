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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DemoPanel extends JPanel {

	private static final long serialVersionUID = -6235582196874360230L;
	public static final int PWIDTH = 1500;
	public static final int PHEIGHT = 800;
	private DemoAnimator animator;
	private GameData gameData;
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

	public DemoPanel(DemoAnimator animator, GameData gameData) {
		///////////////////////////////
		// Use the animator and gamedata from within this class
		this.animator = animator;
		this.gameData = gameData;
		///////////////////////////////

		// Set global background color
		setBackground(Color.gray);
		setLayout(null);
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		mapImage = getImage(imagePath + separator + "images" + separator + "map_new.png");

		// Adds the Regular Tower image from the images director with a distinct
		// seperator and path
		//ArrowTowerImage = getImage(imagePath + separator + "images" + separator + "ArrowTower.png");
		// Adds the Blue Tower image from the images director with a distinct
		// seperator and path
		//BlueTowerImage = getImage(imagePath + separator + "images" + separator + "BlueTower.png");


		RegularMonsterImage = getImage(imagePath + separator + "images" + separator + "RegularMonster1.png");
		FastMonsterImage = getImage(imagePath + separator + "images" + separator + "fastMonster1.png");
		BossImage = getImage(imagePath + separator + "images" + separator + "boss1.png");
		//mute = getImage(imagePath + separator + "images" + separator + "mute.jpg");

	}

	public static Image getImage(String fileName) {
		Image image = null;

		// Try to pull the file from a user defined file path
		try {
			image = ImageIO.read(new File(fileName));
			// If that fails to succeed.
		} catch (IOException ioe) {
			// ERROR: either the the file wasn't in the folder or mispelled.
			//System.out.println("Error: Cannot open image:" + fileName);
			//JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
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

		graphics.clearRect(0, 0, DemoPanel.PWIDTH, DemoPanel.PHEIGHT);
		synchronized (gameData.figures) {

			// Instantiates class GameFigure
			GameFigure f;

			//

			graphics.drawImage(mapImage, 0, 0, null);

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
			
			for (int j = 0; j < gameData.armsFigures.size(); j++) {

				f = (GameFigure) gameData.armsFigures.get(j);
				f.render(graphics);
			}

			
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
	
	public void dispose()
	{
		this.dispose();
	}
}