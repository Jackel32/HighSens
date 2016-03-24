package com.highsens.game;

public class StoreAnimator implements Runnable {
    boolean running;
    StorePanel storePanel = null;
    GameData storeData = null;
    
    public StoreAnimator() {
    }
    
    public void setStorePanel(StorePanel storePanel) {
        this.storePanel = storePanel;
    }
    
    public void setStoreData(GameData storeData) {
        this.storeData = storeData;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            storeData.update();
            storePanel.storeRender();
            storePanel.printScreen();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                
            }
        }
        System.exit(0);
    }
    
}
