
package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class OpenSave {
    
    private FileInputStream arquivoLeitura1;      
    private ObjectInputStream objLeitura1;
    
    private ArrayList <Object> save_list = new ArrayList();
    
    private Player player;
    private ArrayList <SpaceIcon> rebels = new ArrayList();
    private ArrayList <SpaceIcon> empire = new ArrayList();
    private Battlefield battlefield;
    
    private boolean arquivo_encontrado;
    
    //CONSTRUCTOR =======================================================================================================================
    
    public OpenSave(){
        
        this.arquivo_encontrado = true;
        
        try {
            arquivoLeitura1 = new FileInputStream("img/save.bin");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Será iniciado um novo jogo", "Arquivo de save não encontrado", JOptionPane.ERROR_MESSAGE, null);
            this.arquivo_encontrado = false;
            
            //sai do construtor
            return;
        }
        
        try {
            objLeitura1 = new ObjectInputStream(arquivoLeitura1);
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.save_list =  (ArrayList<Object>) objLeitura1.readObject();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            objLeitura1.close();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            arquivoLeitura1.close();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.player = (Player) save_list.get(0);
        this.rebels = (ArrayList<SpaceIcon>) save_list.get(1);
        this.empire = (ArrayList<SpaceIcon>) save_list.get(2);
        this.battlefield = (Battlefield) save_list.get(3);
        
    }
    
    //GETTERS ================================================================================================
    
    public Player getPlayer() {
        return player;
    }

    public ArrayList<SpaceIcon> getRebels() {
        return rebels;
    }

    public ArrayList<SpaceIcon> getEmpire() {
        return empire;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public boolean isArquivo_encontrado() {
        return arquivo_encontrado;
    }
    
    
}
