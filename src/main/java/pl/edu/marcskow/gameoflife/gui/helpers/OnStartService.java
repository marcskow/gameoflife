package pl.edu.marcskow.gameoflife.gui.helpers;

import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.automat1dim.Automaton1DimImpl;
import pl.edu.marcskow.gameoflife.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.automat2dim.LangtonAnt;
import pl.edu.marcskow.gameoflife.automat2dim.QuadLife;
import pl.edu.marcskow.gameoflife.automat2dim.WireWorld;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.factory.GeneralStateFactory;
import pl.edu.marcskow.gameoflife.gui.helpers.GameSettings;
import pl.edu.marcskow.gameoflife.neighborhood.ElementaryNeighborhood;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.neighborhood.MoorNeighborhood;
import pl.edu.marcskow.gameoflife.neighborhood.VonNeumanNeighborhood;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.util.Map;

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

    public void startSettingsUpdate(Map<CellCoordinates, CellState> cells) {
        this.automatonType = GameSettings.AUTOMATON;
        this.neighbourhoodType = GameSettings.NEIGHBORHOOD;
        this.sizeX = GameSettings.COLUMNS;
        this.sizeY = GameSettings.ROWS;
        this.cells = cells;
    }
    public CellNeighborhood neighbourhoodFromString(String type) {
        switch (type) {
            case "Moore":
                return new MoorNeighborhood();
            case "Von Neuman":
                return new VonNeumanNeighborhood();
            case "Elementary":
                return new ElementaryNeighborhood();
            default:
                return new MoorNeighborhood();
        }
    }

    public Automaton getAutomaton() {
        if (automatonType != null) {
            switch (automatonType) {
                case "Game Of Life":
                    GameOfLife gameOfLife = new GameOfLife(
                            new GeneralStateFactory(cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX, sizeY
                    );
                    gameOfLife.setMap(cells);
                    return gameOfLife;

                case "Langton Ant":
                    LangtonAnt langtonAnt = new LangtonAnt(
                            new GeneralStateFactory(cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX, sizeY
                    );
                    langtonAnt.setMap(cells);
                    return langtonAnt;

                case "WireWorld":
                    WireWorld wireWorld = new WireWorld(
                            new GeneralStateFactory(cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX, sizeY
                    );
                    wireWorld.setMap(cells);
                    return wireWorld;
                case "QuadLife":
                    QuadLife quadlife = new QuadLife(
                            new GeneralStateFactory(cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX,
                            sizeY
                    );
                    quadlife.setMap(cells);
                    return quadlife;
                case "Elementary":
                    Automaton1DimImpl elementary = new Automaton1DimImpl(
                            new GeneralStateFactory(cells),
                            neighbourhoodFromString(neighbourhoodType),
                            sizeX * sizeY
                    );
                    elementary.setMap(cells);
                    return elementary;
                default:
                    return null;
            }
        }
        return null;
    }
}