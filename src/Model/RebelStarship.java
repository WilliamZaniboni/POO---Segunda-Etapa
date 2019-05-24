package Model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

public class RebelStarship extends SpaceIcon{
    

    public RebelStarship(int x_position, int y_position){
        super(x_position, y_position, Constants.STARSHIP_LIFE);
    }

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
        g.drawImage(nave2, squareWidth*super.getX()+327, squareWidth*super.getY()+87, null);
        
    }

    
}
