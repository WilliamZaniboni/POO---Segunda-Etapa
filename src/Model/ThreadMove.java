
package Model;


import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMove implements Runnable {
    
   private int x_pixel_position;
   private int stop_flag;
   private boolean EmpireisDead = false;
   private boolean verific = false;
   
    //CONSTRUCTOR ======================================================================================================
    public ThreadMove(int x) {
        this.x_pixel_position=x;
        this.stop_flag = 0; 
    }
    
    
    //Getters ===========================================================================================================
    public int getX_position() {
        return x_pixel_position;
    }
    
    public int getStop_flag() {
        return stop_flag;
    }
    
    public int getX_pixel_position() {
        return x_pixel_position;
    }
    
    public boolean isEmpireisDead() {
        return EmpireisDead;
    }
    
    public boolean isVerific() {
        return verific;
    }
    
   //Setters ===========================================================================================================================
    public void setX_position(int x_position) {
        this.x_pixel_position = x_position;
    }

    public void setStop_flag(int stop_flag) {
        this.stop_flag = stop_flag;
    }

    public void setEmpireisDead(boolean EmpireisDead) {
        this.EmpireisDead = EmpireisDead;
    }

    public void setVerific(boolean verific) {
        this.verific = verific;
    }
    
   //METHODS ======================================================================================================================== 
    
   @Override
    public void run(){
        
        int i = x_pixel_position;
        
        //movimenta a nave enquanto for permitido
        while(this.stop_flag == 0){
            
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMove.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            i=i-Constants.VELOCIDADE;
            
            this.setX_position(i);
            
        }
        
        //caso a nave tenha morrido e não tenha ocorrido a animação de explosão, espera 1000 ms, tempo para desenhar o gif de explosao
        while(this.EmpireisDead == true && this.verific == false){
            
               
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadMove.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                this.setVerific(true);
                
            }
        
    }

    
}
