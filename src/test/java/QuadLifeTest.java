import org.junit.Assert;
import org.junit.Test;
import pl.edu.marcskow.gameoflife.model.automat2dim.QuadLife;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.neighborhood.MoorNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.QuadState;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Skowron on 2016-01-06.
 */
public class QuadLifeTest {

    @Test
    public void testIfThereAreMoreThen3AliveCells(){
        QuadLife quadLife = new QuadLife(null, new MoorNeighborhood(40,32,true,1), 40, 32);

        QuadState currentState1 = QuadState.DEAD;
        Set<Cell> cellSet = new HashSet<Cell>(4);
        cellSet.add(new Cell(QuadState.BLUE, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.BLUE, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.BLUE, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.BLUE, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.BLUE, new Coords2D(1,0)));

        QuadState actual = (QuadState) quadLife.nextCellState(currentState1,cellSet);
        QuadState expected = QuadState.DEAD;

        Assert.assertEquals("failure - should be dead if there are over 3 alive neighbors", expected, actual);
    }

    @Test
    public void testIfThereAreExactly3AliveCells(){
        QuadLife quadLife = new QuadLife(null, new MoorNeighborhood(40,32,true,1), 40, 32);

        QuadState currentState1 = QuadState.DEAD;
        Set<Cell> cellSet = new HashSet<Cell>(4);
        cellSet.add(new Cell(QuadState.RED, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.RED, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.BLUE, new Coords2D(1,0)));

        QuadState actual = (QuadState) quadLife.nextCellState(currentState1,cellSet);
        QuadState expected = QuadState.RED;

        Assert.assertEquals("failure - should be read if there are 3 alive neighbors and 2 of it is red", expected, actual);
    }

    @Test
    public void testIfThereAreExactly2AliveCells(){
        QuadLife quadLife = new QuadLife(null, new MoorNeighborhood(40,32,true,1), 40, 32);

        QuadState currentState1 = QuadState.RED;
        Set<Cell> cellSet = new HashSet<Cell>(4);
        cellSet.add(new Cell(QuadState.YELLOW, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.YELLOW, new Coords2D(1,0)));
        cellSet.add(new Cell(QuadState.YELLOW, new Coords2D(1,0)));

        QuadState actual = (QuadState) quadLife.nextCellState(currentState1,cellSet);
        QuadState expected = QuadState.RED;

        Assert.assertEquals("failure - alive cell with 2 alive neighbours should be alive and has still the same color", expected, actual);
    }
}
