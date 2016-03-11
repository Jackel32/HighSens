package com.highsens.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SellManager implements GameFigure {

	private double x;
	private double y;
	private int state;
	private final int high = 20;
	private final int weight = 30;

	public SellManager(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(Graphics g) {
		Graphics g2 = (Graphics) g;
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Time New Rome", Font.PLAIN, 10));
		g2.drawRect((int) x + 8, (int) y - 24, weight, high);
		g2.setColor(Color.BLACK);
		g2.drawString("SELL", (int) x + 11, (int) y - 12);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean collisionManager(float xPosition, float yPosition) {
		if ((int) xPosition < x + 38 && (int) xPosition > x + 8 && (int) yPosition < y - 6
				&& (int) yPosition > y - 24) {
			return true;
		}
		return false;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getState() {
		return state;
	}

	@Override
	public void setState(int state) {
		this.state = state;

	}

	@Override
	public void updateHealth() {

	}

	@Override
	public int getHealth() {
		return 0;
	}

	@Override
	public void update() {
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
	public boolean collision(GameFigure m) {
		// TODO Auto-generated method stub
		return false;
	}

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
		// TODO Auto-generated method stub
		return false;
	}
	



	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}


	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
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


}
