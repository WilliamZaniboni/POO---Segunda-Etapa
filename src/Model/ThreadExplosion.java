
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadExplosion implements Runnable {
    
    private boolean stop_thread;
    private boolean rebelisDead;
    private boolean verific;
    
    //CONSTRUCTOR =========================================================================================================
    
    public ThreadExplosion(){
        
        this.stop_thread = false;
        this.rebelisDead = false;
        this.verific = false;
        
    }
    
    //GETTERS =============================================================================================================

    public boolean isStop_thread() {
        return stop_thread;
    }

    public boolean isRebelisDead() {
        return rebelisDead;
    }

    public boolean isVerific() {
        return verific;
    }
    
    //SETTERS ==============================================================================================================

    public void setStop_thread(boolean stop_thread) {
        this.stop_thread = stop_thread;
    }

    public void setRebelisDead(boolean rebelisDead) {
        this.rebelisDead = rebelisDead;
    }

    public void setVerific(boolean verific) {
        this.verific = verific;
    }
    
    //METHODS ==============================================================================================================
    

    @Override
    public void run() {
        
        //não faz nada caso a nave esteja viva
        while(this.stop_thread == false){
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadExplosion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //caso a nave tenha morrido e não tenha ocorrido a animação de explosão, espera 1000 ms, tempo para desenhar o gif de explosao
        while(this.rebelisDead == true && this.verific == false){
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadExplosion.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               this.setVerific(true);
                
        }
        
    
        
    }
    
    
    
}
