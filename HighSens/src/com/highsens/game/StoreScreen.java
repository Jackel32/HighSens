package com.highsens.game;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StoreScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel background;
	private JButton firstButton = new JButton();
	private JButton secondButton = new JButton();
	private JButton thirdButton = new JButton();
	private JButton fourthButton = new JButton();
	private JButton backButton = new JButton();
	private JButton arrowTower = new JButton();
	private JButton arrowTowerFirstTier = new JButton();
	private JButton buyButton = new JButton();
	private JLabel firstLabel, secondLabel, thirdLabel, fourthLabel;
	private JTextPane description;
	private JTextPane cashPane;
	private String imagePath = System.getProperty("user.dir");
    private String separator = System.getProperty("file.separator");
    private Player player = Player.getInstance();
    private int currentTower = 0;
    
	public StoreScreen() {
		
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Store Screen");
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		this.setLocationRelativeTo(null);
		
	    background=new JLabel(addImageIcon("store-background"));
	    background.setSize(600, 400);
	    add(background);
	    
	    firstLabel = generateJLabel(189, 393, "first-selected");
	    secondLabel = generateJLabel(189, 393, "second-selected");
	    thirdLabel = generateJLabel(189, 393, "third-selected");
	    fourthLabel = generateJLabel(189, 393, "fourth-selected");
	    
	    description = new JTextPane();
	    description.setSize(182,182);
	    description.setLocation(392,78);
	    description.setOpaque(false);
	    description.setForeground(Color.WHITE);
	    
	    cashPane = new JTextPane();
	    cashPane.setSize(187,23);
	    cashPane.setLocation(388,277);
	    cashPane.setOpaque(false);
	    cashPane.setForeground(Color.WHITE);
	   // cashPane.setText("Game Cash: $" + player.getGameCash());
	    
	    (firstButton = generateJButton(29,18)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prefresh();
				firstLabel.add(firstButton);
				firstLabel.add(secondButton);
				firstLabel.add(thirdButton);
				firstLabel.add(fourthButton);
				firstLabel.add(arrowTower);
				firstLabel.add(arrowTowerFirstTier);
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
				secondLabel.add(arrowTower);
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
				thirdLabel.add(arrowTower);
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
				fourthLabel.add(arrowTower);
				background.add(fourthLabel);
				refresh();
			}
	    });
	    
	    (arrowTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
	    });
	    arrowTower.setLocation(33, 23);
	    arrowTower.setSize(40,40);
	    arrowTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "arrow-tower2-icon" + ".png"));
	    arrowTower.setContentAreaFilled(false);
	    arrowTower.setBorderPainted(false);
	    
	    (arrowTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Arrow Tower \nCost: $150");
				currentTower = 150;
			}
	    });
	    arrowTowerFirstTier.setLocation(145, 107);
	    arrowTowerFirstTier.setSize(40,40);
	    arrowTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "arrow-tower2-icon" + ".png"));
	    arrowTowerFirstTier.setContentAreaFilled(false);
	    arrowTowerFirstTier.setBorderPainted(false);
	    
	    (backButton = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				final MainScreen mainScreen = new MainScreen();
				mainScreen.setVisible(true);
				setVisible(false);
			}
	    });
	    backButton.setLocation(549,0);
	    backButton.setSize(36,41);
	    backButton.setOpaque(false);
	    backButton.setContentAreaFilled(false);
	    backButton.setBorderPainted(false);
	    
	    (buyButton = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				buyCurrentItem();
			}
	    });
	    buyButton.setLocation(128,317);
	    buyButton.setSize(116,41);
	    buyButton.setOpaque(false);
	    buyButton.setContentAreaFilled(false);
	    buyButton.setBorderPainted(false);
	    
	    background.add(firstButton);
	    background.add(secondButton);
	    background.add(thirdButton);
	    background.add(fourthButton);
	    background.add(arrowTower);
	    background.add(backButton);
	    background.add(buyButton);
	    background.add(cashPane);
	}
	
	private void removeButtons(){
		background.remove(firstButton);
		background.remove(secondButton);
		background.remove(thirdButton);
		background.remove(fourthButton);
		background.remove(arrowTower);
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
	    background.add(backButton);
	    background.add(description);
	    background.add(buyButton);
	    background.add(cashPane);
	    add(background);
	    description.setText("");
	    revalidate();
	}
	
	private void refresh(){
		revalidate();
		repaint();
	}
	
	@SuppressWarnings("static-access")
	private void buyCurrentItem(){
		player.setGameCash(player.getGameCash()-currentTower);
		cashPane.setText("Game Cash: $" + player.getGameCash());
	}
	
	//TODO: create tower class with boolean isBought and game cash value.
	//		initially  arrows in tier are gray, when bought change to yellow
}
