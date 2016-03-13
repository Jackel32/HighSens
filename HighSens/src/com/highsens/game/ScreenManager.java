package com.highsens.game;

import javax.swing.JFrame;

public final class ScreenManager {
	
	private static Splash splashscreen = new Splash();
	private static MainScreen mainscreen = new MainScreen();
	private static LevelSelectScreen levelselectscreen = new LevelSelectScreen();
	private static GameoverScreen gameoverscreen = new GameoverScreen(0, 0);
	private static GameScreen game = new GameScreen();
	private static StoreScreen store = new StoreScreen();
	
	public static void displaySplashScreen()
	{
		splashscreen.showSplash();
	}
	
	public static void displayMainScreen()
	{
		mainscreen.setVisible(true);
	}
	
	public static void displayLevelSelectScreen()
	{
		levelselectscreen.setVisible(true);
	}
	
	public static void displayGameoverScreen(int w, int s)
	{
		gameoverscreen.setStats(w, s);
		gameoverscreen.setVisible(true);
	}
	
	public static void displayGameScreen()
	{
		game.setTitle("Tower Defence");
        game.setResizable(false);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
	}
	
	public static void displayStoreScreen()
	{
		store.setVisible(true);
	}

	
	public static void hideMainScreen()
	{
		mainscreen.setVisible(false);
	}
	
	public static void hideLevelSelectScreen()
	{
		levelselectscreen.setVisible(false);
	}
	
	public static void hideGameoverScreen()
	{
		gameoverscreen.setVisible(false);
		gameoverscreen.dispose();
	}
	
	public static void hideGameScreen()
	{
		game.setVisible(false);
		game.dispose();
	}
	
	public static void hideStoreScreen()
	{
		store.setVisible(false);
	}
	
}
