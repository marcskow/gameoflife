package pl.edu.marcskow.gameoflife.gui;

import pl.edu.marcskow.gameoflife.cell.Cell;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.state.BinaryState;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin Skowron on 2016-01-03.
 */
public class Test {
    public static void main(String[] args) {
        int countOfAliveCells = 0;
        Set<Cell> cellNeighbors = new HashSet<>();
        cellNeighbors.add(new Cell(BinaryState.ALIVE, new Coords2D(0,0)));
        cellNeighbors.add(new Cell(BinaryState.ALIVE, new Coords2D(0,0)));
        for (Cell c: cellNeighbors) {
            if(c.getState() == BinaryState.ALIVE) {
                countOfAliveCells++;
            }
        }
        System.out.println(countOfAliveCells);
        System.out.println(cellNeighbors.size());
    }
}
