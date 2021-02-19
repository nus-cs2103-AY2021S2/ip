package duke.storage;

import static duke.commons.core.Messages.MESSAGE_COMMAND_NOT_FOUND;
import static duke.commons.core.Messages.MESSAGE_FILE_NOT_FOUND;
import static duke.commons.core.Messages.MESSAGE_SAVE_FILE_ERROR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.commons.exceptions.DukeException;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.Task;
import duke.model.task.TaskList;
import duke.model.task.ToDo;

/**
 * Stores and restores the user's task list.
 */
public class Storage {
    private static final String DELIMITER = " \\| ";
    private static final char ITEM_EVENT = 'E';
    private static final char ITEM_DEADLINE = 'D';
    private static final char ITEM_TODO = 'T';
    private final File file;

    /**
     * Initialize the storage file from the given directory and creates the folder if it does not exists.
     *
     * @param filePath directory of the storage file
     */
    public Storage(String filePath) {
        file = new File(filePath);
        File folder = file.getParentFile();
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Loads the {@code Task} data from this storage file, and then returns it.
     * Returns empty task list if the file does not exist, or is not a regular file.
     *
     * @return a populated task list
     * @throws DukeException when it tries to open file that does not exists
     */
    public List<Task> load() throws DukeException {
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException(MESSAGE_FILE_NOT_FOUND);
        }

        return populateTasksFromStorage(sc);
    }

    /**
     * Process the data stored in the text file and returns a populated task list.
     *
     * @param sc Scanner object
     * @return a populated task list
     * @throws DukeException when an invalid task type is in the data file
     */
    private List<Task> populateTasksFromStorage(Scanner sc) throws DukeException {
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String taskStorageString = sc.nextLine();
            String[] taskString = taskStorageString.split(DELIMITER);

            char type = taskString[0].charAt(0);
            int done = Integer.parseInt(taskString[1]);
            String desc = taskString[2];

            switch (type) {
            case ITEM_TODO:
                tasks.add(new ToDo(done, desc));
                break;
            case ITEM_EVENT:
                tasks.add(new Event(done, desc, taskString[3]));
                break;
            case ITEM_DEADLINE:
                tasks.add(new Deadline(done, desc, taskString[3]));
                break;
            default:
                throw new DukeException(MESSAGE_COMMAND_NOT_FOUND);
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Saves the {@code Task} data that is stored in the {@code TaskList} to the storage file.
     *
     * @param taskList {@code TaskList} to be saved
     * @throws DukeException if there were errors encountered when trying to write data to the file
     */
    public void saveFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            File folder = file.getParentFile();
            assert folder.exists() : "folder should exist";
            fileWriter.write(taskList.toStorageString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(MESSAGE_SAVE_FILE_ERROR);
        }
    }
}
