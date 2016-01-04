package pl.edu.marcskow.gameoflife.gui;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.automat1dim.Automaton1DimImpl;
import pl.edu.marcskow.gameoflife.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.automat2dim.LangtonAnt;
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

    private String automatonType;
    private String neighbourhoodType;
    Map<CellCoordinates, CellState> cells;
    private int sizeX;
    private int sizeY;

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
                return new GameOfLife(factory, neighborhood);
            case "Langton Ant":
                return new LangtonAnt(factory, neighborhood);
            case "WireWorld":
                return new WireWorld(factory, neighborhood);
            case "QuadLife":
                return new GameOfLife(factory, neighborhood);
            case "Elementary":
                return new Automaton1DimImpl(factory, neighborhood);
            default:
                return new GameOfLife(factory, neighborhood);
        }
    }

    public Automaton getAutomaton() {
        if (automatonType != null) {
            switch (automatonType) {
                case "Game Of Life":
                    GameOfLife gameOfLife = new GameOfLife(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType)
                    );
                    gameOfLife.setHeight(sizeY);
                    gameOfLife.setWidth(sizeX);
                    gameOfLife.setMap(cells);
                    return gameOfLife;

                case "Langton Ant":
                    LangtonAnt langtonAnt = new LangtonAnt(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType)
                    );
                    langtonAnt.setHeight(sizeY);
                    langtonAnt.setWidth(sizeX);
                    langtonAnt.setMap(cells);
                    return langtonAnt;

                case "WireWorld":
                    WireWorld wireWorld = new WireWorld(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType)
                    );
                    wireWorld.setHeight(sizeY);
                    wireWorld.setWidth(sizeX);
                    wireWorld.setMap(cells);
                    return wireWorld;
                case "QuadLife":
                    GameOfLife quadlife = new GameOfLife(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType)
                    );
                    quadlife.setHeight(sizeY);
                    quadlife.setWidth(sizeX);
                    quadlife.setMap(cells);
                    return quadlife;
                case "Elementary":
                    Automaton1DimImpl elementary = new Automaton1DimImpl(
                            stateFactoryFromString(automatonType, cells),
                            neighbourhoodFromString(neighbourhoodType)
                    );
                    elementary.setSize(sizeX * sizeY);
                    elementary.setMap(cells);
                    return elementary;
                default:
                    return null;
            }
        }
        return null;
    }
}