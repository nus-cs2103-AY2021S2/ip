package duke.storage;

import duke.duke.Duke;
import duke.tasks.TaskList;

import java.io.File;
import java.io.IOException;

/**
 * Used to read and write to a file.
 */
public class Storage {

    /**
     * Retrieves and loads list of task stored into task list.
     * @param file the file that task information retrieved from
     * @return task retrieved task list
     * @throws IOException
     */
    public static TaskList runFile(File file) throws IOException {
        return FileReading.loadTask(file);
    }

    /**
     * Saves list of tasks by writing into the file whenever a command updates the task list.
     * @param file the file that is written to
     * @param duke a Duke object that manages task list operations
     * @throws IOException if error occurs while writing to the file
     */
    public static void saveFile(File file, Duke duke) throws IOException {
        FileWriting.writeToFile(file, duke);
    }
}
