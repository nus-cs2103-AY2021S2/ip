package duke.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exception.DukeException;
import duke.task.TaskList;


/**
 * The Storage class manages loading tasks from a file
 * when a new instance of Duke is created and saving tasks
 * to a file when a running instance of Duke is closed.
 *
 * Credits to AB Level 2:
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
 */
public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "data/tasks.txt";
    public final Path path;

    /**
     * Constructor. Uses the default storage filepath.
     * @throws DukeException Error if invalid file path.
     */
    public Storage() throws DukeException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Constructor takes in the path of the file.
     * @param filePath The path of the file.
     */
    public Storage(String filePath) throws DukeException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new DukeException("Storage file should end with '.txt'");
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the task list to storage.
     * @param tasks The task list.
     * @throws DukeException Error if cannot write to file.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            List<String> encodedTasks = TaskListEncoder.encodeTaskList(tasks);
            Files.write(path, encodedTasks);
        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the task list from storage.
     * @return The task list.
     * @throws DukeException Error if cannot read from file.
     */
    public TaskList load() throws DukeException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }

        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }
}
