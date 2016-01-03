package pl.edu.marcskow.gameoflife.coordinates;

public class Coords2D implements CellCoordinates {
    private final int x;
    private final int y;

    public Coords2D(){
        this(0,0);
    }

    public Coords2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coords2D coords2D = (Coords2D) o;

        if (x != coords2D.x) return false;
        return y == coords2D.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coords2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
