package pl.edu.marcskow.gameoflife.gui;

import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;


/**
 * Created by Marcin Skowron on 2016-01-03.
 *
 */
public class GridModel {
    private static final int DEFAULT_GRID_WIDTH = 1000;
    private static final int DEFAULT_GRID_HEIGHT = 800;

    private final double height;
    private final double width;
    private int columns;
    private int rows;
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
    }

    public void setCellsMapState(CellState state){
        for(int i= 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                cells[i][j] = state;
            }
        }
    }

    public void prepareGridAfterAutomatonChange(){
        initializeCellsMap();
        setCellsMapState(GridDisplayController.setDefaultStateToAutomatonType(automatonType));
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

    public CellState getCell(int x, int y){
        return cells[x][y];
    }

    public void setCell(int x, int y, CellState state){
        cells[x][y] = state;
    }

    public CellState getCell(Coords2D coords){
        return cells[coords.getX()][coords.getY()];
    }

    public void setCell(Coords2D coords, CellState state){
        cells[coords.getX()][coords.getY()] = state;
    }
}

