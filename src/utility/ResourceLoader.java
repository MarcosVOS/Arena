package utility;
import javax.swing.ImageIcon;
import java.io.File;

public class ResourceLoader {
    private ImageIcon pollo;
    private ImageIcon loser;
    
    private File loserSound;

    public ResourceLoader(){
        loadImages();
        loadSounds();
    }

    private void loadImages(){
        try {
            this.pollo = new ImageIcon(getClass().getResource("/resources/images/polloIcon.png"));
            this.loser = new ImageIcon(getClass().getResource("/resources/images/loser.jpeg"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unable to load images");
        }   
    }

    private void loadSounds(){
        try{
            this.loserSound = new File(getClass().getResource("/resources/sounds/loser-sound.wav").getFile());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Unable to load sounds");
        }
    }

    public ImageIcon getPollo(){
        return this.pollo;
    }

    public ImageIcon getLoser(){
        return this.loser;
    }

    public File getLoserSound(){
        return this.loserSound;
    }
}
