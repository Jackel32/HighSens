import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class AbstractTower {

	/**
	 * Draws range on x and y coordinates.
	 */
	protected void drawRange(Graphics g, float x, float y, int range) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.green);
		g2d.drawOval((int) x - 75, (int) y - 70, range, range);
	}
}
