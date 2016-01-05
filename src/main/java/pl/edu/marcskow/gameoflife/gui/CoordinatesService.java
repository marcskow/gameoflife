package pl.edu.marcskow.gameoflife.gui;

import java.util.logging.Logger;

/**
 * Created by Marcin Skowron on 2016-01-03.
 */
public class CoordinatesService {
    public static int columns = 40;
    public static int rows = 32;
    //public static boolean isWrapping = true;

    public static void setDimension(int c, int r) {
        columns = c;
        rows = r;
    }

    public static boolean areInsideMap(int x, int y) {
        if ((x > -1) && (x < columns) && (y > -1) && (y < rows)) {
            return true;
        } else {
            return false;
        }
    }

    public static int setWhenWrapping(int x, int containerSize){
        if (x < 0) {
            return (containerSize + x);
        }
        else if(x > containerSize - 1){
            return x - containerSize;
        }
        else{
            return x;
        }
    }

    public static int nextX(int currentX) {
        if (isWrapping && (currentX == columns - 1)) {
            return 0;
        } else {
            return currentX + 1;
        }
    }

    public static int nextY(int currentY) {
        if (isWrapping && (currentY == (rows - 1))) {
            return 0;
        } else {
            return currentY + 1;
        }
    }

    public static int previousX(int currentX) {
        if (isWrapping && currentX == 0) {
            return columns - 1;
        } else {
            return currentX - 1;
        }
    }


    public static int previousY(int currentY) {
        if (isWrapping && currentY == 0) {
            return rows - 1;
        } else {
            return currentY - 1;
        }
    }
}
