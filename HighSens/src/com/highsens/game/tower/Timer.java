package com.highsens.game.tower;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.highsens.game.GameFigure;

public class Timer implements LandmineState{
	
	private int height = 45;
	private int width = 47;
	public float x, y;
	long bStart, bEnd, timer;
	private Image landmineImage, timerImage;
	String imagePath = System.getProperty("user.dir");
	String separator = System.getProperty("file.separator");
	
	public Timer(){
	
		bStart = System.currentTimeMillis();
		landmineImage = getImage(imagePath + separator + "images" + separator + "landmine.png");
		
	}
	
	@Override
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
	public void update(Landmine l) {
		
		bEnd = System.currentTimeMillis();
		timer = (bEnd- bStart) / 100;
		System.out.println("time: " + timer);
		switch ((int)timer) {
		case 1:
			timerImage = getImage(imagePath + separator + "images" + separator + "d3.png");
			break;
		case 4:
			timerImage = getImage(imagePath + separator + "images" + separator + "d2.png");
			break;
		case 7:
			timerImage = getImage(imagePath + separator + "images" + separator + "d1.png");
			break;
		case 11:
			timerImage = getImage(imagePath + separator + "images" + separator + "d0.png");
			break;
		case 13:
			l.curry = new Explode();
			break;	
		}
	}

	@Override
	public void render(Graphics g, Landmine l) {

		this.x = l.x;
		this.y = l.y;
		g.drawImage(landmineImage, (int) x, (int) y, null);
		g.drawImage(timerImage, (int) x + width, (int) y - height, null);
		
	}

	@Override
	public boolean collision(GameFigure m) {
		// TODO Auto-generated method stub
		return false;
	}

}
