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
	private JLabel firstLabel, secondLabel, thirdLabel, fourthLabel;
	private JButton secondTowerFirstTierArrow = new JButton();
	private JButton thirdTowerFirstTierArrow = new JButton();
	private JButton secondTierArrow = new JButton();
	private JButton selected = new JButton();
	private JButton firstButton = new JButton();
	private JButton secondButton = new JButton();
	private JButton thirdButton = new JButton();
	private JButton fourthButton = new JButton();
	private JButton fifthButton = new JButton();
	private JButton backButton = new JButton();
	private JButton arrowTower = new JButton();
	private JButton redTower = new JButton();
	private JButton blueTower = new JButton();
	private JButton arrowTowerFirstTier = new JButton();
	private JButton redTowerFirstTier = new JButton();
	private JButton blueTowerFirstTier = new JButton();
	private JButton arrowTowerSecondTier = new JButton();
	private JButton redTowerSecondTier = new JButton();
	private JButton blueTowerSecondTier = new JButton();
	private JButton buyButton = new JButton();
	private JTextPane description;
	private JTextPane cashPane;
	private String imagePath = System.getProperty("user.dir");
    private String separator = System.getProperty("file.separator");
    private int currentTowerIndex = 0;
    private int currentLabel = 0;
    private TowerData arrowTowerObj = new TowerData();
    private TowerData emptyTower = new TowerData();
    private TowerData redTowerObj = new TowerData();
    private TowerData blueTowerObj = new TowerData();
    private TowerData arrowTowerTier2Obj = new TowerData();
    private TowerData redTowerTier2Obj = new TowerData();
    private TowerData blueTowerTier2Obj = new TowerData();
    private TowerData towerArray[] = {emptyTower, arrowTowerObj, redTowerObj, 
    		blueTowerObj, arrowTowerTier2Obj, redTowerTier2Obj, blueTowerTier2Obj};
    private CurrentPlayer currentPlayer = CurrentPlayer.getCurrentPlayer();
    
    
	public StoreScreen() {
		
		arrowTowerObj.amount = 0;
		arrowTowerObj.bought = true;
		redTowerObj.amount = 150;
		blueTowerObj.amount = 250;
		arrowTowerTier2Obj.amount = 150;
		redTowerTier2Obj.amount = 250;
		blueTowerTier2Obj.amount = 350;
		
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Store Screen");
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		this.setLocationRelativeTo(null);
		
	    background=new JLabel(addImageIcon("store-background2"));
	    background.setSize(600, 400);
	    add(background);
	    
	    firstLabel = generateJLabel(332, 400, "first-selected2");
	    secondLabel = generateJLabel(332, 400, "second-selected2");
	    thirdLabel = generateJLabel(332, 400, "third-selected2");
	    fourthLabel = generateJLabel(332, 400, "fourth-selected");
	    
	    secondTowerFirstTierArrow.setLocation(78,174);
	    secondTowerFirstTierArrow.setSize(79,14);
	    secondTowerFirstTierArrow.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "second-tower-first-tier-arrow.png"));
	    secondTowerFirstTierArrow.setContentAreaFilled(false);
	    secondTowerFirstTierArrow.setBorderPainted(false);
	    
	    thirdTowerFirstTierArrow.setLocation(78,174);
	    thirdTowerFirstTierArrow.setSize(79,101);
	    thirdTowerFirstTierArrow.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "third-tower-first-tier-arrow.png"));
	    thirdTowerFirstTierArrow.setContentAreaFilled(false);
	    thirdTowerFirstTierArrow.setBorderPainted(false);
	    
	    secondTierArrow.setLocation(209,177);
	    secondTierArrow.setSize(35,13);
	    secondTierArrow.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "second-tier-arrow.png"));
	    secondTierArrow.setContentAreaFilled(false);
	    secondTierArrow.setBorderPainted(false);
	    
	    description = new JTextPane();
	    description.setSize(182,182);
	    description.setLocation(351,131);
	    description.setOpaque(false);
	    description.setForeground(new Color(255,239,102));
	    
	    cashPane = new JTextPane();
	    cashPane.setSize(187,23);
	    cashPane.setLocation(351,256);
	    cashPane.setOpaque(false);
	    cashPane.setForeground(new Color(255,239,102));
	    cashPane.setText("Game Cash: $" + currentPlayer.getGameCash());
	    
	    (arrowTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				deSelectButtons();
				firstButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				removeTierButtons();
				fourthButton.add(arrowTowerFirstTier);
				fifthButton.add(arrowTowerSecondTier);
				firstRefresh();
				currentLabel = 1;
			}
	    });
	    arrowTower.setLocation(0, 0);
	    arrowTower.setSize(40,40);
	    arrowTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "arrow-tower2-icon" + ".png"));
	    arrowTower.setContentAreaFilled(false);
	    arrowTower.setBorderPainted(false);
	    
	    (arrowTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Arrow Tower \nCost: $" + arrowTowerObj.amount);
				currentTowerIndex = 1;
				deSelectButtons();
				fourthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
			}
	    });
	    arrowTowerFirstTier.setLocation(165, 162);
	    arrowTowerFirstTier.setSize(40,40);
	    arrowTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "arrow-tower2-icon" + ".png"));
	    arrowTowerFirstTier.setContentAreaFilled(false);
	    arrowTowerFirstTier.setBorderPainted(false);
	    
	    (arrowTowerSecondTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Arrow Tower Upgrade \nCost: $" + arrowTowerTier2Obj.amount);
				currentTowerIndex = 4;
				deSelectButtons();
				fifthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				
			}
	    });
	    arrowTowerSecondTier.setLocation(253, 162);
	    arrowTowerSecondTier.setSize(40,40);
	    arrowTowerSecondTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "arrow-tower2-icon-tier2" + ".png"));
	    arrowTowerSecondTier.setContentAreaFilled(false);
	    arrowTowerSecondTier.setBorderPainted(false);
	    
	    (redTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				deSelectButtons();
				secondButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				removeTierButtons();
				fourthButton.add(redTowerFirstTier);
				fifthButton.add(redTowerSecondTier);
				secondRefresh();
				currentLabel = 2;
			}
	    });
	    //redTower.setLocation(34, 162);
	    redTower.setSize(40,40);
	    redTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "red-tower-icon" + ".png"));
	    redTower.setContentAreaFilled(false);
	    redTower.setBorderPainted(false);
	    
	    (redTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Red Tower \nCost: $" + redTowerObj.amount);
				currentTowerIndex = 2;
				deSelectButtons();
				fourthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
			}
	    });
	    redTowerFirstTier.setLocation(165, 162);
	    redTowerFirstTier.setSize(40,40);
	    redTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "red-tower-icon" + ".png"));
	    redTowerFirstTier.setContentAreaFilled(false);
	    redTowerFirstTier.setBorderPainted(false);
	    
	    (redTowerSecondTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Red Tower Upgrade \nCost: $" + redTowerTier2Obj.amount);
				currentTowerIndex = 5;
				deSelectButtons();
				fifthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
			}
	    });
	    redTowerSecondTier.setLocation(253, 162);
	    redTowerSecondTier.setSize(40,40);
	    redTowerSecondTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "red-tower-icon-tier2" + ".png"));
	    redTowerSecondTier.setContentAreaFilled(false);
	    redTowerSecondTier.setBorderPainted(false);
	    
	    (blueTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				deSelectButtons();
				thirdButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				removeTierButtons();
				fourthButton.add(blueTowerFirstTier);
				fifthButton.add(blueTowerSecondTier);
				thirdRefresh();
				currentLabel = 3;
			}
	    });
	    blueTower.setLocation(34, 252);
	    blueTower.setSize(40,40);
	    blueTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "blue-tower-icon" + ".png"));
	    blueTower.setContentAreaFilled(false);
	    blueTower.setBorderPainted(false);
	    
	    (blueTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Blue Tower \nCost: $" + blueTowerObj.amount);
				currentTowerIndex = 3;
				deSelectButtons();
				fourthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
			}
	    });
	    blueTowerFirstTier.setLocation(165, 162);
	    blueTowerFirstTier.setSize(40,40);
	    blueTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "blue-tower-icon" + ".png"));
	    blueTowerFirstTier.setContentAreaFilled(false);
	    blueTowerFirstTier.setBorderPainted(false);
	    
	    (blueTowerSecondTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Blue Tower Upgrade \nCost: $" + blueTowerTier2Obj.amount);
				currentTowerIndex = 6;
				deSelectButtons();
				fifthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
			}
	    });
	    blueTowerSecondTier.setLocation(253, 162);
	    blueTowerSecondTier.setSize(40,40);
	    blueTowerSecondTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "blue-tower-icon-tier2" + ".png"));
	    blueTowerSecondTier.setContentAreaFilled(false);
	    blueTowerSecondTier.setBorderPainted(false);
	    
	    (firstButton = generateJButton(24,69)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				removeTierButtons();
				fourthButton.add(arrowTowerFirstTier);
				fifthButton.add(arrowTowerSecondTier);
				deSelectButtons();
				firstButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				firstRefresh();
				currentLabel = 1;
			}
	    });
	    
	    (secondButton = generateJButton(24,152)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				removeTierButtons();
				fourthButton.add(redTowerFirstTier);
				fifthButton.add(redTowerSecondTier);
				deSelectButtons();
				secondButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				secondRefresh();
				currentLabel = 2;
			}
	    });
	    
	    (thirdButton = generateJButton(24,242)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				removeTierButtons();
				fourthButton.add(blueTowerFirstTier);
				fifthButton.add(blueTowerSecondTier);
				deSelectButtons();
				thirdButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				thirdRefresh();
				currentLabel = 3;
			}
	    });
	    
	    (fourthButton = generateJButton(155, 152)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				deSelectButtons();
				fourthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				setFirstTierExtras();
			}
	    });
	    
	    (fifthButton = generateJButton(243, 152)).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				deSelectButtons();
				fifthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				setSecondTierExtras();
			}
	    });
	    
	    (backButton = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
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
	    buyButton.setLocation(163,260);
	    buyButton.setSize(116,41);
	    buyButton.setOpaque(false);
	    buyButton.setContentAreaFilled(false);
	    buyButton.setBorderPainted(false);
	    
	    firstButton.add(arrowTower);
	    secondButton.add(redTower);
	    thirdButton.add(blueTower);
	    
	    background.add(firstButton);
	    background.add(secondButton);
	    background.add(thirdButton);
	    background.add(fourthButton);
	    background.add(fifthButton);
	    background.add(backButton);
	    background.add(buyButton);
	    background.add(cashPane);
	}
	
	private void removeButtons(){
		background.remove(firstButton);
		background.remove(secondButton);
		background.remove(thirdButton);
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
		JButton base = new JButton();
		base.setLocation(x, y);
		base.setSize(59,59);
		base.setOpaque(false);
		base.setContentAreaFilled(false);
		base.setBorderPainted(false);
		base.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "tower-base.png"));
	    return base;
	}
	
	private void prefresh(){
		remove(selected);
		removeButtons();
		revalidate();
		removeSelections();
		revalidate();
		remove(background);
		revalidate();
		background=new JLabel(addImageIcon("store-background2"));
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
			if(currentPlayer.getGameCash() >= towerArray[currentTowerIndex].amount){
				if(currentTowerIndex == 1){
					//Commented out because the first tower is free right now. 
					//buy();
					//firstRefresh();
					//firstRefreshBoard();
				} else if(currentTowerIndex == 2){
					buy();
					secondRefresh();
					secondRefreshBoard();
				} else if(currentTowerIndex == 3){
					buy();
					thirdRefresh();
					thirdRefreshBoard();
				} else if(currentTowerIndex == 4){
					if(towerArray[1].bought == true){
						buy();
						firstRefresh();
						fourthRefreshBoard();
					}
				} else if(currentTowerIndex == 5){
					if(towerArray[2].bought == true){
						buy();
						secondRefresh();
						fifthRefreshBoard();
					}
				} else if(currentTowerIndex == 6){
					if(towerArray[3].bought == true){
						buy();
						thirdRefresh();
						sixthRefreshBoard();
					}
				}
			} else {
				//TODO: add way to let the user know he doesn't have enough funds
			}
		}
	}
	
	private void buy(){
		currentPlayer.setGameCash(currentPlayer.getGameCash()-towerArray[currentTowerIndex].amount);
		towerArray[currentTowerIndex].bought = true;
		cashPane.setText("Game Cash: $" + currentPlayer.getGameCash());
	}
	
	private void firstRefresh(){
		prefresh();
		firstLabel.add(firstButton);
		firstLabel.add(secondButton);
		firstLabel.add(thirdButton);
		firstLabel.add(fourthButton);
		firstLabel.add(fifthButton);
		firstLabel.add(buyButton);
		if(arrowTowerTier2Obj.bought == true){
			firstLabel.add(secondTierArrow);
		}
		background.add(firstLabel);
		refresh();
	}
	
	private void firstRefreshBoard(){
		description.setText("Arrow Tower \nCost: $" + arrowTowerObj.amount);
		currentTowerIndex = 1;
	}
	
	private void secondRefresh(){
		prefresh();
		secondLabel.add(firstButton);
		secondLabel.add(secondButton);
		secondLabel.add(thirdButton);
		secondLabel.add(fourthButton);
		secondLabel.add(fifthButton);
		secondLabel.add(buyButton);
		if(redTowerObj.bought == true){
			secondLabel.add(secondTowerFirstTierArrow);
		}
		if(redTowerTier2Obj.bought == true){
			secondLabel.add(secondTierArrow);
		}
		background.add(secondLabel);
		refresh();
	}
	
	private void secondRefreshBoard(){
		description.setText("Red Tower \nCost: $" + redTowerObj.amount);
		currentTowerIndex = 2;
	}
	
	private void thirdRefresh(){
		prefresh();
		thirdLabel.add(firstButton);
		thirdLabel.add(secondButton);
		thirdLabel.add(thirdButton);
		thirdLabel.add(fourthButton);
		thirdLabel.add(fifthButton);
		thirdLabel.add(buyButton);
		if(blueTowerObj.bought == true){
			thirdLabel.add(thirdTowerFirstTierArrow);
		}
		if(blueTowerTier2Obj.bought == true){
			thirdLabel.add(secondTierArrow);
		}
		background.add(thirdLabel);
		refresh();
	}
	
	private void thirdRefreshBoard(){
		description.setText("Blue Tower \nCost: $" + blueTowerObj.amount);
		currentTowerIndex = 3;
	}
	
	private void fourthRefreshBoard(){
		description.setText("Arrow Tower Upgrade \nCost: $" + arrowTowerTier2Obj.amount);
		currentTowerIndex = 4;
	}
	
	private void fifthRefreshBoard(){
		description.setText("Red Tower Upgrade \nCost: $" + redTowerTier2Obj.amount);
		currentTowerIndex = 5;
	}
	
	private void sixthRefreshBoard(){
		description.setText("Blue Tower Upgrade \nCost: $" + blueTowerTier2Obj.amount);
		currentTowerIndex = 6;
	}
	
	private void deSelectButtons(){
		firstButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base.png"));
		secondButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "tower-base.png"));
		thirdButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "tower-base.png"));
		fourthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "tower-base.png"));
		fifthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "tower-base.png"));
	}
	
	private void removeTierButtons(){
		fourthButton.remove(arrowTowerFirstTier);
		fifthButton.remove(arrowTowerSecondTier);
		fourthButton.remove(redTowerFirstTier);
		fifthButton.remove(redTowerSecondTier);
		fourthButton.remove(blueTowerFirstTier);
		fifthButton.remove(blueTowerSecondTier);
	}
	
	private void setFirstTierExtras(){
		if(currentLabel == 1){
			description.setText("Arrow Tower \nCost: $" + arrowTowerObj.amount);
			currentTowerIndex = 1;
		} else if(currentLabel == 2){
			description.setText("Red Tower \nCost: $" + redTowerObj.amount);
			currentTowerIndex = 2;
		} else if(currentLabel == 3){
			description.setText("Blue Tower \nCost: $" + blueTowerObj.amount);
			currentTowerIndex = 3;
		}
	}
	
	private void setSecondTierExtras(){
		if(currentLabel == 1){
			description.setText("Arrow Tower Upgrade \nCost: $" + arrowTowerTier2Obj.amount);
			currentTowerIndex = 4;
		} else if(currentLabel == 2){
			description.setText("Red Tower Upgrade \nCost: $" + redTowerTier2Obj.amount);
			currentTowerIndex = 5;
		} else if(currentLabel == 3){
			description.setText("Blue Tower Upgrade \nCost: $" + blueTowerTier2Obj.amount);
			currentTowerIndex = 6;
		}
	}
}

class TowerData{
	protected int amount = 0;
	protected boolean bought = false;
	protected boolean selected = false;
}
