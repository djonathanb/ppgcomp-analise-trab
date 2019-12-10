package comp;

public class ExecutionStats {

    private double time;
    private long comparisons;

    public ExecutionStats(double time, long comparisons) {
        this.time = time;
        this.comparisons = comparisons;
    }

    public double getTime() {
        return time;
    }

    public long getComparisons() {
        return comparisons;
    }

}
