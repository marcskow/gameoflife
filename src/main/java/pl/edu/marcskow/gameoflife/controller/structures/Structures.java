package pl.edu.marcskow.gameoflife.controller.structures;

import pl.edu.marcskow.gameoflife.model.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.model.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.model.state.CellState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * It's a container of structures, for start there are some defined structures, but they can be also included to the
 * container later.
 * @see Structure
 */
public class Structures {
    private ArrayList<Structure> structures;

    public Structures(){
        int[][] gliderInt = {
                {0,0,1},
                {1,0,1},
                {0,1,1}};

        int[][] LWSS = {
                {0,1,0,0,1},
                {1,0,0,0,0},
                {1,0,0,0,1},
                {1,1,1,1,0}
        };



        int[][] gospelglidergun = {
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                {1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        int[][] orion={
                {0,0,0,1,1,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0},
                {1,1,0,1,0,0,0,0,0,0,0,0,0,0},
                {1,0,0,0,0,1,0,0,0,0,0,0,0,0},
                {1,0,1,1,0,0,0,0,0,0,1,1,1,0},
                {0,0,0,0,0,1,1,1,0,0,0,0,1,1},
                {0,0,0,0,0,0,1,1,1,0,1,0,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,1,1,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,1,1,0,0,0,0,0,0,0}
        };

        structures = new ArrayList<>(10);
        Structure glider = new Structure(3,3,"Glider");
        glider.initializeFromIntArray(3,3,gliderInt);
        structures.add(glider);
        Structure dakota = new Structure(4,5,"Dakota");
        dakota.initializeFromIntArray(4,5,LWSS);
        structures.add(dakota);

        Structure gospel = new Structure(9,36,"Gosper Glider Gun");
        gospel.initializeFromIntArray(9,36,gospelglidergun);
        structures.add(gospel);

        Structure orions = new Structure(15,14,"Orion");
        orions.initializeFromIntArray(15,14,orion);
        structures.add(orions);

    }

    public Structure findOnList(String name){
        for (Structure s: structures) {
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    /**
     * @param name which structure
     * @param type type of automaton
     * @param dead kind of dead cell state
     * @param whereX where structure will be putted
     * @param whereY where structure will be putted
     * @return structure prepared to put on grid
     */
    public Map<CellCoordinates,CellState> returnStructure(String name, String type, CellState dead, int whereX, int whereY){
        Structure temp = findOnList(name);
        Structure temp2 = new Structure(temp.getHeight(),temp.getWidth(),"temp");
        Map<CellCoordinates,CellState> result = new HashMap<>();

        if(type.equals("QuadLife")){
            temp2.setStructure(temp.convertToQuadLife());
        }

        for (int i = 0; i < temp.getHeight(); i++) {
            for (int j = 0; j < temp.getWidth(); j++) {
                CellState c;
                if(type.equals("QuadLife")){
                    c = temp2.getStructure()[i][j];
                }
                else{
                    c = temp.getStructure()[i][j];
                }
                if(c != dead) {
                    result.put(new Coords2D(j + whereX, i + whereY), c);
                }
            }
        }

        return result;
    }

}
