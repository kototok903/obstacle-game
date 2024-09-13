import java.awt.*;

/**
 * Enemy that follows player
 */
public class StalkerEnemy extends Enemy {
    private int speed;
    private Rectangle player;
    
    public StalkerEnemy(int x, int y, int w, int h, Color c, 
            int s, Rectangle p) {
        super(x, y, w, h, c);
        
        speed = s;
        player = p;
    }
    
    public void move() {
        Rectangle r = getRectangle();
        double xShift, yShift, z;
        
        // calculates the vector to the player
        xShift = 1.0 * (player.x + player.width / 2 - r.width / 2) - r.x;
        yShift = 1.0 * (player.y + player.height / 2 - r.height / 2) - r.y;
        // calculates multiplier for the vector
        z = Math.sqrt(speed * speed / (xShift * xShift + yShift * yShift));
        
        // makes the vector length equal to the speed 
        // and moves the enemy
        r.x += (int) (xShift * z);
        r.y += (int) (yShift * z);
    }
    
    public Enemy clone() {
        Rectangle r = getRectangle();
        return new StalkerEnemy(r.x, r.y, r.width, r.height, getColor(),
                speed, player);
    }
}