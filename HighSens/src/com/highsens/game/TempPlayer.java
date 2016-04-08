
package com.highsens.game;

public class TempPlayer{
	//TODO: Info is to be stored persistently in a JSON file now. Change so Player.java is not a singleton
	private static TempPlayer instance = null;
	private static int gameCash = 1000;
	private static String name = "";
	/* if you add variables to player, PlayersData needs to be updated also */
	
	protected TempPlayer(){
	      // Exists only to defeat instantiation.
	}
	
	public static TempPlayer getInstance(){
		if(instance == null){
			instance = new TempPlayer();
	    }
		return instance;
	}
	
	public static int getGameCash(){
		return gameCash;
	}
	
	public static void setGameCash(int newCash){
		gameCash = newCash;
	}
	
	public static void setName(String newName){
		name = newName;
	}
	
	public static String getName(){
		return name;
	}
}
