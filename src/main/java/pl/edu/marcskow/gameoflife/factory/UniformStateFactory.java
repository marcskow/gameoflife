package pl.edu.marcskow.gameoflife.factory;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.state.CellState;

/**
 * Created by intenso on 06.12.15.
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
