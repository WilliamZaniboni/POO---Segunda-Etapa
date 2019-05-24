
package Model;


import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMove implements Runnable {
    
   private int x_pixel_position;
   
    public ThreadMove(int x) {
        
        this.x_pixel_position=x;
        
    }
    
    public int getX_position() {
        return x_pixel_position;
    }

    public void setX_position(int x_position) {
        this.x_pixel_position = x_position;
    }
    
   @Override
    public void run(){
        
        int i = x_pixel_position;
        
        while(true){
           
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMove.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            i=i-Constants.VELOCIDADE;
            
            this.setX_position(i);
            
            System.out.println("A posição x é:"+i);
        }
        
    }

    
}
