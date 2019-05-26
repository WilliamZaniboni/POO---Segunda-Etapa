package Model;

import java.io.Serializable;

public class Battlefield implements Serializable {

    private int width;
    private int height;

    private int[][] moveMatrix;
    private int[][] attackMatrix;
    private int[][] occupiedMatrix;

    public Battlefield(int width, int height) {
        this.width = width;
        this.height = height;
        /*
        A matriz moveMatrix, contém os SpaceIcons presente no Environment num determinado ciclo.
        Adotou-se:
            0 - Posição vazia;
            1 - Rebelde;
            2 - Império;
        */
        moveMatrix = new int [width][height];
        attackMatrix = new int[width][height];
        
        /*
        A matriz occupiedMatrix, contém os tipos de objetos armazenados em cada quadrante.
        Adotou-se:
            0 - Posição vazia;
            1 - SpaceTower;
            2 - Starhip;
            3 - StarBomb;
        */
        
        occupiedMatrix = new int [width][height];
        
    }

    //Getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMoveMatrix() {
        return moveMatrix;
    }

    public int[][] getAttackMatrix() {
        return attackMatrix;
    }

    //Setters
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMoveMatrix(int[][] moveMatrix) {
        this.moveMatrix = moveMatrix;
    }

    public  void setMoveMatrixField(int x, int y, int value){
        this.moveMatrix[x][y] = value;
    }
    
    /////////////////////////////////////////////////////////////////
    
    //getters e setters da matriz que armazena o tipo de nave que ocupa o quadrante
    
    public  void setOccupiedMatrix(int x, int y, int value){
        this.occupiedMatrix[x][y] = value;
    }
    
    public int getOccupiedMatrix (int x, int y){
        return this.occupiedMatrix[x][y];
    }
    
    //////////////////////////////////////////////////////////////////
    

    public void setAttackMatrix(int[][] attackMatrix) {
        this.attackMatrix = attackMatrix;
    }

    public void setAttackMatrixField(int x, int y, int value){
        this.attackMatrix[x][y] = value;
    }

    public void zeroAttackMatrix(){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < height; y++ ){
                this.attackMatrix[x][y] = 0;
            }
        }
    }
}
