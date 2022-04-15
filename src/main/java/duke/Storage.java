package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.command.Parser;
import duke.task.Task;

/**
 * Encapsulates the storage domain that provides persistence to the Duke. This
 * class handles file and directory creation, file saving and loading.
 *
 * @author Aaron Saw Min Sern
 */
public class Storage {
    private final String filePath;

    /**
     * Sole constructor for class Storage.
     *
     * @param   filePath    the file path location this Storage instance is
     *                      directed to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a directory at most 1 level deep and a file in the location
     * specified in the file path of this Storage instance.
     */
    public void createDirectoryAndFile() {
        try {
            final int i = filePath.lastIndexOf("/");
            String dirPath = filePath.substring(0, i);

            Path dir = Paths.get(dirPath);
            Path path = Paths.get(filePath);
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            Files.createFile(path);
        } catch (IOException e) {
            System.err.println("Parent directory does not exist.");
            e.printStackTrace();
        }
    }

    /**
     * Saves a file containing the data in the file path location of this
     * Storage instance.
     *
     * @param   data    a list of String
     */
    public void saveFile(List<String> data) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            createDirectoryAndFile();
        }
        try {
            Files.write(path, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Duke cannot save file.");
            e.printStackTrace();
        }
    }

    /**
     * Returns a TaskList object by loading and processing the file in file path
     * location of this Storage instance. This method will return null if the
     * file specified in the path location does not exist.
     *
     * @return          a TaskList instance decoded by the Parser class
     * @see             duke.command.Parser
     */
    public TaskList loadFile() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return null;
        }
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<String> tasks = new ArrayList<>();
            List<String> stats = new ArrayList<>();

            int i = 0;
            for (; i < lines.size(); i++) {
                if (lines.get(i).equals("%")) {
                    break;
                }
                tasks.add(lines.get(i));
            }
            for (i++; i < lines.size(); i++) {
                stats.add(lines.get(i));
            }


            List<Task> t = tasks.stream()
                    .map(str -> Parser.parseAsTask(str))
                    .filter(task -> task != null)
                    .collect(Collectors.toList());
            Statistics s = Parser.parseAsStats(stats);
            assert(!t.contains(null));
            return new TaskList(t, s);
        } catch (IOException e) {
            System.err.println("Duke cannot read the file.");
            e.printStackTrace();
            return null;
        }
    }

}
