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
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = posX + dx[i];
            int ny = posY + dy[i];

            if (nx >= 0 && nx < max.getPosX() &&
                    ny >= 0 && ny < max.getPosY()) {
                neighbours.add(new Chord(nx, ny));
            }
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