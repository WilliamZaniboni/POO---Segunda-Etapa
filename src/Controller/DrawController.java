
package Controller;

import Model.SpaceIcon;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class DrawController implements Observer{
    
     private ArrayList <SpaceIcon> rebels = new ArrayList();
     private ArrayList <SpaceIcon> empire = new ArrayList();
     
     
    public void setArrayList(ArrayList <SpaceIcon> rebels, ArrayList <SpaceIcon> empire){
        
        this.rebels = rebels;
        this.empire = empire;
        
    } 
    
    public void draw(Graphics2D g){
        //desenha as naves aliadas
        for(SpaceIcon p : rebels){
            p.draw(g);
        }
        
        //desenha as naves inimigas
        for(SpaceIcon p : empire){
            p.draw(g);
        }
    }
       
       @Override
    public void update(Observable o, Object arg) {
        draw((Graphics2D) arg);
    }
    
}
