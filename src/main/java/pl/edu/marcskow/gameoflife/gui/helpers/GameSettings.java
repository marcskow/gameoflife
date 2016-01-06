package pl.edu.marcskow.gameoflife.gui.helpers;

import javafx.scene.paint.Color;

/**
 * Created by Marcin Skowron on 2016-01-05.
 */
public class GameSettings {
    public static String AUTOMATON = "Game Of Life";
    public static int COLUMNS = 40;
    public static int ROWS = 32;
    public static String NEIGHBORHOOD = "Moore";
    public static String STRUCTURE = "Next";
    public static String SIZE = "40x32";

    public static final int DEFAULT_STROKE_WIDTH = 1;
    public static final Color DEFAULT_STROKE_COLOR = Color.GRAY;
    public static final int DEFAULT_GRID_WIDTH = 1000;
    public static final int DEFAULT_GRID_HEIGHT = 800;

    public static int[] bornRule;
    public static int[] surviveRule;
    public static int RADIUS = 1;
    public static boolean ISWRAPPING = true;
    public static int elementaryRule;

    public static void setDimension(int columns, int rows){
        COLUMNS = columns;
        ROWS = rows;
    }
}
