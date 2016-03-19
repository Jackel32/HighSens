package com.highsens.game.monster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import com.highsens.game.GameData;
import com.highsens.game.GameFigure;
import com.highsens.game.Missile;
import com.highsens.game.tower.AbstractTower;
import com.highsens.game.tower.ArrowTower;
import com.highsens.game.tower.BlueTower;


public class Angry implements MonsterState{
	
	private int xOfSet = 10;
	private int yOfSet = 50;
	private int height = 120;
	private int width = 120;
	public float x, y;
	public boolean collision;
	Rectangle2D.Double tower, rangeOfFrozen;
	public GameData gameData;
	
	public Angry(){
		
	}
	
	@Override
	public void updatehealth(Boss s){
		
		
	}
	
	@Override
	public void render(Graphics g, Boss s){
		this.x = s.x;
		this.y = s.y;
		g.setColor(Color.BLUE);
		g.drawRect((int) s.x + xOfSet, (int) s.y + yOfSet, width, height);
		
	}
	
	@Override
	public boolean collision (GameFigure t){

		boolean collision = false;
	    tower =  new Rectangle2D.Double ( (int) t.getX(),(int) t.getY(), 50, 69);
	    rangeOfFrozen = new Rectangle2D.Double(x + xOfSet, y + yOfSet, width, height);
	    
	    if (tower.intersects(rangeOfFrozen)){
	    	collision = true;
	    	//System.out.println("collision: " + collision);
	    }else{
	    	collision = false;
	    }

		return collision;
	
	}

}

