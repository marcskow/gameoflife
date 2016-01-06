import org.junit.Assert;
import org.junit.Test;
import pl.edu.marcskow.gameoflife.model.automat1dim.Elementary;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords1D;
import pl.edu.marcskow.gameoflife.model.neighborhood.ElementaryNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.BinaryState;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Skowron on 2016-01-06.
 */
public class ElementaryTest {

    @Test
    public void nextCellStateForCellWithoutantAndNeighborOnRightWithAntComingToCellRule110() {
        Elementary elementary = new Elementary(null, new ElementaryNeighborhood(50, true),50, 110);

        BinaryState currentState1 = BinaryState.DEAD;
        Set<Cell> cellSet = new HashSet<Cell>(4);

        cellSet.add(new Cell(BinaryState.ALIVE, new Coords1D(2)));
        cellSet.add(new Cell(BinaryState.DEAD, new Coords1D(4)));

        BinaryState expected = BinaryState.ALIVE;
        BinaryState actual = (BinaryState)elementary.nextCellState(currentState1,cellSet);

        Assert.assertEquals("failure - should be alive", expected, actual);
    }


    @Test
    public void nextCellStateAllCellsAliveRule30() {
        Elementary elementary = new Elementary(null, new ElementaryNeighborhood(50, true),50, 30);

        BinaryState currentState1 = BinaryState.ALIVE;
        Set<Cell> cellSet = new HashSet<Cell>(4);

        cellSet.add(new Cell(BinaryState.ALIVE, new Coords1D(2)));
        cellSet.add(new Cell(BinaryState.ALIVE, new Coords1D(4)));

        BinaryState expected = BinaryState.DEAD;
        BinaryState actual = (BinaryState)elementary.nextCellState(currentState1,cellSet);

        Assert.assertEquals("failure - should be dead", expected, actual);
    }
}
