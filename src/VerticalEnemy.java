import java.awt.*;

/**
 * Enemy that moves up and down
 */
public class VerticalEnemy extends DiagonalEnemy {
    
    public VerticalEnemy(int x, int y, int w, int h, Color c, 
            int s, int tB, int bB) {
        super(x, y, w, h, c, 0, s, tB, bB, 0, 0);
    }
    
    public VerticalEnemy(int x, int y, int w, int h, Color c, 
            int s, int gH) {
        super(x, y, w, h, c, 0, s, 0, gH);
    }
}