package pl.edu.marcskow.gameoflife.gui;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.automat1dim.Automaton1DimImpl;
import pl.edu.marcskow.gameoflife.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.automat2dim.LangtonAnt;
import pl.edu.marcskow.gameoflife.automat2dim.QuadLife;
import pl.edu.marcskow.gameoflife.automat2dim.WireWorld;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.factory.GeneralStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.Automaton1DimNeighborhood;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.neighborhood.MoreNeighborhood;
import pl.edu.marcskow.gameoflife.neighborhood.VonNeumanNeighborhood;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.Map;

/**
 * Created by Marcin Skowron on 2016-01-04.
 *
 */
public class OnStartService {

    public static int[] cellSurvivingRule;
    public static int[] newCellRule;

    private String automatonType;
    private String neighbourhoodType;
    Map<CellCoordinates, CellState> cells;
    private int sizeX;
    private int sizeY;
    private Automaton automaton;

    public OnStartService() {
        this.automatonType = null;
        this.neighbourhoodType = null;
        this.cells = null;
        this.sizeX = 0;
        this.sizeY = 0;
    }

    public void startSettingsUpdate(String automatonType, String neighbourhoodType, Map<CellCoordinates, CellState> cells, int sizeX, int sizeY) {
        this.automatonType = automatonType;
        this.neighbourhoodType = neighbourhoodType;
        this.cells = cells;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.automaton = getAutomaton();
    }

    public CellNeighborhood neighbourhoodFromString(String type) {
        switch (type) {
            case "Moore":
                return new MoreNeighborhood();
            case "Von Neuman":
                return new VonNeumanNeighborhood();
            case "Elementary":
                return new Automaton1DimNeighborhood();
            default:
                return new MoreNeighborhood();
        }
    }

    public CellStateFactory stateFactoryFromString(String type, Map<CellCoordinates, CellState> cells) {
        switch (type) {
            case "Game Of Life":
                return new GeneralStateFactory(cells);
            case "Langton Ant":
                return new GeneralStateFactory(cells);
            case "WireWorld":
                return new GeneralStateFactory(cells);
            case "QuadLife":
                return new GeneralStateFactory(cells);
            case "Elementary":
                return new GeneralStateFactory(cells);
            default:
                return new GeneralStateFactory(cells);
        }
    }

    public Automaton automatonFromString(String type, CellNeighborhood neighborhood, CellStateFactory factory) {
        switch (type) {
            case "Game Of Life":
                return new GameOfLife(factory, neighborhood, sizeX, sizeY);
            case "Langton Ant":
                return new LangtonAnt(factory, neighborhood, sizeX, sizeY);
            case "WireWorld":
                return new WireWorld(factory, neighborhood, sizeX, sizeY);
            case "QuadLife":
                return new QuadLife(factory, neighborhood, sizeX, sizeY);
            case "Elementary":
                return new Automaton1DimImpl(factory, neighborhood, sizeX * sizeY);
            default:
                return new GameOfLife(factory, neighborhood, sizeX, sizeY);
        }
    }

    public Automaton updateAutomaton(){
        return null;
    }

    public Automaton getAutomaton() {
        if (automatonType != null) {
            switch (automatonType) {
                case "Game Of Life":
                    GameOfLife gameOfLife = new GameOfLife(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX, sizeY
                    );
                    //gameOfLife.setHeight(sizeY);
                    //gameOfLife.setWidth(sizeX);
                    gameOfLife.setMap(cells);
                    return gameOfLife;

                case "Langton Ant":
                    LangtonAnt langtonAnt = new LangtonAnt(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX, sizeY
                    );
                    //langtonAnt.setHeight(sizeY);
                    //langtonAnt.setWidth(sizeX);
                    langtonAnt.setMap(cells);
                    return langtonAnt;

                case "WireWorld":
                    WireWorld wireWorld = new WireWorld(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX, sizeY
                    );
                    //wireWorld.setHeight(sizeY);
                    //wireWorld.setWidth(sizeX);
                    wireWorld.setMap(cells);
                    return wireWorld;
                case "QuadLife":
                    QuadLife quadlife = new QuadLife(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX,
                            sizeY
                    );
                    //quadlife.setHeight(sizeY);
                    //quadlife.setWidth(sizeX);
                    quadlife.setMap(cells);
                    return quadlife;
                case "Elementary":
                    Automaton1DimImpl elementary = new Automaton1DimImpl(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX * sizeY
                    );
                   // elementary.setSize(sizeX * sizeY);
                    elementary.setMap(cells);
                    return elementary;
                default:
                    return null;
            }
        }
        return null;
    }
}