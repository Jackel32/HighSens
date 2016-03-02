package com.highsens.game;

import javax.swing.JFrame;

public class GUIManager {
	
	private static Splash splashscreen = new Splash();
	private static MainScreen mainscreen = new MainScreen();
	private static LevelSelectScreen levelselectscreen = new LevelSelectScreen();
	private static GameoverScreen gameoverscreen = new GameoverScreen(0, 0);
	private JFrame currentScreen;
	
	public void displaySplashScreen()
	{
		splashscreen.showSplash();
	}
	
	public void displayMainScreen()
	{
		mainscreen.setVisible(true);
	}
	
	public void displayLevelSelectScreen()
	{
		levelselectscreen.setVisible(true);
	}
	
	public void displayGameoverScreen(int w, int s)
	{
		
	}

}
