/**
 * Created by Marcin Skowron on 2015-12-31.
 * @version 1.1
 */
package pl.edu.marcskow.gameoflife.model.automat1dim;

import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.BinaryState;
import pl.edu.marcskow.gameoflife.model.state.CellState;

import java.util.Set;

/**
 * One dimensional automaton extending Automaton1Dim abstract class.
 * This automaton is based on elementary cellular automaton rules. There are 256 rules
 * which may be defined and used by this automaton nextCellState method.
 * @see Automaton1Dim
 * @see Automaton
 */
public class Elementary extends Automaton1Dim {
    private int elementaryRule;

    /**
     * Elementary constructor needs cell state factory, neighborhood type and size of map.
     * @param cellStateFactory type of state factory
     * @param cellNeighborhood type of neighborhood
     * @param size size of map
     */
    public Elementary(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int size, int rule) {
        super(cellStateFactory, cellNeighborhood);
        setSize(size);
        this.elementaryRule = rule;
    }

    /**
     * @param cellStateFactory specific state factory using to initialize automaton state
     * @param cellNeighborhood neighborhood type using by the automaton
     * @return new instance of elementary automaton with specific cell state factory, neighborhood type and size.
     */
    @Override
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new Elementary(cellStateFactory,cellNeighborhood,getSize(), getRule());
    }

    /**
     * In elementary automaton next cell state is based on one of 256 rules, every rule define
     * another type of surviving and new cell creating rules.
     * @param currentState of cell for which we will calculate new state
     * @param neighborsStates neighbors of the cell
     * @return new CellState of Cell
     */
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

    /**
     * Calculate the combinations of rules using given number from 0 to 255
     * @param number number to calculate rule, if number is not on 0-255 it calculate default rules
     * @return array of binary numbers - surviving rules
     */
    private int[] returnTheNumberOnTheBinarySystem(int number){
        if (number > 255 || number < 0){
            number = 30;
        }
        int[] binaryNumber = new int[8];
        int i = 0;
        while(number > 1){
            binaryNumber[i++] = number % 2;
            number /= 2;
        }
        return binaryNumber;
    }

    /**
     * Converting binary 1 or 0 numbers to binary states 1 equal Alive 0 equal Dead.
     * @param binaryNumber is a given numbers calculated by returnTheNumberOnTheBinarySystem method
     * @return array of binary states
     */
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

    /**
     * Convert given set of cells to the array of CellState
     * @param neighbors set of cells
     * @return cell states array of cells from set
     */
    private CellState[] convertStatesFromSetToArray(Set<Cell> neighbors){
        Cell[] neighborsStatesArray = new Cell[neighbors.size()];
        CellState[] cellStates = new CellState[neighbors.size()];
        neighbors.toArray(neighborsStatesArray);

        for(int i = 0; i < neighbors.size(); i++){
            cellStates[i] = neighborsStatesArray[i].getState();
        }

        return cellStates;
    }

    public int getRule() {
        return elementaryRule;
    }
}
