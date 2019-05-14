package Model;

public class RebelSpaceTower extends SpaceIcon {

    public RebelSpaceTower(int x_position, int y_position){
        super(x_position, y_position, Constants.SPACE_TOWER_LIFE);
    }

    @Override
    public void Move(Battlefield battlefield){
        //Os rebeldes não se movem, o jogador os dispõem de acordo com sua estratégia de ataque e estes se mantém fixos.
    }

    @Override
    public int[][] Attack(int[][] moveMatrix){
        int [][] rebel_space_tower_attack_matrix;
        rebel_space_tower_attack_matrix = new int[Constants.BATTLEFIELD_X_DIM][Constants.BATTLEFIELD_Y_DIM];
        for(int y = 0; y < moveMatrix[this.getX()].length; y++){
            if(moveMatrix[this.getX()][y] == 2){
                rebel_space_tower_attack_matrix[this.getX()][y] = Constants.SPACE_TOWER_ATTACK;
            }
        }
        return rebel_space_tower_attack_matrix;
    }
}
