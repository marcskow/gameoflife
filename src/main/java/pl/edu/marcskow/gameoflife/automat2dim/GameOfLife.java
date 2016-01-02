package pl.edu.marcskow.gameoflife.automat2dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.Map;
import java.util.Set;

/**
 * Created by intenso on 20.12.15.
 */
public class GameOfLife extends Automaton2Dim {

    public GameOfLife(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        super(cellStateFactory, cellNeighborhood);
    }

    @Override
    public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new GameOfLife(cellStateFactory, cellNeighborhood);
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
        if(currentState == BinaryState.DEAD && countOfAliveCells == 3) {
            return BinaryState.ALIVE;
        }
        else if (currentState == BinaryState.DEAD && countOfAliveCells != 3){
            return currentState;
        }
        else if (currentState == BinaryState.ALIVE && (countOfAliveCells == 2 || countOfAliveCells == 3)){
            return BinaryState.ALIVE;
        }
        else if (currentState == BinaryState.ALIVE && countOfAliveCells != 2 && countOfAliveCells != 3){
            return BinaryState.DEAD;
        }
        else{
            return currentState;
        }
    }
}