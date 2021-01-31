import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Storage {
    private final Path path;
    private final File file;

    public Storage(String filePath) {
        path = Paths.get(filePath);
        file = new File(path.toString());
    }
}
