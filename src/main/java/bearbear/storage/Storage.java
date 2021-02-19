package bearbear.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import bearbear.bearbear.BearBear;
import bearbear.exceptions.StorageException;
import bearbear.tasks.TaskList;

/**
 * Used to read and write to a file.
 */
public class Storage {
    private static final Path path = Path.of("src/main/data");

    /**
     * Retrieves and loads list of task stored into task list.
     * @param file The file that task information retrieved from.
     * @return Task retrieved task list
     * throws StorageException If error occurs while reading or writing to file.
     */

    public static TaskList runFile(File file) throws StorageException {
        TaskList previous = null;
        try {
            Files.createDirectories(path);

            if (!(file.createNewFile())) {
                previous = FileReading.loadTask(file);
            }
        } catch (IOException e) {
            throw new StorageException("Error writing to file");
        }
        return previous;
    }

    /**
     * Saves list of tasks by writing into the file whenever a command updates the task list.
     * @param file the file that is written to
     * @param bearBear a Duke object that manages task list operations
     * @throws IOException if error occurs while writing to the file
     */
    public static void saveFile(File file, BearBear bearBear) throws IOException {
        FileWriting.writeToFile(file, bearBear);
    }

}
