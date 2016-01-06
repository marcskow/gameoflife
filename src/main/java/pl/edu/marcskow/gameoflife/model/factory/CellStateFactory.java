
/**
 * Created by intenso on 06.12.15.
 */
package pl.edu.marcskow.gameoflife.model.factory;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.state.CellState;

/**
 * This interface should be implements by every class representing factory of cell states
 */
public interface CellStateFactory {
    /**
     * @param cellCoordinates
     * @return CellState
     */
    CellState initialState(CellCoordinates cellCoordinates);
}
