package comp;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ResourceRepository {

    private final Path repoPath;

    public ResourceRepository(String resourcePackage) throws URISyntaxException {
        URL url = ResourceRepository.class.getResource(resourcePackage);
        repoPath = Path.of(url.toURI());
    }

    public Stream<Path> listFiles() throws IOException {
        return Files.list(repoPath);
    }

    public Integer[] loadFile(Path file) throws IOException {
        return Files.readAllLines(file).stream().map(Integer::valueOf).toArray(Integer[]::new);
    }

}
