package Controller;

//Java codes ===========================================================================================================
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observer;

//Developed codes ======================================================================================================
import Model.Battlefield;
import Model.Constants;
import Model.Player;
import Model.ThreadTimer;
import Model.UpdateClass;
import View.Environment;
import View.Menu_usuario;

//Class - EnvironmentController ========================================================================================
public class EnvironmentController implements MouseListener, MouseMotionListener, ActionListener{
    //VARIABLES ========================================================================================================  
        private int flag_for_rebel_choice = 0;
        private int last_x_quad = 0;
        private int last_y_quad = 0;
        private int updating_mode = 0;

    //OBJECTS ==========================================================================================================  
        private Environment environment;
        private Battlefield battlefield;
        private FightController fightController;
        private UpdateClass updater;
        private ArrayList<Observer> observers_fight = new ArrayList();
        private ThreadTimer time;
        private Thread tr;
        private Menu_usuario menu_usuario;
        
  
    //CONSTRUCTOR ======================================================================================================
    public EnvironmentController(Menu_usuario menu_usuario) {
        //Constrói a infraestrutura de luta dentro do jogo -------------------------------------------------------------
            battlefield = new Battlefield(Constants.BATTLEFIELD_X_DIM, Constants.BATTLEFIELD_Y_DIM);
            this.menu_usuario = menu_usuario;
            Player player = new Player(menu_usuario);
            fightController = new FightController(battlefield, player);
            this.registerObserver(fightController);
            
    }


    //METHODS ==========================================================================================================
        public void addView (Environment view){
            this.environment = view;
        }

        public void addUpdater(UpdateClass update){
            this.updater = update;
        }

        public void registerObserver(Observer ob){
           this.observers_fight.add(ob);
        }
        
        public void runEnvironment() {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - environment.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - environment.getHeight()) / 2);
            environment.setLocation(x, y);

            environment.setVisible(true);
        }
        
        //Threads =======================================================================================================
        
        public void set_time_thread(){
            time = new ThreadTimer(environment.getjLabel11(), fightController, environment);
            tr = new Thread(time);
        }
        
        public void Start_Time_thread(){
            tr.start();
        }
        
        public void Stop_Time_thread(){
            //reseta o contador
            time.set_flag(1);
        }

        //Mouse events =================================================================================================
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();//pega as coordenadas do mouse
                int y = e.getY();
                int quadrantex = (x - 327)/54;
                int quadrantey = (y - 94)/54;
                boolean isPossible;

                
               
                    
                    if(flag_for_rebel_choice != 0){
                        this.last_x_quad = quadrantex;
                        this.last_y_quad = quadrantey;
                        this.updating_mode = 0;
                        fightController.update(null, this);
                    }



                flag_for_rebel_choice = 0;

                //torna invisivel as labels de atributo após um clique no tabuleiro

                environment.getjLabel4().setVisible(false);
                environment.getjLabel5().setVisible(false);
                environment.getjLabel6().setVisible(false);

                environment.setClickButton1(false);
                environment.setClickButton2(false);
                environment.setClickButton3(false);
                
                environment.getjLabel8().setText(""+fightController.getGoldPlayer());
               

                environment.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

                int x = e.getX();//pega as coordenadas do mouse
                int y = e.getY();

                int quadrantex = (x - 327)/54;
                int quadrantey = (y - 94)/54;

                
                environment.getMouseCoord().setLocation(x, y);
                environment.repaint();
            }

            @Override //Sem implementação
            public void mouseClicked(MouseEvent e) {
            }

            @Override //Sem implementação
            public void mouseReleased(MouseEvent e) {
            }

            @Override //Sem implementação
            public void mouseEntered(MouseEvent e) {
            }

            @Override //Sem implementação
            public void mouseExited(MouseEvent e) {
            }

            @Override //Sem implementação
            public void mouseDragged(MouseEvent e) {
            }


        @Override
        public void actionPerformed(ActionEvent ae) {
            
            
            
          //verifica de onde veio o evento pressionar botão e torna visivel os atributos da nave escolhida
            if(ae.getSource() == environment.getbutton_spacetower()){
                environment.getjLabel5().setVisible(true);
                environment.setClickButton1(true);
                this.flag_for_rebel_choice = 1;
            }

            if(ae.getSource() == environment.getbutton_starship()){
                environment.getjLabel6().setVisible(true);
                environment.setClickButton2(true);
                this.flag_for_rebel_choice = 2;
            }

            if(ae.getSource() == environment.getbutton_starbomb()){
                environment.getjLabel4().setVisible(true);
                environment.setClickButton3(true);
                this.flag_for_rebel_choice = 3;  
            }
            
            if(ae.getSource() == environment.getbutton_next_cycle()){
                
                this.Stop_Time_thread();
                fightController.cycle_routine(); 
                environment.getjLabel9().setText(""+fightController.getCycle()+"º");
                environment.repaint();
                
                
            }
            
            
            
        }

        //Variables Getters ============================================================================================

        public int getFlag_for_rebel_choice() {
            return flag_for_rebel_choice;
        }

        public int getLast_x_quad() {
            return last_x_quad;
        }

        public int getLast_y_quad() {
            return last_y_quad;
        }

        public int getUpdating_mode() {
            return updating_mode;
        }

        //Variables Setters ============================================================================================

        public void setFlag_for_rebel_choice(int flag_for_rebel_choice) {
            this.flag_for_rebel_choice = flag_for_rebel_choice;
        }

        public void setLast_x_quad(int last_x_quad) {
            this.last_x_quad = last_x_quad;
        }

        public void setLast_y_quad(int last_y_quad) {
            this.last_y_quad = last_y_quad;
        }

        public void setUpdating_mode(int updating_mode) {
            this.updating_mode = updating_mode;
        }

        //FightController getter
        public FightController getFightController() {
            return fightController;
        }
}
