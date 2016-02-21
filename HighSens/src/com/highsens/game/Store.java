package com.highsens.game;
import java.awt.*;

public class Store {
	public static int shopWidth = 8;
	public static int buttonSize = 40;
	public static int cellSpace = 2;
	public static int awayFromRoom = 30;
	public static int iconSize = 16;
	public static int iconSpace = 5;
	public static int iconTextY = 15;
	public static int itemIn = 4;
	public static int heldID = -1;
	public static int realID = -1;
	public static int[] buttonID = {Value.airTower, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airTrashCan};	// 8 items in shop
	public static int[] buttonPrice = {10, 0, 0, 0, 0, 0, 0, 0};
	
	public Rectangle[] button = new Rectangle[shopWidth];
	public Rectangle buttonHealth;
	public Rectangle buttonCoins;
	
	public boolean holdsItem = false;
	
	public Store() {
		define();
	}
	
	public void click(int mouseButton) {
		if(mouseButton == 1) {
			for(int i=0;i<button.length;i++) {
				if(button[i].contains(Screen.mse)) {
					if(buttonID[i] != Value.airAir) {
						if(buttonID[i] == Value.airTrashCan) {
							holdsItem = false;
						} else {
							heldID = buttonID[i];
							realID = i;
							holdsItem = true;
						}
					}
				}
			}
			
			if(holdsItem) {
				if(Screen.coin_number >= buttonPrice[realID]) {
					for(int y=0;y<Screen.room.block.length;y++) {
						for(int x=0;x<Screen.room.block[0].length;x++) {
							if(Screen.room.block[y][x].contains(Screen.mse)) {
								if(Screen.room.block[y][x].groundID != Value.groundRoad && Screen.room.block[y][x].airID == Value.airAir) {
									Screen.room.block[y][x].airID = heldID;
									Screen.coin_number -= buttonPrice[realID];
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void define() {
		for(int i=0;i<button.length;i++) {
			button[i] = new Rectangle((Screen.myWidth/2) - (shopWidth*(buttonSize+cellSpace))/2 + ((buttonSize+cellSpace)*i), (Screen.room.block[Screen.room.worldHeight-1][0].y) + Screen.room.blockSize + awayFromRoom, buttonSize, buttonSize);
		}
		
		buttonHealth = new Rectangle(Screen.room.block[0][0].x - 1, button[0].y, iconSize, iconSize);
		buttonCoins = new Rectangle(Screen.room.block[0][0].x - 1, button[0].y + button[0].height-iconSize, iconSize, iconSize);
	}
	
	public void draw(Graphics g) {
		for (int i=0;i<button.length;i++){
			// need photo shop for the cell image to create trans effect
			if(button[i].contains(Screen.mse)) {
				g.setColor(new Color(255, 255, 255, 150));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
			
			g.drawImage(Screen.tileset_res[0],button[i].x, button[i].y, button[i].width, button[i].height, null);
			if(buttonID[i] != Value.airAir)
				g.drawImage(Screen.tileset_air[buttonID[i]], button[i].x + itemIn, button[i].y + itemIn, button[i].width - (itemIn*2), button[i].height - (itemIn*2), null);
			if(buttonPrice[i] > 0) {
				g.setColor(new Color(255,255,255));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				g.drawString("$" + buttonPrice[i], button[i].x + itemIn, button[i].y + itemIn + 10); //itemIn + 10 to set number in correct position
			}
		}
		
		// health and coins info
		g.drawImage(Screen.tileset_res[1], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
		g.drawImage(Screen.tileset_res[2], buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height, null);
		g.setFont(new Font("Courier New", Font.BOLD,14));    
		g.setColor(new Color(255, 255, 255, 150));         
		g.drawString("" + Screen.health,buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + iconTextY);
		g.drawString("" + Screen.coin_number,buttonCoins.x + buttonCoins.width + iconSpace, buttonCoins.y + iconTextY);

		
		//need photo shop for this drag effect
		if(holdsItem) {
			g.drawImage(Screen.tileset_air[heldID], Screen.mse.x - ((button[0].width - (itemIn*2))/2) + itemIn, Screen.mse.y - ((button[0].width - (itemIn*2))/2) + itemIn, button[0].width - (itemIn*2), button[0].height - (itemIn*2), null);
		}
		
	}
	
}
