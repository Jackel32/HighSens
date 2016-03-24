package com.highsens.game;

import java.awt.Graphics;
import java.awt.Image;

public interface Tower {
	public Image getImage(String fileName);

	public float getXofMissileShoot();

	public float getYofMissileShoot();

	public void render(Graphics g);

	public void updateState();

	public void update();

	public void setState(int state);

	public int getState();

	public void setLevel(int level);

	public int getLevel();;

	public void updateHealth();

	public boolean collision(GameFigure m);
}
