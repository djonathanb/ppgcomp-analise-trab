package comp;

import algs4.SortUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Executor {

    public static void execute(boolean logResults,
                               ResourceRepository loader,
                               String label,
                               String filename,
                               Function<Integer[], Long> method) throws IOException {

        List<Path> files = loader.listFiles().collect(Collectors.toList());

        Map<Integer, ExecutionStats> measures = new TreeMap<>();

        for (Path file : files) {
            Integer[] data = loader.loadFile(file);
            long start = System.nanoTime();
            long comparisons = method.apply(data);
            double elapsed = (System.nanoTime() - start) / 1000.0 / 1000.0;
            measures.put(data.length, new ExecutionStats(elapsed, comparisons));
            SortUtils.verify(data);
//            System.out.println("#" + data.length + ": " + Arrays.toString(data));
        }

        if (logResults) {
            measures.entrySet().forEach(measure -> {
                System.out.println("# " + measure.getKey());
                System.out.println(measure.getValue().getTime() + " ms");
                System.out.println(measure.getValue().getComparisons() + " comp.");
            });

            ReportUtils.saveResults(label, filename, measures);
        }
    }

}
