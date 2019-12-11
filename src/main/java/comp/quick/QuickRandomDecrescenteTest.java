package comp.quick;

import algs4.QuickRandom;
import comp.Executor;
import comp.ResourceRepository;

import java.io.IOException;
import java.net.URISyntaxException;

public class QuickRandomDecrescenteTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // Warmup JIT
        run(false);

        // Real benchmark
        run(true);
    }

    private static void run(boolean logResults) throws URISyntaxException, IOException {
        ResourceRepository loader = new ResourceRepository("../quick/Decrescentes");
        Executor.execute(logResults, loader, "random", "Quick-Random-Decrescente", QuickRandom::sort);
    }

}
