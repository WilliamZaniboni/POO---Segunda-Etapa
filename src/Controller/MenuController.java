
package Controller;

import Model.OpenSave;
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


public class MenuController implements  ActionListener,  ItemListener{
    
     private Menu menu;
     private Menu_usuario menu_usuario;
     private Informacoes informacoes;
     private Sound_menu musica_de_fundo;
     private Thread t;
     
     
     //CONSTRUCTOR ===============================================================================================
     
     public MenuController(){
         
             musica_de_fundo = new Sound_menu();
             t = new Thread(musica_de_fundo);
             t.start();
             musica_de_fundo.play_Music_menu();
     }
     
     //METHODS ADD CONTROLLER ======================================================================================
     
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
     
     //METHODS PARA LER AS AÇÕES DOS BOTÕES ==========================================================================
        
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
            
            //Botão de continuar selecionado
            if( ae.getSource() == menu.getContinue_thegame()){
                
                OpenSave open = new OpenSave();
                
                //caso tenha encontrado o arquivo de save
                if(open.isArquivo_encontrado() == true){
                    
                      //fecha todos os menus abertos
                        menu_usuario.dispose();
                        informacoes.dispose();
                
                      //Cria o Model do Update
                        UpdateClass updater = new UpdateClass(); 
                        UpdateClass fight_updater = new UpdateClass();   
              
                      //adiciona o metodo que printa as naves
                        DrawController drawcontroller = new DrawController();
                   
                        drawcontroller.setArrayList(open.getRebels(), open.getEmpire());
                
                      //Cria o Controller do Environment - os eventos do jogo são tratados e distribuidos aqui
                        EnvironmentController environment_controller = new EnvironmentController(drawcontroller, open.getPlayer(), open.getBattlefield(), open.getRebels(), open.getEmpire());
              
                      //Cria a View do Environment (Painel de jogo)
                        Environment environment_view = new Environment(updater, environment_controller.getFightController(), drawcontroller);
                   
                      //Atualiza o valor do gold  
                        environment_view.getjLabel8().setText(""+open.getPlayer().getGold());
               
                      //Conecta os Controller ao respectivo View
                        environment_view.addController(environment_controller);
                        environment_controller.addView(environment_view);

                      //Roda o View
                        environment_controller.runEnvironment();
               
                      //Inicia a thread que ira fazer o repaint do view 
                        ThreadRepaint repaint = new ThreadRepaint(environment_view);
                        Thread tr = new Thread(repaint);
                        tr.start();
              
                      //Controla a thread do relogio
                         environment_controller.set_time_thread();
                         environment_controller.Start_Time_thread();
               
                      //Fecha o menu e inicia a musica do game
                         menu.dispose(); 
                         musica_de_fundo.stop_Music_menu();
                         musica_de_fundo.play_Music_game();
                }
                
                else{
                    
                    System.out.println("Entrei no else");
                    
                    menu_usuario.setVisible(true);
                    menu.dispose(); 
                    
                }
                
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
       
    @Override
    public void itemStateChanged(ItemEvent ie) {
        
        if(ie.getSource() == menu_usuario.getSave_time()){
            
            menu_usuario.setEscolha(menu_usuario.getSave_time().getSelectedItem());
            
        }
        
    }
    
    
    //METHODS ============================================================================================================
     
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
              
               //controller do timer
               environment_controller.set_time_thread();
               environment_controller.Start_Time_thread();
               
               //controla a música de fundo
               musica_de_fundo.stop_Music_menu();
               musica_de_fundo.play_Music_game();
               
               
               //fecha todos os menus abertos
               menu_usuario.dispose();
               informacoes.dispose();
                
        }
        
    
    
}
