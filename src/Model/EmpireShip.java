package Model;

import View.Sound_explosion;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class EmpireShip extends SpaceIcon {
    
    private transient ThreadMove threadmove;
    private transient Thread tr;
    private transient Sound_explosion sound;
    private transient Thread tr2;
    private int verific[] = new int[14]; 
    private boolean verific_ja_ocorreu_a_animacao_explosao  = false;
    private boolean verific_ja_ocorreu_o_som_da_explosão = false;
    
    
   //CONSTRUCTOR ================================================================================================================
    
    public EmpireShip(int y_position){
        
        super((Constants.BATTLEFIELD_X_DIM-1), y_position, Constants.EMPIRESHIP_LIFE);
        
        //thread para movimentação da nave
        threadmove = new ThreadMove(super.getX_pixel());
        tr = new Thread(threadmove);
        tr.start();
        
        //thread para o som
        sound = new Sound_explosion();
        tr2 = new Thread(sound);
        tr2.start();
        
        //vetor de verificação
        for ( int k=0; k<14; k++) verific[k] = 0; 
        
    }
    
    //Getters ======================================================================================================================
    
    public boolean isVerific_ja_ocorreu_a_animacao_explosao() {
        return verific_ja_ocorreu_a_animacao_explosao;
    }

    public boolean isVerific_ja_ocorreu_o_som_da_explosão() {
        return verific_ja_ocorreu_o_som_da_explosão;
    }
    
    //Setters ======================================================================================================================

    public void setVerific_ja_ocorreu_a_animacao_explosao(boolean verific_ja_ocorreu_a_animacao_explosao) {
        this.verific_ja_ocorreu_a_animacao_explosao = verific_ja_ocorreu_a_animacao_explosao;
    }

    public void setVerific_ja_ocorreu_o_som_da_explosão(boolean verific_ja_ocorreu_o_som_da_explosão) {
        this.verific_ja_ocorreu_o_som_da_explosão = verific_ja_ocorreu_o_som_da_explosão;
    }
    
    @Override
    public void setThreads(){
        
        threadmove = new ThreadMove(super.getX_pixel());
        tr = new Thread(threadmove);
        tr.start();
        
        sound = new Sound_explosion();
        tr2 = new Thread(sound);
        tr2.start();
        
    }
    
    
    public void setNewxpixel_position(int i){
        
        //atualiza o valor do pixel x
        super.setX_pixel(i);
        
        //atualiza o valor do quadrante
        if(super.getX_pixel()<1080 && super.getX_pixel() >=1026 && this.verific[13]==0){
           super.setX(13);  
           this.verific[13] = 1;
        }
        if(super.getX_pixel()<1026 && super.getX_pixel() >=972 && this.verific[12]==0){
           super.setX(12);  
           this.verific[12] = 1;
        }
        if(super.getX_pixel()<972 && super.getX_pixel() >=918 && this.verific[11]==0 ){
           super.setX(11); 
           this.verific[11] = 1;
        }
        if(super.getX_pixel()<918 && super.getX_pixel() >=864 && this.verific[10]==0){
           super.setX(10); 
           this.verific[10] = 1; 
        }
        if(super.getX_pixel()<864 && super.getX_pixel() >=813 && this.verific[9]==0){
           super.setX(9); 
           this.verific[9] = 1; 
        }
        if(super.getX_pixel()<813 && super.getX_pixel() >=759 && this.verific[8]==0){
           super.setX(8); 
           this.verific[8] = 1;
        }
        if(super.getX_pixel()<759 && super.getX_pixel() >=705 && this.verific[7]==0){
           super.setX(7); 
           this.verific[7] = 1;
        }
        if(super.getX_pixel()<705 && super.getX_pixel() >=651 && this.verific[6]==0){
           super.setX(6); 
           this.verific[6] = 1;
        }
        if(super.getX_pixel()<651 && super.getX_pixel() >=597 && this.verific[5]==0){
           super.setX(5); 
           this.verific[5] = 1;
        }
        if(super.getX_pixel()<597 && super.getX_pixel() >=543 && this.verific[4]==0){
           super.setX(4); 
           this.verific[4] = 1;
        }
        if(super.getX_pixel()<543 && super.getX_pixel() >=489 && this.verific[3]==0){
           super.setX(3); 
           this.verific[3] = 1;
        }
        if(super.getX_pixel()<489 && super.getX_pixel() >=435 && this.verific[2]==0){
           super.setX(2); 
           this.verific[2] = 1;
        }
        if(super.getX_pixel()<435 && super.getX_pixel() >=381 && this.verific[1]==0){
           super.setX(1); 
           this.verific[1] = 1;
        }
        if(super.getX_pixel()<381 && super.getX_pixel() >=327 && this.verific[0]==0){
           super.setX(0); 
           this.verific[0] = 1;   
        }
        if(super.getX_pixel()<331){
            threadmove.setStop_flag(1);
            super.setIsAlive(false);
        }
        
        
    }
    
    //METHODS =======================================================================================================================
    
    
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
    
    @Override
    public void draw(Graphics2D g) {
        
      int squareWidth = 54;   
      ImageIcon referencia4 = new ImageIcon("img/eticone.png"); //Inimigo
      Image nave4 = referencia4.getImage();
     
      //verifica se esta vivo e desenha a nave
      if(super.isIsAlive() == true){  
        
        g.drawImage(nave4, super.getX_pixel(), squareWidth*super.getY()+87, null);
        
        this.setNewxpixel_position(threadmove.getX_position());
      }
      
      //caso esteja morto e não tenha ocorrido a animação, desenha a animação de explosao
      if(super.isIsAlive() ==false && this.verific_ja_ocorreu_a_animacao_explosao == false){
          
        //inicia o efeito sonoro   
        if(this.verific_ja_ocorreu_o_som_da_explosão == false){
            
            sound.play_Music();
            this.setVerific_ja_ocorreu_o_som_da_explosão(true);
            
        }  
          
        ImageIcon referencia5 = new ImageIcon("img/explosao.gif"); //Explosao
        Image explosao = referencia5.getImage();
        
        
        g.drawImage(nave4, super.getX_pixel(), squareWidth*super.getY()+87, null);
        g.drawImage(explosao, super.getX_pixel(), squareWidth*super.getY()+87, null); 
        
         
        threadmove.setEmpireisDead(true);
        
        this.setVerific_ja_ocorreu_a_animacao_explosao(threadmove.isVerific());
        
        
      }
      
      
        
    }

     
    
}
