package pl.edu.marcskow.gameoflife.gui;

import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.state.BinaryState;

/**
 * Created by Marcin Skowron on 2016-01-03.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(BinaryState.ALIVE.nextState());
    }
}
