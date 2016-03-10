package com.highsens.game.tower;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.highsens.game.GameData;
import com.highsens.game.GameFigure;
import com.highsens.game.Monster;
import com.highsens.game.Tower;

public class BlueTower extends AbstractTower implements Tower, GameFigure {

	float x, y;
	Point2D.Float target;
	private int state = STATE_IDLE;

	Monster m;
	GameData gd;
	private GameFigure currentTarget;

	public BlueTower(float x, float y, GameData gd) {
		this.gd = gd;
		this.x = x;
		this.y = y;
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		towerImage = getImage(imagePath + separator + "images" + separator + "BlueTower.png");
		boundingBox = new BoundingBox((int) x, (int) x + 50, (int) y, (int) y + 69);
		range = 200;
		radius = new Ellipse2D.Double((this.x + 50) - 175, (this.y + 69) - 175, 300, 300);
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
	public float getXofMissileShoot() {
		return x + 30;
	}

	@Override
	public float getYofMissileShoot() {
		return y + 20;
	}

	@Override
	public void render(Graphics g) {
		drawRange(g, x, y);
		g.drawImage(towerImage, (int) x, (int) y, null);
	}

	@Override
	public void updateState() {

	}

	@Override
	public void update() {
		if (currentTarget != null && 
				(currentTarget.getState() == STATE_DONE ||
				!this.collision(currentTarget))){
			currentTarget = null;
		}
		switch (state) {

		case STATE_DONE:
			gd.moneyManager("sellBlueTower", gd.getMoney());
			break;
		}
	}

	@Override
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public int getState() {
		return state;
	}

	@Override
	public void updateHealth() {

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
	public int getHealth() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
	
	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(float f, float g) {
		// TODO Auto-generated method stub
		return false;
	}
	public GameFigure getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(GameFigure currentTarget) {
		this.currentTarget = currentTarget;
	}
}
