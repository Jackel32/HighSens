package com.highsens.game.monster;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class AbstractMonster {

	protected int maxHealth;
	protected int speed;
	protected int health;

	public AbstractMonster() {
	}

	/**
	 * Draws healthbar on x and y coordinates.
	 */
	protected void drawHealthBar(Graphics g, float x, float y) {
		Graphics2D healthBar = (Graphics2D) g;
		healthBar.setColor(Color.RED);
		healthBar.setFont(new Font("Serif", Font.PLAIN, 10));
		// healthBar.drawString("hp:" + health, x - 5, y - 5);
		healthBar.drawString(calculateHealthbarSize(), x - 5, y - 5);
	}

	private String calculateHealthbarSize() {
		String healthBar = "";
		int currentHealth = health;
		while (currentHealth - (maxHealth / 10) > 1) {
			currentHealth = currentHealth - (maxHealth / 10);
			healthBar += "=";
		}

		return healthBar;
	}

}
