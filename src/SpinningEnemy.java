import java.awt.*;

/**
 * Enemy that spins
 */
public class SpinningEnemy extends TrigEnemy {

    public SpinningEnemy(int cX, int cY, int w, int h, Color c,
            double r, double st) {
        super(cX, cY, w, h, c, r, st, 0, 2 * Math.PI, 0);
    }
    
    public SpinningEnemy(int cX, int cY, int w, int h, Color c,
            double r, double st, boolean rev) {
        super(cX, cY, w, h, c, r, st, rev, 0, 2 * Math.PI, 0);
    }
    
    public SpinningEnemy(int cX, int cY, int w, int h, Color c,
            double r, double st, double initT) {
        super(cX, cY, w, h, c, r, st, 0, 2 * Math.PI, initT);
    }
    
    public SpinningEnemy(int cX, int cY, int w, int h, Color c,
            double r, double st, boolean rev, double initT) {
        super(cX, cY, w, h, c, r, st, rev, 0, 2 * Math.PI, initT);
    }
    
    public double xPos(double t) {
        return Math.cos(t);
    }
    
    public double yPos(double t) {
        return Math.sin(t);
    }
    
    public void drawBackground(Graphics g) {
        super.drawBackground(g);
        
        Rectangle r = getRectangle();
        g.drawLine(getCenterX(), getCenterY(), 
                r.x + r.width / 2, r.y + r.height / 2);
    }
    
    public Enemy clone() {
        Rectangle r = getRectangle();
        return new SpinningEnemy(getCenterX(), getCenterY(), 
                r.width, r.height, getColor(), 
                getSize(), getStep(), getInitT());
    }
}
