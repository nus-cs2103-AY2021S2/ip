package popo.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import popo.tasks.Task;
import popo.tasks.TaskList;

/**
 * Used to read and write to a file.
 */
public class Storage {
    private static final String DEFAULT_FILEPATH = "./data/popo.txt";

    private static final String MESSAGE_INVALID_FILEPATH = "The file path of a storage file should end with '.txt'";
    private static final String MESSAGE_FAILED_INITIALIZATION = "Failed to initialize storage." + "\n"
            + "This error could be due to an invalid file path." + "\n"
            + "Exiting...";
    private static final String MESSAGE_ERROR_WRITING_TO_FILE = "Error writing data to file...";

    private final Path path;

    /**
     * Creates a {@code Storage} object with the default file path.
     *
     * @throws InvalidStorageFilePathException If the default file path is invalid.
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Creates a {@code Storage} object with the given file path.
     *
     * @param filePath File path to read or write to.
     * @throws InvalidStorageFilePathException If the file path is invalid.
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        if (!StorageUtil.isValidFilePath(filePath)) {
            throw new InvalidStorageFilePathException(MESSAGE_INVALID_FILEPATH);
        }
        try {
            path = Path.of(filePath);
        } catch (InvalidPathException ex) {
            throw new InvalidStorageFilePathException(MESSAGE_FAILED_INITIALIZATION);
        }
    }

    /**
     * Loads the list of tasks found in the file and parses the tasks into an operational format.
     *
     * @return {@code TaskList} that represents the current list of tasks in the file.
     * @throws IOException      If an error occurs while reading from the file.
     * @throws StorageException If an error occurs while parsing the data from the file.
     */
    public TaskList loadTasks() throws IOException, StorageException {
        TaskList taskList = new TaskList();
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return taskList;
        }
        List<String> taskStrings = Files.readAllLines(path);
        for (String taskString : taskStrings) {
            Task task = StorageUtil.convertStringToTask(taskString);
            taskList.addTask(task);
        }
        return taskList;
    }

    /**
     * Saves the list of tasks by writing into the file if the list if updated by the previous command.
     *
     * @param taskList Updated task list.
     * @throws StorageException If an error occurs while writing to the file.
     */
    public void saveTasksIfPresent(TaskList taskList) throws StorageException {
        if (taskList == null) {
            return;
        }
        try {
            // Create directories in the file path that do not exist yet
            Path pathToParentDirectory = path.getParent();
            if (pathToParentDirectory != null) {
                Files.createDirectories(pathToParentDirectory);
            }

            List<String> taskStrings = StorageUtil.convertAllTasksToString(taskList);
            assert taskStrings != null;
            Files.write(path, taskStrings);
        } catch (IOException ex) {
            throw new StorageException(MESSAGE_ERROR_WRITING_TO_FILE + path);
        }
    }
}
