package Controller;

import View.Environment;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadRepaint implements Runnable {
    
    private Environment environment;
    
    
   //CONSTRUCTOR ======================================================================================= 
    public ThreadRepaint(Environment environment){
       
        this.environment = environment;
        
    }
    
    //METHODS ==========================================================================================
    
    @Override
    public void run(){
        
        //da repaint no view a cada 10 ms
        while(true){
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRepaint.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            environment.repaint();
        }
    
    }
}
