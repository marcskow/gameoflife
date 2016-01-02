package pl.edu.marcskow.gameoflife.automat;

import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by intenso on 30.11.15.
 * @author intenso
 * @version 0.0.0
 * Main class of game of life model including map of cell coords and cell states.
 */

public abstract class Automaton {
    private Map<CellCoordinates, CellState> cells;
    private CellStateFactory stateFactory;
    private CellNeighborhood neighborsStrategy;

    public Automaton(CellStateFactory stateFactory, CellNeighborhood neighborsStrategy){
        this.stateFactory = stateFactory;
        this.neighborsStrategy = neighborsStrategy;
    }

    // TODO: 2015-12-30 the most important thing 
    public Automaton nextState(){
        Automaton newAutomaton = newInstance(stateFactory,neighborsStrategy);

        CellIterator it = new CellIterator();

        while(it.hasNext()){
            Cell c = it.next();
            Set<CellCoordinates> neighbors = neighborsStrategy.cellNeighborhoods(c.getCoords());
            Set<Cell> mappedNeighbors = mapCoordinates(neighbors);

            CellState newState = nextCellState(c.getState(), mappedNeighbors);
            newAutomaton.cells.put(c.getCoords(), nextCellState(c.getState(),mappedNeighbors));
        }
        return newAutomaton;
    }

    // TODO: 2015-12-30 is it good? probably 
    public void insertStructure(Map<? extends CellCoordinates,? extends CellState> structure){
        cells.putAll(structure);
    }

    public CellIterator iterator() {
        return new CellIterator();
    }

    // TODO: 2015-12-30 is it good? i think
    public CellIterator cellIterator(){
        return new CellIterator();
    }

    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood);
    protected abstract boolean hasNextCoordinates(CellCoordinates cellCoordinates);
    protected abstract CellCoordinates initialCoordinates();
    protected abstract CellState nextCellState(CellState currentState, Set<Cell> neighborsStates);

    private static Set<Cell> mapCoordinates(Set<CellCoordinates> cellCoordinates){
        Set<Cell> cellSet = new HashSet<>(cellCoordinates.size());

        // TODO: 2015-12-30 How?

        return null;
    }

    private class CellIterator {
        private CellCoordinates currentState;

        // TODO: 2015-12-30 is it good? probably yes
        public CellIterator(){
            currentState = initialCoordinates();
        }

        public CellIterator(CellCoordinates cellCoordinates){
            currentState = cellCoordinates;
        }

        public boolean hasNext(){
            return hasNextCoordinates(currentState);
        }

        // TODO: 2015-12-30 Is it good? Probably not
        public Cell next(){
            return new Cell(cells.get(currentState), currentState);
        }

        public void setState(CellState cellState){
            cells.replace(currentState,cellState);
        }

    }
}