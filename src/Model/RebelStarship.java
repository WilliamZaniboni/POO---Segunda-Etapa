package Model;

import View.Sound_explosion;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

public class RebelStarship extends SpaceIcon{
    
    private transient Sound_explosion sound;
    private transient Thread tr2;
    private transient ThreadExplosion explosion;
    private transient Thread tr1;
    private boolean verific_ja_ocorreu_a_animacao_explosao  = false;
    private boolean verific_ja_ocorreu_o_som_da_explosão = false;
    
    //CONSTRUCTOR ===================================================================================================

    public RebelStarship(int x_position, int y_position){
        super(x_position, y_position, Constants.STARSHIP_LIFE);
        
        //thread para controlar o gif de explosão
        explosion = new ThreadExplosion();
        tr1 = new Thread(explosion);
        tr1.start();
        
        //thread para o som
        sound = new Sound_explosion();
        tr2 = new Thread(sound);
        tr2.start();
    }
    
    //GETTERS =======================================================================================================
    
    public boolean isVerific_ja_ocorreu_a_animacao_explosao() {
        return verific_ja_ocorreu_a_animacao_explosao;
    }

    public boolean isVerific_ja_ocorreu_o_som_da_explosão() {
        return verific_ja_ocorreu_o_som_da_explosão;
    }
    
    //SETTERS =======================================================================================================

    public void setVerific_ja_ocorreu_a_animacao_explosao(boolean verific_ja_ocorreu_a_animacao_explosao) {
        this.verific_ja_ocorreu_a_animacao_explosao = verific_ja_ocorreu_a_animacao_explosao;
    }

    public void setVerific_ja_ocorreu_o_som_da_explosão(boolean verific_ja_ocorreu_o_som_da_explosão) {
        this.verific_ja_ocorreu_o_som_da_explosão = verific_ja_ocorreu_o_som_da_explosão;
    }
    
    //METHODS =======================================================================================================

    @Override
    public void Move(Battlefield battlefield){
        //Os rebeldes não se movem, o jogador os dispõem de acordo com sua estratégia de ataque e estes se mantém fixos.
    }

    @Override
    public int[][] Attack(int[][] moveMatrix){
        int [][] rebel_starship_attack_matrix;

        rebel_starship_attack_matrix = new int[Constants.BATTLEFIELD_X_DIM][Constants.BATTLEFIELD_Y_DIM];

        //Primeira coluna
        if(this.getX() + 1 < Constants.BATTLEFIELD_X_DIM){
            if(moveMatrix[this.getX()+1][this.getY()] == 1){
                rebel_starship_attack_matrix[this.getX()+1][this.getY()] = Constants.STARSHIP_ATTACK;
            }
        }
        //Segunda coluna
        if(this.getX() + 2 < Constants.BATTLEFIELD_X_DIM){
            if(moveMatrix[this.getX()+2][this.getY()] == 1){
                rebel_starship_attack_matrix[this.getX()+2][this.getY()] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX() + 2 < Constants.BATTLEFIELD_X_DIM) && (this.getY() - 1 > 0)){
            if(moveMatrix[this.getX()+2][this.getY()-1] == 1){
                rebel_starship_attack_matrix[this.getX()+2][this.getY()-1] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX() + 2 < Constants.BATTLEFIELD_X_DIM) && (this.getY() + 1 < Constants.BATTLEFIELD_Y_DIM)){
            if(moveMatrix[this.getX()+2][this.getY()+1] == 1){
                rebel_starship_attack_matrix[this.getX()+2][this.getY()+1] = Constants.STARSHIP_ATTACK;
            }
        }
        return rebel_starship_attack_matrix;
    }
    
    @Override
    public void draw(Graphics2D g) {
        
        int squareWidth = 54;
                
        ImageIcon referencia2 = new ImageIcon("img/nave2icone.png"); //StarShip
        Image nave2 = referencia2.getImage(); 
        
        //verifica se esta vivo e desenha a nave
        
        if(super.isIsAlive() == true){  
            
          g.drawImage(nave2, squareWidth*super.getX()+327, squareWidth*super.getY()+87, null);
        
        }
        
        //caso esteja morto e não tenha ocorrido a animação, desenha a animação de explosao
        if(super.isIsAlive() == false && this.verific_ja_ocorreu_a_animacao_explosao == false){
          
        //inicia o efeito sonoro   
        if(this.verific_ja_ocorreu_o_som_da_explosão == false){
            
            sound.play_Music();
            this.setVerific_ja_ocorreu_o_som_da_explosão(true);
            
        }  
          
        ImageIcon referencia5 = new ImageIcon("img/explosao.gif"); //Explosao
        Image explosao = referencia5.getImage();
        
        
        g.drawImage(nave2, squareWidth*super.getX()+327, squareWidth*super.getY()+87, null);
        g.drawImage(explosao, squareWidth*super.getX()+327, squareWidth*super.getY()+87, null);
        
        explosion.setRebelisDead(true);
        explosion.setStop_thread(true);
        
        
        this.setVerific_ja_ocorreu_a_animacao_explosao(explosion.isVerific());
        
        
      }
        
    }

    
}
