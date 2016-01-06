/**
 * Created by Marcin Skowron on 30.11.15.
 */
package pl.edu.marcskow.gameoflife.model.state;

/**
 * The CellState interface should be implements by any class who represents state of cells in
 * cellular automaton
 * @see pl.edu.marcskow.gameoflife.model.automat.Automaton
 */
public interface CellState {
    /**
     * This method is using to get next state of the state without knowledge of state type
     * @return CellState represents next state of current state
     */
    CellState nextState();
    /**
     * This method is using to get state which represent "alive" state of the state
     * @return CellState represents next state of current state
     */
    CellState alive();
}
