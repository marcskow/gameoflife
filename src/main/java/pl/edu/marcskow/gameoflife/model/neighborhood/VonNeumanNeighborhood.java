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

    /**
     * setting options of the neighborhood, size of map, is it wrapping and radius of the area
     * @param columns width of map
     * @param rows height of map
     * @param isWrapping wrapping option
     * @param radius radius size
     */
    public VonNeumanNeighborhood(int columns, int rows, boolean isWrapping, int radius){
        this.columns = columns;
        this.rows = rows;
        this.isWrapping = isWrapping;
        this.radius = radius;
    }

    /**
     * in van neuman default neighborhood there are 4 cells in but can be more if radius is greater then 1
     * @param cellCoordinates coordinates of the cell which neighbors we are looking for
     * @return Set with coordinates of cell neighbors on it
     */
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

    /**
     * Set wrapping option
     * @param wrapping isWrapping
     */
    @Override
    public void setWrapping(boolean wrapping) {
        isWrapping = wrapping;
    }

    /**
     * Set radius size
     * @param radius size
     */
    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }
}
