import java.awt.*;

/**
 * Enemy that moves like a DVD sign
 */
public class DiagonalEnemy extends Enemy {
    // Fields
    private int xSpeed, ySpeed;
    private int topBorder, bottomBorder, leftBorder, rightBorder;
    private boolean isMovingUp, isMovingLeft;
    
    public DiagonalEnemy(int x, int y, int w, int h, Color c, 
            int xS, int yS, int tB, int bB, int lB, int rB) {
        super(x, y, w, h, c);
        
        xSpeed = xS;
        ySpeed = yS;
        isMovingUp = true;
        isMovingLeft = true;
        topBorder = tB;
        bottomBorder = bB;
        leftBorder = lB;
        rightBorder = rB;
    }
    
    public DiagonalEnemy(int x, int y, int w, int h, Color c,  
            int xS, int yS, int gW, int gH) {
        super(x, y, w, h, c);
        
        xSpeed = xS;
        ySpeed = yS;
        isMovingUp = true;
        isMovingLeft = true;
        topBorder = 0;
        bottomBorder = gH;
        leftBorder = 0;
        rightBorder = gW;
    }
    
    public void move() {
        Rectangle r = getRectangle();
        
        if (ySpeed != 0)
        if (isMovingUp) {
            r.y -= ySpeed;
            if (r.y <= topBorder) {
                r.y = topBorder;
                isMovingUp = false;
            }
        }
        else {
            r.y += ySpeed;
            if (r.y >= bottomBorder - r.height) {
                r.y = bottomBorder - r.height;
                isMovingUp = true;
            }
        }
        
        if (xSpeed != 0)
        if (isMovingLeft) {
            r.x -= xSpeed;
            if (r.x <= leftBorder) {
                r.x = leftBorder;
                isMovingLeft = false;
            }
        }
        else {
            r.x += xSpeed;
            if (r.x >= rightBorder - r.width) {
                r.x = rightBorder - r.width;
                isMovingLeft = true;
            }
        }
    }
    
    public Enemy clone() {
        Rectangle r = getRectangle();
        return new DiagonalEnemy(r.x, r.y, r.width, r.height, getColor(),
                xSpeed, ySpeed, 
                topBorder, bottomBorder, leftBorder, rightBorder);
    }
}
