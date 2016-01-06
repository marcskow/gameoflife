package pl.edu.marcskow.gameoflife.model.coordinates;


/**
 * This class represents coordinates of cell in one dimensional automaton.
 * That's mean that cell can have only one coord which there is just x.
 * @see CellCoordinates
 */
public class Coords1D implements CellCoordinates {
    /** coord of the cell */
    private final int x;

    /** setting x coordinate as given x*/
    public Coords1D(int x){
        this.x = x;
    }

    /**
     * @return x coordinate of cell
     */
    public int getX(){
        return x;
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

        Coords1D coords1D = (Coords1D) o;

        return x == coords1D.x;

    }

    /**
     * This method is overrided from Object to avoid errors with using Map as container
     * @return hashCode of coord
     */
    @Override
    public int hashCode() {
        return x;
    }
}
