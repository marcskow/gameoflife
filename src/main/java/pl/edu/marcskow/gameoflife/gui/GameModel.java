package pl.edu.marcskow.gameoflife.gui;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;

/**
 * Created by Marcin Skowron on 2016-01-03.
 */
public class GameModel {
    private Automaton automaton;
    private CellNeighborhood neighborhood;
    private CellStateFactory stateFactory;
    public static boolean wrapping;
    public static int mapSizeX;
    public static int mapSizeY;

    public GameModel(CellNeighborhood cellNeighborhood, CellStateFactory cellStateFactory){
        this.neighborhood = cellNeighborhood;
        this.stateFactory = cellStateFactory;
        automaton = new GameOfLife(cellStateFactory, cellNeighborhood);
    }

    public boolean isWrapping() {
        return wrapping;
    }

    public void setWrapping(boolean wrapping) {
        this.wrapping = wrapping;
    }

    public Automaton getAutomaton() {
        return automaton;
    }

    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
    }

    public CellStateFactory getStateFactory() {
        return stateFactory;
    }

    public void setStateFactory(CellStateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }

    public CellNeighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(CellNeighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

}
