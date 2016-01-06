package pl.edu.marcskow.gameoflife.automat2dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.state.QuadState;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Marcin Skowron on 2016-01-04.
 */
public class QuadLife extends Automaton2Dim {

    public QuadLife(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int width, int height){
        super(cellStateFactory, cellNeighborhood);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new QuadLife(cellStateFactory, cellNeighborhood, getWidth(), getHeight());
    }

    @Override
    public CellState nextCellState(CellState currentState, Set<Cell> neighborsStates){

        int countOfAliveCells = 0;
        int countOfRedCells = 0;
        int countOfYellowCells = 0;
        int countOfBlueCells = 0;
        int countOfGreenCells = 0;
        for (Cell c: neighborsStates) {
            if(c.getState() == QuadState.BLUE) {
                countOfBlueCells++;
            }
            else if(c.getState() == QuadState.RED){
                countOfRedCells++;
            }
            else if(c.getState() == QuadState.YELLOW){
                countOfYellowCells++;
            }
            else if(c.getState() == QuadState.GREEN){
                countOfGreenCells++;
            }
        }
        countOfAliveCells = countOfBlueCells + countOfGreenCells + countOfRedCells + countOfYellowCells;
        /*
        Zasady gry:
        - Jeżeli komórka jest martwa i ma DOKLADNIE trzech żywych sąsiadów, to w następnej generacji staje się żywa,
        w przeciwnym wypadku nadal pozostaje martwa.
        - Jeżeli komórka jest żywa i ma 2 lub 3 sąsiadów, nadal pozostaje żywa, w przeciwnym wypadku staje się martwa.
         */
        if(currentState == QuadState.DEAD && countOfAliveCells == 3) {
            return theMostCount(countOfBlueCells,countOfYellowCells,countOfRedCells,countOfGreenCells);
        }
        else if (currentState != QuadState.DEAD && (countOfAliveCells == 2 || countOfAliveCells == 3)){
            return currentState;
        }
        else if (currentState != QuadState.DEAD){
            return QuadState.DEAD;
        }
        else {
            return currentState;
        }
    }

    public QuadState theMostCount(int countBlue, int countYellow, int countRed, int countGreen){
        Random generator = new Random();
        if (((countBlue > countYellow) && (countBlue > countRed) && (countBlue > countGreen))
        || (countGreen == countRed && countRed == countYellow)){
            return QuadState.BLUE;
        }
        else if (((countYellow > countBlue) && (countYellow > countRed) && (countYellow > countGreen))
                || (countGreen == countRed && countRed == countBlue)){
            return QuadState.YELLOW;
        }
        else if (((countRed > countYellow) && (countRed > countBlue) && (countRed > countGreen))
                || (countGreen == countBlue && countBlue == countYellow)){
            return QuadState.RED;
        }
        else if (((countGreen > countYellow) && (countGreen > countRed) && (countGreen > countBlue))
        ||(countRed == countBlue && countBlue == countYellow)){
            return QuadState.GREEN;
        }
        else if (countBlue == 0 && countGreen == 0 && countRed == countYellow){
            if(generator.nextInt() > 0) return QuadState.BLUE;
            else return QuadState.GREEN;
        }
        else if (countBlue == 0 && countRed == 0 && countGreen == countYellow){
            if(generator.nextInt() > 0) return QuadState.BLUE;
            else return QuadState.RED;
        }
        else if (countBlue == 0 && countYellow == 0 && countRed == countGreen){
            if(generator.nextInt() > 0) return QuadState.BLUE;
            else return QuadState.YELLOW;
        }
        else if (countRed == 0 && countGreen == 0 && countBlue == countYellow){
            if(generator.nextInt() > 0) return QuadState.RED;
            else return QuadState.GREEN;
        }
        else if (countRed == 0 && countYellow == 0 && countBlue == countGreen){
            if(generator.nextInt() > 0) return QuadState.YELLOW;
            else return QuadState.RED;
        }
        else if (countGreen == 0 && countYellow == 0 && countBlue == countRed){
            if(generator.nextInt() > 0) return QuadState.GREEN;
            else return QuadState.YELLOW;
        }
        return QuadState.DEAD;
    }

}
