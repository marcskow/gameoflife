package pl.edu.marcskow.gameoflife.automat2dim;


import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.AntState;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.state.LangtonCell;

import java.util.Set;

/**
 * Created by intenso on 20.12.15.
 */
public class LangtonAnt extends Automaton2Dim{

    public LangtonAnt(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        super(cellStateFactory, cellNeighborhood);
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new LangtonAnt(cellStateFactory, cellNeighborhood);
    }

    protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates){
        Coords2D currentCoordinates = (Coords2D)currentCoordinates(neighborsStates);
        LangtonCell currentLangtonCell = (LangtonCell) currentState;

        // TODO: 2015-12-31 do it

        return currentState;
    }

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