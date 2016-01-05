package pl.edu.marcskow.gameoflife.state;

/**
 * Created by intenso on 06.12.15.
 */
public enum AntState implements CellState {
    NONE, NORTH, SOUTH, EAST, WEST;

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
}
