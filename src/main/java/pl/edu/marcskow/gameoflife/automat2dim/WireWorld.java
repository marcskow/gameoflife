package pl.edu.marcskow.gameoflife.automat2dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.state.WireElectronState;

import java.util.Set;

/**
 * Created by intenso on 20.12.15.
 */
public class WireWorld extends Automaton2Dim{

    public WireWorld(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        super(cellStateFactory, cellNeighborhood);
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new WireWorld(cellStateFactory, cellNeighborhood);
    }

    protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates){
        int countOfElectronHeads = 0;

        for (Cell c: neighborsStates) {
            if(c.getState() == WireElectronState.ELECTRON_HEAD) {
                countOfElectronHeads++;
            }
        }

        if (currentState == WireElectronState.ELECTRON_HEAD){
            return WireElectronState.ELECTRON_TAIL;
        }
        else if(currentState == WireElectronState.ELECTRON_TAIL){
            return WireElectronState.WIRE;
        }
        else if(currentState == WireElectronState.WIRE && (countOfElectronHeads == 1 || countOfElectronHeads == 2)){
            return WireElectronState.ELECTRON_HEAD;
        }
        else {
            return currentState;
        }

    }
}