import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class BlueTower extends AbstractTower implements Tower, GameFigure {

	Image towerImage;
	float x, y;
	Point2D.Float target;
	private int state = STATE_IDLE;
	Monster m;
	GameData gd; 
	public Ellipse2D.Double radius;
	private int range;

	public BlueTower(float x, float y, GameData gd) {
		this.gd = gd;
		this.x = x;
		this.y = y;
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		towerImage = getImage(imagePath + separator + "images" + separator + "BlueTower.png");

		radius = new Ellipse2D.Double((this.x + 50) - 175, (this.y + 69) - 175, 300, 300);
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
	public float getXofMissileShoot() {
		return x + 30;
	}

	@Override
	public float getYofMissileShoot() {
		return y + 20;
	}

	@Override
	public void render(Graphics g) {
		drawRange(g, x, y, 200);
		g.drawImage(towerImage, (int) x, (int) y, null);
	}

	@Override
	public void updateState() {

	}

	@Override
	public void update() {

	}

	@Override
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public int getState() {
		return state;
	}

	@Override
	public void updateHealth() {

	}

	@Override
	public boolean collision(GameFigure m) {
		boolean collision = false;
		if (radius.intersects(m.getX(), m.getY(), 47, 37)) {
			collision = true;
		} else {
			collision = false;
		}
		return collision;
	}

	@Override
	public int getHealth() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
}
