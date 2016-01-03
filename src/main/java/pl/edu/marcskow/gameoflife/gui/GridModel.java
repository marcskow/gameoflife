package pl.edu.marcskow.gameoflife.gui;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by Marcin Skowron on 2016-01-03.
 *
 */
public class GridModel {
    private static final Logger log = Logger.getLogger(GridModel.class.getName());
    private static final int DEFAULT_GRID_WIDTH = 1000;
    private static final int DEFAULT_GRID_HEIGHT = 800;

    private final double height;
    private final double width;
    private int columns;
    private int rows;
    private Map<CellCoordinates,CellState> cellsMap;
    private CellState[][] cells;
    private String automatonType;

    public GridModel(){
        this(40,32, "Game Of Life");
    }

    public GridModel(int columns, int rows, String automatonType){
        this(DEFAULT_GRID_WIDTH,DEFAULT_GRID_HEIGHT,columns,rows,automatonType);
    }

    public GridModel(double width, double height, int columns, int rows, String automatonType){
        this.height = height;
        this.width = width;
        this.columns = columns;
        this.rows = rows;
        this.automatonType = automatonType;
        initializeCellsMap();
        setCellsMapState(GridDisplayController.setDefaultStateToAutomatonType(automatonType));
    }

    public void initializeCellsMap(){
        this.cells = new CellState[columns][rows];
        this.cellsMap = new HashMap<CellCoordinates,CellState>(columns*rows);
    }

    public void setCellsMapState(CellState state){
        for(int i= 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                putElementOnMap(i,j,state);
                cells[i][j] = state;
            }
        }
    }

    public void putElementOnMap(int x, int y, CellState state){
        if(automatonType.equals("Elementary")){
            cellsMap.put(new Coords1D(columns*y + x),state);
        }
        else{
            cellsMap.put(new Coords2D(x,y),state);
        }
    }

    public void prepareGridAfterAutomatonChange(){
        initializeCellsMap();
        setCellsMapState(GridDisplayController.setDefaultStateToAutomatonType(automatonType));
    }
    /*
    public void makeGrider(){
        Map<CellCoordinates,CellState> map = new HashMap<CellCoordinates, CellState>(columns*rows);
        map.put(new Coords2D(0,0),BinaryState.ALIVE);
        map.put(new Coords2D(1,0),BinaryState.ALIVE);
        map.put(new Coords2D(2,0),BinaryState.ALIVE);
        map.put(new Coords2D(0,1),BinaryState.ALIVE);
        map.put(new Coords2D(1,2),BinaryState.ALIVE);
        setFromMap(map);
    }*/

    public Map<CellCoordinates, CellState> toMap2D(){
        Map<CellCoordinates,CellState> map = new HashMap<CellCoordinates, CellState>(columns*rows);
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                map.put(new Coords2D(i,j),cells[i][j]);
            }
        }
        return map;
    }

    public void setFromMap(Map<CellCoordinates,CellState> map){
        for (int i = 0; i < columns; i++) {
            for(int j = 0; j < rows; j++){
                if (map.get(new Coords2D(i,j)) == null) {
                    cells[i][j] = GridDisplayController.setDefaultStateToAutomatonType(automatonType);
                }
                else {
                    cells[i][j] = map.get(new Coords2D(i,j));
                }
            }
        }
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

    public CellState[][] getCells() {
        return cells;
    }

    public void setCells(CellState[][] cells) {
        this.cells = cells;
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

    public CellState getCell(int x, int y){
        return cells[x][y];
    }

    public void setCell(int x, int y, CellState state){
        cells[x][y] = state;
    }

    public void setCellInMap(int x, int y, CellState state){
        if (automatonType.equals("Elementary")){
            cellsMap.put(new Coords1D(y*columns + x),state);
        }else{
            cellsMap.put(new Coords2D(x,y), state);
        }
    }

    public CellState getCell(Coords2D coords){
        return cells[coords.getX()][coords.getY()];
    }

    public void setCell(Coords2D coords, CellState state){
        cells[coords.getX()][coords.getY()] = state;
    }

    public void changeCellStateToNext(int x, int y){
        cells[x][y] = cells[x][y].nextState();
    }

    public void changeOnMapCellStateToNext(int x, int y){
        if (automatonType.equals("Elementary")){
            cellsMap.put(new Coords1D(y*columns + x),cellsMap.get(new Coords1D(y*columns + x)).nextState());
        }else{
            log.info("Putted cell on "+ x+","+y);
            cellsMap.put(new Coords2D(x,y), cellsMap.get(new Coords2D(x,y)).nextState());
        }
    }
}

