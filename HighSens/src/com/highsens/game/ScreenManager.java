package com.highsens.game;

import javax.swing.JFrame;

public final class ScreenManager {
	
	private static Splash splashscreen = new Splash();
	private static MainScreen mainscreen = new MainScreen();
	private static LevelSelectScreen levelselectscreen = new LevelSelectScreen();
	private static GameoverScreen gameoverscreen = new GameoverScreen(0, 0);
	private static GameScreen game = new GameScreen();
	private static StoreScreen store = new StoreScreen();
	private static AboutScreen about = new AboutScreen();
	private static LoginScreen login = new LoginScreen();
	private static TitleScreen title = new TitleScreen();
	
	public static void setUser()
	{
		mainscreen.setUserLabel();
	}
	
	public static void displayTitleScreen()
	{
		title.setVisible(true);
		title.playGame();
	}
	
	public static void hideTitleScreen()
	{
		title.setVisible(false);
		title.dispatch();
		displayMainScreen();
	}
	
	public static void displaySplashScreen()
	{
		splashscreen.showSplash();
	}
	
	public static void displayMainScreen()
	{
		mainscreen.setVisible(true);
		mainscreen.setUserLabel();
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
		//final StorePanel storePanel = new StorePanel(animator, gameData);
		store.setVisible(true);
		/*
		if(game.isActive)
		{
			game.setEnabled(false); 
		}
		else
			mainscreen.setEnabled(false); //disable the mainscreen until user closes storescreen
			*/
	}
	
	public static void displayAboutScreen()
	{
		about.setVisible(true);
		hideMainScreen();
	}

	public static void displayLoginScreen()
	{
		login.setVisible(true);
		hideMainScreen();
	}

	
	public static void hideLoginScreen()
	{
		login.setVisible(false);
		displayMainScreen();
	}
	
	
	public static void hideMainScreen()
	{
		mainscreen.setVisible(false);
	}
	
	public static void hideLevelSelectScreen()
	{
		levelselectscreen.setVisible(false);
		levelselectscreen.dispose();
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
		game.isActive = false;
	}
	
	public static void hideStoreScreen()
	{
		store.setVisible(false);
		store.dispose();
		/*
		if(game.isActive)
		{
			game.setEnabled(true);
			displayGameScreen();
		}
		
		else
		{
			mainscreen.setEnabled(true);
			displayMainScreen();
		}
		*/
		
	}
	
	public static void hideAboutScreen()
	{
		about.setVisible(false);
		about.dispose();

	}
	
	public static void disposeAll()
	{
		mainscreen.dispose();
		levelselectscreen.dispose();
		game.dispose();
		store.dispose();
		about.dispose();
		gameoverscreen.dispose();
	}
}
