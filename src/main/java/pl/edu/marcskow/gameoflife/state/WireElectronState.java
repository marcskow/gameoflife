package pl.edu.marcskow.gameoflife.state;

/**
 * Created by intenso on 02.12.15.
 */
public enum WireElectronState implements CellState{
    VOID, WIRE, ELECTRON_HEAD, ELECTRON_TAIL;

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
}
