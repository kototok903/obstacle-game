import java.awt.*;

/**
 * Enemy that moves left and right
 */
public class HorizontalEnemy extends DiagonalEnemy {
    
    public HorizontalEnemy(int x, int y, int w, int h, Color c, 
            int s, int lB, int rB) {
        super(x, y, w, h, c, s, 0, 0, 0, lB, rB);
    }
    
    public HorizontalEnemy(int x, int y, int w, int h, Color c, 
            int s, int gW) {
        super(x, y, w, h, c, s, 0, gW, 0);
    }
}
