package pl.edu.marcskow.gameoflife.factory;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.state.CellState;

/**
 * Created by intenso on 06.12.15.
 */
public interface CellStateFactory {
    CellState initialState(CellCoordinates cellCoordinates);
}
