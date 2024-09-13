import java.awt.*;

public abstract class Enemy  {
    private Rectangle rect;
    private Color color;
    
    public Enemy(int x, int y, int w, int h, Color c) {
        rect = new Rectangle(x, y, w, h);
        color = c;
    }
    
    public Rectangle getRectangle() {
        return rect;
    }
    
    public boolean intersects(Rectangle p) {
        return rect.intersects(p);
    }
    
    /**
     * Draws layer above the player
     */
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }
    
    /**
     * Draws layer under the player
     */
    public void drawBackground(Graphics g) { }
    
    public Color getColor() {
        return color;
    }
    
    public abstract void move();
    
    public abstract Enemy clone();
}