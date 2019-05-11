
package Controller;

import Model.UpdateClass;
import SpaceBattle.Main;
import View.Environment;
import View.Menu;
import View.Menu_usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class MenuController implements  ActionListener,  ItemListener{
    
     private Menu menu;
     private Menu_usuario menu_usuario;
     
     public void addMenu(Menu menu){
            this.menu = menu;
        }
     
     public void addMenu_usuario(Menu_usuario menu_usuario){
            this.menu_usuario = menu_usuario;
       }
     
     public void runMenu(){
            menu.setVisible(true);      
        }
     
        
         @Override
        public void actionPerformed(ActionEvent ae) {
        
        //verifica se o botão de iniciar o jogo foi apertado
            if(ae.getSource() == menu.getPlay()){
             
                menu_usuario.setVisible(true);
             
                menu.dispose(); 
             
            }
            
            if(ae.getSource() == menu_usuario.getPlay()){
                
               //Cria o Model do Update
              UpdateClass updater = new UpdateClass(); //Interface gráfica
              UpdateClass fight_updater = new UpdateClass();   
                
               //Cria o Controller do Environment - os eventos do jogo são tratados e distribuidos aqui
              EnvironmentController environment_controller = new EnvironmentController(menu_usuario);
              
              //Cria a View do Environment (Painel de jogo)
               Environment environment_view = new Environment(updater, environment_controller.getFightController());

              //Conecta os Controller ao respectivo View
               environment_view.addController(environment_controller);
               environment_controller.addView(environment_view);

              //Roda o View
               environment_controller.runEnvironment();
               
               environment_controller.set_time_thread();
               
               environment_controller.Start_Time_thread();
               
               menu_usuario.dispose();
                
            }
            
        }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
        if(ie.getSource() == menu_usuario.getSave_time()){
            
            menu_usuario.setEscolha(menu_usuario.getSave_time().getSelectedItem());
            
        }
        
    }
    
    
}
