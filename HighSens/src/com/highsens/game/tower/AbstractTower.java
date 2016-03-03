package com.highsens.game.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.List;

import com.highsens.game.GameData;
import com.highsens.game.GameFigure;

public abstract class AbstractTower {

	public GameData data;
	public Ellipse2D.Double radius;
	public BoundingBox boundingBox;
	public Image towerImage;
	public int level;
	private int xOffset = 80;
	protected int range;
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
		range = (int) (range * 1.5);
		xOffset = (int) (xOffset * 1.5);
		yOffset = (int) (yOffset * 1.5);
		for (int i = 0; i < figures.size(); i++) {
			if (figures.get(i) instanceof ArrowTower || figures.get(i) instanceof BlueTower) {
				// System.out.println("The level of " + figures.get(i) + " is "
				// + figures.get(i).getLevel());

				level = figures.get(i).getLevel();
				level++;
				figures.get(i).setLevel(level++);
			}
		}

		changeRange();
	}

	private void changeRange() {
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
		int towerLevel = 0;
		List<GameFigure> figures = null;
		figures = data.returnList();

		System.out.println("Test");

		for (int i = 0; i < figures.size(); i++) {
			if (figures.get(i) instanceof ArrowTower || figures.get(i) instanceof BlueTower) {
				System.out.println("Testing getLevel");

				towerLevel = figures.get(i).getLevel();
			}
		}
		level = towerLevel;
		return level;

	}

	public int getRange() {
		return range;
	}

}
