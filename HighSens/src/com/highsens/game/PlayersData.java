package com.highsens.game;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PlayersData {
	private static JSONArray playersArray = new JSONArray();
	private static String projectDirectory = System.getProperty("user.dir");
	private static String separator = System.getProperty("file.separator");
	private static PlayersData instance = null;
	private static JSONParser parser = new JSONParser();
	
	protected PlayersData(){
		try{
			playersArray = (JSONArray)parser.parse(new FileReader(projectDirectory + separator
		            + "players.json"));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static PlayersData getInstance(){
		if(instance == null){
			instance = new PlayersData();
	    }
		return instance;
	}
	
	public static JSONArray getPlayerArray(){
		return playersArray;
	}
	
	public void setPlayerArray(JSONArray ja){
		playersArray = ja;
	}
	
	public static void addCurrentPlayerToJSON(){
		JSONObject playerJSONObj = new JSONObject();
		playerJSONObj.put("gameCash", CurrentPlayer.getGameCash());
		playerJSONObj.put("name", CurrentPlayer.getName());
		playersArray.add(playerJSONObj);
		savePlayers();
	}
	
	public static void savePlayers(){
		try {
			FileWriter file = new FileWriter(projectDirectory + separator
		            + "players.json");
			file.write(playersArray.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isRegistered(String name){
		for(int i = 0; i < playersArray.size(); i++){
			if(name.compareTo(((JSONObject)playersArray.get(i)).get("name").toString())==0){
				return true;
			}
		}
		return false;
	}
}
