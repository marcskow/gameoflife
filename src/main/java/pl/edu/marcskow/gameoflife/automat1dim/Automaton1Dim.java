package pl.edu.marcskow.gameoflife.automat1dim;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;

/**
 * Created by intenso on 20.12.15.
 */
public abstract class Automaton1Dim extends Automaton {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Automaton1Dim(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        super(cellStateFactory, cellNeighborhood);
    }

    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates){
        if (((Coords1D) cellCoordinates).getX() == size - 1){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    protected CellCoordinates initialCoordinates() {
        return new Coords1D(-1);
    }

    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates){
        return new Coords1D(((Coords1D) cellCoordinates).getX() + 1);
    }
}