/**
 * Created by Marcin Skowron on 20.12.15.
 *
 */
package pl.edu.marcskow.gameoflife.model.automat2dim;

import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;

/**
 * This class is mother class for every 2 dimensional automaton
 * @see Automaton
 * @see GameOfLife
 */
public abstract class Automaton2Dim extends Automaton{
    /** number of the map columns */
    private int width;
    /** number of the map rows */
    private int height;

    /**
     * @return map columns
     */
    public int getWidth() {
        return width;
    }

    /**
     * set the map columns
     * @param width number of columns
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return number of map rows
     */
    public int getHeight() {
        return height;
    }

    /**
     * set the map rows
     * @param height number of rows
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public Automaton2Dim(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        super(cellStateFactory, cellNeighborhood);
    }

    /**
     * Method using for calculate next coordinates in 2 dimensional automatons.
     * @param cellCoordinates coordinates to which we are looking for next coorinates
     * @return next coordinates to given coordinates
     */
    @Override
    public boolean hasNextCoordinates(CellCoordinates cellCoordinates){
        Coords2D cellCoords = (Coords2D)cellCoordinates;

        /* User set width on 10 so now x coordinates are 0 1 2 3 4 5 6 7 8 9
        that's why width - 1, tha same for y */
        if(cellCoords.getY() == height - 1 && cellCoords.getX() == width - 1) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * returning initial coordinates for 2 dimensional automaton it's always (-1,0) for 2 dimensional map
     * what is the cell coordinates before first cell
     * @return Coords2D with x = -1 and y = 0
     */
    @Override
    public CellCoordinates initialCoordinates(){
        return new Coords2D(-1,0);
    }

    /**
     * For 2 dimensional automaton next coordinates is currentX + 1, currentY or if the currentY is the last
     * one returning 0, currentY + 1
     * @param cellCoordinates coordinates for which we are finding the next one
     * @return Coords2D next coordinates for a given coordinates
     */
    public CellCoordinates nextCoordinates(CellCoordinates cellCoordinates){
        Coords2D cellCoords = (Coords2D)cellCoordinates;
        int previousX = cellCoords.getX();
        int previousY = cellCoords.getY();

        if(previousX == width - 1){
            return new Coords2D(0, previousY + 1);
        }
        else{
            return new Coords2D(previousX + 1, previousY);
        }
    }
}