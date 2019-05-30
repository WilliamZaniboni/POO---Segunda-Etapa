/********************************************************************************
 *   Programação Orientada à Objetos - 2019                                     *
 *   Universidade de São Paulo                                                  *
 *   Instituto de Ciências Matemáticas e Computação                             *
 *                                                                              *
 *   Trabalho 1 - Desenvolvimento de um jogo com interface gráfica              *
 *   Autores:                                                                   *
 *       João Matheus Siqueira Souza - NUSP: 10309100  (jmssouza)               *
 *       William Zaniboni Silva      - NUSP: 10309159  (WilliamZaniboni)        *
 *   SpaceBattle v1.0                                                           *
 *                                                                              *
 ********************************************************************************/
/* Essa  classe faz o controle do combate entre os rebeldes e o impérion dentro
 * do jogo. Como se trata  da versão 1.0, não faremos o uso de threads ou outras
 * técnicas para fazer o controle das interações entre os combatentes. Para esse
 * problema, desenvolvemos a abordagem de ciclos. A cada ciclo que se passa, são
 * analisados o campo de ataque de cada ícone no jogo, seu nível de vida e o ní-
 * vel de dano gerado nos ícones dentro do campo de ataque de cada um. Ao final,
 * os combatentes com nível de vida menor ou igual a zero são excluídos do jogo.*/

package Controller;

import Mathematics.BasicLinearAlgebra;
import Model.*;
import View.Canvas;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class FightController implements Observer {
    //"TIME" CONTROL ===================================================================================================
        private int cycle = 0;


    //ICONS CONTROL ====================================================================================================
        private int rebels_number = 0;
        private int empire_number = 0;
        private ArrayList <SpaceIcon> rebels = new ArrayList();
        private ArrayList <SpaceIcon> empire = new ArrayList();
        private Battlefield battlefield;
        private Player player;
        private Random random;
        private DrawController drawcontroller;
        private Canvas canvas;
        private ThreadSave save;
        private Thread thread;

    //CONSTRUCTOR ======================================================================================================
        public FightController(Battlefield battlefield, Player player, DrawController drawcontroller) {
            
            this.battlefield = battlefield;
            this.player = player;
            this.drawcontroller = drawcontroller;
            random = new Random();
            
            //inicia a thread para save automático
            save = new ThreadSave(player.getSave_time());
            thread = new Thread(save);
            thread.start();
            
        }
        
        
        //Construtor utilizado a partir do arquivo de save
        public FightController(Battlefield battlefield, Player player, DrawController drawcontroller, ArrayList <SpaceIcon> rebels, ArrayList <SpaceIcon> empire ) {
            
            this.battlefield = battlefield;
            this.player = player;
            this.drawcontroller = drawcontroller;
            this.rebels = rebels;
            this.empire = empire;
            random = new Random();
            
            //inicia as threads das naves
            
            for(SpaceIcon p : empire){
                  p.setThreads();
             }
            
            for(SpaceIcon p : rebels){
                  p.setThreads();
             }
            
            
            //inicia a thread para save automático
            save = new ThreadSave(player.getSave_time());
            thread = new Thread(save);
            thread.start();
        }
        
        
       

    //METHODS ==========================================================================================================

        //Getters e Setters --------------------------------------------------------------------------------------------

             public int getCycle() {
                 return cycle;
            }
             
            public int getRebels_number() {
                return rebels_number;
            }

            public int getEmpire_number() {
                return empire_number;
            }

            public ArrayList<SpaceIcon> getRebels() {
                return rebels;
            }
           

            public ArrayList<SpaceIcon> getEmpire() {
                return empire;
            }

          
            
        //atualiza o drawingController
            
        public void setdrawingController(){
            
            drawcontroller.setArrayList(rebels, empire);
            
        }
     
            
        //Inserção de um novo SpaceIcon --------------------------------------------------------------------------------
            public boolean insertNewSpaceTower(int x, int y){
                if(battlefield.getMoveMatrix()[x][y] == 0 && player.getGold()>=Constants.SPACE_TOWER_GOLD){
                    rebels.add(new RebelSpaceTower(x, y));
                    rebels_number ++;
                    battlefield.setMoveMatrixField(x, y, 1);
                    battlefield.setOccupiedMatrix(x,y,1);
                    System.out.println("SpaceTower criada em: " + x + ", " + y);          
                    player.setNewgold(Constants.SPACE_TOWER_GOLD);   
                    this.setdrawingController();
                    return true;
                }
                else{
                    return false;
                }

            }

            public boolean insertNewStarship(int x, int y){
                if(battlefield.getMoveMatrix()[x][y] == 0 && player.getGold()>= Constants.STARSHIP_GOLD){
                    rebels.add(new RebelStarship(x, y));
                    rebels_number ++;
                    battlefield.setMoveMatrixField(x, y, 1);
                    battlefield.setOccupiedMatrix(x,y,2);
                    System.out.println("Starshiṕ criada em: " + x + ", " + y);
                    player.setNewgold(Constants.STARSHIP_GOLD);
                    this.setdrawingController();
                    return true;
                }
                else{
                    return false;
                }

            }

            public boolean insertNewStarbomb(int x, int y){
                if(battlefield.getMoveMatrix()[x][y] == 0 && player.getGold()>= Constants.STARBOMB_GOLD){
                    rebels.add(new RebelStarbomb(x, y));
                    rebels_number ++;
                    battlefield.setMoveMatrixField(x, y, 1);
                    battlefield.setOccupiedMatrix(x,y,3);
                    System.out.println("Starbomb criada em: " + x + ", " + y);
                    player.setNewgold(Constants.STARBOMB_GOLD);
                    this.setdrawingController();
                    return true;
                }
                else{
                    return false;
                }

            }

            public boolean insertNewEmpireShip(int y){
                if(battlefield.getMoveMatrix()[Constants.BATTLEFIELD_X_DIM-1][y] == 0 ){
                    empire.add(new EmpireShip(y));
                    empire_number ++;
                    battlefield.setMoveMatrixField(Constants.BATTLEFIELD_X_DIM-1, y, 2);
                    battlefield.setOccupiedMatrix(Constants.BATTLEFIELD_X_DIM-1,y,4);
                    System.out.println("EmpireShip criada em: " + (Constants.BATTLEFIELD_X_DIM - 1) + ", " + y);
                    this.setdrawingController();
                    return true;
                }
                else{
                    return false;
                }
            }
         
           
            // retorna o objeto que ocupa determinado quadrante
            public int getMatrixpreenchida(int x, int y){
                return battlefield.getOccupiedMatrix(x, y);
            }
            
            //retorna a quantidade de gold do player
            
            public int getGoldPlayer(){
                return player.getGold();
            }
            
            
            

        //Métodos de operação durante um ciclo -------------------------------------------------------------------------
            public void moveEmpireShips(){
                for(int i = 0; i < empire_number; i++){
                    
                    battlefield.setOccupiedMatrix(empire.get(i).getX(),empire.get(i).getY(), 0);
                    
                    empire.get(i).Move(battlefield);
                    
                    battlefield.setOccupiedMatrix(empire.get(i).getX(),empire.get(i).getY(), 4);
                    
                }
            }
            

            public boolean isAlive(SpaceIcon fighter ,int[][] attackMatrix){
                fighter.setLife_value(fighter.getLife_value()-attackMatrix[fighter.getX()][fighter.getY()]);
                return fighter.getLife_value() >= 0;
            }

            public void cycle_interaction(){
                //Monta a matriz de ataque do ciclo --------------------------------------------------------------------
                    battlefield.zeroAttackMatrix();
                    BasicLinearAlgebra calculator = new BasicLinearAlgebra();
                    for(int i = 0; i < rebels_number; i++){
                        battlefield.setAttackMatrix(calculator.sumMatrix(battlefield.getAttackMatrix(), rebels.get(i).Attack(battlefield.getMoveMatrix())));
                    }
                    for(int i = 0; i < empire_number; i++){
                        battlefield.setAttackMatrix(calculator.sumMatrix(battlefield.getAttackMatrix(), empire.get(i).Attack(battlefield.getMoveMatrix())));
                    }
                //Atualiza dados de vida dos SpaceIcons e já os retira os ícones mortos --------------------------------
                //Rebels  ..............................................................................................
                    int flag_for_death = 0;
                    int[] flag_for_rebel_death = new int[rebels_number];
                    for(int i = 0; i < rebels_number; i++){
                        if(isAlive(rebels.get(i), battlefield.getAttackMatrix()) == false){  //Manteve-se formato sem simplificação para facilitar entendimento
                            flag_for_rebel_death[flag_for_death] = i;
                            flag_for_death++;
                        }
                    }
                    for(int i = 0; i < flag_for_death; i++){
                        battlefield.setMoveMatrixField(rebels.get(flag_for_rebel_death[i]).getX(), rebels.get(flag_for_rebel_death[i]).getY(), 0);
                        battlefield.setOccupiedMatrix(rebels.get(flag_for_rebel_death[i]).getX(), rebels.get(flag_for_rebel_death[i]).getY(), 0);
                        rebels.remove(flag_for_rebel_death[i]);
                        rebels_number--;
                    }
                //Empire ...............................................................................................
                    flag_for_death = 0;
                    int[] flag_for_empire_death = new int [empire_number];
                    for(int i = 0; i < empire_number; i++){
                        if(isAlive(empire.get(i), battlefield.getAttackMatrix()) == false){
                            flag_for_empire_death[flag_for_death] = i;
                            flag_for_death++;
                        }
                    }
                    for(int i = 0; i < flag_for_death; i++){
                        battlefield.setMoveMatrixField(empire.get(flag_for_empire_death[i]).getX(), empire.get(flag_for_empire_death[i]).getY(), 0);
                        battlefield.setOccupiedMatrix(empire.get(flag_for_empire_death[i]).getX(), empire.get(flag_for_empire_death[i]).getY(), 0);
                        rebels.remove(flag_for_empire_death[i]);
                        empire_number--;
                    }
            }

            public int generateEmpireArmy(){
                int probability;
                probability = random.nextInt(100);
                if((cycle % 5  == 0) && (probability >= 25) && (probability <= 75)){
                    return random.nextInt(Constants.BATTLEFIELD_Y_DIM);
                }
                else if((cycle % 5 != 0) && (probability >= 2) && (probability <= 6)){
                    return random.nextInt(Constants.BATTLEFIELD_Y_DIM);
                }
                else{
                    return Constants.BATTLEFIELD_Y_DIM;
                }
            }

            public void cycle_routine(){
                int newEmpireshipPosition = 9;
                this.moveEmpireShips();
                this.cycle_interaction();
                
                //////////////////
                
                save.setPlayer(player);
                save.setEmpire(empire);
                save.setRebels(rebels);
                save.setBattlefield(battlefield);
                
                //////////////////
                
                this.cycle++;
                newEmpireshipPosition = this.generateEmpireArmy();
                if(newEmpireshipPosition < 8){
                    this.insertNewEmpireShip(newEmpireshipPosition);
                }
            }
            
          //Atualiza os dados do FightController com base em update do EnvironmentController -----------------------------
            @Override
            public void update(Observable o, Object arg) { 
                EnvironmentController controller = (EnvironmentController) arg;
                if(controller.getUpdating_mode() == 0){
                    boolean isPossible;
                    if(controller.getFlag_for_rebel_choice() != 0){
                        if(controller.getFlag_for_rebel_choice() == 1){
                            isPossible = this.insertNewSpaceTower(controller.getLast_x_quad(), controller.getLast_y_quad());
                            controller.setFlag_for_rebel_choice(0);
                            controller.setLast_x_quad(0);
                            controller.setLast_y_quad(0);
                        }
                        else if(controller.getFlag_for_rebel_choice() == 2){
                            isPossible = this.insertNewStarship(controller.getLast_x_quad(), controller.getLast_y_quad());
                            controller.setFlag_for_rebel_choice(0);
                            controller.setLast_x_quad(0);
                            controller.setLast_y_quad(0);
                        }
                        else if(controller.getFlag_for_rebel_choice() == 3){
                            isPossible = this.insertNewStarbomb(controller.getLast_x_quad(), controller.getLast_y_quad());
                            controller.setFlag_for_rebel_choice(0);
                            controller.setLast_x_quad(0);
                            controller.setLast_y_quad(0);
                        }

                    }
                }
                else if (controller.getUpdating_mode() == 1){
                    this.cycle_routine();
                }
                else if (controller.getUpdating_mode() == 2 ){

                }
                else{

                }
            }

}