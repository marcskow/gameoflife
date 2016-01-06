package pl.edu.marcskow.gameoflife.gui.structures;

import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.state.QuadState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Structure {
    private int width;
    private int height;
    String name;

    private CellState[][] structure;

    public Structure(String name){
        this(0,0, null, name);
    }

    public Structure(int height, int width, String name){
        this(height,width,new CellState[height][width],name);
    }

    public Structure(int height, int width, CellState[][] structure, String name){
        this.width = width;
        this.height = height;
        this.structure = structure;
        this.name = name;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public CellState[][] getStructure() {
        return structure;
    }

    public void setStructure(CellState[][] structure) {
        this.structure = structure;
    }

    public void initializeWithDead(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                structure[i][j] = BinaryState.DEAD;
            }
        }
    }

    public void setCell(int x, int y, CellState state){
        structure[x][y] = state;
    }

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
