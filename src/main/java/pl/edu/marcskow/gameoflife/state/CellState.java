package pl.edu.marcskow.gameoflife.state;

/**
 * Created by intenso on 30.11.15.
 */
public interface CellState {
    CellState nextState();
    CellState alive();
}
