package tlylt.haha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility for storing tasks to file.
 */
public class Storage {
    private Path file;

    /**
     * Initialises by checking and creating storage file if needed.
     */
    public Storage() {
        try {
            String currentDir = System.getProperty("user.dir");
            Path folder = Paths.get(currentDir, "Haha_data");
            this.file = Paths.get(currentDir, "Haha_data", "database.txt");
            if (Files.notExists(folder)) {
                Files.createDirectories(folder);
            }
            if (Files.notExists(file)) {
                Files.createFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads storage file content into list for further parsing.
     *
     * @return List of String containing task details from file.
     */
    public List<String> getTasks() {
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
