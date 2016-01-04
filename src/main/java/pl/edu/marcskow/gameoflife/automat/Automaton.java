package pl.edu.marcskow.gameoflife.automat;

import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.gui.AutomatonController;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by intenso on 30.11.15.
 * @author intenso
 * @version 0.0.0
 * Main class of game of life model including map of cell coords and cell states.
 */

public abstract class Automaton {
    private static final Logger log = Logger.getLogger(Automaton.class.getName());

    private Map<CellCoordinates, CellState> cells;
    private CellNeighborhood neighborsStrategy;
    private CellStateFactory stateFactory;

    public Automaton(CellStateFactory stateFactory, CellNeighborhood neighborsStrategy){
        this.stateFactory = stateFactory;
        this.neighborsStrategy = neighborsStrategy;
        this.cells = new HashMap<>();
    }

    public Automaton nextState(){
        Automaton newAutomaton = newInstance(stateFactory,neighborsStrategy);

        CellIterator it = cellIterator();

        while(it.hasNext()){
            Cell c = it.next();
            Set<CellCoordinates> neighbors = neighborsStrategy.cellNeighborhoods(c.getCoords());
            Set<Cell> mappedNeighbors = mapCoordinates(neighbors);

            CellState newState = nextCellState(c.getState(), mappedNeighbors);
            newAutomaton.cells.put(c.getCoords(), newState);
        }
        return newAutomaton;
    }

    public void insertStructure(Map<? extends CellCoordinates,? extends CellState> structure){
        cells.putAll(structure);
    }

    public CellIterator cellIterator(){
        return new CellIterator();
    }

    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood);
    protected abstract boolean hasNextCoordinates(CellCoordinates cellCoordinates);
    protected abstract CellCoordinates initialCoordinates();
    protected abstract CellCoordinates nextCoordinates(CellCoordinates cellCoordinates);
    protected abstract CellState nextCellState(CellState currentState, Set<Cell> neighborsStates);

    private Set<Cell> mapCoordinates(Set<CellCoordinates> cellCoordinates){
        Set<Cell> cellSet = new HashSet<>(cellCoordinates.size());

        for (CellCoordinates coords : cellCoordinates) {
            cellSet.add(new Cell(cells.get(coords),coords));
        }
        return cellSet;
    }

    public void setMap(Map<CellCoordinates, CellState> map){
        cells = map;
    }

    public Map<CellCoordinates, CellState> getCells() {
        return cells;
    }

    private class CellIterator {
        private CellCoordinates currentState;

        public CellIterator(){
            currentState = initialCoordinates();
        }

        public boolean hasNext(){
            return hasNextCoordinates(currentState);
        }

        public Cell next() {
            currentState = nextCoordinates(currentState);
            return new Cell(cells.get(currentState), currentState);
        }

        public void setState(CellState cellState){
            cells.replace(currentState,cellState);
        }
    }
}