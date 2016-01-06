import org.junit.Assert;
import org.junit.Test;
import pl.edu.marcskow.gameoflife.model.automat2dim.LangtonAnt;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.neighborhood.VonNeumanNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.AntState;
import pl.edu.marcskow.gameoflife.model.state.BinaryState;
import pl.edu.marcskow.gameoflife.model.state.LangtonCell;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Skowron on 2015-12-29.
 */

public class LangtonAntTest {
    public final static int COLUMNS = 40;
    public final static int ROWS = 32;

    @Test
    public void nextCellStateForCellWithAnt() {
        LangtonAnt langtonAnt = new LangtonAnt(
                null,
                new VonNeumanNeighborhood(COLUMNS,ROWS, true,1),
                301,
                201
        );

        LangtonCell currentState1 = new LangtonCell(BinaryState.DEAD, AntState.NORTH);
        Set<Cell> cellSet = new HashSet<Cell>(4);

        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(2,3)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(3,2)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(4,3)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(3,4)));

        LangtonCell actual1 = (LangtonCell) langtonAnt.nextCellState(currentState1,cellSet);
        LangtonCell expected = new LangtonCell(BinaryState.ALIVE, AntState.NONE);

        Assert.assertTrue(areEquals(actual1,expected));
    }

    @Test
    public void nextCellStateForCellWithoutantAndNeighborOnRightWithAntComingToCell() {
        LangtonAnt langtonAnt = new LangtonAnt(
                null,
                new VonNeumanNeighborhood(COLUMNS,ROWS, true,1),
                501,
                401
        );

        LangtonCell currentState1 = new LangtonCell(BinaryState.DEAD, AntState.NONE);
        Set<Cell> cellSet = new HashSet<Cell>(4);

        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(2,3)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(3,2)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.ALIVE, AntState.SOUTH), new Coords2D(4,3)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(3,4)));

        LangtonCell actual1 = (LangtonCell) langtonAnt.nextCellState(currentState1,cellSet);
        LangtonCell expected = new LangtonCell(BinaryState.DEAD, AntState.WEST);

        Assert.assertTrue(areEquals(actual1,expected));
    }


    public boolean areEquals(LangtonCell one, LangtonCell two){
        return (one.getAntState() == two.getAntState()) && (one.getCellState() == two.getCellState());
    }


}