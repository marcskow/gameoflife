package pl.edu.marcskow.gameoflife.automat2dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.gui.AutomatonController;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by intenso on 20.12.15.
 */
public class GameOfLife extends Automaton2Dim {
    private static final Logger log = Logger.getLogger(GameOfLife.class.getName());

    public GameOfLife(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int width, int height){
        super(cellStateFactory, cellNeighborhood);
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new GameOfLife(cellStateFactory, cellNeighborhood, getWidth(), getHeight());
    }

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
        if(currentState == BinaryState.DEAD && isNumberInArray(countOfAliveCells,AutomatonController.newCellRule)) {
            return BinaryState.ALIVE;
        }
        else if (currentState == BinaryState.ALIVE && isNumberInArray(countOfAliveCells,AutomatonController.surviveRule)){
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

    public boolean isNumberInArray(int number, int[] array){
        for (int i: array) {
            if(number == i) return true;
        }
        return false;
    }
}