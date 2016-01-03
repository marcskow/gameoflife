package pl.edu.marcskow.gameoflife.state;

/**
 * Created by intenso on 02.12.15.
 */
public enum QuadState implements CellState {
    DEAD, RED, YELLOW, BLUE, GREEN;

    @Override
    public CellState nextState() {
        switch (this) {
            case DEAD:
                return RED;
            case RED:
                return YELLOW;
            case YELLOW:
                return BLUE;
            case BLUE:
                return GREEN;
            case GREEN:
                return DEAD;
            default: return this;
        }
    }
}
