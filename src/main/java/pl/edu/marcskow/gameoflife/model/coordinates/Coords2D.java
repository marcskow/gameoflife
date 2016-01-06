package pl.edu.marcskow.gameoflife.model.coordinates;

/**
 * Type of CellCoordinates using in 2 dimensional automatons. This type of coordinates has two fields
 * x which represents horizontal, and y represents vertical
 * @see CellCoordinates
 */
public class Coords2D implements CellCoordinates {
    /** horizontal coord */
    private final int x;
    /** vertical coord */
    private final int y;

    /**
     * constructor setting x and y as given x and given y
     * @param x horizontal coord
     * @param y vertical coord
     */
    public Coords2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return x position
     */
    public int getX(){
        return x;
    }

    /**
     * @return y position
     */
    public int getY(){
        return y;
    }

    /**
     * This method is overrided from Object to avoid errors with using Map as container
     * @param o is another cell coord
     * @return {@code true} if is equal {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coords2D coords2D = (Coords2D) o;

        if (x != coords2D.x) return false;
        return y == coords2D.y;

    }

    /**
     * This method is overrided from Object to avoid errors with using Map as container
     * @return hashCode of coord
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    /**
     * Useful method to explore cell states
     */
    @Override
    public String toString() {
        return "Coords2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
