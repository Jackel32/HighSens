package com.highsens.game.monster;

import java.awt.Graphics;
import com.highsens.game.GameFigure;

public interface MonsterState {
	
	public void updatehealth(Boss s);
	public void render(Graphics g, Boss s);
	public boolean collision (GameFigure t);
	
}

