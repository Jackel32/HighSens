package com.highsens.game;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreScreen extends JFrame {
	public StoreScreen() {
		
		String imagePath = System.getProperty("user.dir");
	    String separator = System.getProperty("file.separator");
		
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Store Screen");
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		this.setLocationRelativeTo(null);
		
	    JLabel background=new JLabel(new ImageIcon(imagePath + separator + "images" + separator
	            + "store-background.png"));
	    background.setSize(600, 400);
	    getContentPane().add(background);
	    
	    JLabel emptyTowerSpace = new JLabel(new ImageIcon(imagePath + separator + "images" + separator
	            + "empty-tower-space.png"));
	    emptyTowerSpace.setSize(81, 94);
	    emptyTowerSpace.setLocation(18,10);
	    background.add(emptyTowerSpace);
	    
	    JButton button = new JButton("<<<<<< Back");
	    button.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final MainScreen mainScreen = new MainScreen();
				mainScreen.setVisible(true);
				setVisible(false);
			}
		});
	    button.setBounds(146, 338, 272, 23);
	    getContentPane().add(button);
	}
}
