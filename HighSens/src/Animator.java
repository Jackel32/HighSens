
public class Animator implements Runnable {
    boolean running;
    GamePanel gamePanel = null;
    GameData gameData = null;
    
    public Animator() {
    }
    
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            gameData.update();
            gamePanel.gameRender();
            gamePanel.printScreen();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                
            }
        }
        System.exit(0);
    }
    
}
