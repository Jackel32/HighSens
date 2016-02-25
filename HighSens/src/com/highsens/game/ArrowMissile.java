package com.highsens.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import javax.vecmath.Vector2f;
public class ArrowMissile  extends Ellipse2D.Float implements GameFigure{

	static int SIZE = 2;
    //static int widthSIZE = 1;
    //static int highSIZE = 1;
    Color color;
    Point2D.Float target;
    private int state = STATE_TRAVELING;
   // private static final int UNIT_TRAVEL_DISTANCE = 5;  Original
    private static int UNIT_TRAVEL_DISTANCE = 5;    // test
    private int explosionSize = SIZE;
    private int explosionMaxSize;
    long ElapsedTime;
    long Start, End;
    public boolean targetReached = false;
    
    public void setUNIT_TRAVEL_DISTANCE(){
    	UNIT_TRAVEL_DISTANCE += 3;
    }


    public boolean isTargetReached() {
        return targetReached;
    }

    public void setTargetReached(boolean targetReached) {
        this.targetReached = targetReached;
    }

    public ArrowMissile(float x, float y, Color color) {
        setFrameFromCenter(x, y, x + SIZE -1 , y + SIZE);
        Start = System.currentTimeMillis();
        this.color = color;
    }

    public void setTarget(float x, float y) {
        target = new Point2D.Float(x, y);
    }
    
    public void setExplosionMaxSize(int size) {
        explosionMaxSize = size;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)super.x, (int)super.y, 10, 3);
        //g.fillOval((int)super.x, (int)super.y, (int)super.width, (int)super.height);
    }

    @Override
    public void update() {
        updateState();
        if (state == STATE_TRAVELING) {
            updateLocation();
            ElapsedTime = (End - Start) * 5;
        } else if (state == STATE_EXPLODING) {
            updateSize();
        }
    }

    // Vector arithmetic
    // A: current position
    // B: target position
    // d: distance to travel along the line from A to B
    //     A_moved = A + |B - A| * d where |  | represents 'norm'
    public void updateLocation() {
        Vector2f currentLoc = new Vector2f((float) getCenterX(), (float) getCenterY());
        Vector2f update = new Vector2f(target.x, target.y);
        update.sub(currentLoc); // B - A
        update.normalize(); // |B - A|
        update.scale(UNIT_TRAVEL_DISTANCE * 3); // |B - A| x dist
        
        currentLoc.add(update) ; // A + |B - A| x d
    
        setFrameFromCenter(currentLoc.x, currentLoc.y,
        currentLoc.x + SIZE, currentLoc.y + SIZE);
        setTargetReached(true);
    }

    public void updateSize() {
        double x = target.getX();
        double y = target.getY();
        explosionSize += 2;
        setFrameFromCenter(x, y, x + explosionSize, y + explosionSize);
    }

    public void updateState() {
        if (state == STATE_TRAVELING) {
            double distance = target.distance(getCenterX(), getCenterY());
            boolean targetReached = distance <= 2.0 ? true : false;
            End = System.currentTimeMillis();
            if (targetReached) {
                state = STATE_EXPLODING;
            } else if (!targetReached && ElapsedTime > 1300)
            {
                state = STATE_DONE;
            }
        } else if (state == STATE_EXPLODING) {
            if (explosionSize >= explosionMaxSize) {
                state = STATE_DONE;
            }
        }
    }

    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void updateHealth() {

    }

    @Override
    public boolean collision(GameFigure m) {
        return false;
    }

    @Override
    public int getHealth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
	
}
