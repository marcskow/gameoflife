/**
 * Created by Marcin Skowron on 02.12.15.
 */
package pl.edu.marcskow.gameoflife.model.state;

import java.util.Random;

/**
 * Type of state using by quadlife automaton can be five states
 * default - dead, and red, yellow, blue or green states of alive cells.
 * @see CellState
 */
public enum QuadState implements CellState {
    DEAD, RED, YELLOW, BLUE, GREEN;

    /**
     * @return next state of the state
     */
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

    /**
     * Method is using random number generator to calculate random alive state
     * @return random state represents alive state: red, blue, yellow or red
     */
    @Override
    public CellState alive() {
        Random generator = new Random();
        if(generator.nextInt(4) == 0) {
            return RED;
        }
        else if(generator.nextInt(4) == 1){
            return BLUE;
        }
        else if(generator.nextInt(4) == 2){
            return YELLOW;
        }
        else if(generator.nextInt(4) == 3){
            return GREEN;
        }
        else return RED;
    }

}
