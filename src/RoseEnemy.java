import java.awt.*;

/**
 * Enemy that follows a pattern that looks like a rose
 */
public class RoseEnemy extends TrigEnemy {
    private double a;
    
    public RoseEnemy(int cX, int cY, int w, int h, Color c, 
            double a, double s, double st, double rL) {
        super(cX, cY, w, h, c, s, st, 0, rL);
        
        this.a = a;
    }
    
    public RoseEnemy(int cX, int cY, int w, int h, Color c, 
            double a, double s, double st, boolean rev, double rL) {
        super(cX, cY, w, h, c, s, st, rev, 0, rL);
        
        this.a = a;
    }
    
    public int randomInt(int l, int r) {
        return (int) (Math.random() * (r - l)) + l;
    }
    
    public double xPos(double t) {
        return Math.sin(a * t) * Math.cos(t);
    }
    
    public double yPos(double t) {
        return Math.sin(a * t) * Math.sin(t);
    }
    
    public Enemy clone() {
        Rectangle r = getRectangle();
        return new RoseEnemy(getCenterX(), getCenterY(), 
                r.width, r.height, getColor(), a, getSize(),
                getStep(), getReverse(), getRightLimit());
    }
}
