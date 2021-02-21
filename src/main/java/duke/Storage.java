package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.parser.CommandParser;
import duke.parser.FileDataParser;
import duke.task.Task;

/**
 * Represents the storage functionalities used by
 * the Duke chat bot. It manages the loading and saving
 * of tasks in and out of the disk.
 */
public class Storage {

    protected Path filePath;

    /**
     * Initialises the class with a file path
     * where the file will be used for data storage
     * operations.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(System.getProperty("user.dir"), filePath);
    }

    /**
     * Loads task list data found in the disk
     * into the Duke chat bot.
     *
     * @return Task list.
     * @throws DukeException If the file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            List<String> lines;
            lines = Files.readAllLines(this.filePath);

            ArrayList<Task> taskList = FileDataParser.getTaskListData(lines);
            return taskList;
        } catch (IOException e) {
            throw new DukeException(Ui.getLoadTaskListFailure());
        }
    }

    public void save(TaskList taskList) throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task task : taskList.getList()) {
                sb.append(task);
                sb.append('\n');
            }
            Files.write(this.filePath, sb.toString().getBytes());
        } catch (IOException e) {
            throw new DukeException(Ui.getSaveTaskListFailure());
        }
    }
}
