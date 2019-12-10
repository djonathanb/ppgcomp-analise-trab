package comp;

import algs4.Counter;
import algs4.SortUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Executor {

    public static void execute(boolean logResults,
                               ResourceRepository loader,
                               String label,
                               String filename,
                               BiConsumer<Integer[], Counter> method) throws IOException {

        List<Path> files = loader.listFiles().collect(Collectors.toList());

        Map<Integer, ExecutionStats> measures = new TreeMap<>();

        for (Path file : files) {
            Integer[] data = loader.loadFile(file);
            Counter counter = new Counter();
            long start = System.nanoTime();
            method.accept(data, counter);
            double elapsed = (System.nanoTime() - start) / 1000.0 / 1000.0;
            measures.put(data.length, new ExecutionStats(elapsed, counter.getCount()));

            SortUtils.verify(data);
            if (logResults) {
                System.out.println("# " + data.length);
                System.out.println(elapsed + " ms");
                System.out.println(counter.getCount() + " comp.");
                if (data.length == 100000) {
                    System.out.println(Arrays.toString(data));
                }
            }
        }

        if (logResults) {
            ReportUtils.saveResults(label, filename, measures);
        }
    }

}
