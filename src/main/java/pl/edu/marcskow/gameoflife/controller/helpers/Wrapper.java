package pl.edu.marcskow.gameoflife.controller.helpers;

/**
 * This class is responsible for wrap coordinates if they are outside the map
 * wrapper is getting map size because it's diffrent on 1 dimension and 2 dimension mechanics
 */
public class Wrapper {
    /** isWrapping option */
    private boolean isWrapping;

    /**
     * set is wrapping will return normal coordinates or wrapped coordinates
     * @param isWrapping is map wrapping
     */
    public Wrapper(boolean isWrapping){
        this.isWrapping = isWrapping;
    }

    /**
     * Method calculate new coordinates depended on isWrapping option
     * @param coord given coordiante
     * @param containerSize map size
     * @return wrapped coord
     */
    public int wrapIfWrapping(int coord, int containerSize){
        if (coord < 0 && isWrapping) {
            return (containerSize + coord);
        }
        else if(coord > containerSize - 1 && isWrapping){
            return coord - containerSize;
        }
        else {
            return coord;
        }
    }

    /**
     * Additional method using to calculate is coordinate inside the map
     * @param x horizontal coordinate
     * @param y vertical coorinate
     * @param columns horizontal map size
     * @param rows vertical map size
     * @return is coordinate inside the map
     */
    public boolean areInsideMap(int x, int y, int columns, int rows) {
        if ((x > -1) && (x < columns) && (y > -1) && (y < rows)) {
            return true;
        } else {
            return false;
        }
    }

}
