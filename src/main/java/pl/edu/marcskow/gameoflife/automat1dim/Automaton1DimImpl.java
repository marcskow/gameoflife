package pl.edu.marcskow.gameoflife.automat1dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.Set;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class Automaton1DimImpl extends Automaton1Dim {

    public Automaton1DimImpl(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        super(cellStateFactory, cellNeighborhood);
    }

    @Override
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new Automaton1DimImpl(cellStateFactory,cellNeighborhood);
    }

    @Override
    protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates) {
        CellState[] neighbors = convertStatesFromSetToArray(neighborsStates);

        if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.ALIVE){
            return BinaryState.DEAD;
        }
        if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.DEAD){
            return BinaryState.DEAD;
        }
        if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.ALIVE){
            return BinaryState.DEAD;
        }
        if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.ALIVE){
            return BinaryState.DEAD;
        }
    }



    @Override
    protected CellCoordinates initialCoordinates() {
        return null;
    }

    private CellState[] convertStatesFromSetToArray(Set<Cell> neighbors){
        Cell[] neighborsStatesArray = new Cell[neighbors.size()];
        CellState[] cellStates = new CellState[neighbors.size()];
        neighbors.toArray(neighborsStatesArray);

        for(int i = 0; i < neighbors.size(); i++){
            cellStates[i] = neighborsStatesArray[i].getState();
        }

        return cellStates;
    }
}
