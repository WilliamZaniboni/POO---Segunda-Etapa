package Model;

import View.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

public class EmpireShip extends SpaceIcon {
    
    private ThreadMove threadmove;
    private Thread tr;
    private Canvas canvas;
    
    public EmpireShip(int y_position){
        
        super((Constants.BATTLEFIELD_X_DIM-1), y_position, Constants.EMPIRESHIP_LIFE);
        threadmove = new ThreadMove(super.getX_pixel());
        tr = new Thread(threadmove);
        tr.start();
        
    }

    @Override
    public void Move(Battlefield battlefield){
        if(this.getX() != 0) {
            if (battlefield.getMoveMatrix()[this.getX() - 1][this.getY()] == 0) {
                battlefield.setMoveMatrixField(this.getX(), this.getY(), 0);
                this.setX(this.getX() - 1);
                battlefield.setMoveMatrixField(this.getX(), this.getY(), 2);
            }
        }
    }

    @Override
    public int[][] Attack(int[][] moveMatrix){
        int [][] empire_ship_attack_matrix;

        empire_ship_attack_matrix = new int[Constants.BATTLEFIELD_X_DIM][Constants.BATTLEFIELD_Y_DIM];

        if(this.getX() - 1 > 0){
            if(moveMatrix[this.getX()-1][this.getY()] == 1){
                empire_ship_attack_matrix[this.getX()-1][this.getY()] = Constants.EMPIRESHIP_ATTACK;
            }
        }
        if(this.getX() - 2 > 0){
            if(moveMatrix[this.getX()-2][this.getY()] == 1){
                empire_ship_attack_matrix[this.getX()-2][this.getY()] = Constants.EMPIRESHIP_ATTACK;
            }
        }
        if((this.getX() - 2 > 0) && (this.getY() - 1 > 0)){
            if(moveMatrix[this.getX()-2][this.getY()-1] == 1){
                empire_ship_attack_matrix[this.getX()-2][this.getY()-1] = Constants.EMPIRESHIP_ATTACK;
            }
        }
        if((this.getX() - 2 > 0) && (this.getY() + 1 < Constants.BATTLEFIELD_Y_DIM)){
            if(moveMatrix[this.getX()-2][this.getY()+1] == 1){
                empire_ship_attack_matrix[this.getX()-2][this.getY()+1] = Constants.EMPIRESHIP_ATTACK;
            }
        }
        return empire_ship_attack_matrix;
    }
    
    public void setNewxpixel_position(int i){
        super.setX_pixel(i);
       // if(super.getX_pixel()>)
        
    }
    
    
    @Override
    public void draw(Graphics2D g) {
        
        int squareWidth = 54;
        
        ImageIcon referencia4 = new ImageIcon("img/eticone.png"); //Inimigo
        Image nave4 = referencia4.getImage();

        g.drawImage(nave4, super.getX_pixel(), squareWidth*super.getY()+87, null);
        
        this.setNewxpixel_position(threadmove.getX_position());
        
        
    }

     
    
}
