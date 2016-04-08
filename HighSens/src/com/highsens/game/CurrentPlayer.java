package com.highsens.game;

import org.json.simple.JSONObject;

public class CurrentPlayer {
	private static JSONObject playerJSON = new JSONObject();
	private static int gameCash = 1000;
	private static String name = "";
	private static CurrentPlayer currentPlayer;
	
	protected CurrentPlayer(){}
	
	public static CurrentPlayer getCurrentPlayer(){
		if(currentPlayer == null){
			currentPlayer = new CurrentPlayer();
	    }
		return currentPlayer;
	}
	
	public static int getGameCash() {
		return gameCash;
	}
	public static void setGameCash(int cash) {
		gameCash = cash;
		playerJSON.put("gameCash", gameCash);
	}
	public static String getName() {
		return name;
	}
	public static void setName(String n) {
		name = n;
		playerJSON.put("name", name);
	}
	
	public static JSONObject getJSONObject(){
		return playerJSON;
	}
	
	public static void setJSONObject(JSONObject j){
		gameCash = (int) j.get("gameCash");
		name = (String)j.get("name");
		playerJSON = j;
	}

	
}
