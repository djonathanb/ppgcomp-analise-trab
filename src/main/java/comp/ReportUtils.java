package comp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class ReportUtils {

    public static void saveResults(String label, String filename, Map<Integer, ExecutionStats> measures) throws IOException {
        StringBuilder labels = new StringBuilder();
        labels.append(";");
        measures.entrySet().forEach(measure -> {
            labels.append(measure.getKey()).append(";");
        });

        StringBuilder times = new StringBuilder(label + ";");
        StringBuilder comparisons = new StringBuilder(label + ";");
        measures.entrySet().forEach(measure -> {
            times.append(measure.getValue().getTime()).append(";");
            comparisons.append(measure.getValue().getComparisons()).append(";");
        });

        saveAnalisis(filename + "-times", labels, times);
        saveAnalisis(filename + "-comparisons", labels, comparisons);
    }

    public static void saveTreeResults(String label, String filename, Map<Integer, TreeExecutionStats> measures) throws IOException {
        StringBuilder labels = new StringBuilder();
        labels.append(";");
        measures.entrySet().forEach(measure -> {
            labels.append(measure.getKey()).append(";");
        });

        StringBuilder buildTimes = new StringBuilder(label + "-build;");
        StringBuilder buildComparisons = new StringBuilder(label + "-build;");
        StringBuilder queryTimes = new StringBuilder(label + "-query;");
        StringBuilder queryComparisons = new StringBuilder(label + "-query;");
        measures.entrySet().forEach(measure -> {
            buildTimes.append(measure.getValue().getBuildTime()).append(";");
            buildComparisons.append(measure.getValue().getBuildComparisons()).append(";");
            queryTimes.append(measure.getValue().getQueryTime()).append(";");
            queryComparisons.append(measure.getValue().getQueryComparisons()).append(";");
        });

        saveAnalisis(filename + "-buildTimes", labels, buildTimes);
        saveAnalisis(filename + "-buildComparisons", labels, buildComparisons);
        saveAnalisis(filename + "-queryTimes", labels, queryTimes);
        saveAnalisis(filename + "-queryComparisons", labels, queryComparisons);
    }

    private static void saveAnalisis(String filename, StringBuilder labels, StringBuilder measures) throws IOException {
        File file = new File("report/" + filename + ".csv");
        if (!file.getParentFile().exists()) {
            Files.createDirectories(file.getParentFile().toPath());
        }

        FileOutputStream out = new FileOutputStream(file);
        out.write(labels.toString().getBytes());
        out.write("\n".getBytes());
        out.write(measures.toString().getBytes());
    }

}
