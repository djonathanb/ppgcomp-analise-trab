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
