/**
 * @author Marcin Skowron
 * @version 1.0.2
 *
 * */
package pl.edu.marcskow.gameoflife.model.automat;

import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.model.state.CellState;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;

import java.util.*;

/**
 * Main class of automaton including map of cell coords and cell states.
 * It's an abstract class responsible for making new instance of automaton. Classes
 * of the specific automatons have to extend this class other specific class model.
 * @see pl.edu.marcskow.gameoflife.model.automat2dim.GameOfLife
 */
public abstract class Automaton {
    private Map<CellCoordinates, CellState> cells;
    private CellNeighborhood neighborsStrategy;
    private CellStateFactory stateFactory;

    public Automaton(CellStateFactory stateFactory, CellNeighborhood neighborsStrategy){
        this.stateFactory = stateFactory;
        this.neighborsStrategy = neighborsStrategy;
        this.cells = new HashMap<>();
    }

    /**
     * nextState method is responsible for creation a new generation of cellular automat
     * based on it's specific rules.
     * @return new generation of automat
     */
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

    /**
     * Insert new structure to the automat cells container
     * @param structure which will be included
     */
    public void insertStructure(Map<? extends CellCoordinates,? extends CellState> structure){
        cells.putAll(structure);
    }

    public CellIterator cellIterator(){
        return new CellIterator();
    }

    /**
     * When we use automaton class we can't predict which one automaton should be constructed
     * this method is responsible for making new instance of the specific automaton.
     * Specific cellular automaton extending Automaton must override this method.
     * @param cellStateFactory specific state factory using to initialize specific automaton state
     * @param cellNeighborhood neighborhood type using by the specific automaton
     * @return new instance of automaton
     */
    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood);

    /**
     * Abstract method which have to be extended in specific automaton
     * to check is next coordinates existing.
     * @param cellCoordinates coordinates to which we are looking for next coorinates
     * @return {@code true} if next coordinates exists, {@code false} otherwise
     */
    protected abstract boolean hasNextCoordinates(CellCoordinates cellCoordinates);

    /**
     * Abstract method. Specific automaton have to extends this method.
     * @return the coordinates before the first coordinates on map, e.q. for 2 dimension (-1,0)
     */
    protected abstract CellCoordinates initialCoordinates();

    /**
     * Abstract method. Specific automaton have to extends this method.
     * @param cellCoordinates coordinates from which we are looking for next coordinates
     * @return next coordinates to the given coordinates
     */
    protected abstract CellCoordinates nextCoordinates(CellCoordinates cellCoordinates);

    /**
     * Abstract method which have to be extended by specific automaton.
     * This method is used by nextState method to define state of cells in automaton map.
     * @param currentState of cell for which we will calculate new state
     * @param neighborsStates neighbors of the cell
     * @return new state of the cell
     */
    protected abstract CellState nextCellState(CellState currentState, Set<Cell> neighborsStates);

    /**
     * Method responsible for map coordinates from set of coordinates to set of cell.
     * Method assign cellstate to given cellcoordinates to make a cell instance and add it to the cell set.
     * @param cellCoordinates key values
     * @return set of cells
     */
    private Set<Cell> mapCoordinates(Set<CellCoordinates> cellCoordinates){
        Set<Cell> cellSet = new HashSet<>(cellCoordinates.size());

        for (CellCoordinates coords : cellCoordinates) {
            cellSet.add(new Cell(cells.get(coords),coords));
        }
        return cellSet;
    }

    /**
     * It's simple alternative form of state factory used for assign given cells map to the automaton
     * cells map.
     * @param map cells to assign
     */
    public void setMap(Map<CellCoordinates, CellState> map){
        cells = map;
    }

    /**
     * @return automaton cells
     */
    public Map<CellCoordinates, CellState> getCells() {
        return cells;
    }

    public CellNeighborhood getNeighborsStrategy() {
        return neighborsStrategy;
    }

    /**
     * Method responsible for updating neighbors strategy of automaton
     * @param neighborsStrategy new neighbors strategy
     */
    public void setNeighborsStrategy(CellNeighborhood neighborsStrategy) {
        this.neighborsStrategy = neighborsStrategy;
    }

    public CellStateFactory getStateFactory() {
        return stateFactory;
    }

    /**
     * Method responsible for updating state factory of automaton
     * @param stateFactory new state factory
     */
    public void setStateFactory(CellStateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }

    /**
     * Inner class. It's type of Iterator used for navigate through cells map of automaton.
     */
    private class CellIterator {
        /** Which cell iterator indicate now */
        private CellCoordinates currentState;

        public CellIterator(){
            currentState = initialCoordinates();
        }

        /**
         * Method use the {@link #hasNextCoordinates} method from automaton class to check is there next cells for
         * cell which iterator indicate now
         * @return {@code true} if next coordintates exists {@code false} otherwise
         */
        public boolean hasNext(){
            return hasNextCoordinates(currentState);
        }

        /**
         * Method use the {@link #nextCoordinates} method from automaton class to return the current cell state which
         * iterator indicate now and then jump to the next cell
         * @return current cell state
         */
        public Cell next() {
            currentState = nextCoordinates(currentState);
            return new Cell(cells.get(currentState), currentState);
        }

        /**
         * Method used to replace the cell which iterator indicate now with other cell
         * @param cellState which will be replaced with the current
         */
        public void setState(CellState cellState){
            cells.replace(currentState,cellState);
        }
    }
}