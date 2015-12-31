package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords1D;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class Automaton1DimNeighborhood implements CellNeighborhood {
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>(1);

        Coords1D currentCoords = (Coords1D) cellCoordinates;
        cellNeighbors.add(new Coords1D(currentCoords.getX() - 1));
        cellNeighbors.add(new Coords1D(currentCoords.getX() + 1));

        return cellNeighbors;
    }
}
