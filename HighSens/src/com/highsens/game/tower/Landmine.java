package com.highsens.game.tower;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.highsens.game.GameData;
import com.highsens.game.GameFigure;


public class Landmine extends AbstractTower implements GameFigure {
	
	public float x, y;
	//private GameData gd;
	//public LandmineState curry;
	public Image explosionImage;
	public boolean collision;
	//Rectangle2D.Double monster, range;
	private int state = STATE_IDLE;

	
	public Landmine(float x, float y, GameData gd){
		this.x = x;
		this.y = y;
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		towerImage = getImage(imagePath + separator + "images" + separator + "landmine.png");
		//collision = false;
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
	public void render(Graphics g) {
		g.drawImage(towerImage, (int) x, (int) y, null);
	}

	@Override
	public void update() {
        if (state == STATE_EXPLODING) {
        	String imagePath = System.getProperty("user.dir");
			String separator = System.getProperty("file.separator");
			explosionImage = getImage(imagePath + separator + "images" + separator + "explosion.png");
			state = STATE_DONE;
        }
	}


	@Override
	public int getState() {
		
		return state;
	}

	@Override
	public void setState(int state) {
		
		this.state = state;
		
	}

	@Override
	public double getX() {
		return (float) x;
	}

	@Override
	public double getY() {
		return (float) y;
	}

	@Override
	public void updateHealth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean collision(GameFigure m) {
/*		System.out.println("Collision?");
		if(m.getX() >= this.getX()) {
			System.out.println("True");
			state = STATE_EXPLODING;
			collision = true;
		}else{ System.out.println("False"); }*/
		//collision = false;
	    //range = new Rectangle(this.x, this.y , getWidth(), getHeight());
	    
	    /*collision = m.contains(this.x, this.y);
	   .
	    if(m instanceof Boss){
	    	monster =  new Rectangle2D.Double ( (int) m.getX(),(int) m.getY(), 128, 128);
	    }else if(m instanceof BloonMonster){
	    	monster =  new Rectangle2D.Double ( (int) m.getX(),(int) m.getY(), 20, 27);
	    }else{
	    	monster =  new Rectangle2D.Double ( (int) m.getX(),(int) m.getY(), 47, 37);
	    }
    	System.out.println("collision: " + collision);

	    if (monster.intersects(range)){
	    	collision = true;
	    	m.setState(0);
	    	//System.out.println("collision: " + collision);
	    }else{
	    	collision = false;
	 	   System.out.println("false");

	    }
	   System.out.println("1111");*/
		return false;
	}
		
		
		//collision = m.contains(this.x, this.y);

		//collision = curry.collision(m);
		//System.out.println("landmine Test");
		//return collision;
	//}

	@Override
	public float getXofMissileShoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYofMissileShoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(float x, float y) {
		System.out.println("inside landmine contains");
		if (x < this.x)
			return false;
		if (x > this.x + 100)
			return false;
		if (y < this.y)
			return false;
		if (y > this.y + 100)
			return false;

		return true;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsAngry(boolean t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsAngry() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getBulletCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setBulletCount(int bulletCount) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}

