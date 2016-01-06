package pl.edu.marcskow.gameoflife.model.neighborhood;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.controller.helpers.Wrapper;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for finding neighborhoods of the cell
 * based on the Elementary Neighborhood properties.
 * Class implements CellNeighborhood interface.
 * @see CellNeighborhood
 */
public class ElementaryNeighborhood implements CellNeighborhood {
    /** size of map */
    private int size;
    /** isWrapping settings */
    private boolean isWrapping;
    /** radius settings */
    private int radius;

    /**
     * Constructor settings options of ElementaryNeighborhood
     * @param size of map
     * @param isWrapping options
     */
    public ElementaryNeighborhood(int size, boolean isWrapping){
        this.size = size;
        this.isWrapping = isWrapping;
    }

    /**
     * @see CellNeighborhood#cellNeighborhoods(CellCoordinates)
     * @param cellCoordinates coordinates of the cell which neighbors we are looking for
     * @return the cell neighbors
     */
    @Override
    public Set<CellCoordinates> cellNeighborhoods(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> cellNeighbors = new HashSet<>(1);
        int mapSize = size;
        int currentX = ((Coords1D) cellCoordinates).getX();
        Wrapper wrapper = new Wrapper(isWrapping);

        if(wrapper.wrapIfWrapping(currentX - 1, mapSize) >= 0) {
            cellNeighbors.add(new Coords1D(wrapper.wrapIfWrapping(currentX - 1, mapSize)));
        }
        if(wrapper.wrapIfWrapping(currentX + 1, mapSize) < mapSize){
            cellNeighbors.add(new Coords1D(wrapper.wrapIfWrapping(currentX + 1, mapSize)));
        }
        return cellNeighbors;
    }

    /**
     * Set is it map wrapping or not
     * @param wrapping is wrapping on
     */
    @Override
    public void setWrapping(boolean wrapping) {
        isWrapping = wrapping;
    }

    /**
     * Set how many cells is inside neighborhood
     * @param radius radius of the neighborhood circle
     */
    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }

}
