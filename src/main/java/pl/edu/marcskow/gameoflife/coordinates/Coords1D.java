package pl.edu.marcskow.gameoflife.coordinates;

/**
 * Created by intenso on 02.12.15.
 */
public class Coords1D implements CellCoordinates {
    private final int x;

    public Coords1D(){
        this(0);
    }

    public Coords1D(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coords1D coords1D = (Coords1D) o;

        return x == coords1D.x;

    }

    @Override
    public int hashCode() {
        return x;
    }
}
