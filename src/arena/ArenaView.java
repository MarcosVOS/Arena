package arena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import characters.Bot;
import characters.Player;
import points.GamePoints;

public class ArenaView extends JPanel implements KeyListener, ActionListener {
    
    private Stadium window; 
    private Player player;
    private GamePoints[] points;   
    private Bot[] bots;  
    private Timer timer;

    private final int MINIMUM_NUMBER_OF_POINTS = 40; 
    private final int MAXIMUM_NUMBER_OF_POINTS = 60; 

    public ArenaView(Stadium stadium){
        this.window = stadium;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        stadium.getWindow().add(this);
        this.player = new Player();
        genereatePoints();
        genereateBots();
        startBotMoverTimer();
    }

    private void genereatePoints(){
        Random random = new Random();
        int quantity = random.nextInt((MAXIMUM_NUMBER_OF_POINTS - MINIMUM_NUMBER_OF_POINTS) + 1) + MINIMUM_NUMBER_OF_POINTS;
        points = new GamePoints[quantity];
        for(int item = 0; item < quantity; item++){
            points[item] = new GamePoints(this.window);
        }
    }

    private void endGameWindow() {
        stopGame();
        this.window.getWindow().setContentPane(new GameOverView(this.window));
        this.window.getWindow().revalidate();
        this.window.getWindow().requestFocusInWindow();  
    }
    
    private void winGameWindow() {
        stopGame();
        this.window.getWindow().setContentPane(new WinGameView(this.window));
        this.window.getWindow().revalidate();
        this.window.getWindow().requestFocusInWindow();  
    }

    public void reStartGame() {
        this.player = new Player();
        genereatePoints();
        genereateBots();
        startBotMoverTimer();
    
        this.window.getWindow().getContentPane().removeAll();
        this.window.getWindow().setContentPane(this);
        
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.window.getWindow().revalidate();
        this.window.getWindow().repaint();
    }

    private void genereateBots(){
        int quantity = 3;
        bots = new Bot[quantity];
        for(int item = 0; item < quantity; item++){
            bots[item] = new Bot(this.window);
        }
    }

    private void startBotMoverTimer() {
        timer = new Timer(1, this);
        timer.start();
    }

    public void stopGame(){
        timer.stop();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int render = 0; render < points.length; render++) {
            g.setColor(points[render].getColor());
            g.fillOval(points[render].getAxisX() - 5, points[render].getAxisY() - 5, 10, 10);
        }

        for (int render = 0; render < bots.length; render++) {
            g.setColor(Color.GRAY);
            g.fillOval(
                    bots[render].getAxisX() - bots[render].getCircleSize() / 2,
                    bots[render].getAxisY() - bots[render].getCircleSize() / 2,
                    bots[render].getCircleSize(),
                    bots[render].getCircleSize());
        }

        g.setColor(Color.BLACK);
        g.fillOval(player.getAxisX() - player.getCircleSize() / 2, player.getAxisY() - player.getCircleSize() / 2,
                player.getCircleSize(), player.getCircleSize());
    }

    @Override
    public void keyPressed(KeyEvent k) {
        player.setLastKeyPressed(k);
        player.move();
        points = player.consumePoint(points);
        bots = player.consumeBots(bots);
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

    @Override
    public void keyTyped(KeyEvent k) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (player.winGame(points, bots)) {
            timer.stop();
            winGameWindow();
        }

        if (player.getTheGameStarted()) {
            for (Bot bot : bots) {
                bot.move();
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
}
