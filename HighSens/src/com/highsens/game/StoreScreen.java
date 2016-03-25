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
	private JLabel selected = new JLabel();
	private JLabel firstLabel, secondLabel, thirdLabel, fourthLabel;
	private JLabel topLeftArrow, lowerLeftArraw;
	private JButton firstButton = new JButton();
	private JButton secondButton = new JButton();
	private JButton thirdButton = new JButton();
	private JButton fourthButton = new JButton();
	private JButton backButton = new JButton();
	private JButton arrowTower = new JButton();
	private JButton redTower = new JButton();
	private JButton blueTower = new JButton();
	private JButton arrowTowerFirstTier = new JButton();
	private JButton redTowerFirstTier = new JButton();
	private JButton blueTowerFirstTier = new JButton();
	private JButton buyButton = new JButton();
	private JTextPane description;
	private JTextPane cashPane;
	private String imagePath = System.getProperty("user.dir");
    private String separator = System.getProperty("file.separator");
    private Player player = Player.getInstance();
    private int currentTowerIndex = 0;
    private TowerData arrowTowerObj = new TowerData();
    private TowerData emptyTower = new TowerData();
    private TowerData redTowerObj = new TowerData();
    private TowerData blueTowerObj = new TowerData();
    private TowerData towerArray[] = {emptyTower, arrowTowerObj, redTowerObj, blueTowerObj};
    
    
	public StoreScreen() {
		
		arrowTowerObj.amount = 150;
		redTowerObj.amount = 250;
		blueTowerObj.amount = 350;
		
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
	    
	    selected = new JLabel(addImageIcon(imagePath + separator + "images" + separator
                + "selected" + ".png"));
	    selected.setSize(59,59);
	    //selected.setLocation(136,99);
	    selected.setLocation(0,0);
	    		
	    
	    firstLabel = generateJLabel(189, 393, "first-selected");
	    secondLabel = generateJLabel(189, 393, "second-selected");
	    thirdLabel = generateJLabel(189, 393, "third-selected");
	    fourthLabel = generateJLabel(189, 393, "fourth-selected");
	    
	    topLeftArrow = new JLabel(addImageIcon(imagePath + separator + "images" + separator
                + "top-left-arrow" + ".png"));
	    topLeftArrow.setSize(35,13);
	    topLeftArrow.setLocation(191,124);
	    
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
	    cashPane.setText("Game Cash: $" + player.getGameCash());
	    
	    (firstButton = generateJButton(29,18)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prefresh();
				firstLabel.add(firstButton);
				firstLabel.add(secondButton);
				firstLabel.add(thirdButton);
				firstLabel.add(fourthButton);
				firstLabel.add(arrowTower);
				firstLabel.add(redTower);
				firstLabel.add(blueTower);
				firstLabel.add(arrowTowerFirstTier);
				//if(towerArray[currentTowerIndex].bought == true){
					background.add(topLeftArrow);
				//}
				background.add(firstLabel);
				refresh();
				currentTowerIndex = 0;
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
				secondLabel.add(redTower);
				secondLabel.add(redTowerFirstTier);
				secondLabel.add(blueTower);
				background.add(secondLabel);
				refresh();
				currentTowerIndex = 0;
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
				thirdLabel.add(redTower);
				thirdLabel.add(blueTower);
				thirdLabel.add(blueTowerFirstTier);
				background.add(thirdLabel);
				refresh();
				currentTowerIndex = 0;
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
				fourthLabel.add(redTower);
				fourthLabel.add(blueTower);
				background.add(fourthLabel);
				refresh();
				currentTowerIndex = 0;
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
				currentTowerIndex = 1;
			}
	    });
	    arrowTowerFirstTier.setLocation(145, 107);
	    arrowTowerFirstTier.setSize(40,40);
	    arrowTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "arrow-tower2-icon" + ".png"));
	    arrowTowerFirstTier.setContentAreaFilled(false);
	    arrowTowerFirstTier.setBorderPainted(false);
	    
	    (redTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
	    });
	    redTower.setLocation(34, 112);
	    redTower.setSize(40,40);
	    redTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "red-tower-icon" + ".png"));
	    redTower.setContentAreaFilled(false);
	    redTower.setBorderPainted(false);
	    
	    (redTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Red Tower \nCost: $150");
				currentTowerIndex = 2;
			}
	    });
	    redTowerFirstTier.setLocation(145, 107);
	    redTowerFirstTier.setSize(40,40);
	    redTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "red-tower-icon" + ".png"));
	    redTowerFirstTier.setContentAreaFilled(false);
	    redTowerFirstTier.setBorderPainted(false);
	    
	    (blueTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
	    });
	    blueTower.setLocation(34, 202);
	    blueTower.setSize(40,40);
	    blueTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "blue-tower-icon" + ".png"));
	    blueTower.setContentAreaFilled(false);
	    blueTower.setBorderPainted(false);
	    
	    (blueTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Blue Tower \nCost: $150");
				currentTowerIndex = 3;
			}
	    });
	    blueTowerFirstTier.setLocation(145, 107);
	    blueTowerFirstTier.setSize(40,40);
	    blueTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "blue-tower-icon" + ".png"));
	    blueTowerFirstTier.setContentAreaFilled(false);
	    blueTowerFirstTier.setBorderPainted(false);
	    
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
	    background.add(redTower);
	    background.add(blueTower);
	    background.add(backButton);
	    background.add(buyButton);
	    background.add(cashPane);
	    background.add(selected);
	}
	
	private void removeButtons(){
		background.remove(firstButton);
		background.remove(secondButton);
		background.remove(thirdButton);
		background.remove(fourthButton);
		background.remove(arrowTower);
		background.remove(redTower);
		background.remove(blueTower);
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
		if(towerArray[currentTowerIndex].bought == false){
			player.setGameCash(player.getGameCash()-towerArray[currentTowerIndex].amount);
			towerArray[currentTowerIndex].bought = true;
			cashPane.setText("Game Cash: $" + player.getGameCash());
		}
	}
	
	//TODO: create tower class with boolean isBought and game cash value.
	//		initially  arrows in tier are gray, when bought change to yellow
}

class TowerData{
	protected int amount = 0;
	protected boolean bought = false;
}
