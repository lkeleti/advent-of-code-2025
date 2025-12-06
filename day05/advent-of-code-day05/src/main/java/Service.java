import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Service {
    private final List<Interval> intervals = new ArrayList<>();
    private final List<Long> numbers = new ArrayList<>();

    public void readInput(Path path) {
        boolean isNumbers = false;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!isNumbers) {
                    if (line.isEmpty()) {
                        isNumbers = true;
                    } else {
                        String[] datas = line.split("-");
                        addAndMerge(Long.parseLong(datas[0]), Long.parseLong(datas[1]));
                    }
                } else {
                        numbers.add(Long.parseLong(line));
                }

            }
            finalizeIntervals();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    public void addAndMerge(long start, long end) {
        intervals.add(new Interval(start, end));
    }

    public void finalizeIntervals() {
        intervals.sort(Comparator.comparingLong(i -> i.start));

        List<Interval> merged = new ArrayList<>();
        Interval current = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);

            if (next.start <= current.end) {
                current = new Interval(
                        current.start,
                        Math.max(current.end, next.end)
                );
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);

        intervals.clear();
        intervals.addAll(merged);
    }

    public boolean contains(long value) {
        int left = 0;
        int right = intervals.size() - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            Interval interval = intervals.get(mid);

            if (value < interval.start) {
                right = mid - 1;
            } else if (value > interval.end) {
                left = mid + 1;
            } else {
                return true;  // benne van az intervallumban
            }
        }
        return false;
    }

    public long partOne() {
        int counter = 0;
        for (long number: numbers) {
            if (contains(number)) {
                counter += 1;
            }
        }
        return counter;
    }
    public long partTwo() {
        long counter = 0L;
        for (Interval interval: intervals) {
            counter += (interval.end- interval.start + 1);
        }
        return counter;
    }
}
