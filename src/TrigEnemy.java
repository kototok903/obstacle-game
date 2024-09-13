import java.awt.*;

/**
 * Enemy that follows parametric equation path
 * (x, y) = (f(t), g(t))
 * f() - xPos()
 * g() - yPos()
 */
public abstract class TrigEnemy extends Enemy {
    private int centerX, centerY;
    private double size; // enemy path's size multiplier
    private double leftLim, rightLim; // limits of t
    private double t, initialT;
    private double step; // step of t
    private int reverse; // 1 - enemy goes normally, -1 - enemy goes backwards

    public TrigEnemy(int cX, int cY, int w, int h, Color c, 
             double s, double st, double lL, double rL, double initT) {
        super(0, 0, w, h, c);
        
        getRectangle().x = cX + (int) (s * xPos(initT)) - w/2;
        getRectangle().y = cY + (int) (s * yPos(initT)) - h/2;
        
        centerX = cX;
        centerY = cY;
        size = s;
        t = initT;
        initialT = initT;
        step = st; 
        reverse = 1;
        leftLim = lL;
        rightLim = rL;
    }
    
    public TrigEnemy(int cX, int cY, int w, int h, Color c, 
             double s, double st, boolean rev, double lL, double rL, double initT) {
        super(0, 0, w, h, c);
        
        getRectangle().x = cX + (rev ? -1 : 1) * (int) (s * xPos(initT)) - w/2;
        getRectangle().y = cY + (rev ? -1 : 1) * (int) (s * yPos(initT)) - h/2;
        
        centerX = cX;
        centerY = cY;
        size = s;
        t = initT;
        initialT = initT;
        step = st; 
        reverse = (rev ? -1 : 1);
        leftLim = lL;
        rightLim = rL;
    }
    
    public TrigEnemy(int cX, int cY, int w, int h, Color c, 
             double s, double st, double lL, double rL) {
        super(0, 0, w, h, c);
        
        getRectangle().x = cX + (int) (s * xPos(lL)) - w/2;
        getRectangle().y = cY + (int) (s * yPos(lL)) - h/2;
        
        centerX = cX;
        centerY = cY;
        size = s;
        t = lL;
        initialT = lL;
        step = st; 
        reverse = 1;
        leftLim = lL;
        rightLim = rL;
    }
    
    public TrigEnemy(int cX, int cY, int w, int h, Color c, 
             double s, double st, boolean rev, double lL, double rL) {
        super(0, 0, w, h, c);
        
        getRectangle().x = cX + (rev ? -1 : 1) * (int) (s * xPos(lL)) - w/2;
        getRectangle().y = cY + (rev ? -1 : 1) * (int) (s * yPos(lL)) - h/2;
        
        centerX = cX;
        centerY = cY;
        size = s;
        t = lL;
        initialT = lL;
        step = st; 
        reverse = (rev ? -1 : 1);
        leftLim = lL;
        rightLim = rL;
    }
    
    public int getCenterX() {
        return centerX;
    }
    
    public int getCenterY() {
        return centerY;
    }
    
    public double getSize() {
        return size;
    }
    
    public double getStep() {
        return step;
    }
    
    public boolean getReverse() {
        return (reverse == -1 ? true : false);
    }
    
    public double getInitT() {
        return initialT;
    }
    
    public double getRightLimit() {
        return rightLim;
    }
    
    public double getLeftLimit() {
        return leftLim;
    }
    
    public abstract double xPos(double t);
    
    public abstract double yPos(double t);

    /**
     * Draws dot in the enemies path center
     */
    public void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(getCenterX() - 5, getCenterY() - 5, 10, 10);
    }
    
    public void move() {
        t += step;
        
        Rectangle r = getRectangle();
        
        r.x = centerX + reverse * (int) (size * xPos(t)) - r.width / 2;
        r.y = centerY + reverse * (int) (size * yPos(t)) - r.height / 2;
        
        // t changes from rightLim to leftLim
        if (t >= rightLim) t = leftLim;
    }
}
