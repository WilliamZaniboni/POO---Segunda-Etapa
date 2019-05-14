package Model;

public class EmpireShip extends SpaceIcon {
    public EmpireShip(int y_position){
        super((Constants.BATTLEFIELD_X_DIM-1), y_position, Constants.EMPIRESHIP_LIFE);
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
}
