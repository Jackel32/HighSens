package com.highsens.game;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;


public class Screen extends JPanel implements Runnable {
	public Thread thread = new Thread(this);
	
	public static Image[] tileset_ground = new Image[100];
	public static Image[] tileset_air = new Image[100];
	public static Image[] tileset_res = new Image[100];
	public static Image[] tileset_enemy = new Image[100];
	
	public static int myWidth, myHeight;
	public static int coin_number = 10, health = 100;        // Coin and heal info
	public static int killed = 0, killsToWin = 0, level = 1, maxLevel = 3;
	public static int winTime = 4000, winFrame = 0;
	
	public static boolean isFirst = true;
	public static boolean isDegub = false;
	public static boolean isWin = false;
	
	public static Point mse = new Point(0, 0);
	
	public static Room room;
	public static Save save;
	public static Store store;
	
	public static Enemy[] enemys = new Enemy[100];
	
	public Screen(Frame frame) {
		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		
		thread.start();
	}
	
	public static void hasWon() {
		if(killed == killsToWin) {
			isWin = true;
			killed = 0;
			coin_number = 0;
		}
	}
	
	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();
		
		coin_number = 10;
		health = 100;
		
		for(int i=0;i<tileset_ground.length;i++) {
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png").getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 20*i, 20, 20)));
		}	
		for(int i=0;i<tileset_air.length;i++) {
			tileset_air[i] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 20*i, 20, 20)));
		}
		
		tileset_res[0] = new ImageIcon("res/cell.png").getImage();
		tileset_res[1] = new ImageIcon("res/heart.png").getImage();
		tileset_res[2] = new ImageIcon("res/coin.png").getImage();
		
		tileset_enemy[0] = new ImageIcon("res/Regularmonster.png").getImage();
		
		save.loadSave(new File("save/mission" + level + ".map"));
		
		for (int i=0;i<enemys.length;i++) {
			enemys[i] = new Enemy();
		}
	}
	
	public void paintComponent (Graphics g) {
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			
			define();
			
			isFirst = false;
		}
		
		g.setColor(new Color(50, 50, 50));			// background color
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0, 0, 0));
		
		room.draw(g); // Drawing the room.
		
		for (int i=0;i<enemys.length;i++) {
			if(enemys[i].inGame) {
				enemys[i].draw(g);
			}
		}
		
		store.draw(g);// Drawing the store.
		if(health < 1) {
			g.setColor(new Color(240, 20, 20));
			g.fillRect(0, 0, myWidth, myHeight);
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Courier New", Font.BOLD, 14));
			g.drawString("Game Over", 10, 20);
		}
		
		if(isWin) {
			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Courier New", Font.BOLD, 14));
			if(level == maxLevel) {
				g.drawString("You won the whole game! you may now close the window down...", 10, 20);
			} else {
				g.drawString("You won! Please wait for the next level...", 10, 20);
			}
		}
	}
	
	public int spawnTime = 1600, spawnFrame = 0;
	public void enemySpawner() {
		if(spawnFrame >= spawnTime) {
			for (int i=0;i<enemys.length;i++) {
				if(!enemys[i].inGame) {
					enemys[i].spawnEnemy(Value.enemySelf);
					break;
				}
			}
			
			spawnFrame = 0;
		} else {
			spawnFrame += 1;
		}
	}
	
	public void run() {
		while (true) {
			if(!isFirst && health > 0 && !isWin){
				room.physic();
				enemySpawner();
				for(int i=0;i<enemys.length;i++) {
					if(enemys[i].inGame) {
						enemys[i].physic();
					}
				}
			} else {
				if(isWin) {
					if(winFrame >= winTime) {
						if(level == maxLevel) {
							System.exit(0);
						} else {
							level += 1;
							define();
							isWin = false;
						}

						
						winFrame = 0;
					} else {
						winFrame += 1;
					}
				}
			}
			
			repaint();
			
			try {
				thread.sleep(1);
			} catch(Exception e) { }
		}
	}
}
