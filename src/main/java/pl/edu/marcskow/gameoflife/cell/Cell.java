package pl.edu.marcskow.gameoflife.cell;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.state.CellState;

/**
 * Created by intenso on 30.11.15.
 */
public class Cell {
    private final CellState state;
    private final CellCoordinates coords;

    public CellState getState() {
        return state;
    }

    public CellCoordinates getCoords() {
        return coords;
    }

    public Cell(CellState state, CellCoordinates coords){
        this.state = state;
        this.coords = coords;
    }
}


