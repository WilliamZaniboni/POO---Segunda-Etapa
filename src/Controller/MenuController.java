
package Controller;

import Model.ThreadMove;
import Model.UpdateClass;
import View.Environment;

import View.Informacoes;
import View.Menu;
import View.Menu_usuario;
import View.Sound_menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;


public class MenuController implements  ActionListener,  ItemListener{
    
     private Menu menu;
     private Menu_usuario menu_usuario;
     private Informacoes informacoes;
     private Sound_menu musica_de_fundo;
     private Thread t;
     
     public MenuController(){
         
             musica_de_fundo = new Sound_menu();
             t = new Thread(musica_de_fundo);
             t.start();
             musica_de_fundo.play_Music_menu();
     }
     
     
     
     public void addMenu(Menu menu){
            this.menu = menu;
        }
     
     public void addMenu_usuario(Menu_usuario menu_usuario){
            this.menu_usuario = menu_usuario;
       }
     
     public void addInformacoes(Informacoes informacoes){
            this.informacoes = informacoes;
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
                
               // verifica se o botão iniciar jogo do segundo menu foi apertado 
               //chama o método que inicia o jogo 
               this.starting_the_game();
                
            }
            
            if( ae.getSource() == menu.getContinue_thegame()){
                
                JOptionPane.showMessageDialog(null, "Será iniciado um novo jogo", "Arquivo de save não encontrado", JOptionPane.ERROR_MESSAGE, null);
                
            }
            
            if( ae.getSource() == menu.getInformations()){
                // verifica se o botão "Sobre" foi apertado 
                menu.setVisible(false);
                informacoes.setVisible(true);
                
            }
            
            
            if (ae.getSource() == informacoes.getRetornar()){
               // verifica se o botão "retornar" foi apertado 
                informacoes.setVisible(false);
                menu.setVisible(true);
                
            }
            
        }
        
        
        //inicia o jogo
        public void starting_the_game(){
            
               //Cria o Model do Update
              UpdateClass updater = new UpdateClass(); 
              UpdateClass fight_updater = new UpdateClass();   
              
              //adiciona o metodo que printa as naves
              DrawController drawcontroller = new DrawController();
                
               //Cria o Controller do Environment - os eventos do jogo são tratados e distribuidos aqui
              EnvironmentController environment_controller = new EnvironmentController(menu_usuario, drawcontroller);
              
              //Cria a View do Environment (Painel de jogo)
               Environment environment_view = new Environment(updater, environment_controller.getFightController(), drawcontroller);
               
              //Conecta os Controller ao respectivo View
               environment_view.addController(environment_controller);
               environment_controller.addView(environment_view);

               
              //Roda o View
               environment_controller.runEnvironment();
               
              //Inicia a thread que ira fazer o repaint do view 
              ThreadRepaint repaint = new ThreadRepaint(environment_view);
              Thread tr = new Thread(repaint);
              tr.start();
              
               //
               environment_controller.set_time_thread();
               
               environment_controller.Start_Time_thread();
               
               //
               
               musica_de_fundo.stop_Music_menu();
               musica_de_fundo.play_Music_game();
               
               
               //fecha todos os menus abertos
               menu_usuario.dispose();
               informacoes.dispose();
                
        }
        
        

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
        if(ie.getSource() == menu_usuario.getSave_time()){
            
            menu_usuario.setEscolha(menu_usuario.getSave_time().getSelectedItem());
            
        }
        
    }
    
    
}
