package pl.edu.marcskow.gameoflife.state;

/**
 * Created by intenso on 02.12.15.
 */
public enum BinaryState implements CellState {
    DEAD, ALIVE;

    @Override
    public CellState nextState() {
        switch (this){
            case DEAD: return ALIVE;
            case ALIVE: return DEAD;
            default: return this;
        }
    }
}
