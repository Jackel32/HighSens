package com.highsens.game.monster;

import java.awt.Graphics;

import com.highsens.game.GameFigure;

public class General implements MonsterState {
	public General(){
		
	}
	
	@Override
	public void updatehealth(Boss s){
		if (s.getHealth() <= 250){
			s.curry = new Angry();
		}
		
	}
	
	@Override
	public void render(Graphics g, Boss s){
		
	}
	
	@Override
	public boolean collision (GameFigure t){
		
		return false;
		
	}

}

