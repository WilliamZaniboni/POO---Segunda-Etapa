
package Controller;

import Model.Battlefield;
import Model.Player;
import Model.SpaceIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSave implements Runnable {
    
    private int save_time;
    private FileOutputStream arquivoSave1;
    private ObjectOutputStream objGravar1;
    private FileOutputStream arquivoSave2;
    private ObjectOutputStream objGravar2;
    private FileOutputStream arquivoSave3;
    private ObjectOutputStream objGravar3;
    private FileOutputStream arquivoSave4;
    private ObjectOutputStream objGravar4;
    
    
    private Player player;
    private ArrayList <SpaceIcon> rebels = new ArrayList();
    private ArrayList <SpaceIcon> empire = new ArrayList();
    private Battlefield battlefield;
    
    //CONSTRUCTOR ================================================================================
    public ThreadSave (int time){
        
        this.save_time = time;
       
    } 

    //SETTERS =====================================================================================
    
    
    public void setSave_time(int save_time) {
        
        this.save_time = save_time;
        
    }

    public void setPlayer(Player player) {
        
        this.player = player;
        
    }

    public void setRebels(ArrayList<SpaceIcon> rebels) {
        this.rebels = rebels;
    }

    public void setEmpire(ArrayList<SpaceIcon> empire) {
        this.empire = empire;
    }

    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
    }
    
    
    
    
    //METHODS =====================================================================================
    
    
    @Override
    public void run() {
      
        while(true){
            
            try {
                Thread.sleep(save_time*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("SALVO");
            
            try {
                arquivoSave1 = new FileOutputStream("img/saves/player_save.txt");
                arquivoSave2 = new FileOutputStream("img/saves/rebels_save.txt");
                arquivoSave3 = new FileOutputStream("img/saves/empire_save.txt");
                arquivoSave4 = new FileOutputStream("img/saves/battlefield_save.txt");
                
            } catch (FileNotFoundException ex) {
                 Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                objGravar1 = new ObjectOutputStream(arquivoSave1);
                objGravar2 = new ObjectOutputStream(arquivoSave2);
                objGravar3 = new ObjectOutputStream(arquivoSave3);
                objGravar4 = new ObjectOutputStream(arquivoSave4);
            } catch (IOException ex) {
                 Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                objGravar1.writeObject(player);
                objGravar2.writeObject(rebels);
                objGravar3.writeObject(empire);
                objGravar4.writeObject(battlefield);
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                objGravar1.flush();
                objGravar2.flush();
                objGravar3.flush();
                objGravar4.flush();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                objGravar1.close();
                objGravar2.close();
                objGravar3.close();
                objGravar4.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                arquivoSave1.flush();
                arquivoSave2.flush();
                arquivoSave3.flush();
                arquivoSave4.flush();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                arquivoSave1.close();
                arquivoSave2.close();
                arquivoSave3.close();
                arquivoSave4.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    
    
}
