package com.highsens.game.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import com.highsens.game.GameData;

public abstract class AbstractTower {

	public GameData data;
	public Ellipse2D.Double radius;
	public BoundingBox boundingBox;
	public Image towerImage;
	public int level;
	private int xOffset = 80;
	protected int range;
	private int yOffset = 80;
	public int bulletCount;

	/**
	 * Draws range on x and y coordinates.
	 */
	protected void drawRange(Graphics g, float x, float y) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.BLUE);
		g2d.drawOval((int) x - xOffset, (int) y - yOffset, range, range);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean collision(int xPosition, int yPosition) {
		if (boundingBox.getxMin() <= xPosition && xPosition <= boundingBox.getxMax()
				&& boundingBox.getyMin() <= yPosition && yPosition <= boundingBox.getyMax()) {
			return true;
		}
		return false;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void upgradeTower() {
		level++;
		changeRange();
	}

	public void changeRange() {
		range = (int) (range * 1.15);
		xOffset = (int) (xOffset * 1.15);
		yOffset = (int) (yOffset * 1.15);
	}

	public void setTowerImage(Image towerImage) {
		this.towerImage = towerImage;
	}

	public Image getTowerImage() {
		return towerImage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRange() {
		return range;
	}

	public int getBulletCount() {
		return bulletCount;
	}

	public void setBulletCount(int bulletCount) {
		this.bulletCount = bulletCount;
	}
	
	
	
}
