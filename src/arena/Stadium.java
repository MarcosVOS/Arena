package arena;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import characters.Bot;
import characters.Player;
import points.GamePoints;
import utility.ResourceLoader;

public class Stadium extends JPanel implements KeyListener, ActionListener {

    // ResourceLoader
    private final int SIZE_HEIGHT = 720;
    private final int SIZE_WIDTH = 1280;
    private JFrame window;
    private final String NAME = "Arena";
    private ResourceLoader resourceLoader;
    private Player player;
    private final int minimumNumberOfPoints = 40; 
    private final int maximumNumberOfPoints = 60; 
    private GamePoints[] points;   
    private Bot[] bots;  
    private Timer timer;

    public Stadium() {
        this.window = new JFrame(NAME);
        this.resourceLoader = new ResourceLoader();
        this.player = new Player();
        setupWindow();
        genereatePoints();
        genereateBots();
        startBotMoverTimer();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void setupWindow() {
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setIconImage(resourceLoader.getPollo().getImage());
        this.window.setLocationRelativeTo(null);
        this.window.setFocusTraversalKeysEnabled(false);
        this.window.add(this);
        this.window.getContentPane().setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
        this.window.pack();
        this.window.setLocationRelativeTo(null); 
        this.window.setVisible(true);
    }

    private void endGameWindow() {
        stopGame();
        this.window.setContentPane(new GameOverView(this));
        this.window.revalidate();
        this.window.requestFocusInWindow();  
    }
    
    private void winGameWindow() {
        stopGame();
        this.window.setContentPane(new WinGameView(this));
        this.window.revalidate();
        this.window.requestFocusInWindow();  
    }

    public void reStartGame() {
        this.player = new Player();
        genereatePoints();
        genereateBots();
        startBotMoverTimer();
    
        this.window.getContentPane().removeAll();
        this.window.setContentPane(this);
        
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.window.revalidate();
        this.window.repaint();
    }

    public void stopGame(){
        timer.stop();
    }

    public JFrame getWindow(){
        return this.window;
    }

    public int getWidth(){
        return this.SIZE_WIDTH;
    }

    public int getheight(){
        return this.SIZE_HEIGHT;
    }

    public ResourceLoader getResourceLoader(){
        return this.resourceLoader;
    }

    private void genereatePoints(){
        Random random = new Random();
        int quantity = random.nextInt((maximumNumberOfPoints - minimumNumberOfPoints) + 1) + minimumNumberOfPoints;
        points = new GamePoints[quantity];
        for(int item = 0; item < quantity; item++){
            points[item] = new GamePoints(this);
        }
    }

    private void genereateBots(){
        int quantity = 3;
        bots = new Bot[quantity];
        for(int item = 0; item < quantity; item++){
            bots[item] = new Bot(this);
        }
    }

    private void startBotMoverTimer() {
        timer = new Timer(1, this);
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        for(int render = 0; render < points.length; render++){
            g.setColor(points[render].getColor());
            g.fillOval(points[render].getAxisX() - 5, points[render].getAxisY() - 5, 10, 10);
        }
    
        for(int render = 0; render < bots.length; render++){
            g.setColor(Color.GRAY);
            g.fillOval(
                bots[render].getAxisX() - bots[render].getCircleSize() / 2, 
                bots[render].getAxisY() - bots[render].getCircleSize() / 2,
                bots[render].getCircleSize(),
                bots[render].getCircleSize()
            );
        }
    
        g.setColor(Color.BLACK);
        g.fillOval(player.getAxisX() - player.getCircleSize() / 2, player.getAxisY() - player.getCircleSize() / 2, player.getCircleSize(), player.getCircleSize());
        // g.setColor(Color.GREEN);
        // g.fillOval(player.getAxisX() - 2, player.getAxisY() - 2, 4, 4);
    }

    @Override
    public void keyPressed(KeyEvent k) {
       
        player.movePlayer(k);
        points = player.consumePoint(points);
        bots = player.consumeBots(bots);
        // repaint();
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

    @Override
    public void keyTyped(KeyEvent k) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(player.winGame(points, bots)){
            timer.stop();
            winGameWindow();
        }

        if(player.getTheGameStarted()){
            Random random = new Random();
            for (Bot bot : bots) {
                int direction = random.nextInt(2); 
                int deltaX = 0, deltaY = 0;
                
                if (direction == 0) {
                    deltaX = random.nextInt(3) - 1;
                } else {
                    deltaY = random.nextInt(3) - 1; 
                }
        
                bot.setAxisX(bot.getAxisX() + deltaX * bot.getSpeed() / 2); 
                bot.setAxisY(bot.getAxisY() + deltaY * bot.getSpeed() / 2);
                points = bot.consumePoint(points);
                bots = bot.consumeBots(bots);
                if (bot.consumePlayer(player)) {
                    stopGame();
                    endGameWindow();
                }
            }
            repaint();
        }
    }

    public static void main(String[] args) {
        new Stadium(); 
    }
}
