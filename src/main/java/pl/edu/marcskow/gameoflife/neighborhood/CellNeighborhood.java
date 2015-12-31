package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;

import java.util.Set;

/**
 * Created by intenso on 06.12.15.
 */
public interface CellNeighborhood {
    Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates);
}
