package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.gui.helpers.GameSettings;
import pl.edu.marcskow.gameoflife.helpers.Wrapper;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for finding neighborhoods of the cell
 * based on the VonNeuman Neighborhood properties.
 * Class implements CellNeighborhood interface.
 * @see CellNeighborhood
 */
public class VonNeumanNeighborhood implements CellNeighborhood {
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Wrapper wrapper = new Wrapper(GameSettings.ISWRAPPING);
        Set<CellCoordinates> cellNeighbors = new HashSet<>(4);

        Coords2D currentCoords = (Coords2D) cellCoordinates;
        int currentX = currentCoords.getX();
        int currentY = currentCoords.getY();

        for(int i = (-1) * GameSettings.RADIUS; i <= GameSettings.RADIUS; i++){
            if (i != 0) {
                if (GameSettings.ISWRAPPING) {
                    cellNeighbors.add(new Coords2D(wrapper.wrapIfWrapping(currentX, GameSettings.COLUMNS),
                            wrapper.wrapIfWrapping(currentY + i, GameSettings.ROWS)));
                    cellNeighbors.add(new Coords2D(wrapper.wrapIfWrapping(currentX + i,GameSettings.COLUMNS),
                            wrapper.wrapIfWrapping(currentY, GameSettings.ROWS)));
                }
                else{
                    if(wrapper.areInsideMap(currentX + i, currentY)){
                        cellNeighbors.add(new Coords2D(currentX + i, currentY));
                    }
                    if(wrapper.areInsideMap(currentX, currentY + i)){
                        cellNeighbors.add(new Coords2D(currentX, currentY + i));
                    }
                }
            }
        }

        return cellNeighbors;
    }
}
