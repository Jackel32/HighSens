package com.highsens.game;

public class DemoAnimator implements Runnable {
    boolean running;
    DemoPanel demoPanel = null;
    GameData gameData = null;
    
    public DemoAnimator() {
    }
    

    
    public void setDemoPanel(DemoPanel demoPanel) {
        this.demoPanel = demoPanel;
    }
    
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            gameData.update();
            demoPanel.gameRender();
            demoPanel.printScreen();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                
            }
        }
        System.exit(0);
    }
    
}