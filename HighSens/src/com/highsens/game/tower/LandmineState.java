package com.highsens.game.tower;

import java.awt.Graphics;
import java.awt.Image;

import com.highsens.game.GameFigure;

public interface LandmineState {
	
	public void update(Landmine l);
	public void render(Graphics g, Landmine l);
	public boolean collision (GameFigure m);
	public Image getImage(String fileName);

}
