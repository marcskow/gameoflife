package pl.edu.marcskow.gameoflife.factory;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.Map;

/**
 * Created by intenso on 06.12.15.
 */
public class GeneralStateFactory implements CellStateFactory{
    private Map<CellCoordinates,CellState> states;

    public GeneralStateFactory(Map<CellCoordinates,CellState> states){
        this.states = states;
    }

    @Override
    public CellState initialState(CellCoordinates cellCoordinates) {
        return states.get(cellCoordinates);
    }
}
