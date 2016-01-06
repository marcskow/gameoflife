/**
 * Created by Marcin Skowron on 20.12.15.
 */
package pl.edu.marcskow.gameoflife.model.automat2dim;


import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.AntState;
import pl.edu.marcskow.gameoflife.model.state.BinaryState;
import pl.edu.marcskow.gameoflife.model.state.CellState;
import pl.edu.marcskow.gameoflife.model.state.LangtonCell;

import java.util.Set;
import java.util.logging.Logger;

/**
 * kind of 2 dimensional automaton it's implementation of langton ant automaton
 * next state of cell is based on ant state
 * @see Automaton
 * @see Automaton2Dim
 */
public class LangtonAnt extends Automaton2Dim{
    /**
     * all of parameters are definitely needed
     * @param cellStateFactory type of cell state factory of this automaton
     * @param cellNeighborhood type of automaton neighborhood
     * @param width number of map columns
     * @param height number of map rows
     */
    public LangtonAnt(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int width, int height){
        super(cellStateFactory, cellNeighborhood);
        this.setWidth(width);
        this.setHeight(height);;
    }


    /**
     * returning new instance of langton ant automaton
     * @param cellStateFactory state factory using to initialize automaton state
     * @param cellNeighborhood neighborhood type using by the automaton
     * @return new instance of LangtonAnt automaton
     */
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new LangtonAnt(cellStateFactory, cellNeighborhood, getWidth(), getHeight());
    }

    /**
     * in langton ant next state of cells is basing on the ant state, if there is an ant the cell state is
     * becoming next state because ant making cellstate to the next, if there is not any ant we are looking
     * is there ant coming to the cell, if it is the state is becoming from none to ant
     * @param currentState of cell for which we will calculate new state
     * @param neighborsStates neighbors of the cell
     * @return next CellState of the current cell
     */
    protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates){
        Coords2D currentCoordinates = (Coords2D)currentCoordinates(neighborsStates);
        int x = currentCoordinates.getX();
        int y = currentCoordinates.getY();

        LangtonCell currentLangtonCell = (LangtonCell) currentState;
        BinaryState currentBinaryState = currentLangtonCell.getCellState();
        AntState currentAntState = currentLangtonCell.getAntState();

        LangtonCell result = new LangtonCell(currentBinaryState,currentAntState);

        if(currentAntState != AntState.NONE){
            result.setAntState(AntState.NONE);
            result.setCellState((BinaryState)currentBinaryState.nextState());
        }

        for (Cell c : neighborsStates) {
            if ((ifCoordsEqual(c, x + 1, y) && ifStatesEqual(c, BinaryState.ALIVE, AntState.SOUTH))) {
                result.setAntState(AntState.WEST);
            } else if ((ifCoordsEqual(c, x + 1, y) && ifStatesEqual(c, BinaryState.DEAD, AntState.NORTH))) {
                result.setAntState(AntState.WEST);
            } else if ((ifCoordsEqual(c, x - 1, y) && ifStatesEqual(c, BinaryState.DEAD, AntState.SOUTH))) {
                result.setAntState(AntState.EAST);
            } else if ((ifCoordsEqual(c, x - 1, y) && ifStatesEqual(c, BinaryState.ALIVE, AntState.NORTH))) {
                result.setAntState(AntState.EAST);
            } else if ((ifCoordsEqual(c, x, y - 1) && ifStatesEqual(c, BinaryState.DEAD, AntState.WEST))) {
                result.setAntState(AntState.SOUTH);
            } else if ((ifCoordsEqual(c, x, y - 1) && ifStatesEqual(c, BinaryState.ALIVE, AntState.EAST))) {
                result.setAntState(AntState.SOUTH);
            } else if ((ifCoordsEqual(c, x, y + 1) && ifStatesEqual(c, BinaryState.DEAD, AntState.EAST))) {
                result.setAntState(AntState.NORTH);
            } else if ((ifCoordsEqual(c, x, y + 1) && ifStatesEqual(c, BinaryState.ALIVE, AntState.WEST))) {
                result.setAntState(AntState.NORTH);
            }
        }

        return result;
    }

    /**
     * checking if given coords are equals to the Cell coord
     * @param c cell to check
     * @param x x coordinate to check
     * @param y y coordinate to check
     * @return {@code true} if it is equal {@code false} if not
     */
    private boolean ifCoordsEqual(Cell c, int x, int y){
        return ((Coords2D)c.getCoords()).getX() == x && ((Coords2D)c.getCoords()).getY() == y;
    }

    /**
     * check if the Cell c state is equal to LangtonCell with given fields
     * @param c checking cell
     * @param state binary state of langton cell
     * @param ant  ant state of langton cell
     * @return {@code true} if it is equal {@code false} if not
     */
    private boolean ifStatesEqual(Cell c, BinaryState state, AntState ant){
        return ((((LangtonCell)c.getState()).getCellState() == state) && ((LangtonCell) c.getState()).getAntState() == ant) ;
    }

    /**
     * Calculate coordinates of current cell from cell neighbors
     * @param neighbors neighbors of cell
     * @return CellCoordinates of current cell
     */
    private CellCoordinates currentCoordinates(Set<Cell> neighbors){
        int sumOfAllXCoordinates = 0;
        int sumOfAllYCoordinates = 0;

        for (Cell c : neighbors) {
            sumOfAllXCoordinates += ((Coords2D) c.getCoords()).getX();
            sumOfAllYCoordinates += ((Coords2D) c.getCoords()).getY();
        }

        return new Coords2D(sumOfAllXCoordinates / neighbors.size(), sumOfAllYCoordinates / neighbors.size());
    }
}