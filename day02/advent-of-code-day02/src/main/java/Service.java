import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public List<Range> ranges = new ArrayList<>();

    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(",");
                for (String data: datas) {
                    String start = data.split("-")[0];
                    String end = data.split("-")[1];
                    ranges.add(new Range(start,end));
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public Long isValid(Long number) {
        return 0L;
    }
    public long partOne() {
        Long sumOfInvalidId = 0L;
        for (Range range: ranges) {
            if (range.getStart().startsWith("0") || range.getEnd().startsWith("0") ) {
                for (long number = Long.parseLong(range.getStart()); number <= Long.parseLong(range.getEnd()); number++) {
                    sumOfInvalidId += number;
                }
            } else {
                for (long number = Long.parseLong(range.getStart()); number <= Long.parseLong(range.getEnd()); number++) {
                    sumOfInvalidId += isValid(number);
                }
            }
        }
        return sumOfInvalidId;
    }
    public int partTwo() {
        return 0;
    }
}
