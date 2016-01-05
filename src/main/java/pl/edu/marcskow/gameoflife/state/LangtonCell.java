package pl.edu.marcskow.gameoflife.state;

import pl.edu.marcskow.gameoflife.cell.Ant;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by intenso on 06.12.15.
 */
public class LangtonCell implements CellState {
    private BinaryState cellState;
    private AntState antState;

    @Override
    public CellState nextState() {
        return new LangtonCell((BinaryState)cellState.nextState(), (AntState)antState.nextState());
    }

    // TODO: 2015-12-30 should be <Ant>?
    public LangtonCell() {
        this(BinaryState.DEAD, AntState.NONE);
    }

    public LangtonCell(BinaryState cellState, AntState antState) {
        this.cellState = cellState;
        this.antState = antState;
    }

    public BinaryState getCellState() {
        return cellState;
    }

    public void setCellState(BinaryState cellState) {
        this.cellState = cellState;
    }

    public AntState getAntState() {
        return antState;
    }

    public void setAntState(AntState antState) {
        this.antState = antState;
    }
}
