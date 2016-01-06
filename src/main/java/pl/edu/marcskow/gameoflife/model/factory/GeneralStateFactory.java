/**
 * Created by intenso on 06.12.15.
 */
package pl.edu.marcskow.gameoflife.model.factory;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.state.CellState;

import java.util.Map;

/**
 * It's a type of cellStateFactory using for initialize state of the cell
 * @see CellStateFactory
 */
public class GeneralStateFactory implements CellStateFactory{
    /** Container of CellStates and keys: CellCoordinates of his states*/
    private Map<CellCoordinates,CellState> states;

    public GeneralStateFactory(Map<CellCoordinates,CellState> states){
        this.states = states;
    }

    /**
     * Initialize state of cell coordinates
     * @param cellCoordinates
     * @return CellState
     */
    @Override
    public CellState initialState(CellCoordinates cellCoordinates) {
        return states.get(cellCoordinates);
    }
}
