package com.highsens.game.tower;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


import com.highsens.game.GameFigure;
import com.highsens.game.GameData;
import com.highsens.game.monster.BloonMonster;
import com.highsens.game.monster.Boss;

public class Explode implements LandmineState{
	
	private GameData gd;
	private int height = 128;
	private int width = 128;
	public float x, y;
	long bStart, bEnd;
	private Image landmineImage;
	public boolean collision;
	Rectangle2D.Double monster, range;
	
	public Explode(){
		
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		landmineImage = getImage(imagePath + separator + "images" + separator + "explosion.png");
		bStart = System.currentTimeMillis();
		collision = false;
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
		if ((bEnd -  bStart) > 300){
			l.setState(0);
		}
		
	}

	@Override
	public void render(Graphics g, Landmine l) {

		this.x = l.x;
		this.y = l.y;
		g.drawImage(landmineImage, (int) x, (int) y -23, null);
		//g.drawRect((int) x, (int) y - 23, width, height);
		
	}

	@Override
	public boolean collision(GameFigure m) {
		
		collision = false;
	    range = new Rectangle2D.Double((int) x , (int) y - 23, width, height);
	    
	    if(m instanceof Boss){
	    	monster =  new Rectangle2D.Double ( (int) m.getX(),(int) m.getY(), 128, 128);
	    }else if(m instanceof BloonMonster){
	    	monster =  new Rectangle2D.Double ( (int) m.getX(),(int) m.getY(), 20, 27);
	    }else{
	    	monster =  new Rectangle2D.Double ( (int) m.getX(),(int) m.getY(), 47, 37);
	    }
	    
	    if (monster.intersects(range)){
	    	collision = true;
	    	//System.out.println("collision: " + collision);
	    }else{
	    	collision = false;
	    }

		return collision;
	}

}

