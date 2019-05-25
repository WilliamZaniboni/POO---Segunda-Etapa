
package View;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sound_explosion implements Runnable{
    
    URL music = Sound_explosion.class.getResource("explosao_som.wav");
    AudioClip Music = Applet.newAudioClip(music);
   

    public void play_Music(){
         this.Music.play();
    }
    
    
    @Override
    public void run() {
        
    }
    
    
    
    
}
