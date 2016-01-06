package pl.edu.marcskow.gameoflife.controller.helpers;

import pl.edu.marcskow.gameoflife.controller.structures.Structures;
import pl.edu.marcskow.gameoflife.model.state.*;

/**
 * This class is using to putting structure on map
 */
public class Putter {
    /** Map of simulation */
    private GridModel gridModel;

    public Putter(GridModel model){
        this.gridModel = model;
    }

    /**
     * Method putting structure on the map depended on structure type, automaton type and mause clicked coordinates
     * @param roundedX where to put the structure
     * @param roundedY where to put the structure
     * @param action what kind of structure
     * @param automatonType type of automaton
     * @param structures list of structures
     * @return gridModel with structure on it
     */
    public GridModel putOnMap(int roundedX, int roundedY, String action, String automatonType, Structures structures) {
        switch (action) {
            case "Ant":
                gridModel.putElementOnMap(roundedX, roundedY, new LangtonCell(BinaryState.DEAD, AntState.NORTH));
                break;
            case "Ant North":
                gridModel.putElementOnMap(roundedX, roundedY, new LangtonCell(BinaryState.ALIVE, AntState.NORTH));
                break;
            case "Ant South":
                gridModel.putElementOnMap(roundedX, roundedY, new LangtonCell(BinaryState.ALIVE, AntState.SOUTH));
                break;
            case "Ant West":
                gridModel.putElementOnMap(roundedX, roundedY, new LangtonCell(BinaryState.ALIVE, AntState.WEST));
                break;
            case "Ant East":
                gridModel.putElementOnMap(roundedX, roundedY, new LangtonCell(BinaryState.ALIVE, AntState.EAST));
                break;
            case "Red":
                gridModel.putElementOnMap(roundedX, roundedY, QuadState.RED);
                break;
            case "Blue":
                gridModel.putElementOnMap(roundedX, roundedY, QuadState.BLUE);
                break;
            case "Yellow":
                gridModel.putElementOnMap(roundedX, roundedY, QuadState.YELLOW);
                break;
            case "Green":
                gridModel.putElementOnMap(roundedX, roundedY, QuadState.GREEN);
                break;
            case "Wire":
                gridModel.putElementOnMap(roundedX, roundedY, WireElectronState.WIRE);
                break;
            case "Head":
                gridModel.putElementOnMap(roundedX, roundedY, WireElectronState.ELECTRON_HEAD);
                break;
            case "Tail":
                gridModel.putElementOnMap(roundedX, roundedY, WireElectronState.ELECTRON_TAIL);
                break;
            default:
                if (automatonType.equals("Game Of Life")) {
                    gridModel.addStructure(structures.returnStructure(action, "Game Of Life", BinaryState.DEAD, roundedX, roundedY));
                } else if (automatonType.equals("QuadLife")) {
                    gridModel.addStructure(structures.returnStructure(action, "QuadLife", QuadState.DEAD, roundedX, roundedY));
                }
        }
        return gridModel;
    }
}
