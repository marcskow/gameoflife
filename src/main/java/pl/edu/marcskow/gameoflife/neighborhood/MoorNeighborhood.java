package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.gui.helpers.GameSettings;
import pl.edu.marcskow.gameoflife.helpers.Wrapper;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for finding neighborhoods of the cell
 * based on the Moore Neighborhood properties.
 * Class implements CellNeighborhood interface.
 * @see CellNeighborhood
 */
public class MoorNeighborhood implements CellNeighborhood {
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>();
        Wrapper wrapper = new Wrapper(GameSettings.ISWRAPPING);

        Coords2D currentCoords = (Coords2D) cellCoordinates;
        int currentX = currentCoords.getX();
        int currentY = currentCoords.getY();

        for(int i = (-1)*GameSettings.RADIUS; i <= GameSettings.RADIUS; i++){
            for (int j = (-1)*GameSettings.RADIUS; j <= GameSettings.RADIUS; j++){
                if ((!(i == 0 && j == 0))) {
                    if(GameSettings.ISWRAPPING){
                        cellNeighbors.add(new Coords2D(wrapper.wrapIfWrapping(currentX + i, GameSettings.COLUMNS),
                                wrapper.wrapIfWrapping(currentY + j, GameSettings.ROWS)));
                    }
                    else if(!GameSettings.ISWRAPPING && wrapper.areInsideMap(currentX + i, currentY + j)){
                        cellNeighbors.add(new Coords2D(currentX + i, currentY + j));
                    }
                }
            }
        }
        return cellNeighbors;
    }
}