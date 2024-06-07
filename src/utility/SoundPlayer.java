package utility;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
    
    private Clip clip; 

    public void playSound(File sound){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
           System.out.println(e);
        }
        return;
    }

    public void stopSound(){
        if(clip != null){
            clip.stop();
            clip.close();
        }
    }
}
