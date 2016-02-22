package com.highsens.game;
import java.awt.Graphics;
import java.awt.Image;

public interface Monster
{
    public Image getImage(String fileName);
    public void render(Graphics g);
    public void update();
    public void updateState();
    public void updateHealth();
    public void updateLives();
    public boolean contains(float x, float y);
    public void moveRight();
    public void moveLeft();
    public void moveUp();
    public void moveDown();
    public void setState(int state);
    public int getState();
    public double getX();
    public void setX(float x);
    public double getY();
    public void setY(float y);
    public boolean collision(GameFigure m);
};
