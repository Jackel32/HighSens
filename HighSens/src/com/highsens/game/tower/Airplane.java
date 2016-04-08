package com.highsens.game.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.highsens.game.GameData;
import com.highsens.game.GameFigure;
import com.highsens.game.Tower;
import com.highsens.game.monster.Boss;
import com.highsens.game.monster.FastMonster;
import com.highsens.game.monster.RegularMonster;

public class Airplane extends AbstractTower implements Tower, GameFigure {
	Image launcherImage;
    public float x, y;
    int range;
    
    Point2D.Float target;
	private int state = STATE_IDLE;
	FastMonster fastMonster;
	RegularMonster regularMonster;
	Boss boss;
	GameData gd;
    
    public Airplane(float x, float y) {
        this.x = x;
        this.y = y;
        
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");

        // put images in 'images' folder, which is on the top level of
        // the NetBeans project folder.
        // Using "Files" tab of the NetBeans explorer window, right click on
        // the project folder name, and create a folder named "image"
        // You cannot see "images" folder in 'Project' tab, though
        launcherImage = getImage(imagePath + separator + "images" + separator
                + "superman.png");
        
        boundingBox = new BoundingBox((int) x, (int) x + 50, (int) y, (int) y + 69);
        this.range = 200;
		this.level = 1;
		radius = new Ellipse2D.Double((this.x + 50) - 175, (this.y + 69) - 175, 300, 300);
    }
    
  
    
 
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		drawRange(g, x, y);
		g.drawImage(launcherImage, (int)x, (int)y, null);
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return STATE_TRAVELING;
	}
	@Override
	public void setState(int state) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
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
		boolean collision = false;
		if (radius.intersects(m.getX(), m.getY(), 47, 37)) {
			collision = true;
		} else {
			collision = false;
		}
		return collision;
	}
	@Override
	public float getXofMissileShoot() {
		// TODO Auto-generated method stub
		return x+35;
	}
	@Override
	public float getYofMissileShoot() {
		// TODO Auto-generated method stub
		return y+20;
	}
	@Override
	public boolean contains(float f, float g) {
		// TODO Auto-generated method stub
		return false;
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
	public void updateState() {
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




	@Override
	public Image getUpgradeTowerImage() {
		// TODO Auto-generated method stub
		return null;
	}
}
