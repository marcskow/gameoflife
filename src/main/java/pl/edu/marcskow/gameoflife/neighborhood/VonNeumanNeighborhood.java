package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.gui.AutomatonController;
import pl.edu.marcskow.gameoflife.gui.CoordinatesService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by intenso on 06.12.15.
 * Sąsiedztwo Neumana czyli komórka nad, z lewej, z prawej i u dołu.
 */
public class VonNeumanNeighborhood implements CellNeighborhood {
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>(4);

        Coords2D currentCoords = (Coords2D) cellCoordinates;
        int currentX = currentCoords.getX();
        int currentY = currentCoords.getY();

        for(int i = (-1) * AutomatonController.radius; i <= AutomatonController.radius; i++){
            if (i != 0) {
                if (CoordinatesService.isWrapping) {
                    cellNeighbors.add(new Coords2D(CoordinatesService.setWhenWrapping(currentX, CoordinatesService.columns),
                            CoordinatesService.setWhenWrapping(currentY + i,CoordinatesService.rows)));
                    cellNeighbors.add(new Coords2D(CoordinatesService.setWhenWrapping(currentX + i, CoordinatesService.columns),
                            CoordinatesService.setWhenWrapping(currentY,CoordinatesService.rows)));
                }
                else{
                    if(CoordinatesService.areInsideMap(currentX + i, currentY)){
                        cellNeighbors.add(new Coords2D(currentX + i, currentY));
                    }
                    if(CoordinatesService.areInsideMap(currentX, currentY + i)){
                        cellNeighbors.add(new Coords2D(currentX, currentY + i));
                    }
                }
            }
        }

        return cellNeighbors;
    }
}
