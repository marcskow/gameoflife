package pl.edu.marcskow.gameoflife.model.factory;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.state.CellState;

/**
 * It's a type of cellStateFactory using for initialize state of the cell
 */
public class UniformStateFactory implements CellStateFactory {
    private CellState state;

    public UniformStateFactory(CellState state){
        this.state = state;
    }

    @Override
    public CellState initialState(CellCoordinates cellCoordinates) {
        return state;
    }
}
