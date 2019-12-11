package comp.quick;

import algs4.QuickLow;
import comp.Executor;
import comp.ResourceRepository;

import java.io.IOException;
import java.net.URISyntaxException;

public class QuickLowDecrescenteTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // Warmup JIT
        run(false);

        // Real benchmark
        run(true);
    }

    private static void run(boolean logResults) throws URISyntaxException, IOException {
        ResourceRepository loader = new ResourceRepository("../quick/Decrescentes");
        Executor.execute(logResults, loader, "low", "Quick-Low-Decrescente", QuickLow::sort);
    }

}
