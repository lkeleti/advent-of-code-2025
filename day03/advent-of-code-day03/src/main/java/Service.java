import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final List<String> numbers = new ArrayList<>();

    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(line);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }
    public NumPos findLargestNumber(int start, int end, String number) {
        int maxNumber = -1;
        int maxPosition = -1;
        for (int i= start; i < end; i++) {
            int defDigit = Integer.parseInt(number.substring(i,i+1));
            if (defDigit > maxNumber ) {
                maxNumber = defDigit;
                maxPosition = i;
            }
        }
        return new NumPos(maxNumber,maxPosition);
    }
    public long partOne() {
        Long sumOfNumbers = 0L;
        for (String number: numbers) {
            NumPos first = findLargestNumber(0, number.length() - 1, number);
            NumPos secund = findLargestNumber(first.getPosition() + 1, number.length(), number);
            sumOfNumbers += (first.getNumber() * 10L) + secund.getNumber();
        }
        return sumOfNumbers;
    }
    public int partTwo() {
        return 0;
    }
}
