package utility;

import javax.swing.ImageIcon;

public class ResourceLoader {
    private ImageIcon pollo;

    public ResourceLoader(){
        loadImages();
    }

    private void loadImages(){
        try {
            this.pollo = new ImageIcon(getClass().getResource("/resources/polloIcon.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unable to load images");
        }   
    }

    public ImageIcon getPollo(){
        return this.pollo;
    }
}
