import org.junit.Assert;
import org.junit.Test;
import pl.edu.marcskow.gameoflife.model.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.neighborhood.MoorNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.BinaryState;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Skowron on 2015-12-29.
 */
public class GameOfLifeTest {
    public final static int WIDTH = 300 + 1;
    public final static int HEIGHT = 200 + 1;
    public final static double DELTA = 1.e-15;
    public final static int COLUMNS = 40;
    public final static int ROWS = 32;
    public final static int[] newRule = {3};
    public final static int[] survive = {2,3};

    @Test
    public void hasNextCoordinates() {
        GameOfLife gameOfLife = new GameOfLife(
                null,
                new MoorNeighborhood(COLUMNS,ROWS, true,1),
                301,
                201, survive,newRule
        );

        Coords2D coords1 = new Coords2D(0, 0);
        Coords2D coords2 = new Coords2D(300, 150);
        Coords2D coords3 = new Coords2D(300, 200);
        Coords2D coords4 = new Coords2D(-1, 0);

        Assert.assertTrue("failure - should be true", gameOfLife.hasNextCoordinates(coords1));
        Assert.assertTrue("failure - should be true", gameOfLife.hasNextCoordinates(coords2));
        Assert.assertFalse("failure - should be false", gameOfLife.hasNextCoordinates(coords3));
        Assert.assertTrue("failure - should be true", gameOfLife.hasNextCoordinates(coords4));
    }

    @Test
    public void initialCoordinates() {
        GameOfLife gameOfLife = new GameOfLife(
                null,
                new MoorNeighborhood(COLUMNS,ROWS,true,1),
                301,
                201, survive, newRule
        );

        Coords2D actual = (Coords2D) gameOfLife.initialCoordinates();

        Assert.assertEquals("failure - expected x initialCoord should be -1", -1, actual.getX());
        Assert.assertEquals("failure - expected y initialCoord should be 0", 0, actual.getY());
    }

    @Test
    public void nextCoordinates() {
        GameOfLife gameOfLife = new GameOfLife(
                null,
                new MoorNeighborhood(COLUMNS,ROWS, true,1),
                301,
                201, survive, newRule
        );

        Coords2D cellCoords1 = new Coords2D(0, 0);
        Coords2D cellCoords2 = new Coords2D(299, 200);
        Coords2D cellCoords3 = new Coords2D(300, 10);

        Coords2D actual1 = (Coords2D) gameOfLife.nextCoordinates(cellCoords1);
        Coords2D actual2 = (Coords2D) gameOfLife.nextCoordinates(cellCoords2);
        Coords2D actual3 = (Coords2D) gameOfLife.nextCoordinates(cellCoords3);

        Assert.assertEquals("failure - expected next x coord for (0,0) should be 1", 1, actual1.getX());
        Assert.assertEquals("failure - expected next y coord for (0,0) should be 0", 0, actual1.getY());

        Assert.assertEquals("failure - expected next x coord for (299,200) should be 300", 300, actual2.getX());
        Assert.assertEquals("failure - expected next y coord for (299,200) should be 200", 200, actual2.getY());

        Assert.assertEquals("failure - expected next x coord for (300,10) should be 0", 0, actual3.getX());
        Assert.assertEquals("failure - expected next y coord for (300,10) should be 11", 11, actual3.getY());
    }

    @Test
    public void nextCellStateForCellInBothStatesAnd3AliveNeighboorCells() {
        GameOfLife gameOfLife = new GameOfLife(
                null,
                new MoorNeighborhood(COLUMNS,ROWS, true,1),
                301,
                201, survive, newRule
        );


        BinaryState currentState1 = BinaryState.DEAD;
        BinaryState currentState2 = BinaryState.ALIVE;
        Set<Cell> cellSet = new HashSet<Cell>(8);

        for(int i = 0; i < 3; i++) {
            cellSet.add(new Cell(BinaryState.ALIVE, new Coords2D(i,i)));
        }

        for(int i = 3; i < 8; i++) {
            cellSet.add(new Cell(BinaryState.DEAD, new Coords2D(i,i)));
        }

        BinaryState actual1 = (BinaryState)gameOfLife.nextCellState(currentState1,cellSet);
        BinaryState actual2 = (BinaryState)gameOfLife.nextCellState(currentState2,cellSet);

        Assert.assertEquals("failure - cell should be still alive while having 3 alive neighboors", BinaryState.ALIVE, actual1);
        Assert.assertEquals("failure - cell should become alive while get 3 alive neighboors", BinaryState.ALIVE, actual2);
    }

    @Test
    public void nextCellStateForCellInBothStatesAnd6AliveNeighboorCells() {
        GameOfLife gameOfLife = new GameOfLife(
                null,
                new MoorNeighborhood(COLUMNS,ROWS, true,1),
                301,
                201, survive, newRule
        );

        BinaryState currentState1 = BinaryState.DEAD;
        BinaryState currentState2 = BinaryState.ALIVE;
        Set<Cell> cellSet = new HashSet<>(8);

        for(int i = 0; i < 6; i++) {
            cellSet.add(new Cell(BinaryState.ALIVE, new Coords2D(i,i)));
        }

        for(int i = 6; i < 8; i++) {
            cellSet.add(new Cell(BinaryState.DEAD, new Coords2D(i,i)));
        }

        BinaryState actual1 = (BinaryState)gameOfLife.nextCellState(currentState1,cellSet);
        BinaryState actual2 = (BinaryState)gameOfLife.nextCellState(currentState2,cellSet);

        Assert.assertEquals("failure - cell should be still alive while having 3 alive neighboors", BinaryState.DEAD, actual1);
        Assert.assertEquals("failure - cell should become alive while get 3 alive neighboors", BinaryState.DEAD, actual2);
    }
}
