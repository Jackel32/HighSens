package com.highsens.game;

import java.awt.Graphics;

public interface GameFigure {
    public void render(Graphics g);
    public void update();
    public int getState();
    public void setState(int state);
    public double getX();
    public double getY();
    public void updateHealth();
    public int getHealth();
    public boolean collision(GameFigure m);
    
    
    //missle states --------------
    static final int STATE_TRAVELING = 1;
    static final int STATE_EXPLODING = 2;
    //Monster states -------------
    static final int STATE_ANGRY = 3;
    static final int STATE_DAMAGE = 4;
    static final int STATE_ALIVE = 5;
    static final int PATH_0 = 10;
    static final int PATH_1 = 11;
    static final int PATH_2 = 12;   
    static final int PATH_3 = 13; 
    static final int PATH_4 = 14; 
    static final int PATH_5 = 15; 
    static final int PATH_6 = 16; 
    //Tower states ---------------
    static final int STATE_SHOOTING = 7;
    static final int STATE_IDLE = 8;
    //Ending state ----------------
    static final int STATE_DONE = 0;
    static final int LIFE_LOST = 17;
    static final int STATE_KILLED = 18;

}
