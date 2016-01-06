/**
 * Created by Marcin Skowron on 20.12.15.
 */
package pl.edu.marcskow.gameoflife.model.automat2dim;

import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.CellState;
import pl.edu.marcskow.gameoflife.model.state.WireElectronState;

import java.util.Set;

/**
 * kind of 2 dimensional automaton it's a type automaton with specific cells having
 * four states: wire, electron_head, electron_tail and none. Next cell is calculate based on the
 * neighors of cell.
 * @see Automaton
 * @see Automaton2Dim
 */
public class WireWorld extends Automaton2Dim{
    /**
     * @param cellStateFactory type of cell state factory of this automaton
     * @param cellNeighborhood type of automaton neighborhood
     * @param width number of map columns
     * @param height number of map rows
     */
    public WireWorld(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int width, int height){
        super(cellStateFactory, cellNeighborhood);
        setWidth(width);
        setHeight(height);
    }

    /**
     * returning new instance of WireWorld automaton
     * @param cellStateFactory state factory using to initialize WireWorld automaton state
     * @param cellNeighborhood neighborhood type using by the WireWorld
     * @return new instance of WireWorld automaton
     */
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new WireWorld(cellStateFactory, cellNeighborhood, getWidth(), getHeight());
    }

    /**
     * In the wireworld state of cell is based on neighbors states. Wire becoming electron head if there is
     * 1 or 2 electron heads near to it. Electron head becoming electron tail, and electron tail becoming wire,
     * because elementary charges is still running.
     * @param currentState of cell for which we will calculate new state
     * @param neighborsStates neighbors of the cell
     * @return WireElectronState
     */
    public CellState nextCellState(CellState currentState, Set<Cell> neighborsStates){
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