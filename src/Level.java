import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Level {
    // Fields
    private BufferedImage bgImg;
    private Color bgColor;
    private int playerX, playerY;
    private int goalX, goalY;
    private Color playerColor, goalColor;
    private Enemy[] enemies;
    
    public Level(String bgpath, int pX, int pY, int gX, int gY, 
            Color pC, Color gC, Enemy[] es) {
        bgImg = ImageLoader.loadCompatibleImage(bgpath);
        bgColor = Color.white;
        playerX = pX;
        playerY = pY;
        goalX = gX;
        goalY = gY;
        playerColor = pC;
        goalColor = gC;
        enemies = es;
    }

    public Level(String bgpath, Color pC, Color gC, Enemy[] es) {
        bgImg = ImageLoader.loadCompatibleImage(bgpath);
        bgColor = Color.white;
        playerX = 50;
        playerY = 50;
        goalX = 515;
        goalY = 515;
        playerColor = pC;
        goalColor = gC;
        enemies = es;
    }
    
    public Level(Color bgC, int pX, int pY, int gX, int gY,
            Color pC, Color gC, Enemy[] es) {
        bgImg = null;
        bgColor = bgC;
        playerX = pX;
        playerY = pY;
        goalX = gX;
        goalY = gY;
        playerColor = pC;
        goalColor = gC;
        enemies = es;
    }
    
    public Level(Color bgC, Color pC, Color gC, Enemy[] es) {
        bgImg = null;
        bgColor = bgC;
        playerX = 50;
        playerY = 50;
        goalX = 515;
        goalY = 515;
        playerColor = pC;
        goalColor = gC;
        enemies = es;
    }

    public int getPlayerX() {
        return playerX;
    }
    
    public int getPlayerY() {
        return playerY;
    }
    
    public int getGoalX() {
        return goalX;
    }
    
    public int getGoalY() {
        return goalY;
    }
    
    public Color getPlayerColor() {
        return playerColor;
    }
    
    public Color getGoalColor() {
        return goalColor;
    }
    
    public Enemy[] getEnemiesClone() {
        Enemy[] clone = new Enemy[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            clone[i] = enemies[i].clone();
        }
        return clone;
    }
    
    public void drawBackground(Graphics g) {
        if (bgImg == null) {
            g.setColor(bgColor);
            g.fillRect(0, 0, 600, 600);
        }
        else {
            g.drawImage(bgImg, 0, 0, null);
        }
    }
}
