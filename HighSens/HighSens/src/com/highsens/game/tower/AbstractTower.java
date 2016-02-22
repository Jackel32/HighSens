package com.highsens.game.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public abstract class AbstractTower {

	public Image towerImage;
	public Ellipse2D.Double radius;
	public BoundingBox boudingBox;
	public int range;
	private int xOffset = 75;
	private int yOffset = 80;

	/**
	 * Draws range on x and y coordinates.
	 */
	protected void drawRange(Graphics g, float x, float y) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.green);
		g2d.drawOval((int) x - xOffset, (int) y - yOffset, range, range);
	}

	/**
	 * Return true if x and y postion has a collision with the tower.
	 */
	public boolean collision(int xPosition, int yPosition) {
		if (boudingBox.getxMin() <= xPosition && xPosition <= boudingBox.getxMax() && boudingBox.getyMin() <= yPosition
				&& yPosition <= boudingBox.getyMax()) {
			return true;
		}
		return false;
	}

	public void changeRange() {
		range = (int) (range * 1.5);
		xOffset = (int) (xOffset * 1.5);
		yOffset = (int) (yOffset * 1.5);
	}

	public void setTowerImage(Image towerImage) {
		this.towerImage = towerImage;
	}

}
