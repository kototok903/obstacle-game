import java.awt.*;

/**
 * Enemy that follows a pattern that looks like an infinity sign
 */
public class InfinityEnemy extends TrigEnemy {

    public InfinityEnemy(int cX, int cY, int w, int h, Color c, 
            double s, double st) {
        super(cX, cY, w, h, c, s, st, -Math.PI / 2, 3 * Math.PI / 2);
    }
    
    public InfinityEnemy(int cX, int cY, int w, int h, Color c, 
            double s, double st, boolean rev) {
        super(cX, cY, w, h, c, s, st, rev, -Math.PI / 2, 3 * Math.PI / 2);
    }
    
    public int randomInt(int l, int r) {
        return (int) (Math.random() * (r - l)) + l;
    }
    
    public double xPos(double t) {
        return Math.cos(t);
    }
    
    public double yPos(double t) {
        return Math.sin(t) * Math.cos(t);
    }
    
    public Enemy clone() {
        Rectangle r = getRectangle();
        return new InfinityEnemy(getCenterX(), getCenterY(), 
                r.width, r.height, getColor(), getSize(), 
                getStep(), getReverse());
    }
}
