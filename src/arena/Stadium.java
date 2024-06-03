package arena;

import javax.swing.JFrame;
import javax.swing.JPanel;

import characters.Player;
import utility.ResourceLoader;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.Random;
import points.GamePoints;

public class Stadium extends JPanel implements KeyListener {

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

    public Stadium() {
        this.resourceLoader = new ResourceLoader();
        this.player = new Player(100, 100);
        setupWindow();
        genereate();
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

    private void genereate(){
        Random random = new Random();
        int quantity = random.nextInt((maximumNumberOfPoints - minimumNumberOfPoints) + 1) + minimumNumberOfPoints;
        points = new GamePoints[quantity];
        for(int item = 0; item < quantity; item++){
            points[item] = new GamePoints(SIZE_WIDTH,SIZE_HEIGHT);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int render = 0; render < points.length; render++){
            g.setColor(points[render].getColor());
            g.fillOval(points[render].getAxisX(), points[render].getAxisY(), 10, 10);
        }

        g.setColor(Color.BLACK);
        g.fillOval(player.getAxisX(), player.getAxisY(), 30, 30);

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
                player.setAxisX(player.getAxisX() -player.getSpeed() );
                break;
            case KeyEvent.VK_RIGHT:
                player.setAxisX(player.getAxisX() + player.getSpeed() );
                break;
            default:
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

    @Override
    public void keyTyped(KeyEvent k) {
    }
}
