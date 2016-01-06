package pl.edu.marcskow.gameoflife.controller.helpers;

import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.model.automat1dim.Elementary;
import pl.edu.marcskow.gameoflife.model.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.model.automat2dim.LangtonAnt;
import pl.edu.marcskow.gameoflife.model.automat2dim.QuadLife;
import pl.edu.marcskow.gameoflife.model.automat2dim.WireWorld;
import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.factory.GeneralStateFactory;
import pl.edu.marcskow.gameoflife.model.neighborhood.ElementaryNeighborhood;
import pl.edu.marcskow.gameoflife.model.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.model.neighborhood.MoorNeighborhood;
import pl.edu.marcskow.gameoflife.model.neighborhood.VonNeumanNeighborhood;
import pl.edu.marcskow.gameoflife.model.state.CellState;

import java.util.Map;

public class OnStartService {
    private String automatonType;
    private String neighbourhoodType;
    Map<CellCoordinates, CellState> cells;
    private int sizeX;
    private int sizeY;
    private boolean isWrapping;
    private int radius;

    public OnStartService() {
        this.automatonType = null;
        this.neighbourhoodType = null;
        this.cells = null;
        this.sizeX = 0;
        this.sizeY = 0;
    }

    public CellNeighborhood neighbourhoodFromString(String type) {
        switch (type) {
            case "Moore":
                return new MoorNeighborhood(sizeX,sizeY,isWrapping,radius);
            case "Von Neuman":
                return new VonNeumanNeighborhood(sizeX,sizeY,isWrapping,radius);
            case "Elementary":
                return new ElementaryNeighborhood(sizeX*sizeY,isWrapping);
            default:
                return new MoorNeighborhood(sizeX,sizeY,isWrapping,radius);
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
                    Elementary elementary = new Elementary(
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


    public boolean isWrapping() {
        return isWrapping;
    }

    public void setWrapping(boolean wrapping) {
        isWrapping = wrapping;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getNeighbourhoodType() {
        return neighbourhoodType;
    }

    public void setNeighbourhoodType(String neighbourhoodType) {
        this.neighbourhoodType = neighbourhoodType;
    }

    public String getAutomatonType() {
        return automatonType;
    }

    public void setAutomatonType(String automaton){
        this.automatonType = automaton;
    }

    public void setDimension(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void startSettingsUpdate(Map<CellCoordinates, CellState> cells) {
        this.cells = cells;
    }
}