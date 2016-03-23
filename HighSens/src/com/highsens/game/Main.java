package com.highsens.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class Main extends JFrame {


	public static void main(String[] args) {
		ScreenManager.displaySplashScreen();
		ScreenManager.displayMainScreen();
	}
}


