package pl.edu.marcskow.gameoflife.state;

import java.util.Random;

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
