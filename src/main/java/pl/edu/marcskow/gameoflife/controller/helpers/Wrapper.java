package pl.edu.marcskow.gameoflife.controller.helpers;

public class Wrapper {
    private boolean isWrapping;

    public Wrapper(boolean isWrapping){
        this.isWrapping = isWrapping;
    }

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

    public boolean areInsideMap(int x, int y, int columns, int rows) {
        if ((x > -1) && (x < columns) && (y > -1) && (y < rows)) {
            return true;
        } else {
            return false;
        }
    }

}
