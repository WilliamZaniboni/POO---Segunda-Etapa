package Model;

public abstract class SpaceIcon implements SpaceFighter {

    private int x;
    private int y;
    private int life_value;

    public SpaceIcon(int x, int y, int life_value) {
        this.x = x;
        this.y = y;
        this.life_value = life_value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLife_value() {
        return life_value;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLife_value(int life_value) {
        this.life_value = life_value;
    }


    public abstract void Move(Battlefield battlefield);
    public abstract int[][] Attack(int[][] moveMatrix);
}
