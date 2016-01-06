package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.gui.helpers.GameSettings;
import pl.edu.marcskow.gameoflife.helpers.Wrapper;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for finding neighborhoods of the cell
 * based on the Elementary Neighborhood properties.
 * Class implements CellNeighborhood interface.
 * @see CellNeighborhood
 */
public class ElementaryNeighborhood implements CellNeighborhood {
    /**
     * @see CellNeighborhood#cellNeighborhoods(CellCoordinates)
     * @param cellCoordinates coordinates of the cell which neighbors we are looking for
     * @return the cell neighbors
     */
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>(1);
        int mapSize = GameSettings.COLUMNS * GameSettings.ROWS;
        int currentX = ((Coords1D) cellCoordinates).getX();
        Wrapper wrapper = new Wrapper(GameSettings.ISWRAPPING);

        if(wrapper.wrapIfWrapping(currentX - 1, mapSize) >= 0) {
            cellNeighbors.add(new Coords1D(wrapper.wrapIfWrapping(currentX - 1, mapSize)));
        }
        if(wrapper.wrapIfWrapping(currentX + 1, mapSize) < mapSize){
            cellNeighbors.add(new Coords1D(wrapper.wrapIfWrapping(currentX + 1, mapSize)));
        }
        return cellNeighbors;
    }
}
