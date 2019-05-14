package Model;

public interface SpaceFighter {
    void Move(Battlefield battlefield);
    int[][] Attack(int[][] moveMatrix);
}
