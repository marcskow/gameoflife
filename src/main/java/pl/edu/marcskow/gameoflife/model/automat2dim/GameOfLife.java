/**
 * Created by Marcin Skowron on 20.12.15.
 */
package pl.edu.marcskow.gameoflife.model.automat2dim;

import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.controller.helpers.GameSettings;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.BinaryState;
import pl.edu.marcskow.gameoflife.model.state.CellState;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Kind of 2 dimensional automaton it's implementation of convay's game of life cellular automaton
 * next state of cell is based on the given rules of surviving and new cell borning rules
 * which are defined in automaton simulation
 * @see Automaton
 * @see Automaton2Dim
 */
public class GameOfLife extends Automaton2Dim {

    private int[] surviveRule;
    private int[] newBornRule;

    /**
     * it's the only one constructor cause all of parameters are definitely needed
     * @param cellStateFactory type of cell state factory of this automaton
     * @param cellNeighborhood type of automaton neighborhood
     * @param width number of map columns
     * @param height number of map rows
     */
    public GameOfLife(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int width, int height, int[] survive, int[] newRule){
        super(cellStateFactory, cellNeighborhood);
        this.setWidth(width);
        this.setHeight(height);
        this.surviveRule = survive;
        this.newBornRule = newRule;
    }

    /**
     * returning new instance of game of life automaton
     * @param cellStateFactory state factory using to initialize game of life automaton state
     * @param cellNeighborhood neighborhood type using by the automaton
     * @return new instance of GameOfLife
     */
    @Override
    public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new GameOfLife(cellStateFactory, cellNeighborhood, getWidth(), getHeight(), getSurviveRule(), getNewBornRule());
    }

    /**
     * in game of life next state of cells is basing on the neighbors states and rule of new cell borning
     * and rule of old cell surviving, default rule is that cell begin alive when it was dead and it has
     * 3 alive neighbours, and if it has 2 or 3 neighbours and it's alive it will be still alive
     * @param currentState of cell for which we will calculate new state
     * @param neighborsStates neighbors of the cell
     * @return next CellState of the current cell
     */
    @Override
    public CellState nextCellState(CellState currentState, Set<Cell> neighborsStates){
        int countOfAliveCells = 0;
        for (Cell c: neighborsStates) {

            if(c.getState() == BinaryState.ALIVE) {
                countOfAliveCells++;
            }
        }
        /*
        Zasady gry:
        - Jeżeli komórka jest martwa i ma DOKLADNIE trzech żywych sąsiadów, to w następnej generacji staje się żywa,
        w przeciwnym wypadku nadal pozostaje martwa.
        - Jeżeli komórka jest żywa i ma 2 lub 3 sąsiadów, nadal pozostaje żywa, w przeciwnym wypadku staje się martwa.
         */
        if(currentState == BinaryState.DEAD && isNumberInArray(countOfAliveCells, newBornRule)) {
            return BinaryState.ALIVE;
        }
        else if (currentState == BinaryState.ALIVE && isNumberInArray(countOfAliveCells,surviveRule)){
            return BinaryState.ALIVE;
        }
        else if (currentState == BinaryState.ALIVE){
            return BinaryState.DEAD;
        }
        else if (currentState == BinaryState.DEAD){
            return currentState;
        }
        else return currentState;
    }

    /**
     * method to check is the number of living cell neighbours is the same as survive or new cell born rule
     * @param number count of alive neighbours
     * @param array rule
     * @return {@code true} if it meets rule {@code false} otherwise
     */
    public boolean isNumberInArray(int number, int[] array){
        for (int i: array) {
            if(number == i) return true;
        }
        return false;
    }

    public int[] getSurviveRule() {
        return surviveRule;
    }

    public int[] getNewBornRule() {
        return newBornRule;
    }
}