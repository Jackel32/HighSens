package com.highsens.game.monster;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.highsens.game.AudioPlayer;
import com.highsens.game.GameData;
import com.highsens.game.GameFigure;
import com.highsens.game.IStrategy;
import com.highsens.game.Monster;

public class Boss extends AbstractMonster implements Monster, GameFigure, IStrategy {

	private Image bossImage;
	private GameData gd;
	private int state;
	public float x, y;
	private int buffCount = 0;

	public Boss(float x, float y, GameData gd) {
		this.gd = gd;
		this.state = PATH_0;
		this.x = x;
		this.y = y;
		this.maxHealth = 1000;
		this.health = maxHealth;
		this.speed = 3;
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		bossImage = getImage(imagePath + separator + "images" + separator + "boss.png");
		AudioPlayer.loadClip("pop", "sounds/pop.wav");
	}

	@Override
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
		g.drawImage(bossImage, (int) x, (int) y, null);
	}

	@Override
	public void update() {
		updateState();
		switch (state) {
		case PATH_0:
			moveRight();
			break;
		case PATH_1:
			moveUp();
			break;
		case PATH_2:
			moveRight();
			break;
		case PATH_3:
			moveDown();
			break;
		case PATH_4:
			moveRight();
			break;
		case PATH_5:
			moveUp();
			break;
		case PATH_6:
			moveRight();
			break;
		case STATE_DONE:
			AudioPlayer.play("pop", false);
			gd.moneyManager("bossKill", gd.getMoney());
			gd.monsterManager("bossKill");
			break;
		case LIFE_LOST:
			updateLives();
			state = STATE_DONE;
			gd.monsterManager("bossKill");
			break;
		}
	}

	@Override
	public void updateState() {
		switch (state) {
		case PATH_0:
			if (x >= 20)
				state = PATH_1;
			break;
		case PATH_1:
			if (y <= 7)
				state = PATH_2;
			break;
		case PATH_2:
			if (x >= 140)
				state = PATH_3;
			break;
		case PATH_3:
			if (y >= 150)
				state = PATH_4;
			break;
		case PATH_4:
			if (x >= 300)
				state = PATH_5;
			break;
		case PATH_5:
			if (y <= 70)
				state = PATH_6;
			break;
		case PATH_6:
			if (x >= 600)
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

	@Override
	public boolean contains(float x, float y) {
		if (x < this.x)
			return false;
		if (x > this.x + 128)
			return false;
		if (y < this.y)
			return false;
		if (y > this.y + 128)
			return false;

		return true;
	}

	@Override
	public void updateHealth() {
		health -= 5;
		if (health <= 0) {
			state = STATE_DONE;
		} else if ((health >= (health / 4) && (health <= (health / 4) + 5))) {
			if (buffCount < 1) {
				getAngry();
			}
			buffCount++;
		}
	}

	public void getAngry() {
		health += 1000;
		speed += 2;
	}

	public int getScore() {
		return gd.score += 25;
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
	public void setY(float y) {
		this.y = y;
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
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
}
