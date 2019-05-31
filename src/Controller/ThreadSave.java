
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
    private ArrayList <Object> save_list = new ArrayList();
    
    //CONSTRUCTOR ================================================================================
    public ThreadSave (int time){
        
        this.save_time = time;
       
    } 

    //SETTERS =====================================================================================
    
    
    public void setArrayList_save(Player player, ArrayList<SpaceIcon> rebels, ArrayList<SpaceIcon> empire, Battlefield battlefield){
      
        //apaga a arraylist
        save_list.clear();
        
        //adiciona os objetos
        save_list.add(player);
        save_list.add(rebels);
        save_list.add(empire);
        save_list.add(battlefield);
         
    }
    
    
    //METHODS =====================================================================================
    
    
    @Override
    public void run() {
      
        while(true){
            
            try {
                arquivoSave1 = new FileOutputStream("img/save.bin");
            } catch (FileNotFoundException ex) {
                 Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                objGravar1 = new ObjectOutputStream(arquivoSave1);
            } catch (IOException ex) {
                 Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                objGravar1.writeObject(save_list);
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                objGravar1.flush();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                objGravar1.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                arquivoSave1.flush();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                arquivoSave1.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                Thread.sleep(save_time*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }
    
    
    
}
