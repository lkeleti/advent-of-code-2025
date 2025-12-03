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

    private long extractNumber(String number, int digits) {
        StringBuilder sb = new StringBuilder();
        int start = 0;

        for (int remaining = digits; remaining > 0; remaining--) {
            int end = number.length() - remaining + 1;

            int maxDigit = -1;
            int maxPos = -1;

            for (int i = start; i < end; i++) {
                int digit = number.charAt(i) - '0';
                if (digit > maxDigit) {
                    maxDigit = digit;
                    maxPos = i;
                }
            }

            sb.append(maxDigit);
            start = maxPos + 1;
        }
        return Long.parseLong(sb.toString());
    }

    public long partOne() {
        long sum = 0;
        for (String number : numbers) {
            sum += extractNumber(number, 2);
        }
        return sum;
    }

    public long partTwo() {
        long sum = 0;
        for (String number : numbers) {
            sum += extractNumber(number, 12);
        }
        return sum;
    }
}
