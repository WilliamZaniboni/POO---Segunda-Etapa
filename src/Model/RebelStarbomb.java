package Model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observable;
import javax.swing.ImageIcon;

public class RebelStarbomb extends SpaceIcon{
    public RebelStarbomb(int x_position, int y_position){
        super(x_position, y_position, Constants.STARBOMB_LIFE);
    }

    @Override
    public void Move(Battlefield battlefield){
        //Os rebeldes não se movem, o jogador os dispõem de acordo com sua estratégia de ataque e esses se mantém fixos.
    }

    @Override
    public int[][] Attack(int[][] moveMatrix){
        int [][] rebel_starbomb_attack_matrix;

        rebel_starbomb_attack_matrix = new int[Constants.BATTLEFIELD_X_DIM][Constants.BATTLEFIELD_Y_DIM];

        //Primeira coluna
        if(this.getX()+1 < Constants.BATTLEFIELD_X_DIM){
            if(moveMatrix[this.getX()+1][this.getY()] == 2){
                rebel_starbomb_attack_matrix[this.getX()+1][this.getY()] = Constants.STARSHIP_ATTACK;
            }
        }
        //Segunda coluna
        if(this.getX()+2 < Constants.BATTLEFIELD_X_DIM){
            if(moveMatrix[this.getX()+2][this.getY()] == 2){
                rebel_starbomb_attack_matrix[this.getX()+2][this.getY()] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX()+2 < Constants.BATTLEFIELD_X_DIM) && (this.getY() -1 > 0)){
            if(moveMatrix[this.getX()+2][this.getY()-1] == 2){
                rebel_starbomb_attack_matrix[this.getX()+2][this.getY()-1] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX()+2 < Constants.BATTLEFIELD_X_DIM) && (this.getY() + 1 < Constants.BATTLEFIELD_Y_DIM)){
            if(moveMatrix[this.getX()+2][this.getY()+1] == 2){
                rebel_starbomb_attack_matrix[this.getX()+2][this.getY()+1] = Constants.STARSHIP_ATTACK;
            }
        }

        //Terceira coluna
        if(this.getX()+3 < Constants.BATTLEFIELD_X_DIM){
            if(moveMatrix[this.getX()+3][this.getY()] == 2){
                rebel_starbomb_attack_matrix[this.getX()+3][this.getY()] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX()+3 < Constants.BATTLEFIELD_X_DIM) && (this.getY() -1 > 0)){
            if(moveMatrix[this.getX()+3][this.getY()-1] == 2){
                rebel_starbomb_attack_matrix[this.getX()+3][this.getY()-1] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX()+3 < Constants.BATTLEFIELD_X_DIM) && (this.getY() -2 > 0)){
            if(moveMatrix[this.getX()+3][this.getY()-2] == 2){
                rebel_starbomb_attack_matrix[this.getX()+3][this.getY()-2] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX()+3 < Constants.BATTLEFIELD_X_DIM) && (this.getY() + 1 < Constants.BATTLEFIELD_Y_DIM)){
            if(moveMatrix[this.getX()+3][this.getY()+1] == 2){
                rebel_starbomb_attack_matrix[this.getX()+3][this.getY()+1] = Constants.STARSHIP_ATTACK;
            }
        }
        if((this.getX()+3 < Constants.BATTLEFIELD_X_DIM) && (this.getY() + 2 < Constants.BATTLEFIELD_Y_DIM)){
            if(moveMatrix[this.getX()+3][this.getY()+2] == 2){
                rebel_starbomb_attack_matrix[this.getX()+3][this.getY()+2] = Constants.STARSHIP_ATTACK;
            }
        }
        return rebel_starbomb_attack_matrix;
    }
    
     @Override
    public void draw(Graphics2D g) {
        
     int squareWidth = 54;   
        
     ImageIcon referencia3 = new ImageIcon("img/nave3icone.png");
     Image nave3 = referencia3.getImage();
     g.drawImage(nave3, squareWidth*super.getX()+327, squareWidth*super.getY()+87, null);
        
    }

    
    
}
