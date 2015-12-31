package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by intenso on 06.12.15.
 * Sąsiedztwo More'a czyli trzy komórki nad, komórka z lewej, komórka z prawej i trzy komórki pod.
 */
public class MoreNeighborhood implements CellNeighborhood {
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>(8);

        Coords2D currentCoords = (Coords2D) cellCoordinates;
        int currentX = currentCoords.getX();
        int currentY = currentCoords.getY();

        cellNeighbors.add(new Coords2D(currentX - 1, currentY + 1));
        cellNeighbors.add(new Coords2D(currentX,     currentY + 1));
        cellNeighbors.add(new Coords2D(currentX + 1, currentY + 1));

        cellNeighbors.add(new Coords2D(currentX - 1, currentY));
        cellNeighbors.add(new Coords2D(currentX + 1, currentY));

        cellNeighbors.add(new Coords2D(currentX - 1, currentY - 1));
        cellNeighbors.add(new Coords2D(currentX,     currentY - 1));
        cellNeighbors.add(new Coords2D(currentX + 1, currentY - 1));

        return cellNeighbors;
    }
}
