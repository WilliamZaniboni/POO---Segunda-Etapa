
package Model;

import Controller.FightController;
import View.Environment;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class ThreadTimer implements Runnable {

   private JLabel jlabel;
   private FightController fightController;
   private Environment environment;
   private Font fonte_digital;
   private int flag=0;
   
   
 
    //CONSTRUCTOR =============================================================================
   
    public ThreadTimer(JLabel label, FightController fightController, Environment environment) {
        this.jlabel = label;
        this.fightController = fightController;
        this.environment = environment;
    }
    
    //SETTERS =================================================================================
    
    public void set_flag(int x){
        this.flag = x;
    }
    
    //GETTERS =================================================================================
    
    public int get_flag(){
        return this.flag;
    }

    //METHODS ================================================================================
    
   @Override
    public void run(){
        
        int time = Constants.TIME;
        
        //adicionando a fonte do texto
        try {
                Font fonte_digital = Font.createFont(Font.TRUETYPE_FONT, new File("img/DS-DIGI.ttf")).deriveFont(Font.PLAIN, 47);
                jlabel.setFont(fonte_digital);
            } catch (FontFormatException ex) {
                Logger.getLogger(ThreadTimer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ThreadTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        while(true){
            
            //verifica se a flag = 0 e decrementa o contador
             while (this.flag==0){
            
                  if(time<10 && time>0){
                     jlabel.setForeground(Color.red);
                     jlabel.setText("00:0"+time);       
                    }
                  else if(time<=0){
                     //quando o tempo acaba, começa um novo ciclo 
                     fightController.cycle_routine();
                     environment.getjLabel9().setText(""+fightController.getCycle()+"º");
                     this.set_flag(1);
                     jlabel.setText("00:00");
                    }
                  else{
                     jlabel.setForeground(Color.white);
                     jlabel.setText("00:"+time); 
                   }
             
                  try {
                     Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                          Logger.getLogger(ThreadTimer.class.getName()).log(Level.SEVERE, null, ex);
                    }
             
                   time--;  
                }
        
            // verifica se a flag é 1 e reseta o contador
            if(this.flag ==1){
                 this.set_flag(0);
                 time = Constants.TIME;
            }
        }
        
    }
    
    
}
