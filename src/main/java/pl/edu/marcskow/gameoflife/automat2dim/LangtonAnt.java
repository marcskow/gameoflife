package pl.edu.marcskow.gameoflife.automat2dim;


import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.cell.Ant;
import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.state.AntState;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;
import pl.edu.marcskow.gameoflife.state.LangtonCell;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by intenso on 20.12.15.
 */
public class LangtonAnt extends Automaton2Dim{
    private static final Logger log = Logger.getLogger(LangtonAnt.class.getName());

    public LangtonAnt(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood, int width, int height){
        super(cellStateFactory, cellNeighborhood);
        this.setWidth(width);
        this.setHeight(height);;
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        return new LangtonAnt(cellStateFactory, cellNeighborhood, getWidth(), getHeight());
    }

    protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates){
        Coords2D currentCoordinates = (Coords2D)currentCoordinates(neighborsStates);
        int x = currentCoordinates.getX();
        int y = currentCoordinates.getY();

        LangtonCell currentLangtonCell = (LangtonCell) currentState;
        BinaryState currentBinaryState = currentLangtonCell.getCellState();
        AntState currentAntState = currentLangtonCell.getAntState();

        LangtonCell result = new LangtonCell(currentBinaryState,currentAntState);

        if(currentAntState != AntState.NONE){
            result.setAntState(AntState.NONE);
            result.setCellState((BinaryState)currentBinaryState.nextState());
        }

        for (Cell c : neighborsStates) {
            if ((ifCoordsEqual(c, x + 1, y) && ifStatesEqual(c, BinaryState.ALIVE, AntState.SOUTH))) {
                result.setAntState(AntState.WEST);
            } else if ((ifCoordsEqual(c, x + 1, y) && ifStatesEqual(c, BinaryState.DEAD, AntState.NORTH))) {
                result.setAntState(AntState.WEST);
            } else if ((ifCoordsEqual(c, x - 1, y) && ifStatesEqual(c, BinaryState.DEAD, AntState.SOUTH))) {
                result.setAntState(AntState.EAST);
            } else if ((ifCoordsEqual(c, x - 1, y) && ifStatesEqual(c, BinaryState.ALIVE, AntState.NORTH))) {
                result.setAntState(AntState.EAST);
            } else if ((ifCoordsEqual(c, x, y - 1) && ifStatesEqual(c, BinaryState.DEAD, AntState.WEST))) {
                result.setAntState(AntState.SOUTH);
            } else if ((ifCoordsEqual(c, x, y - 1) && ifStatesEqual(c, BinaryState.ALIVE, AntState.EAST))) {
                result.setAntState(AntState.SOUTH);
            } else if ((ifCoordsEqual(c, x, y + 1) && ifStatesEqual(c, BinaryState.DEAD, AntState.EAST))) {
                result.setAntState(AntState.NORTH);
            } else if ((ifCoordsEqual(c, x, y + 1) && ifStatesEqual(c, BinaryState.ALIVE, AntState.WEST))) {
                result.setAntState(AntState.NORTH);
            }
        }
            /*

            if((ifCoordsEqual(c,x+1,y) && isAntEqual(c,AntState.WEST) && currentBinaryState == BinaryState.ALIVE)) {

                return new LangtonCell(BinaryState.DEAD, AntState.SOUTH);
            }
            else if((ifCoordsEqual(c,x+1,y) && isAntEqual(c,AntState.WEST) && currentBinaryState == BinaryState.DEAD)) {
                return new LangtonCell(BinaryState.ALIVE, AntState.NORTH);
            }
            else if((ifCoordsEqual(c,x-1,y) && isAntEqual(c,AntState.EAST) && currentBinaryState == BinaryState.ALIVE)) {
                return new LangtonCell(BinaryState.DEAD, AntState.NORTH);
            }
            else if((ifCoordsEqual(c,x-1,y) && isAntEqual(c,AntState.EAST) && currentBinaryState == BinaryState.DEAD)) {
                return new LangtonCell(BinaryState.ALIVE, AntState.SOUTH);
            }
            else if((ifCoordsEqual(c,x,y+1) && isAntEqual(c,AntState.NORTH) && currentBinaryState == BinaryState.ALIVE)) {
                return new LangtonCell(BinaryState.DEAD, AntState.WEST);
            }
            else if((ifCoordsEqual(c,x,y+1) && isAntEqual(c,AntState.NORTH) && currentBinaryState == BinaryState.DEAD)) {
                return new LangtonCell(BinaryState.ALIVE, AntState.EAST);
            }
            else if((ifCoordsEqual(c,x,y-1) && isAntEqual(c,AntState.SOUTH) && currentBinaryState == BinaryState.ALIVE)) {
                return new LangtonCell(BinaryState.DEAD, AntState.EAST);
            }
            else if((ifCoordsEqual(c,x,y-1) && isAntEqual(c,AntState.SOUTH) && currentBinaryState == BinaryState.DEAD)) {
                return new LangtonCell(BinaryState.ALIVE, AntState.WEST);
            }
        }*/

        return result;
    }

    private boolean ifCoordsEqual(Cell c, int x, int y){
        return ((Coords2D)c.getCoords()).getX() == x && ((Coords2D)c.getCoords()).getY() == y;
    }

    private boolean ifStatesEqual(Cell c, BinaryState state, AntState ant){
        return ((((LangtonCell)c.getState()).getCellState() == state) && ((LangtonCell) c.getState()).getAntState() == ant) ;
    }

    private boolean isAntEqual(Cell c, AntState ant){
        return (((LangtonCell) c.getState()).getAntState() == ant);
    }

    private CellCoordinates currentCoordinates(Set<Cell> neighbors){
        int sumOfAllXCoordinates = 0;
        int sumOfAllYCoordinates = 0;

        for (Cell c : neighbors) {
            sumOfAllXCoordinates += ((Coords2D) c.getCoords()).getX();
            sumOfAllYCoordinates += ((Coords2D) c.getCoords()).getY();
        }

        return new Coords2D(sumOfAllXCoordinates / neighbors.size(), sumOfAllYCoordinates / neighbors.size());
    }
}