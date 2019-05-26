package Model;

import java.awt.Graphics2D;

public abstract class SpaceIcon implements SpaceFighter {

    private int x;
    private int y;
    private int life_value;
    private int x_pixel;
    private boolean isAlive;
    
    //CONSTRUCTOR =========================================================================================================
    public SpaceIcon(int x, int y, int life_value) {
        
        this.x = x;
        this.y = y;
        this.life_value = life_value;
        this.x_pixel = 54*(Constants.BATTLEFIELD_X_DIM-1)+327;
        this.isAlive = true;
        
    }
    
    //GETTERS ==============================================================================================================
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getX_pixel() {
        return x_pixel;
    }

    public int getLife_value() {
        return life_value;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    
    //SETTERS ===============================================================================================================
    
    public void setX(int x) {
        this.x = x; 
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLife_value(int life_value) {
        this.life_value = life_value;
    }

    public void setX_pixel(int x_pixel) {
        this.x_pixel = x_pixel;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    

    //METHODS ============================================================================================================
    
    
    @Override
    public abstract void Move(Battlefield battlefield);
    @Override
    public abstract int[][] Attack(int[][] moveMatrix);
    public abstract void draw(Graphics2D g);
}
