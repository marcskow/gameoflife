package pl.edu.marcskow.gameoflife.state;

import pl.edu.marcskow.gameoflife.cell.Ant;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by intenso on 06.12.15.
 */
public class LangtonCell implements CellState {
    private BinaryState cellState;
    private Set<Ant> ants;

    // TODO: 2015-12-30 should be <Ant>?
    public LangtonCell() {
        this(BinaryState.DEAD, new HashSet<Ant>(1));
    }

    public LangtonCell(BinaryState cellState, Set<Ant> ants) {
        this.cellState = cellState;
        this.ants = ants;
    }

    public BinaryState getCellState() {
        return cellState;
    }

    public void setCellState(BinaryState cellState) {
        this.cellState = cellState;
    }

    public Set<Ant> getAnts() {
        return ants;
    }

    public void setAnts(Set<Ant> ants) {
        this.ants = ants;
    }

    public void addAnt(Ant ant){
        ants.add(ant);
    }
}
