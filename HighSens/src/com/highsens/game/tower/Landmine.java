package com.highsens.game.tower;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.highsens.game.GameData;
import com.highsens.game.GameFigure;


public class Landmine implements GameFigure {
	
	private int state;
	public float x, y;
	private GameData gd;
	public LandmineState curry;
	public boolean collision;
	
	public Landmine(float x, float y, GameData gd){
		
		this.gd = gd;
		this.x = x;
		this.y = y;
		state = STATE_IDLE;
		curry = new NoExplode();
	}

	
	@Override
	public void render(Graphics g) {
		curry.render(g, this);
		
	}

	@Override
	public void update() {
		
		curry.update(this);
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
		
		collision = curry.collision(m);
		
		return collision;
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

