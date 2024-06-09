package arena;

import javax.swing.JFrame;
import java.awt.Dimension;
import utility.ResourceLoader;

public class Stadium {

    private final int SIZE_HEIGHT = 720;
    private final int SIZE_WIDTH = 1280;
    private JFrame window;
    private final String NAME = "Arena";
    private ResourceLoader resourceLoader;
    private ArenaView arenaView;

    public Stadium() {
        this.window = new JFrame(NAME);
        this.resourceLoader = new ResourceLoader();
        setupWindow();
        this.arenaView = new ArenaView(this);
    }

    private void setupWindow() {
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setIconImage(resourceLoader.getPollo().getImage());
        this.window.setLocationRelativeTo(null);
        this.window.setFocusTraversalKeysEnabled(false);
        this.window.getContentPane().setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
        this.window.pack();
        this.window.setLocationRelativeTo(null); 
        this.window.setVisible(true);
    }

    public JFrame getWindow(){
        return this.window;
    }

    public ArenaView getArenaView(){
        return this.arenaView;
    }

    public int getWidth(){
        return this.SIZE_WIDTH;
    }

    public int getHeight(){
        return this.SIZE_HEIGHT;
    }

    public ResourceLoader getResourceLoader(){
        return this.resourceLoader;
    }
    public static void main(String[] args) {
        new Stadium(); 
    }
}
