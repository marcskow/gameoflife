package pl.edu.marcskow.gameoflife.controller.helpers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.edu.marcskow.gameoflife.controller.structures.Structures;
import pl.edu.marcskow.gameoflife.model.state.*;

/**
 * Created by Marcin Skowron on 2016-01-06.
 */
public class Putter {
    private GridModel gridModel;

    public Putter(GridModel model){
        this.gridModel = model;
    }

    public GridModel putOnMap(int roundedX, int roundedY, String action, String automatonType, Structures structures){
        if(action.equals("Ant")){
            gridModel.putElementOnMap(roundedX,roundedY,new LangtonCell(BinaryState.DEAD, AntState.NORTH));
        }
        else if(action.equals("Ant North")){
            gridModel.putElementOnMap(roundedX,roundedY,new LangtonCell(BinaryState.ALIVE, AntState.NORTH));
        }
        else if(action.equals("Ant South")){
            gridModel.putElementOnMap(roundedX,roundedY,new LangtonCell(BinaryState.ALIVE, AntState.SOUTH));
        }
        else if(action.equals("Ant West")){
            gridModel.putElementOnMap(roundedX,roundedY,new LangtonCell(BinaryState.ALIVE, AntState.WEST));
        }
        else if(action.equals("Ant East")){
            gridModel.putElementOnMap(roundedX,roundedY,new LangtonCell(BinaryState.ALIVE, AntState.EAST));
        }
        else if(action.equals("Red")){
            gridModel.putElementOnMap(roundedX,roundedY,QuadState.RED);
        }
        else if(action.equals("Yellow")){
            gridModel.putElementOnMap(roundedX,roundedY,QuadState.YELLOW);
        }
        else if(action.equals("Green")){
            gridModel.putElementOnMap(roundedX,roundedY,QuadState.GREEN);
        }
        else if(action.equals("Wire")){
            gridModel.putElementOnMap(roundedX,roundedY, WireElectronState.WIRE);
        }
        else if(action.equals("Head")){
            gridModel.putElementOnMap(roundedX,roundedY, WireElectronState.ELECTRON_HEAD);
        }
        else if(action.equals("Tail")){
            gridModel.putElementOnMap(roundedX,roundedY, WireElectronState.ELECTRON_TAIL);
        }
        else if(automatonType.equals("Game Of Life")) {
            gridModel.addStructure(structures.returnStructure(action,"Game Of Life", BinaryState.DEAD,roundedX,roundedY));
        }
        else if(automatonType.equals("QuadLife")){
            gridModel.addStructure(structures.returnStructure(action, "QuadLife", QuadState.DEAD,roundedX,roundedY));
        }
        return gridModel;
    }

}
