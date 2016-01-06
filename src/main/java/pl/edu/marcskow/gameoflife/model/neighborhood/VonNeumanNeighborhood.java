package pl.edu.marcskow.gameoflife.model.neighborhood;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.controller.helpers.Wrapper;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for finding neighborhoods of the cell
 * based on the VonNeuman Neighborhood properties.
 * Class implements CellNeighborhood interface.
 * @see CellNeighborhood
 */
public class VonNeumanNeighborhood implements CellNeighborhood {
    private int columns;
    private int rows;
    private boolean isWrapping;
    private int radius;

    public VonNeumanNeighborhood(int columns, int rows, boolean isWrapping, int radius){
        this.columns = columns;
        this.rows = rows;
        this.isWrapping = isWrapping;
        this.radius = radius;
    }

    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Wrapper wrapper = new Wrapper(isWrapping);
        Set<CellCoordinates> cellNeighbors = new HashSet<>(4);

        Coords2D currentCoords = (Coords2D) cellCoordinates;
        int currentX = currentCoords.getX();
        int currentY = currentCoords.getY();

        for(int i = (-1) * radius; i <= radius; i++){
            if (i != 0) {
                if (isWrapping) {
                    cellNeighbors.add(new Coords2D(wrapper.wrapIfWrapping(currentX, columns),
                            wrapper.wrapIfWrapping(currentY + i, rows)));
                    cellNeighbors.add(new Coords2D(wrapper.wrapIfWrapping(currentX + i,columns),
                            wrapper.wrapIfWrapping(currentY, rows)));
                }
                else{
                    if(wrapper.areInsideMap(currentX + i, currentY, columns, rows)){
                        cellNeighbors.add(new Coords2D(currentX + i, currentY));
                    }
                    if(wrapper.areInsideMap(currentX, currentY + i, columns, rows)){
                        cellNeighbors.add(new Coords2D(currentX, currentY + i));
                    }
                }
            }
        }

        return cellNeighbors;
    }

    @Override
    public void setWrapping(boolean wrapping) {
        isWrapping = wrapping;
    }

    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }
}
