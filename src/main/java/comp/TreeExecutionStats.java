package comp;

public class TreeExecutionStats {

    private double buildTime;
    private long buildComparisons;
    private double queryTime;
    private long queryComparisons;

    public TreeExecutionStats(double buildTime, long buildComparisons, double queryTime, long queryComparisons) {
        this.buildTime = buildTime;
        this.buildComparisons = buildComparisons;
        this.queryTime = queryTime;
        this.queryComparisons = queryComparisons;
    }

    public double getBuildTime() {
        return buildTime;
    }

    public long getBuildComparisons() {
        return buildComparisons;
    }

    public double getQueryTime() {
        return queryTime;
    }

    public long getQueryComparisons() {
        return queryComparisons;
    }

}
