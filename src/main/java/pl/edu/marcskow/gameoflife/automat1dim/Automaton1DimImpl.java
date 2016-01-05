package pl.edu.marcskow.gameoflife.automat1dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.Set;

/**
 *
 * Created by Marcin Skowron on 2015-12-31.
 */
public class Automaton1DimImpl extends Automaton1Dim {

    public Automaton1DimImpl(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int size) {
        super(cellStateFactory, cellNeighborhood);
        setSize(size);
    }

    @Override
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new Automaton1DimImpl(cellStateFactory,cellNeighborhood,getSize());
    }

    @Override
    protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates) {
        CellState[] neighbors = convertStatesFromSetToArray(neighborsStates);

        int[] results = returnTheNumberOnTheBinarySystem(110);
        BinaryState[] resultStates = convertTheNumberToCellState(results);

        if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.ALIVE){
            return resultStates[0];
        }
        else if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.DEAD){
            return resultStates[1];
        }
        else if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.DEAD && neighbors[1] == BinaryState.ALIVE){
            return resultStates[2];
        }
        else if(neighbors[0] == BinaryState.ALIVE && currentState == BinaryState.DEAD && neighbors[1] == BinaryState.DEAD){
            return resultStates[3];
        }
        else if(neighbors[0] == BinaryState.DEAD && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.ALIVE){
            return resultStates[4];
        }
        else if(neighbors[0] == BinaryState.DEAD && currentState == BinaryState.ALIVE && neighbors[1] == BinaryState.DEAD){
            return resultStates[5];
        }
        else if(neighbors[0] == BinaryState.DEAD && currentState == BinaryState.DEAD && neighbors[1] == BinaryState.ALIVE){
            return resultStates[6];
        }
        else if(neighbors[0] == BinaryState.DEAD && currentState == BinaryState.DEAD && neighbors[1] == BinaryState.DEAD){
            return resultStates[7];
        }
        else {
            return currentState;
        }
    }

    private int[] returnTheNumberOnTheBinarySystem(int number){
        if (number > 255){
            return null;
        }
        int[] binaryNumber = new int[8];
        int i = 0;
        while(number > 1){
            binaryNumber[i++] = number % 2;
            number /= 2;
        }
        return binaryNumber;
    }

    private BinaryState[] convertTheNumberToCellState(int[] binaryNumber){
        BinaryState[] cellState = new BinaryState[binaryNumber.length];
        for (int i = 0; i < binaryNumber.length; i++){
            if (binaryNumber[i] == 0) {
                cellState[i] = BinaryState.DEAD;
            }
            else {
                cellState[i] = BinaryState.ALIVE;
            }
        }
        return cellState;
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
