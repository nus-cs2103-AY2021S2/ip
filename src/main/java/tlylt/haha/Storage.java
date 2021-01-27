package tlylt.haha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path file;

    public Storage() {
        try {
            String currentDir = System.getProperty("user.dir");
            Path folder = Paths.get(currentDir, "Haha_data");
            Path file = Paths.get(currentDir, "Haha_data", "database.txt");
            if (Files.notExists(folder)) {
                Files.createDirectories(folder);
            }
            if (Files.notExists(file)) {
                Files.createFile(file);
            }
            this.file = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTasks() {
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
