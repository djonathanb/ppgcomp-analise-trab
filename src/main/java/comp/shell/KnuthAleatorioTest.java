package comp.shell;

import algs4.Knuth;
import comp.Executor;
import comp.ResourceRepository;

import java.io.IOException;
import java.net.URISyntaxException;

public class KnuthAleatorioTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // Warmup JIT
        run(false);

        // Real benchmark
        run(true);
    }

    private static void run(boolean logResults) throws URISyntaxException, IOException {
        ResourceRepository loader = new ResourceRepository("../shell/Aleatórios");
        Executor.execute(logResults, loader, "knuth", "Knuth-Aleatorios", Knuth::sort);
    }

}
