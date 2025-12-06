public class Interval {
    public final long start;
    public final long end;

    public Interval(long start, long end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
