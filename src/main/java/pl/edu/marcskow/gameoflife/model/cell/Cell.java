package pl.edu.marcskow.gameoflife.model.cell;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.state.CellState;

/**
 * This class represents state of one cell in every automaton, the cell has state and position.
 */
public class Cell {
    /** state of cell */
    private final CellState state;
    /** position of cell */
    private final CellCoordinates coords;

    /**
     * @return CellState of current cell
     */
    public CellState getState() {
        return state;
    }

    /**
     * @return CellCoordinates of current cell
     */
    public CellCoordinates getCoords() {
        return coords;
    }

    /**
     * Setting a cell
     * @param state CellState of cell
     * @param coords CellCoordinates of cell
     */
    public Cell(CellState state, CellCoordinates coords){
        this.state = state;
        this.coords = coords;
    }
}


