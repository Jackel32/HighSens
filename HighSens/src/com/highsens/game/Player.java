
package com.highsens.game;

public class Player{
	private static Player instance = null;
	private static int gameCash = 1000;
	
	protected Player(){
	      // Exists only to defeat instantiation.
	}
	
	public static Player getInstance(){
		if(instance == null){
			instance = new Player();
	    }
		return instance;
	}
	
	public static int getGameCash(){
		return gameCash;
	}
	
	public static void setGameCash(int newCash){
		gameCash = newCash;
	}
}
