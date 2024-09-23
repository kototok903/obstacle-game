import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class Game extends JPanel implements ActionListener, KeyListener {

    // Constants
    private int gameWidth = 600; // game area
    private int gameHeight = 600; 
    private int menuWidth = gameWidth; // bottom menu
    private int menuHeight = 80; 
    
    private Color pauseColor = new Color(0, 0, 0, 135);
    
    private static Font fontMain = loadFont("./assets/fonts/eurostile.TTF");
    private static Font fontBold = loadFont("./assets/fonts/EurostileExtendedBlack.ttf");
    
    private Sound bgMusic = new Sound("./assets/sounds/music1.wav", 50);
    
    // Game objects
    private Rectangle playerPart1 = new Rectangle(0, 0, 25, 35); 
    private Rectangle playerPart2 = new Rectangle(0, 0, 35, 25); 
    private int playerSpeed = 4; // pixels per frame
    private Rectangle goal = new Rectangle(); 
    private Enemy[] enemies = new Enemy[0]; 
    private BufferedImage finishFlagImg = ImageLoader.loadCompatibleImage("assets/images/finishFlag1.png");
    private BufferedImage heroFaceImg = ImageLoader.loadCompatibleImage("assets/images/heroFace1.png");
    
    // Levels
    private Level[] levels = new Level[] {
        // Level 1
        new Level("assets/images/laminatbg1.jpg", 
            //Color.blue, Color.green, 
            new Color(25, 137, 194), new Color(23, 145, 23), 
            new Enemy[] {
                new VerticalEnemy(200, 300, 25, 25, new Color(217, 140, 17), 5, gameHeight),
                new HorizontalEnemy(200, 400, 25, 25, new Color(217, 140, 17), 5, gameWidth),
                new DiagonalEnemy(100, 500, 20, 20, Color.green.darker(), 8, 6, gameWidth, gameHeight),
                new StalkerEnemy(200, 500, 12, 12, Color.magenta, 3, playerPart1),
                new StalkerEnemy(500, 200, 12, 12, Color.magenta, 4, playerPart1),
                new SpinningEnemy(200, 200, 20, 20, Color.red, 140, 0.12, true),
                //new EightEnemy(400, 300, 20, 20, Color.red.darker(), 150, 0.07),
                new RoseEnemy(350, 350, 20, 20, Color.red.darker(), -3.0/4, 200, 0.08, 8 * Math.PI),
            }
        ),
        
        // Level 2
        new Level("assets/images/modernbgs2.jpg", 
            new Color(43, 150, 205), new Color(32, 132, 32), 
            new Enemy[] {
                new JumpStalkerEnemy(200, 500, 12, 12, Color.white, 12, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(250, 550, 12, 12, Color.white, 13, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(300, 500, 12, 12, Color.white, 8, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(400, 500, 12, 12, Color.white, 11, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(350, 550, 12, 12, Color.white, 6, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(500, 100, 12, 12, Color.white, 9, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(550, 150, 12, 12, Color.white, 10, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(500, 500, 12, 12, Color.white, 13, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(520, 500, 12, 12, Color.white, 7, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(520, 500, 12, 12, Color.white, 6, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(520, 500, 12, 12, Color.white, 4, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(300, 300, 12, 12, Color.white, 6, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(300, 450, 12, 12, Color.white, 7, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(450, 300, 12, 12, Color.white, 7, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(588, 588, 12, 12, Color.white, 3, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(548, 588, 12, 12, Color.white, 2, playerPart1, gameWidth, gameHeight),
            }
        ),
        
        // Level 3
        new Level("assets/images/lightbg1.jpg", 
            new Color(66, 164, 214), new Color(66, 199, 66),
            new Enemy[] {
                new EightEnemy(400, 300, 20, 20, Color.black, 180, 0.07),
                new EightEnemy(200, 300, 20, 20, Color.black, 180, 0.07),
                new InfinityEnemy(400, 300, 20, 20, Color.black, 180, 0.07),
                new InfinityEnemy(200, 300, 20, 20, Color.black, 180, 0.07),
                new EightEnemy(300, 400, 20, 20, Color.black, 180, 0.07, true),
                new EightEnemy(300, 200, 20, 20, Color.black, 180, 0.07, true),
                new InfinityEnemy(300, 400, 20, 20, Color.black, 180, 0.07, true),
                new InfinityEnemy(300, 200, 20, 20, Color.black, 180, 0.07, true),
                new RoseEnemy(-5, 605, 20, 20, Color.black, 4, 190, 0.03, 2 * Math.PI),
                new RoseEnemy(-5, 605, 20, 20, Color.black, 4, 190, 0.03, true, 2 * Math.PI),
                new RoseEnemy(605, -5, 20, 20, Color.black, 4, 190, 0.03, 2 * Math.PI),
                new RoseEnemy(605, -5, 20, 20, Color.black, 4, 190, 0.03, true, 2 * Math.PI),
            }
        ),
        
        // Level 4
        new Level("assets/images/purplebg1.jpg", 
            new Color(43, 150, 205), new Color(32, 132, 32),
            new Enemy[] {
                new DiagonalEnemy(110, 510, 20, 20, new Color(17, 236, 240), 8, 6, gameWidth, gameHeight),
                new DiagonalEnemy(120, 520, 20, 20, new Color(210, 17, 240), 9, 7, gameWidth, gameHeight),
                new DiagonalEnemy(130, 530, 20, 20, new Color(17, 236, 240), 8, 10, gameWidth, gameHeight),
                new DiagonalEnemy(140, 540, 20, 20, new Color(210, 17, 240), 4, 6, gameWidth, gameHeight),
                new DiagonalEnemy(150, 550, 20, 20, new Color(17, 236, 240), 5, 5, gameWidth, gameHeight),
                new DiagonalEnemy(160, 560, 20, 20, new Color(210, 17, 240), 7, 8, gameWidth, gameHeight),
                new DiagonalEnemy(510, 110, 20, 20, new Color(17, 236, 240), 6, 6, gameWidth, gameHeight),
                new DiagonalEnemy(520, 120, 20, 20, new Color(210, 17, 240), 7, 9, gameWidth, gameHeight),
                new DiagonalEnemy(530, 130, 20, 20, new Color(17, 236, 240), 8, 5, gameWidth, gameHeight),
                new DiagonalEnemy(540, 140, 20, 20, new Color(210, 17, 240), 4, 6, gameWidth, gameHeight),
                new DiagonalEnemy(250, 450, 10, 10, new Color(17, 236, 240), 5, 4, gameWidth, gameHeight),
                new DiagonalEnemy(260, 460, 10, 10, new Color(210, 17, 240), 3, 3, gameWidth, gameHeight),
            }
        ),
        
        // Level 5
        new Level("assets/images/covers2.jpg", 
            new Color(43, 150, 205), new Color(32, 132, 32),
            new Enemy[] {
                //new RoseEnemy(300, 300, 20, 20, Color.red, 9.0/2, 250, 0.04),
                //new RoseEnemy(300, 300, 20, 20, Color.red, 9.0/2, 250, 0.04, true),
                new SpinningEnemy(300, 300, 20, 20, new Color(166, 116, 252), 100, 0.08),
                new SpinningEnemy(300, 300, 12, 12, new Color(166, 116, 252), 125, 0.08),
                new SpinningEnemy(300, 300, 20, 20, new Color(166, 116, 252), 150, 0.08),
                new SpinningEnemy(300, 300, 12, 12, new Color(166, 116, 252), 175, 0.08),
                new SpinningEnemy(300, 300, 20, 20, new Color(166, 116, 252), 200, 0.08),
                new SpinningEnemy(300, 300, 20, 20, new Color(166, 116, 252), 150, 0.08, -Math.PI),
                new SpinningEnemy(300, 300, 12, 12, new Color(166, 116, 252), 125, 0.08, -Math.PI),
                new VerticalEnemy(150, 10, 20, 80, new Color(230, 230, 230), 5, gameHeight),
                new VerticalEnemy(430, 510, 20, 80, new Color(20, 20, 20), 5, gameHeight),
                new HorizontalEnemy(10, 150, 80, 20, new Color(230, 230, 230), 5, gameWidth),
                new HorizontalEnemy(510, 430, 80, 20, new Color(20, 20, 20), 5, gameWidth),
                new JumpStalkerEnemy(150, 500, 12, 12, new Color(121, 109, 252), 7, playerPart1, gameWidth, gameHeight),
                new JumpStalkerEnemy(500, 150, 12, 12, new Color(121, 109, 252), 6, playerPart1, gameWidth, gameHeight),
            }
        ),
    };
    
    private boolean up, down, left, right; // booleans that track which keys are currently pressed
    private Timer timer; // the update timer
    private boolean isPaused = true;
    private char pauseState = 's'; // s - start, w - win, l - lose, p - regular pause
    private int levelInd = 0; // current level index
    private boolean isSoundOn = true;
    
    // GUI elements
    private static JLabel dialogLabel;
    private static JFrame frame;
    private static JPanel menu;
    private static JDialog dialog;
    private static JLabel levelLabel = new JLabel("Level: ");
    private static JButton[] levelButtons = new JButton[5];
    private static JLabel soundLabel = new JLabel("      Sound: ");
    private static JButton soundButton = new JButton("On");
   
    /**
     * Starts the Obstacle game
     */
    public static void main(String[] args) {
        frame = new JFrame();
       
        dialog = new JDialog(frame, "Status");
        dialogLabel = new JLabel("");
        dialogLabel.setHorizontalAlignment(JLabel.CENTER);
        dialog.add(dialogLabel);
        dialog.setBounds(125, 125, 100, 70);
        dialog.setVisible(false);
        dialog.setLocationRelativeTo(null);
        
        frame.setTitle("Obstacle Game by Stas");
        frame.setLayout(new BorderLayout());
        
        Game game = new Game();
        
        menu = new JPanel();
        menu.setLayout(new FlowLayout());
        
        levelLabel.setFont(fontBold.deriveFont(12f));
        menu.add(levelLabel);
        
        for (int i = 0; i < levelButtons.length; i++) {
            levelButtons[i] = new JButton(i + 1 + "");
            levelButtons[i].setFont(fontBold.deriveFont(12f));
            levelButtons[i].setActionCommand("" + i);
            levelButtons[i].addActionListener(game);
            levelButtons[i].addKeyListener(game);
            levelButtons[i].setFocusable(false);
            menu.add(levelButtons[i]);
        }
        
        soundLabel.setFont(fontBold.deriveFont(12f));
        menu.add(soundLabel);
        
        soundButton.setFont(fontBold.deriveFont(12f));
        soundButton.setActionCommand("s");
        soundButton.addActionListener(game);
        soundButton.addKeyListener(game);
        soundButton.setFocusable(false);
        menu.add(soundButton);
        
        frame.add(game, BorderLayout.CENTER);
        frame.add(menu, BorderLayout.PAGE_END);
        
        game.addKeyListener(game);
        menu.addKeyListener(game);
        frame.addKeyListener(game);
       
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        game.setUpGame();
        //game.enterFullScreen();
    }
   
    /**
     * Constructor for the game panel
     */
    public Game() {
        setPreferredSize(new Dimension(gameWidth, gameHeight));
    }
    
    /**
     * Method that is called by the timer 30 times per second (roughly)
     * Most games go through states - updating objects, then drawing them
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            if (e.getActionCommand().charAt(0) == 's') {
                isSoundOn = !isSoundOn;
                if (isSoundOn) {
                    bgMusic.loop();
                    soundButton.setText("On");
                }
                else {
                    bgMusic.stop();
                    soundButton.setText("Off");
                }
                soundButton.setSelected(false);
                soundButton.setFocusPainted(false);
            }
            else {
                JButton b = (JButton) e.getSource();
                b.setSelected(false);
                b.setFocusPainted(false);
                levelInd = Integer.parseInt(e.getActionCommand());
                setUpGame();
            }
        }
        update();
        repaint();
    }
   
    /**
     * Called every time a key is pressed
     * Stores the down state for use in the update method
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                isPaused = !isPaused;
                pauseState = 'p';
                break;
        }
    }
   
    /**
     * Called every time a key is released
     * Stores the down state for use in the update method
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }
    
    /**
     * Called every time a key is typed
     */
    public void keyTyped(KeyEvent e) { }
    
    /**
     * Sets the initial state of the game
     * Could be modified to allow for multiple levels
     */
    public void setUpGame() {
        if(timer != null) 
            timer.stop();
   
        timer = new Timer(1000 / 30, this); //roughly 30 frames per second
        timer.start();
       
        up = down = left = right = false;
   
        int pX = levels[levelInd].getPlayerX();
        int pY = levels[levelInd].getPlayerY();
        playerPart1.x = pX + 5;
        playerPart1.y = pY;
        playerPart2.x = pX;
        playerPart2.y = pY + 5;
        
        goal = new Rectangle(levels[levelInd].getGoalX(), 
                levels[levelInd].getGoalY(), 35, 35);
        
        enemies = levels[levelInd].getEnemiesClone();
        
        if (isSoundOn) 
            bgMusic.loop();
    }
    
    /**
     * Enters full screen mode
     */
    private void enterFullScreen() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphicsEnvironment.getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(frame);
            frame.validate();
        }
    }
   
    /**
     * The update method does 5 things:
     * 1 - it has the player move based on what key is currently being pressed
     * 2 - it prevents the player from leaving the screen
     * 3 - it checks if the player has reached the goal, and if so congratulates them and restarts the game
     * 4 - it checks if any of the Enemy objects are touching the player, and if so notifies the player of their defeat and restarts the game
     * 5 - it tells each of the Enemy objects to update()
     */
    public void update() {
        if (isPaused) {
            return;
        }
        
        if (up) {
            playerPart1.y -= playerSpeed;
            playerPart2.y -= playerSpeed;
        }
        if (down) {
            playerPart1.y += playerSpeed;
            playerPart2.y += playerSpeed;
        }
        if (left) {
            playerPart1.x -= playerSpeed;
            playerPart2.x -= playerSpeed;
        }
        if (right) {
            playerPart1.x += playerSpeed;
            playerPart2.x += playerSpeed;
        }
        
        if (playerPart2.x < 0) {
            playerPart1.x = 5;
            playerPart2.x = 0;
        }
        else if (playerPart2.x + playerPart2.width > gameWidth) {
            playerPart1.x = gameWidth - playerPart1.width - 5;
            playerPart2.x = gameWidth - playerPart2.width;
        }
        
        if (playerPart1.y < 0) {
            playerPart1.y = 0;
            playerPart2.y = 5;
        }
        else if (playerPart1.y + playerPart1.height > gameHeight) {
            playerPart1.y = gameHeight - playerPart1.height;
            playerPart2.y = gameHeight - playerPart2.height - 5;
        }
        
        if(playerPart1.intersects(goal) || playerPart2.intersects(goal))
            onWin();
        
        for (Enemy enemy : enemies) {
            if (enemy == null)
                continue;
       
            if (enemy.intersects(playerPart1) || enemy.intersects(playerPart2)) 
                onLose();
           
            enemy.move();
        }
    }
   
    /**
     * The paint method does 4 things
     * 1 - it draws a white background
     * 2 - it draws the player in blue
     * 3 - it draws the goal in green
     * 4 - it draws all the Enemy objects
     */
    public void paint(Graphics g) {
        levels[levelInd].drawBackground(g);
   
        for (Enemy enemy : enemies) {
            if(enemy == null)
                continue;
            
            enemy.drawBackground(g);
        }
        
        g.setColor(levels[levelInd].getPlayerColor());
        g.fillRect(playerPart1.x, playerPart1.y, playerPart1.width, playerPart1.height);
        g.fillRect(playerPart2.x, playerPart2.y, playerPart2.width, playerPart2.height);
        g.drawImage(heroFaceImg, playerPart2.x + 5, playerPart1.y + 5, null);
        
        g.setColor(levels[levelInd].getGoalColor());
        g.fillRect(goal.x, goal.y, goal.width, goal.height);
        g.drawImage(finishFlagImg, goal.x - 2, goal.y + goal.height / 2 - 57 + 3, null);
        
        for (Enemy enemy : enemies) {
            if(enemy == null)
                continue;
            
            enemy.draw(g);
        }
        
        if (isPaused) {
            g.setColor(pauseColor);
            g.fillRect(0, 0, gameWidth, gameHeight);
            
            g.setColor(Color.white);
            
            switch (pauseState) {
                case 's':
                    g.setFont(fontMain.deriveFont(80f));
                    g.drawString("Obstacle", 50, 300);
                    g.setFont(fontMain.deriveFont(40f));
                    g.drawString("Press SPACE to Start", 50, 350);
                    g.drawString("Movement — ARROW KEYS", 50, 400);
                    g.drawString("Pause — SPACE", 50, 450);
                    break;
                case 'w':
                    g.setFont(fontMain.deriveFont(80f));
                    g.drawString("You Won!", 50, 300);
                    g.setFont(fontMain.deriveFont(40f));
                    g.drawString("Press SPACE to continue", 50, 350);
                    break;
                case 'l':
                    g.setFont(fontMain.deriveFont(80f));
                    g.drawString("You Lost", 50, 300);
                    g.setFont(fontMain.deriveFont(40f));
                    g.drawString("Press SPACE to continue", 50, 350);
                    //g.drawString("Game will automatically continue in 2 sec", 50, 400);
                    break;
                default:
                    g.setFont(fontMain.deriveFont(80f));
                    g.drawString("PAUSED", 50, 300);
                    g.setFont(fontMain.deriveFont(40f));
                    g.drawString("Press SPACE to continue", 50, 350);
            }
        }
    }
   
    /**
     * When player wins the game
     */
    private void onWin() {
        //createDialog("You Won!", 1500);
        levelInd = (levelInd + 1) % levels.length;
        
        pauseState = 'w';
        isPaused = true;
        
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } 
            catch (Exception e) {
                System.err.println("Sleep thread failed.");
            }
            
            if (pauseState == 'w') {
                isPaused = false;
                pauseState = 'p';
            }
        });
        thread.start();
        
        setUpGame();
    }
   
    /**
     * When player loses the game
     */
    private void onLose() {
        //createDialog("You Lost", 1500);
        
        pauseState = 'l';
        isPaused = true;
        
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } 
            catch (Exception e) {
                System.err.println("Sleep thread failed.");
            }
            
            if (pauseState == 'l') {
                isPaused = false;
                pauseState = 'p';
            }
        });
        thread.start();
        
        setUpGame();
    }
   
    /** 
     * Sets visible a Pseudo-dialog that removes itself after a fixed time interval
     * Uses a thread to not block the rest of the program
     *
     * @param: message: String -> The message that will appear on the dialog
     * @param: delay: int -> How long (in milliseconds) that Dialog is visible
     */
    private void createDialog(String message, int delay) {
        dialogLabel.setText(message);
        dialog.setVisible(true);
        frame.requestFocus();
        
        Thread thread = new Thread(() -> {
            try {
                // Show pop up for [delay] milliseconds
                Thread.sleep(delay);
            } 
            catch (Exception e) {
                System.err.println("Dialog thread failed.");
                dialog.setVisible(false);
                frame.requestFocus();
            }
            
            // Close the pop-up
            dialog.setVisible(false);
            frame.requestFocus();
        });
        
        thread.start();
    }
    
    /**
     * Loads font
     */
    private static Font loadFont(String filepath) {
        try {
            InputStream stream = Game.class.getResourceAsStream(filepath);
            return Font.createFont(
                    Font.TRUETYPE_FONT,
                    stream);
        }
        catch (Exception e) {
            System.err.println("Unable to load font:\n\t" + filepath);
        }
        return new Font("Courier", Font.PLAIN, 20); // default font
    }
}