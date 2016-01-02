package pl.edu.marcskow.gameoflife.automat2dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.Map;

/**
 * Created by intenso on 20.12.15.
 */
public abstract class Automaton2Dim extends Automaton{
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Automaton2Dim(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        super(cellStateFactory, cellNeighborhood);
    }

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

    @Override
    public CellCoordinates initialCoordinates(){
        return new Coords2D(-1,0);
    }

    /**
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