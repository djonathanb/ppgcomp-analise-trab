package comp.shell;

import algs4.Shell;
import comp.Executor;
import comp.ResourceRepository;

import java.io.IOException;
import java.net.URISyntaxException;

public class ShellParcialTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // Warmup JIT
        run(false);

        // Real benchmark
        run(true);
    }

    private static void run(boolean logResults) throws URISyntaxException, IOException {
        ResourceRepository loader = new ResourceRepository("../shell/ParcialmenteOrdenados");
        Executor.execute(logResults, loader, "shell", "Shell-Parcial", Shell::sort);
    }

}
