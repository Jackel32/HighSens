package com.highsens.game;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StoreScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private MoveableLabel background;
	private MoveableLabel firstLabel, secondLabel, thirdLabel;
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
	private JButton greenTower = new JButton();
	private JButton blueTower = new JButton();
	private JButton arrowTowerFirstTier = new JButton();
	private JButton greenTowerFirstTier = new JButton();
	private JButton blueTowerFirstTier = new JButton();
	private JButton arrowTowerSecondTier = new JButton();
	private JButton greenTowerSecondTier = new JButton();
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
    private TowerData greenTowerObj = new TowerData();
    private TowerData blueTowerObj = new TowerData();
    private TowerData arrowTowerTier2Obj = new TowerData();
    private TowerData greenTowerTier2Obj = new TowerData();
    private TowerData blueTowerTier2Obj = new TowerData();
    private TowerData towerArray[] = {emptyTower, arrowTowerObj, blueTowerObj, greenTowerObj, 
    		arrowTowerTier2Obj, blueTowerTier2Obj, greenTowerTier2Obj};
    private CurrentPlayer currentPlayer = CurrentPlayer.getCurrentPlayer();

	public StoreScreen() {
		setUndecorated(true);
		
		arrowTowerObj.amount = 0;
		arrowTowerTier2Obj.amount = 150;
		arrowTowerObj.bought = true;
		blueTowerObj.amount = 150;
		blueTowerTier2Obj.amount = 250;
		greenTowerObj.amount = 250;
		greenTowerTier2Obj.amount = 350;
		
		setMaximumSize(new Dimension(597, 381));
		setMinimumSize(new Dimension(597, 381));
		setResizable(false);
		setPreferredSize(new Dimension(597, 381));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Store Screen");
		getContentPane().setLayout(null);
		getContentPane().setSize(597, 381);
		this.setLocationRelativeTo(null);
		
	    background = new MoveableLabel(this);
	    background.setIcon(addImageIcon("store-background2"));
	    background.setSize(597, 381);
	    add(background);
	    
	    firstLabel = generateJLabel(332, 381, "first-selected2");
	    secondLabel = generateJLabel(332, 381, "second-selected2");
	    thirdLabel = generateJLabel(332, 381, "third-selected2");
	    
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
	    //arrowTower.setLocation(0, 0);
	    arrowTower.setSize(40,40);
	    arrowTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "arrow-tower2-icon" + ".png"));
	    arrowTower.setContentAreaFilled(false);
	    arrowTower.setBorderPainted(false);
	    arrowTower.setOpaque(false);
	    arrowTower.setFocusPainted(false);
	    
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
	    arrowTowerFirstTier.setOpaque(false);
	    arrowTowerFirstTier.setFocusPainted(false);
	    
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
                + "red-tower-icon" + ".png"));
	    arrowTowerSecondTier.setContentAreaFilled(false);
	    arrowTowerSecondTier.setBorderPainted(false);
	    arrowTowerSecondTier.setOpaque(false);
	    arrowTowerSecondTier.setFocusPainted(false);
	    
	    (blueTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				deSelectButtons();
				secondButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				removeTierButtons();
				fourthButton.add(blueTowerFirstTier);
				fifthButton.add(blueTowerSecondTier);
				secondRefresh();
				currentLabel = 2;
			}
	    });
	    blueTower.setSize(40,40);
	    blueTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "blue-tower-icon" + ".png"));
	    blueTower.setContentAreaFilled(false);
	    blueTower.setBorderPainted(false);
	    blueTower.setOpaque(false);
	    blueTower.setFocusPainted(false);
	    
	    (blueTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Blue Tower \nCost: $" + blueTowerObj.amount);
				currentTowerIndex = 2;
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
	    blueTowerFirstTier.setOpaque(false);
	    blueTowerFirstTier.setFocusPainted(false);
	    
	    (blueTowerSecondTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("Blue Tower Upgrade \nCost: $" + blueTowerTier2Obj.amount);
				currentTowerIndex = 5;
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
	    blueTowerSecondTier.setOpaque(false);
	    blueTowerSecondTier.setFocusPainted(false);
	    
	    (greenTower = new JButton()).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currentTowerIndex = 0;
				deSelectButtons();
				thirdButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
				removeTierButtons();
				fourthButton.add(greenTowerFirstTier);
				fifthButton.add(greenTowerSecondTier);
				thirdRefresh();
				currentLabel = 3;
			}
	    });
	    greenTower.setSize(40,40);
	    greenTower.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "green-tower-icon" + ".png"));
	    greenTower.setContentAreaFilled(false);
	    greenTower.setBorderPainted(false);
	    greenTower.setOpaque(false);
	    greenTower.setFocusPainted(false);
	    
	    (greenTowerFirstTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("green Tower \nCost: $" + greenTowerObj.amount);
				currentTowerIndex = 3;
				deSelectButtons();
				fourthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
			}
	    });
	    greenTowerFirstTier.setLocation(165, 162);
	    greenTowerFirstTier.setSize(40,40);
	    greenTowerFirstTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "green-tower-icon" + ".png"));
	    greenTowerFirstTier.setContentAreaFilled(false);
	    greenTowerFirstTier.setBorderPainted(false);
	    greenTowerFirstTier.setOpaque(false);
	    greenTowerFirstTier.setFocusPainted(false);
	    
	    (greenTowerSecondTier = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				description.setText("green Tower Upgrade \nCost: $" + greenTowerTier2Obj.amount);
				currentTowerIndex = 6;
				deSelectButtons();
				fifthButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "tower-base-selected.png"));
			}
	    });
	    greenTowerSecondTier.setLocation(253, 162);
	    greenTowerSecondTier.setSize(40,40);
	    greenTowerSecondTier.setIcon(new ImageIcon(imagePath + separator + "images" + separator
                + "green-tower-icon-tier2" + ".png"));
	    greenTowerSecondTier.setContentAreaFilled(false);
	    greenTowerSecondTier.setBorderPainted(false);
	    greenTowerSecondTier.setOpaque(false);
	    greenTowerSecondTier.setFocusPainted(false);
	    
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
				fourthButton.add(blueTowerFirstTier);
				fifthButton.add(blueTowerSecondTier);
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
				fourthButton.add(greenTowerFirstTier);
				fifthButton.add(greenTowerSecondTier);
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
	    backButton.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            backButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "back-button.png"));
	        }
	        
	        public void mouseReleased(MouseEvent e){
	        	backButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "invisible-back-button.png"));
	        }
        });
	    backButton.setLocation(547,0);
	    backButton.setSize(47,47);
	    backButton.setOpaque(false);
	    backButton.setContentAreaFilled(false);
	    backButton.setBorderPainted(false);
	    
	    (buyButton = new JButton()).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				buyCurrentItem();
			}
	    });
	    buyButton.setLocation(162,259);
	    buyButton.setSize(118,42);
	    buyButton.setOpaque(false);
	    buyButton.setContentAreaFilled(false);
	    buyButton.setBorderPainted(false);
	    buyButton.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	        	buyButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "buy-button.png"));
	        }
	        
	        public void mouseReleased(MouseEvent e){
	        	buyButton.setIcon(new ImageIcon(imagePath + separator + "images" + separator
		                + "invisible-buy-button.png"));
	        }
        });
	    
	    firstButton.add(arrowTower);
	    secondButton.add(blueTower);
	    thirdButton.add(greenTower);
	    
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
	}
	
	private ImageIcon addImageIcon(String filename){
		return new ImageIcon(imagePath + separator + "images" + separator
                + filename + ".png");
	}
	
	private MoveableLabel generateJLabel(int x, int y, String filename){
		MoveableLabel label = new MoveableLabel(this);
		label.setIcon(addImageIcon(filename));
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
		background = new MoveableLabel(this);
		background.setIcon(addImageIcon("store-background2"));
	    background.setSize(597, 381);
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
					CurrentPlayer.setBlueTowerPurchased(true);
				} else if(currentTowerIndex == 3){
					buy();
					thirdRefresh();
					thirdRefreshBoard();
					CurrentPlayer.setGreenTowerPurchased(true);
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
				JOptionPane.showMessageDialog(new Frame(), "You don't have enough game cash to purchase this item");
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
		if(blueTowerObj.bought == true){
			secondLabel.add(secondTowerFirstTierArrow);
		}
		if(blueTowerTier2Obj.bought == true){
			secondLabel.add(secondTierArrow);
		}
		background.add(secondLabel);
		refresh();
	}
	
	private void secondRefreshBoard(){
		description.setText("Blue Tower \nCost: $" + blueTowerObj.amount);
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
		if(greenTowerObj.bought == true){
			thirdLabel.add(thirdTowerFirstTierArrow);
		}
		if(greenTowerTier2Obj.bought == true){
			thirdLabel.add(secondTierArrow);
		}
		background.add(thirdLabel);
		refresh();
	}
	
	private void thirdRefreshBoard(){
		description.setText("green Tower \nCost: $" + greenTowerObj.amount);
		currentTowerIndex = 3;
	}
	
	private void fourthRefreshBoard(){
		description.setText("Arrow Tower Upgrade \nCost: $" + arrowTowerTier2Obj.amount);
		currentTowerIndex = 4;
	}
	
	private void fifthRefreshBoard(){
		description.setText("Blue Tower Upgrade \nCost: $" + blueTowerTier2Obj.amount);
		currentTowerIndex = 5;
	}
	
	private void sixthRefreshBoard(){
		description.setText("green Tower Upgrade \nCost: $" + greenTowerTier2Obj.amount);
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
		fourthButton.remove(greenTowerFirstTier);
		fifthButton.remove(greenTowerSecondTier);
		fourthButton.remove(blueTowerFirstTier);
		fifthButton.remove(blueTowerSecondTier);
	}
	
	private void setFirstTierExtras(){
		if(currentLabel == 1){
			description.setText("Arrow Tower \nCost: $" + arrowTowerObj.amount);
			currentTowerIndex = 1;
		} else if(currentLabel == 2){
			description.setText("Blue Tower \nCost: $" + blueTowerObj.amount);
			currentTowerIndex = 2;
		} else if(currentLabel == 3){
			description.setText("green Tower \nCost: $" + greenTowerObj.amount);
			currentTowerIndex = 3;
		}
	}
	
	private void setSecondTierExtras(){
		if(currentLabel == 1){
			description.setText("Arrow Tower Upgrade \nCost: $" + arrowTowerTier2Obj.amount);
			currentTowerIndex = 4;
		} else if(currentLabel == 2){
			description.setText("Blue Tower Upgrade \nCost: $" + blueTowerTier2Obj.amount);
			currentTowerIndex = 5;
		} else if(currentLabel == 3){
			description.setText("green Tower Upgrade \nCost: $" + greenTowerTier2Obj.amount);
			currentTowerIndex = 6;
		}
	}
}

class TowerData{
	protected int amount = 0;
	protected boolean bought = false;
	protected boolean selected = false;
}
