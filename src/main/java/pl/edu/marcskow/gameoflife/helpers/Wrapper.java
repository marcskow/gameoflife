package pl.edu.marcskow.gameoflife.helpers;

import pl.edu.marcskow.gameoflife.gui.helpers.GameSettings;

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

    public boolean areInsideMap(int x, int y) {
        if ((x > -1) && (x < GameSettings.COLUMNS) && (y > -1) && (y < GameSettings.ROWS)) {
            return true;
        } else {
            return false;
        }
    }

}
