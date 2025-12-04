import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<List<Character>> table = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<Character> row = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                for(char c:line.toCharArray()) {
                    row.add(c);
                }
                table.add(row);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }
    public long partOne() {
        return 0;
    }
    public int partTwo() {
        return 0;
    }
}
