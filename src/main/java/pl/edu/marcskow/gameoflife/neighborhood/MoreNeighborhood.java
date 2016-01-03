package pl.edu.marcskow.gameoflife.neighborhood;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.gui.CoordinatesService;
import pl.edu.marcskow.gameoflife.gui.GameModel;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by intenso on 06.12.15.
 * Sąsiedztwo More'a czyli trzy komórki nad, komórka z lewej, komórka z prawej i trzy komórki pod.
 */
public class MoreNeighborhood implements CellNeighborhood {
    private static final Logger log = Logger.getLogger(MoreNeighborhood.class.getName());
    public static int id = 0;
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>();

        Coords2D currentCoords = (Coords2D) cellCoordinates;
        int currentX = currentCoords.getX();
        int currentY = currentCoords.getY();

        if(CoordinatesService.areInsideMap(CoordinatesService.previousX(currentX), CoordinatesService.previousY(currentY))){
            cellNeighbors.add(new Coords2D(CoordinatesService.previousX(currentX), CoordinatesService.previousY(currentY)));
        }
        if(CoordinatesService.areInsideMap(currentX, CoordinatesService.previousY(currentY))){
            cellNeighbors.add(new Coords2D(currentX, CoordinatesService.previousY(currentY)));
        }
        if(CoordinatesService.areInsideMap(CoordinatesService.nextX(currentX), CoordinatesService.previousY(currentY))){
            cellNeighbors.add(new Coords2D(CoordinatesService.nextX(currentX), CoordinatesService.previousY(currentY)));
        }
        if(CoordinatesService.areInsideMap(CoordinatesService.previousX(currentX), currentY)){
            cellNeighbors.add(new Coords2D(CoordinatesService.previousX(currentX), currentY));
        }
        if(CoordinatesService.areInsideMap(CoordinatesService.nextX(currentX), currentY)){
            cellNeighbors.add(new Coords2D(CoordinatesService.nextX(currentX), currentY));
        }
        if(CoordinatesService.areInsideMap(CoordinatesService.previousX(currentX), CoordinatesService.nextY(currentY))){
            cellNeighbors.add(new Coords2D(CoordinatesService.previousX(currentX), CoordinatesService.nextY(currentY)));
        }
        if(CoordinatesService.areInsideMap(currentX, CoordinatesService.nextY(currentY))){
            cellNeighbors.add(new Coords2D(currentX, CoordinatesService.nextY(currentY)));
        }
        if(CoordinatesService.areInsideMap(CoordinatesService.nextX(currentX), CoordinatesService.nextY(currentY))){
            cellNeighbors.add(new Coords2D(CoordinatesService.nextX(currentX), CoordinatesService.nextY(currentY)));
        }
        /*
        if (id < 100) {
            log.info("" + CoordinatesService.areInsideMap(CoordinatesService.previousX(currentX), CoordinatesService.nextY(currentY))+
                    " " + CoordinatesService.areInsideMap(currentX, CoordinatesService.nextY(currentY)) +
                    " " + CoordinatesService.areInsideMap(CoordinatesService.nextX(currentX), CoordinatesService.nextY(currentY)) +
                    " " + CoordinatesService.areInsideMap(CoordinatesService.previousX(currentX), currentY) +
                    " " + CoordinatesService.areInsideMap(CoordinatesService.nextX(currentX), currentY) +
            " " + CoordinatesService.areInsideMap(CoordinatesService.previousX(currentX), CoordinatesService.previousY(currentY)) +
            " " + CoordinatesService.areInsideMap(currentX, CoordinatesService.previousY(currentY)) +
            " " + CoordinatesService.areInsideMap(CoordinatesService.nextX(currentX), CoordinatesService.previousY(currentY)));
            id++;
        }*/
        return cellNeighbors;
    }
}