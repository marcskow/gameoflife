/**
 * Created by intenso on 06.12.15.
 */
package pl.edu.marcskow.gameoflife.model.state;

/**
 * It's a state of ant in langton and automaton
 * @see pl.edu.marcskow.gameoflife.model.automat2dim.LangtonAnt
 * @see LangtonCell
 * @see CellState
 */
public enum AntState implements CellState {
    NONE, NORTH, SOUTH, EAST, WEST;

    /**
     * Using on simulation to calculate new state of cell
     * @return AntState
     */
    @Override
    public CellState nextState() {
        switch (this) {
            case NONE:
                return NORTH;
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            case WEST:
                return NONE;
            default: return NORTH;
        }
    }

    /**
     * @return AntState NONE
     */
    @Override
    public CellState alive() {
        return NONE;
    }


}
