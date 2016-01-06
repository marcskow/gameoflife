package pl.edu.marcskow.gameoflife.model.neighborhood;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.controller.helpers.Wrapper;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for finding neighborhoods of the cell
 * based on the Moore Neighborhood properties.
 * Class implements CellNeighborhood interface.
 * @see CellNeighborhood
 */
public class MoorNeighborhood implements CellNeighborhood {
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
    public MoorNeighborhood(int columns, int rows, boolean isWrapping, int radius){
        this.columns = columns;
        this.rows = rows;
        this.isWrapping = isWrapping;
        this.radius = radius;
    }


    /**
     *  In moore neighborhood there are 8 neighbors in the cell area (there could be more if radius is > 1)
     *  @param cellCoordinates coordinates of the cell which neighbors we are looking for
     *  @return the cell neighbors
     */
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>();
        Wrapper wrapper = new Wrapper(isWrapping);

        Coords2D currentCoords = (Coords2D) cellCoordinates;
        int currentX = currentCoords.getX();
        int currentY = currentCoords.getY();

        for(int i = (-1)*radius; i <= radius; i++){
            for (int j = (-1)*radius; j <= radius; j++){
                if ((!(i == 0 && j == 0))) {
                    if(isWrapping){
                        cellNeighbors.add(new Coords2D(wrapper.wrapIfWrapping(currentX + i, columns),
                                wrapper.wrapIfWrapping(currentY + j, rows)));
                    }
                    else if(!isWrapping && wrapper.areInsideMap(currentX + i, currentY + j, columns, rows)){
                        cellNeighbors.add(new Coords2D(currentX + i, currentY + j));
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