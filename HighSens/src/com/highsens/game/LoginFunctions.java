package com.highsens.game;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class LoginFunctions {
	
	public boolean registerPlayer(String name){
		if(PlayersData.getInstance().isRegistered(name)){
			JOptionPane.showMessageDialog(new Frame(), "Player Name is already registered");
			return false;
		} else {
			setCurrentUser(name);
			PlayersData.getInstance().addCurrentPlayerToJSON();
			return true;
		}
	}
	
	public boolean tryLogin(String name){
		if(PlayersData.getInstance().isRegistered(name)){
			setCurrentUser(name);
			return true;
		} else {
			JOptionPane.showMessageDialog(new Frame(), "An error occured while logging in.");
			return false;
		}
	}
	
	private void setCurrentUser(String name){
		CurrentPlayer.setName(name);
		CurrentPlayer.setGameCash(1000);
	}
}
