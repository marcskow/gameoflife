import org.junit.Assert;
import org.junit.Test;
import pl.edu.marcskow.gameoflife.model.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.model.automat2dim.LangtonAnt;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.neighborhood.MoorNeighborhood;
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
                new MoorNeighborhood(COLUMNS,ROWS, true,1),
                301,
                201
        );

        LangtonCell currentState1 = new LangtonCell(BinaryState.ALIVE, AntState.NORTH);
        Set<Cell> cellSet = new HashSet<Cell>(8);

        cellSet.add(new Cell(new LangtonCell(BinaryState.ALIVE, AntState.NONE), new Coords2D(1,0)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(0,1)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.ALIVE, AntState.NONE), new Coords2D(2,1)));
        cellSet.add(new Cell(new LangtonCell(BinaryState.DEAD, AntState.NONE), new Coords2D(1,2)));

        LangtonCell actual1 = (LangtonCell) langtonAnt.nextCellState(currentState1,cellSet);

        Assert.assertEquals("failure - cell should be AntState.NONE, BinaryState.DEAD", new LangtonCell(BinaryState.DEAD,AntState.NONE), actual1);
    }


}