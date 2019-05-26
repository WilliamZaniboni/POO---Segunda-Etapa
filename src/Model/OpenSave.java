
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
    private FileInputStream arquivoLeitura2;      
    private ObjectInputStream objLeitura2;
    private FileInputStream arquivoLeitura3;      
    private ObjectInputStream objLeitura3;
    private FileInputStream arquivoLeitura4;      
    private ObjectInputStream objLeitura4;
    
    private Player player;
    private ArrayList <SpaceIcon> rebels = new ArrayList();
    private ArrayList <SpaceIcon> empire = new ArrayList();
    private Battlefield battlefield;
    
    
    public OpenSave(){
        
        try {
            arquivoLeitura1 = new FileInputStream("img/saves/player_save.txt");
            arquivoLeitura2 = new FileInputStream("img/saves/rebels_save.txt");
            arquivoLeitura3 = new FileInputStream("img/saves/empire_save.txt");
            arquivoLeitura4 = new FileInputStream("img/saves/battlefield_save.txt");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Será iniciado um novo jogo", "Arquivo de save não encontrado", JOptionPane.ERROR_MESSAGE, null);
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            objLeitura1 = new ObjectInputStream(arquivoLeitura1);
            objLeitura2 = new ObjectInputStream(arquivoLeitura2);
            objLeitura3 = new ObjectInputStream(arquivoLeitura3);
            objLeitura4 = new ObjectInputStream(arquivoLeitura4);
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.player = (Player) objLeitura1.readObject();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.rebels = (ArrayList<SpaceIcon>) objLeitura2.readObject();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {     
            this.empire  = (ArrayList<SpaceIcon>) objLeitura3.readObject();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.battlefield = (Battlefield) objLeitura4.readObject();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        try {
            System.out.println(objLeitura1.readObject());
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            objLeitura1.close();
            objLeitura2.close();
            objLeitura3.close();
            objLeitura4.close();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            arquivoLeitura1.close();
            arquivoLeitura2.close();
            arquivoLeitura3.close();
            arquivoLeitura4.close();
        } catch (IOException ex) {
            Logger.getLogger(OpenSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
    
}
