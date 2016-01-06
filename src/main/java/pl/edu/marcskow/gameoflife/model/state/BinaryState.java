/**
 * Created by Marcin Skowron on 02.12.15.
 */
package pl.edu.marcskow.gameoflife.model.state;

/**
 * CellState usually using in automatons.
 * @see CellState
 */
public enum BinaryState implements CellState {
    DEAD, ALIVE;

    /**
     * @return next state of this state
     */
    @Override
    public CellState nextState() {
        switch (this){
            case DEAD: return ALIVE;
            case ALIVE: return DEAD;
            default: return this;
        }
    }

    /**
     * @return BinaryState.ALIVE
     */
    @Override
    public CellState alive() {
        return ALIVE;
    }


}
