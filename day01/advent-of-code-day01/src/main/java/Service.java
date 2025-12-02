import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public List<Integer> sequences = new ArrayList<>();
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                int number =  Integer.parseInt(line.substring(1));
                if (line.startsWith("L")) {
                    sequences.add(-1*number);
                } else {
                    sequences.add(number);
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }
    public long partOne() {
        int pos = 50;
        int zeroStopCount = 0;
        for (int move : sequences) {
            if (move > 0) {
                pos = (pos + move) % 100;

            } else {
                int steps = (-move) % 100;

                pos -= steps;
                if (pos < 0) pos += 100;
            }

            if (pos == 0) {
                zeroStopCount++;
            }
        }
        return zeroStopCount;
    }
    public int partTwo() {
        int pos = 50;
        int zeroCross = 0;
        int zeroStop = 0;

        for (int move : sequences) {

            int start = pos;
            int amount = Math.abs(move);
            int dir = Integer.signum(move);

            // 1. Teljes körök
            int full = amount / 100;
            zeroCross += full;

            // 2. Maradék lépések
            int rest = amount % 100;
            if (rest == 0) {
                zeroCross -= 1;
                continue;}

            // 3. Új pozíció
            int end;
            if (dir > 0) { // jobbra
                end = (start + rest) % 100;
            } else { // balra
                end = start - rest;
                if (end < 0) end += 100;
            }

            // 4. Maradék lépésben történik-e átlépés?
            if (dir > 0) {
                if (start + rest >= 100 && end != 0) zeroCross++;
            } else {
                if (start - rest < 0 && end != 0) zeroCross++;
            }

            // 5. 0-n állt meg
            if (end == 0) zeroStop++;

            pos = end;
        }

        return zeroCross + zeroStop;
        //6603 high
        //6527 low
    }
}
