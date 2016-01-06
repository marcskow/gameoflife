package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;

import java.util.Set;

/**
 * The CellNeighborhood interface should be implements by any class whose instances are
 * created for finding neighborhoods of the cell (based on various cellular automaton neighborhoods types)
 * @since A0.1
 */
public interface CellNeighborhood {
    /**
     *  When an object implementing interface CellNeighborhood is used to create a {@code Set<CellCoordinates>}
     *  which contain the cell neighbors.
     *  @param cellCoordinates coordinates of the cell which neighbors we are looking for
     *  @return the cell neighbors
     */
    Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates);
}
