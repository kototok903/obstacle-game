import java.awt.*;

/**
 * Enemy that works like a bullet
 */
public class JumpStalkerEnemy extends Enemy {
    private int shiftMult;
    private int speed;
    private int xDir, yDir;
    private int topBorder, bottomBorder, leftBorder, rightBorder;
    private Rectangle player;
    
    public JumpStalkerEnemy(int x, int y, int w, int h, Color c, 
            int s, Rectangle p, int tB, int bB, int lB, int rB) {
        super(x, y, w, h, c);
        
        speed = s;
        player = p;
        topBorder = tB;
        bottomBorder = bB;
        leftBorder = lB;
        rightBorder = rB;
        shiftMult = getShiftMult();
        
        updateDirection();
    }
    
    public JumpStalkerEnemy(int x, int y, int w, int h, Color c, 
            int s, Rectangle p, int gW, int gH) {
        super(x, y, w, h, c);
        
        speed = s;
        player = p;
        topBorder = 0;
        bottomBorder = gH;
        leftBorder = 0;
        rightBorder = gW;
        shiftMult = getShiftMult();
        
        updateDirection();
    }
    
    /**
     * @return new shift multiplier
     */
    private int getShiftMult() {
        // shift multiplier should be bigger than the size of 
        // the map, so that enemy will collide with the wall
        return Math.max(bottomBorder, rightBorder) + 1;
    }
    
    /**
     * Sets the place far behind the player as a new destination
     * 
     * This method takes vector from the enemy to the player, 
     * lengthens it, and make the enemy follow it so that 
     * the enemy will collide into the wall behind the player
     */
    private void updateDirection() {
        Rectangle r = getRectangle();
        
        // player's center coordinates
        xDir = (player.x + player.width / 2 - r.width / 2);
        yDir = (player.y + player.height / 2 - r.height / 2);
        
        // vector to the player
        double xShift, yShift;
        xShift = xDir - r.x;
        yShift = yDir - r.y;
        
        // increase it
        xDir = r.x + (int) (xShift * shiftMult);
        yDir = r.y + (int) (yShift * shiftMult);
    }
    
    public void move() {
        Rectangle r = getRectangle();
        
        // calculate new destination
        if (r.x <= leftBorder || r.x >= rightBorder - r.width || 
                r.y <= topBorder || r.y >= bottomBorder - r.height) {
            updateDirection();
        }
        
        // move
        double xShift, yShift, z;
    
        xShift = xDir - r.x;
        yShift = yDir - r.y;
        z = Math.sqrt(speed * speed / (xShift * xShift + yShift * yShift));
        
        r.x += (int) (xShift * z);
        r.y += (int) (yShift * z);
    }
    
    public Enemy clone() {
        Rectangle r = getRectangle();
        return new JumpStalkerEnemy(r.x, r.y, r.width, r.height, getColor(), 
                speed, player, 
                topBorder, bottomBorder, leftBorder, rightBorder);
    }
}
