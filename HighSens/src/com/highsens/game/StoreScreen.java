package com.highsens.game;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
//<<<<<<< HEAD
import javax.swing.JButton;
//=======
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//>>>>>>> origin/master

public class StoreScreen extends JFrame {
	private JLabel background;
	private JButton firstButton = new JButton();
	private JButton secondButton = new JButton();
	private JButton thirdButton = new JButton();
	private JButton fourthButton = new JButton();
	private JLabel firstLabel, secondLabel, thirdLabel, fourthLabel;
	private String imagePath = System.getProperty("user.dir");
    private String separator = System.getProperty("file.separator");
    
	public StoreScreen() {
		
//<<<<<<< HEAD
//=======
		String imagePath = System.getProperty("user.dir");
	    String separator = System.getProperty("file.separator");
		
//>>>>>>> origin/master
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Store Screen");
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		this.setLocationRelativeTo(null);
		
//<<<<<<< HEAD
	    background=new JLabel(addImageIcon("store-background"));
//=======
	    JLabel background=new JLabel(new ImageIcon(imagePath + separator + "images" + separator
	            + "store-background.png"));
//>>>>>>> origin/master
	    background.setSize(600, 400);
	    getContentPane().add(background);
	    
//<<<<<<< HEAD
	    firstLabel = generateJLabel(189, 393, "first-selected");
	    secondLabel = generateJLabel(189, 393, "second-selected");
	    thirdLabel = generateJLabel(189, 393, "third-selected");
	    fourthLabel = generateJLabel(189, 393, "fourth-selected");
	    
	    (firstButton = generateJButton(29,18)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prefresh();
				firstLabel.add(firstButton);
				firstLabel.add(secondButton);
				firstLabel.add(thirdButton);
				firstLabel.add(fourthButton);
				background.add(firstLabel);
				refresh();
			}
	    });
	    
	    (secondButton = generateJButton(29,108)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prefresh();
				secondLabel.add(firstButton);
				secondLabel.add(secondButton);
				secondLabel.add(thirdButton);
				secondLabel.add(fourthButton);
				background.add(secondLabel);
				refresh();
			}
	    });
	    
	    (thirdButton = generateJButton(29,197)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prefresh();
				thirdLabel.add(firstButton);
				thirdLabel.add(secondButton);
				thirdLabel.add(thirdButton);
				thirdLabel.add(fourthButton);
				background.add(thirdLabel);
				refresh();
			}
	    });
	    
	    (fourthButton = generateJButton(29,283)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prefresh();
				fourthLabel.add(firstButton);
				fourthLabel.add(secondButton);
				fourthLabel.add(thirdButton);
				fourthLabel.add(fourthButton);
				background.add(fourthLabel);
				refresh();
			}
	    });
	    
	    background.add(firstButton);
	    background.add(secondButton);
	    background.add(thirdButton);
	    background.add(fourthButton);
	}
	
	private void removeButtons(){
		background.remove(firstButton);
		background.remove(secondButton);
		background.remove(thirdButton);
		background.remove(fourthButton);
	}
	
	private void removeSelections(){
		background.remove(firstLabel);
		background.remove(secondLabel);
		background.remove(thirdLabel);
		background.remove(fourthLabel);
	}
	
	private ImageIcon addImageIcon(String filename){
		return new ImageIcon(imagePath + separator + "images" + separator
                + filename + ".png");
	}
	
	private JLabel generateJLabel(int x, int y, String filename){
		JLabel label = new JLabel(addImageIcon(filename));
		label.setSize(x,y);
		label.setLocation(0,0);
		return label;
	}
	
	private JButton generateJButton(int x, int y){
		JButton b = new JButton();
		b.setLocation(x, y);
	    b.setSize(50,50);
	    b.setOpaque(false);
	    b.setContentAreaFilled(false);
	    b.setBorderPainted(false);
	    return b;
	}
	
	private void prefresh(){
		removeButtons();
		revalidate();
		removeSelections();
		revalidate();
		remove(background);
		revalidate();
		background=new JLabel(addImageIcon("store-background"));
	    background.setSize(600, 400);
	    add(background);
	    revalidate();
	}
	
	private void refresh(){
		revalidate();
		repaint();
//=======
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
//>>>>>>> origin/master
	}
}
