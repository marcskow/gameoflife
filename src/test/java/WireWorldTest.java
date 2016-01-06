import junit.framework.Assert;
import org.junit.Test;
import pl.edu.marcskow.gameoflife.model.automat2dim.WireWorld;
import pl.edu.marcskow.gameoflife.model.cell.Cell;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.neighborhood.MoorNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.WireElectronState;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Skowron on 2016-01-06.
 */
public class WireWorldTest {
    @Test
    public void nextStateForWireAndNextElectronHeadAndNextElectronTail(){
        WireWorld wireWorld = new WireWorld(null,
                new MoorNeighborhood(40,32,true,1),
                500, 300);

        WireElectronState current1 = WireElectronState.ELECTRON_HEAD;

        Set<Cell> cellSet = new HashSet<Cell>(8);
        cellSet.add(new Cell(WireElectronState.ELECTRON_TAIL, new Coords2D(0,1)));
        cellSet.add(new Cell(WireElectronState.VOID, new Coords2D(1,0)));
        cellSet.add(new Cell(WireElectronState.VOID, new Coords2D(2,1)));
        cellSet.add(new Cell(WireElectronState.VOID, new Coords2D(2,1)));

        WireElectronState actual = (WireElectronState)wireWorld.nextCellState(current1, cellSet);
        WireElectronState expected = WireElectronState.ELECTRON_TAIL;

        Assert.assertEquals("failure - should be tail if is electron head", expected, actual);
    }


    @Test
    public void nextStateForWireIfHeadNextTo(){
        WireWorld wireWorld = new WireWorld(null,
                new MoorNeighborhood(40,32,true,1),
                500, 300);

        WireElectronState current1 = WireElectronState.WIRE;

        Set<Cell> cellSet = new HashSet<Cell>(8);
        cellSet.add(new Cell(WireElectronState.ELECTRON_HEAD, new Coords2D(0,1)));

        WireElectronState actual = (WireElectronState)wireWorld.nextCellState(current1, cellSet);
        WireElectronState expected = WireElectronState.ELECTRON_HEAD;

        Assert.assertEquals("failure - should be head if is wire", expected, actual);
    }

    @Test
    public void nextStateForWireIf3HeadNextTo(){
        WireWorld wireWorld = new WireWorld(null,
                new MoorNeighborhood(40,32,true,1),
                500, 300);

        WireElectronState current1 = WireElectronState.WIRE;

        Set<Cell> cellSet = new HashSet<Cell>(8);
        cellSet.add(new Cell(WireElectronState.ELECTRON_HEAD, new Coords2D(0,1)));
        cellSet.add(new Cell(WireElectronState.ELECTRON_HEAD, new Coords2D(0,1)));
        cellSet.add(new Cell(WireElectronState.ELECTRON_HEAD, new Coords2D(0,1)));

        WireElectronState actual = (WireElectronState)wireWorld.nextCellState(current1, cellSet);
        WireElectronState expected = WireElectronState.WIRE;

        Assert.assertEquals("failure - should be wire if there are 3 heads in neighbors", expected, actual);
    }
}
