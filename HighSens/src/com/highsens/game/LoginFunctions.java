package com.highsens.game;


public class LoginFunctions {
	private PlayersData playersData = PlayersData.getInstance();
	
	public void registerPlayer(String name){
		setCurrentUser(name);
		//playersData.addCurrentPlayerToJSON();
	}
	
	public boolean tryLogin(String name){
		if(playersData.isRegistered(name)){
			setCurrentUser(name);
			return true;
		} else {
			return false;
		}
	}
	
	public void setCurrentUser(String name){
		CurrentPlayer.setName(name);
		CurrentPlayer.setGameCash(1000);
	}

}
