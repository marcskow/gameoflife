package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.gui.CoordinatesService;

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

        if(currentCoords.getX() - 1 >= 0) {
            cellNeighbors.add(new Coords1D(currentCoords.getX() - 1));
        }
        if(currentCoords.getX() + 1 < CoordinatesService.columns*CoordinatesService.rows){
            cellNeighbors.add(new Coords1D(currentCoords.getX() + 1));
        }
        if(CoordinatesService.isWrapping){
            if(currentCoords.getX() - 1 == -1){
                cellNeighbors.add(new Coords1D(CoordinatesService.columns*CoordinatesService.rows - 1));
            }
            if(currentCoords.getX() + 1 == CoordinatesService.columns*CoordinatesService.rows){
                cellNeighbors.add(new Coords1D(0));
            }
        }
        return cellNeighbors;
    }
}
