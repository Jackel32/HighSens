
import java.awt.*;

public class Block extends Rectangle {
	public int groundID;
	public int airID;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);
		
		if (airID != Value.airAir) {
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
		}
	}
}
