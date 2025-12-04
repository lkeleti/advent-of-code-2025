import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chord {
    private int posX;
    private int posY;

    public Chord(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public List<Chord> getNeighbours(Chord max) {
        List<Chord> neighbours = new ArrayList<>();
        if (getPosX() - 1 >= 0) {
            neighbours.add(new Chord(getPosX()-1, getPosY()));
            if (getPosY() - 1 >= 0) {
                neighbours.add(new Chord(getPosX()-1, getPosY()-1));
            }

            if (getPosY() + 1 <= max.getPosY()) {
                neighbours.add(new Chord(getPosX()-1, getPosY()+1));
            }
        }

        if (getPosX() + 1 <= max.getPosX()) {
            neighbours.add(new Chord(getPosX()+1, getPosY()));
            if (getPosY() - 1 >= 0) {
                neighbours.add(new Chord(getPosX()+1, getPosY()-1));
            }

            if (getPosY() + 1 <= max.getPosY()) {
                neighbours.add(new Chord(getPosX()+1, getPosY()+1));
            }
        }

        if (getPosY() - 1 >= 0) {
            neighbours.add(new Chord(getPosX(), getPosY()-1));
        }

        if (getPosY() + 1 <= max.getPosY()) {
            neighbours.add(new Chord(getPosX(), getPosY()+1));
        }
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Chord chord)) return false;
        return getPosX() == chord.getPosX() && getPosY() == chord.getPosY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosX(), getPosY());
    }
}