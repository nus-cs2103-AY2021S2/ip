package bearbear.storage;

import java.io.File;
import java.io.IOException;

import bearbear.bearbear.BearBear;
import bearbear.tasks.TaskList;

/**
 * Used to read and write to a file.
 */
public class Storage {

    /**
     * Retrieves and loads list of task stored into task list.
     * @param file The file that task information retrieved from.
     * @return Task retrieved task list
     * @throws IOException If error occurs while writing or retrieving tasks from file.
     */
    public static TaskList runFile(File file) throws IOException {
        return FileReading.loadTask(file);
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
