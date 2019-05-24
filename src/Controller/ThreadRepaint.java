package Controller;

import View.Environment;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ThreadRepaint implements Runnable {
    
    private Environment environment;
    
    public ThreadRepaint(Environment environment){
        
        this.environment = environment;
        
    }
    
    @Override
    public void run(){
        
        while(true){
           
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRepaint.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            environment.repaint();
        }
    
    }
}
