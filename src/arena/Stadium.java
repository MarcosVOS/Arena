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
        this.resourceLoader = new ResourceLoader();
        this.player = new Player(100, 100);
        setupWindow();
        genereatePoints();
        genereateBots();
        startBotMoverTimer();
    }

    private void setupWindow() {
        this.window = new JFrame(NAME);
        this.window.setSize(SIZE_WIDTH, SIZE_HEIGHT);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setIconImage(resourceLoader.getPollo().getImage());
        this.window.setLocationRelativeTo(null);
        this.window.addKeyListener(this);
        this.window.setFocusTraversalKeysEnabled(false);
        this.window.add(this);
        this.window.setVisible(true);
    }

    private void endGameWindow() {
      this.window.setContentPane(new GameOverWindow(this.window, this.SIZE_WIDTH,this.SIZE_HEIGHT));
      this.window.revalidate();

    }

    private void genereatePoints(){
        Random random = new Random();
        int quantity = random.nextInt((maximumNumberOfPoints - minimumNumberOfPoints) + 1) + minimumNumberOfPoints;
        points = new GamePoints[quantity];
        for(int item = 0; item < quantity; item++){
            points[item] = new GamePoints(SIZE_WIDTH,SIZE_HEIGHT);
        }
    }

    private void genereateBots(){
        int quantity = 5;
        bots = new Bot[quantity];
        for(int item = 0; item < quantity; item++){
            bots[item] = new Bot(SIZE_WIDTH, SIZE_HEIGHT, points, player.getAxisX(), player.getAxisY());
        }
    }

    private void startBotMoverTimer() {
        timer = new Timer(1, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int render = 0; render < points.length; render++){
            g.setColor(points[render].getColor());
            g.fillOval(points[render].getAxisX(), points[render].getAxisY(), 10, 10);
        }

        for(int render = 0; render < bots.length; render++){
            g.setColor(Color.GRAY);
            g.fillOval(
                bots[render].getAxisX(), 
                bots[render].getAxisY(),
                bots[render].getCircleSize(),
                bots[render].getCircleSize()
            );
        }

        g.setColor(Color.BLACK);
        g.fillOval(player.getAxisX(), player.getAxisY(), player.getCircleSize(), player.getCircleSize());
    }

    @Override
    public void keyPressed(KeyEvent k) {
        int pressedKey = k.getKeyCode();
        switch (pressedKey) {
            case KeyEvent.VK_UP:
                player.setAxisY(player.getAxisY() - player.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                player.setAxisY(player.getAxisY() + player.getSpeed());
                break;
            case KeyEvent.VK_LEFT:
                player.setAxisX(player.getAxisX() - player.getSpeed() );
                break;
            case KeyEvent.VK_RIGHT:
                player.setAxisX(player.getAxisX() + player.getSpeed() );
                break;
            default:
                break;
        }

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
        Random random = new Random();
        for (Bot bot : bots) {
            int deltaX = random.nextInt(3) - 1;
            int deltaY = random.nextInt(3) - 1;
            bot.setAxisX(bot.getAxisX() + deltaX * bot.getSpeed());
            bot.setAxisY(bot.getAxisY() + deltaY * bot.getSpeed());
            points = bot.consumePoint(points);
            bots = bot.consumeBots(bots);
            System.out.println("Consume Player");
            System.out.println(bot.consumePlayer(player));
            if(bot.consumePlayer(player)){
                endGameWindow();
            }
        }
        repaint();
    }
}
