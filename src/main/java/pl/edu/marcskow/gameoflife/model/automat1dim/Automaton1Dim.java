package pl.edu.marcskow.gameoflife.model.automat1dim;

import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.model.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;

/**
 * Specific group of automatons, this abstract class is responsible
 * for making cellular automaton existing in one dimension.
 * @see Automaton
 */
public abstract class Automaton1Dim extends Automaton {
    /** Size of automaton map */
    private int size;

    public Automaton1Dim(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        super(cellStateFactory, cellNeighborhood);
    }

    /**
     * In one dimensional automaton there is only one coordinate we have to check.
     * Cell is the last cell on map if it's coord is equal size of map - 1, cause
     * size of map including 0 coord.
     * @param cellCoordinates coordinates to which we are looking for next coorinates
     * @return {@code true} if there is next cell {@code false} otherwise
     */
    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates){
        if (((Coords1D) cellCoordinates).getX() == size - 1){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * @return instance of 1 dimensional coordinates with specific x = -1
     */
    @Override
    protected CellCoordinates initialCoordinates() {
        return new Coords1D(-1);
    }

    /**
     * Returning next coordinates to given coordinates in one dimension it is current + 1
     * @param cellCoordinates coordinates from which we are looking for next coordinates
     * @return current + 1
     */
    @Override
    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates){
        return new Coords1D(((Coords1D) cellCoordinates).getX() + 1);
    }

    /**
     * @return map size
     */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}