package gameoflife;

public class Position
{

    /**
     * les coordonnées dans le plan des positions de chaque cellules
     */
    private int x, y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

}
