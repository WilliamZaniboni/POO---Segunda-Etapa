
package Model;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMove implements Runnable {
    
   private int y_position;
   private  SpaceIcon inimigo;
   
 

    public ThreadMove(int y, SpaceIcon inimigo) {
        
        this.y_position=y;
        this.inimigo = inimigo;
        
    }
    
    public int getY_position() {
        return y_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }
    
   @Override
    public void run(){
        
        int i = y_position;
        
        while(true){
           
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMove.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            i++;
            
            this.setY_position(i);
            
            System.out.println("A posição y é:"+i);
        }
        
    }

    
}
