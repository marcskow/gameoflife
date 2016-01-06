package pl.edu.marcskow.gameoflife.controller.structures;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.state.BinaryState;
import pl.edu.marcskow.gameoflife.model.state.CellState;
import pl.edu.marcskow.gameoflife.model.state.QuadState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class represents one structure e.g. Glider, Dakota. Each structure has width, height, name.
 * Structure is a container of CellStates defining a structure.
 */
public class Structure {
    /** structure width */
    private int width;
    /** structure height */
    private int height;
    /** structure name */
    String name;
    /** structure fields */
    private CellState[][] structure;

    public Structure(int height, int width, String name){
        this(height,width,new CellState[height][width],name);
    }

    /**
     * @param height of structure
     * @param width of structure
     * @param structure define the fields of structure
     * @param name name of structrue
     */
    public Structure(int height, int width, CellState[][] structure, String name){
        this.width = width;
        this.height = height;
        this.structure = structure;
        this.name = name;
    }

    /**
     * Structure fields are two dimensional array but can be convert to Map
     * @param states states of structure
     * @param deadState what kind of structure is it, what is the deadState, e.g. QuadState.Dead or BinaryState.Dead
     * @return map of structure fields
     */
    public Map<CellCoordinates,CellState> toMap2D(CellState[][] states, CellState deadState){
        Map<CellCoordinates, CellState> mappedStructure = new HashMap<>();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(states[i][j] != deadState){
                    mappedStructure.put(new Coords2D(i,j),states[i][j]);
                }
            }
        }
        return mappedStructure;
    }

    public void initializeFromIntArray(int height, int width, int[][] intstruct){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){

                if(intstruct[i][j] == 0){
                    structure[i][j] = BinaryState.DEAD;
                }
                else{
                    structure[i][j] = BinaryState.ALIVE;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellState[][] getStructure() {
        return structure;
    }

    public void setStructure(CellState[][] structure) {
        this.structure = structure;
    }

    /**
     * The same structure can represent Game Of Life structure and QuadLife structure with other fields states, so
     * that's easy to make on structure from another automaton type
     * @return CellState[][] of structure fields
     */
    public CellState[][] convertToQuadLife(){
        CellState[][] quadStructure = new CellState[height][width];
        Random generator = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (structure[i][j] == BinaryState.DEAD) {
                    quadStructure[i][j] = QuadState.DEAD;
                } else if (generator.nextInt(100) < 25) {
                    quadStructure[i][j] = QuadState.GREEN;
                } else if ((generator.nextInt(100) > 25) && (generator.nextInt(100) <= 50)) {
                    quadStructure[i][j] = QuadState.RED;
                } else if ((generator.nextInt(100) > 50) && (generator.nextInt(100) <= 75)) {
                    quadStructure[i][j] = QuadState.BLUE;
                } else {
                    quadStructure[i][j] = QuadState.YELLOW;
                }
            }
        }
        return quadStructure;
    }
}
