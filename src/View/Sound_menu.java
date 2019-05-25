package View;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


public class Sound_menu implements Runnable{
    
    URL music_menu = Sound_menu.class.getResource("menu_music.wav");
    AudioClip Music_menu = Applet.newAudioClip(music_menu);
    
    URL music_game = Sound_menu.class.getResource("game_music.wav");
    AudioClip Music_game = Applet.newAudioClip(music_game);
   

    public void play_Music_menu(){
         this.Music_menu.loop();
    }
    
    public void stop_Music_menu(){
        this.Music_menu.stop();
    }
    
    public void play_Music_game(){
         this.Music_game.loop();
    }
    
    public void stop_Music_game(){
        this.Music_game.stop();
    }
    
    @Override
    public void run() {
        
    }
    
    
    
    
}
