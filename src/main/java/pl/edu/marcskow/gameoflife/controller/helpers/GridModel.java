package pl.edu.marcskow.gameoflife.controller.helpers;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.state.CellState;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the map model with cells inside it, there are performed operations on the map
 * such initializations and states changes
 */
public class GridModel {
    private static final int DEFAULT_GRID_WIDTH = 1000;
    private static final int DEFAULT_GRID_HEIGHT = 800;
    private final double height;
    private final double width;
    private int columns;
    private int rows;
    private Map<CellCoordinates,CellState> cellsMap;
    private String automatonType;

    public GridModel(int columns, int rows, String automatonType){
        this(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT, columns, rows, automatonType);
    }

    /**
     * @param width width of grid in pixels
     * @param height height of grid in pixels
     * @param columns width of grid in columns amount
     * @param rows height of grid in rows amount
     * @param automatonType automaton type of running simulation
     */
    public GridModel(double width, double height, int columns, int rows, String automatonType){
        this.height = height;
        this.width = width;
        this.columns = columns;
        this.rows = rows;
        this.automatonType = automatonType;
        initializeCellsMap();
        setCellsMapState(DisplayService.setDefaultStateToAutomatonType(automatonType));
    }

    public void initializeCellsMap(){
        this.cellsMap = new HashMap<CellCoordinates,CellState>(columns*rows);
    }

    /**
     * Initialize map with given CellState
     * @param state CellState to initialize map
     */
    public void setCellsMapState(CellState state){
        for(int i= 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                putElementOnMap(i,j,state);
            }
        }
    }

    /**
     * Set element on map to given element
     * @param x element x position
     * @param y element y position
     * @param state element new state
     */
    public void putElementOnMap(int x, int y, CellState state){
        if(automatonType.equals("Elementary")){
            cellsMap.put(new Coords1D(columns*y + x),state);
        }
        else{
            cellsMap.put(new Coords2D(x,y),state);
        }
    }

    /**
     * When automaton is changed grid have to be rebuild
     */
    public void prepareGridAfterAutomatonChange(){
        initializeCellsMap();
        setCellsMapState(DisplayService.setDefaultStateToAutomatonType(automatonType));
    }

    public String getAutomatonType() {
        return automatonType;
    }

    public void setAutomatonType(String automatonType) {
        this.automatonType = automatonType;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public void setDimension(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
    }

    public Map<CellCoordinates, CellState> getCellsMap() {
        return cellsMap;
    }

    public void setCellsMap(Map<CellCoordinates, CellState> cellsMap) {
        this.cellsMap = cellsMap;
    }

    public CellState getCellFromMap(int x, int y){
        if (automatonType.equals("Elementary")){
            return cellsMap.get(new Coords1D(y*columns + x));
        }else{
            return cellsMap.get(new Coords2D(x,y));
        }
    }

    public void addStructure(Map<? extends CellCoordinates,? extends CellState> structure){
        cellsMap.putAll(structure);
    }

    /**
     * This method use CellState nextState() method to set current element to the next state
     * @param x position of element
     * @param y position of element
     */
    public void changeOnMapCellStateToNext(int x, int y){
        if (automatonType.equals("Elementary")){
            cellsMap.put(new Coords1D(y*columns + x),cellsMap.get(new Coords1D(y*columns + x)).nextState());
        }else{
            cellsMap.put(new Coords2D(x,y), cellsMap.get(new Coords2D(x,y)).nextState());
        }
    }
}

