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

    public int getMapSizeY() {
        return mapSizeY;
    }

    public void setMapSizeY(int mapSizeY) {
        this.mapSizeY = mapSizeY;
    }

    public int getMapSizeX() {
        return mapSizeX;
    }

    public void setMapSizeX(int mapSizeX) {
        this.mapSizeX = mapSizeX;
    }

    public static int nextXForNeighbourWhenMapWprapped(int currentX){
        if(currentX < mapSizeX){
            return currentX + 1;
        }
        else {
            return 0;
        }
    }

    public static int nextYForNeighbourWhenMapWprapped(int currentY){
        if(currentY < mapSizeY){
            return currentY + 1;
        }
        else {
            return 0;
        }
    }

    public static  int previousXForNeighbourWhenMapWprapped(int previousX){
        if(previousX > 0){
            return previousX - 1;
        }
        else {
            return mapSizeX - 1;
        }
    }

    public static int previousYForNeighbourWhenMapWprapped(int previousY){
        if(previousY > 0){
            return previousY - 1;
        }
        else {
            return mapSizeY + 1;
        }
    }

    public static int nextX(int x){
        if (wrapping) return nextXForNeighbourWhenMapWprapped(x);
        else return x+1;
    }
    public static int nextY(int Y){
        if (wrapping) return nextYForNeighbourWhenMapWprapped(Y);
        else return Y+1;
    }
    public static int previousX(int x){
        if (wrapping) return previousXForNeighbourWhenMapWprapped(x);
        else return x-1;
    }
    public static int previousY(int y){
        if (wrapping) return previousYForNeighbourWhenMapWprapped(y);
        else return y-1;
    }
}
