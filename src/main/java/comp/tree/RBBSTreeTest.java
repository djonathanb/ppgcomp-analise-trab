package comp.tree;

import algs4.Counter;
import algs4.RedBlackBST;
import comp.ReportUtils;
import comp.ResourceRepository;
import comp.TreeExecutionStats;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RBBSTreeTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // Warmup JIT
        run(false);

        // Real benchmark
        run(true);
    }

    private static void run(boolean logResults) throws URISyntaxException, IOException {
        ResourceRepository constructorLoader = new ResourceRepository("../tree/Construir/Conjunto 6");
        ResourceRepository queryLoader = new ResourceRepository("../tree/Consultar/Conjunto 6");

        List<Path> files = constructorLoader.listFiles().collect(Collectors.toList());

        Map<Integer, TreeExecutionStats> measures = new TreeMap<>();

        for (Path file : files) {
            Integer[] data = constructorLoader.loadTreeFile(file);
            System.out.println("# " + data.length);
            Counter buildCounter = new Counter();
            long start = System.nanoTime();
            RedBlackBST<Integer, Integer> integerBST = RedBlackBST.create(data, buildCounter);
            double elapsedBuildTime = (System.nanoTime() - start) / 1000.0 / 1000.0;

            if (logResults) {
                System.out.println("# " + data.length);
                System.out.println(elapsedBuildTime + " ms");
                System.out.println(buildCounter.getCount() + " comp.");
                if (data.length == 100000) {
                    System.out.println(Arrays.toString(data));
                }
            }

            Integer[] queryData = queryLoader.loadTreeFile(queryLoader.getPath(file.getFileName()));
            Counter queryCounter = new Counter();
            start = System.nanoTime();
            RedBlackBST.query(integerBST, queryData, queryCounter);
            double elapsedQueryTime = (System.nanoTime() - start) / 1000.0 / 1000.0;

            if (logResults) {
                System.out.println(elapsedQueryTime + " ms");
                System.out.println(queryCounter.getCount() + " comp.");
                if (data.length == 100000) {
                    System.out.println(Arrays.toString(data));
                }
            }

            measures.put(data.length, new TreeExecutionStats(
                    elapsedBuildTime, buildCounter.getCount(), elapsedQueryTime, queryCounter.getCount()));

        }

        if (logResults) {
            ReportUtils.saveTreeResults("RedBlack", "RedBlackBST", measures);
        }
    }

}
