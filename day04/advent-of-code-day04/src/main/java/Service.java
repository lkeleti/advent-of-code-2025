import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<List<Character>> table = new ArrayList<>();
    private Chord tableSize;
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for(char c:line.toCharArray()) {
                    row.add(c);
                }
                table.add(row);
            }
            tableSize = new Chord(table.getFirst().size(), table.size());
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }
    public long partOne() {
        return findRollsToRemove(false);
    }

    private Long findRollsToRemove(boolean remove) {
        long sum = 0L;
        List<Chord> rollsToRemove = new ArrayList<>();
        for (int y = 0; y < tableSize.getPosY();y++) {
            for (int x = 0; x < tableSize.getPosX();x++) {
                if (table.get(y).get(x) == '@') {
                    Chord defPos = new Chord(x, y);
                    int counter = 0;
                    for (Chord neighbour : defPos.getNeighbours(tableSize)) {
                        if (table.get(neighbour.getPosY()).get(neighbour.getPosX()) == '@') {
                            counter++;
                        }
                    }
                    if (counter < 4) {
                        sum +=1;
                        rollsToRemove.add(defPos);
                    }
                }
            }
        }
        if (remove) {
            for (Chord chord : rollsToRemove) {
                table.get(chord.getPosY()).set(chord.getPosX(), '.');
            }
        }
        return sum;
    }

    public long partTwo() {
        long total = 0L;
        long sum;
        while((sum = findRollsToRemove(true)) != 0) {
            total += sum;
        }
        return total;
    }
}
