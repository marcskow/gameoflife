/**
 * Created by intenso on 06.12.15.
 */
package pl.edu.marcskow.gameoflife.model.state;

/**
 * LangtonCell is a complex CellState representing a field with an ant on it
 * to define langton cell state we have to define BinaryState of the field and AntState of ant
 */
public class LangtonCell implements CellState {
    /** state of the field */
    private BinaryState cellState;
    /** state of the and */
    private AntState antState;

    /**
     * @return state represents next state of the this state
     */
    @Override
    public CellState nextState() {
        return new LangtonCell((BinaryState)cellState.nextState(), (AntState)antState.nextState());
    }

    /** Default LangtonCell state is BinaryState.DEAD and AntState.NONE */
    public LangtonCell() {
        this(BinaryState.DEAD, AntState.NONE);
    }

    /**
     * Constructor with 2 parameters to define LangtonCell state.
     * @param cellState state of field
     * @param antState state of ant
     */
    public LangtonCell(BinaryState cellState, AntState antState) {
        this.cellState = cellState;
        this.antState = antState;
    }

    /**
     * This method is using to get state which represent "alive" state of this type of state
     * @return CellState represents next state of current LangtonCell State
     */
    @Override
    public CellState alive() {
        return new LangtonCell((BinaryState)cellState.alive(),(AntState)antState.alive());
    }

    /**
     * @return BinaryState of this
     */
    public BinaryState getCellState() {
        return cellState;
    }

    public void setCellState(BinaryState cellState) {
        this.cellState = cellState;
    }

    /**
     * @return AntState of this
     */
    public AntState getAntState() {
        return antState;
    }

    public void setAntState(AntState antState) {
        this.antState = antState;
    }
}
