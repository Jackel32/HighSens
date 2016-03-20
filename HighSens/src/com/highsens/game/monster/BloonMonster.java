package com.highsens.game.monster;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.highsens.game.AudioData;
import com.highsens.game.AudioPlayer;
import com.highsens.game.GameData;
import com.highsens.game.GameFigure;
import com.highsens.game.IStrategy;
import com.highsens.game.Missile;
import com.highsens.game.Monster;

public class BloonMonster extends AbstractMonster implements Monster, GameFigure, IStrategy {
	AudioData deathSound;
	Point2D.Float target;
	Missile missile;
	GameData gd;
	private int state;
	public float x, y;

	int buffCount = 0;

	String imagePath = System.getProperty("user.dir");
	String separator = System.getProperty("file.separator");

	Image BlueMonsterImage = getImage(imagePath + separator + "images" + separator + "BlueMonster.png");
	Image GreenMonsterImage = getImage(imagePath + separator + "images" + separator + "GreenMonster.png");
	Image OrangeMonsterImage = getImage(imagePath + separator + "images" + separator + "OrangeMonster.png");
	Image YellowMonsterImage = getImage(imagePath + separator + "images" + separator + "YellowMonster.png");

	public BloonMonster(float x, float y, GameData gd) {
		this.gd = gd;
		this.state = PATH_0;
		this.x = x;
		this.y = y;
		this.maxHealth = 100;
		this.health = maxHealth;
		this.speed = 8;
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		AudioPlayer.loadClip("pop", "sounds/pop.wav");
	}

	public Image getImage(String fileName) {
		Image image = null;
		try {
			image = ImageIO.read(new File(fileName));
		} catch (Exception ioe) {
			System.out.println("Error: Cannot open image:" + fileName);
			JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
		}
		return image;
	}

	@Override
	public void render(Graphics g) {
		drawHealthBar(g, x, y);
		if (health > 0 && health <= 25) {
			g.drawImage(YellowMonsterImage, (int) x, (int) y, null);
			this.setSpeed(2);
		} else if (health > 25 && health <= 50) {
			g.drawImage(OrangeMonsterImage, (int) x, (int) y, null);
			this.setSpeed(4);
		} else if (health > 50 && health <= 75) {
			g.drawImage(GreenMonsterImage, (int) x, (int) y, null);
			this.setSpeed(6);
		} else if (health > 75 && health <= 100) {
			g.drawImage(BlueMonsterImage, (int) x, (int) y, null);
			this.setSpeed(8);
		}
	}

	@Override
	public void update() {
		updateState();
		switch (state) {
		case PATH_0:
			moveRight();
			break;
		case PATH_1:
			moveRight();
			break;
		case PATH_2:
			moveRight();
			break;
		case PATH_3:
			moveRight();
			break;
		case PATH_4:
			moveRight();
			break;
		case PATH_5:
			moveRight();
			break;
		case PATH_6:
			moveRight();
			break;
		case STATE_DONE:
			AudioPlayer.play("pop", false);
			gd.moneyManager("bloonKill", gd.getMoney());
			gd.monsterManager("bloonKill");
			break;
		case LIFE_LOST:
			updateLives();
			state = STATE_DONE;
			gd.monsterManager("bloonKill");
			break;
		}
	}

	@Override
	public void updateState() {
		switch (state) {
		case PATH_0:
			if (x >= 69)
				state = PATH_1;
			break;
		case PATH_1:
			if (x >= 87)
				state = PATH_2;
			break;
		case PATH_2:
			if (x >= 200)
				state = PATH_3;
			break;
		case PATH_3:
			if (x >= 240)
				state = PATH_4;
			break;
		case PATH_4:
			if (x >= 350)
				state = PATH_5;
			break;
		case PATH_5:
			if (x >= 560)
				state = PATH_6;
			break;
			
		case PATH_6:
			if (x >= 1250)
				state = LIFE_LOST;
		}
	}

	public void moveRight() {
		x += speed;
	}

	public void moveLeft() {
		x -= speed;
	}

	public void moveUp() {
		y -= speed;
	}

	public void moveDown() {
		y += speed;
	}

	@Override
	public int getState() {
		return state;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	
	
	public int getHealth() {
		return health;
	}

	@Override
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public double getX() {
		return (float) x;
	}

	@Override
	public double getY() {
		return (float) y;

	}

	public boolean contains(float x, float y) {
		//System.out.println("X: " + x + ", Y: " + y);
		if (x < this.x)
			return false;
		if (x > this.x + 27)
			return false;
		if (y < this.y)
			return false;
		if (y > this.y + 20)
			return false;

		return true;
	}

	@Override
	public void updateHealth() {
		health -= 8;
		if (health <= 0) {
			state = STATE_DONE;
		}
		// else if ((health >= (health / 4) && (health <= (health / 4) + 5))) {
		// if (buffCount < 1) {
		// getAngry();
		// }
		// buffCount++;
		// }
	}

	public void getAngry() {
		//had to keep this to keep this... don't care why.
		health += 0;
		speed += 0;
	}

	public int getScore() {
		return gd.score += 10;
	}

	@Override
	public boolean collision(GameFigure m) {
		return false;
	}

	@Override
	public void updateLives() {
		gd.minusLives();
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getXofMissileShoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYofMissileShoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIsAngry(boolean t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsAngry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBulletCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBulletCount(int bulletCount) {
		// TODO Auto-generated method stub
		
	}

}
