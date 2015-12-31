package pl.edu.marcskow.gameoflife.cell;

import pl.edu.marcskow.gameoflife.state.AntState;
import pl.edu.marcskow.gameoflife.state.BinaryState;

import java.util.Set;

/**
 * Created by Marcin Skowron on 2015-12-30.
 */
public class Ant {
    private static int id = 0;

    private int antId;
    private AntState antState;

    public Ant(AntState antState) {
        this.antId = id++;
        this.antState = antState;
    }

    public int getAntId() {
        return antId;
    }

    public void setAntId(int antId) {
        this.antId = antId;
    }

    public AntState getAntState() {
        return antState;
    }

    public void setAntState(AntState antState) {
        this.antState = antState;
    }
}
