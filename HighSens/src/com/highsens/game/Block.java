package com.highsens.game;
import java.awt.*;

public class Block extends Rectangle {
	public Rectangle towerSquare;
	public int towerSquareSize = 104; // shooting range
	public int groundID;
	public int airID;
	public int loseTime = 100, loseFrame = 0;
	
	public int shotEnemy = -1;
	public boolean shoting = false;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		towerSquare = new Rectangle(x - (towerSquareSize/2), y - (towerSquareSize/2), width + (towerSquareSize), height + (towerSquareSize));
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);
		
		if (airID != Value.airAir) {
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
		}
	}
	
	public void physic() {
		if(shotEnemy != -1 && towerSquare.intersects(Screen.enemys[shotEnemy])) {
			shoting = true;
		} else {
			shoting = false;
		}
		
		if(!shoting) {
			if(airID == Value.airTower) {
				for(int i=0;i<Screen.enemys.length;i++) {
					if(Screen.enemys[i].inGame) {
						if(towerSquare.intersects(Screen.enemys[i])) {
							shoting = true;
							shotEnemy = i;
						}
					}
				}
			}
		}
		
		if(shoting) {
			if(loseFrame >= loseTime) {
				Screen.enemys[shotEnemy].loseHealth(1);
				
				loseFrame = 0;
			} else {
				loseFrame += 1;
			}
			
			if(Screen.enemys[shotEnemy].isDead()) {
				shoting = false;
				shotEnemy = -1;
				
				Screen.killed += 1;
				
				Screen.hasWon();
			}
		}
	}
	
	public void getMoney(int enemyID) {
		Screen.coin_number += Value.deathReward[enemyID];
	}
	
	public void fight(Graphics g) {
		if(Screen.isDegub) {
			if(airID == Value.airTower) {
				g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
			}
		}
		
		if(shoting) {
			g.setColor(new Color(255, 255, 0));
			g.drawLine(x + (width/2), y + (height/2), Screen.enemys[shotEnemy].x + (Screen.enemys[shotEnemy].width/2), Screen.enemys[shotEnemy].y + (Screen.enemys[shotEnemy].height/2));
		}
	}
}
