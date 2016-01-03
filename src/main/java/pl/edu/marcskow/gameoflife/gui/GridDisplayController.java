package pl.edu.marcskow.gameoflife.gui;

import javafx.scene.paint.Color;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.state.QuadState;
import pl.edu.marcskow.gameoflife.state.WireElectronState;

/**
 * Created by Marcin Skowron on 2016-01-03.
 */
public class GridDisplayController {
    public static final int DEFAULT_STROKE_WIDTH = 1;
    public static final Color DEFAULT_STROKE_COLOR = Color.GRAY;

    private Color defaultColor = Color.WHITE;

    public Color convertBinaryStateToColor(BinaryState state){
        switch (state){
            case ALIVE: return Color.YELLOW;
            case DEAD: return Color.WHITE;
            default: return Color.WHITE;
        }
    }

    public Color convertQuadStateToColor(QuadState state){
        switch (state){
            case DEAD: return Color.WHITE;
            case RED: return Color.RED;
            case GREEN: return Color.GREEN;
            case BLUE: return Color.BLUE;
            case YELLOW: return Color.YELLOW;
            default: return Color.WHITE;
        }
    }

    public Color convertWireElectronStateToColor(WireElectronState state){
        switch (state){
            case VOID: return Color.GRAY;
            case WIRE: return Color.ORANGE;
            case ELECTRON_HEAD: return Color.BLUE;
            case ELECTRON_TAIL: return Color.RED;
            default: return Color.GRAY;
        }
    }

    public Color setColorToAutomatonType(String automaton, CellState state){
        switch (automaton){
            case "Game Of Life": return convertBinaryStateToColor((BinaryState) state);
            case "Langton Ant": return convertBinaryStateToColor((BinaryState) state);
            case "WireWorld": return convertWireElectronStateToColor((WireElectronState) state);
            case "QuadLife": return convertQuadStateToColor((QuadState) state);
            case "Elementary": return convertBinaryStateToColor((BinaryState) state);
            default: return Color.WHITE;
        }
    }

    public static CellState setDefaultStateToAutomatonType(String automaton){
        switch (automaton){
            case "Game Of Life": return BinaryState.DEAD;
            case "Langton Ant": return BinaryState.DEAD;
            case "WireWorld": return WireElectronState.VOID;
            case "QuadLife": return QuadState.DEAD;
            case "Elementary": return BinaryState.DEAD;
            default: return BinaryState.DEAD;
        }
    }

}
