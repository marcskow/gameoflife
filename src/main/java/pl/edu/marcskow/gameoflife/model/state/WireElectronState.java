/**
 * Created by Marcin Skowron on 02.12.15.
 */
package pl.edu.marcskow.gameoflife.model.state;

/**
 * Enum class represents state of Cell in WireWorld automaton.
 * Can be void, wire, electron_head or electron_tail
 * @see CellState
 */
public enum WireElectronState implements CellState{
    VOID, WIRE, ELECTRON_HEAD, ELECTRON_TAIL;

    /**
     * @return next state of the state
     */
    @Override
    public CellState nextState() {
        switch (this) {
            case VOID:
                return WIRE;
            case WIRE:
                return ELECTRON_HEAD;
            case ELECTRON_HEAD:
                return ELECTRON_TAIL;
            case ELECTRON_TAIL:
                return VOID;
            default: return this;
        }
    }

    /**
     * @return the state which represent alive state of this which mean there: wire
     */
    @Override
    public CellState alive() {
        return WIRE;
    }
}
