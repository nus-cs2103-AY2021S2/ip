package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Stores and restores the user's task list.
 */
public class Storage {
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
            throw new DukeException("File not found.");
        }

        List<Task> tasks = populateTaskListFromStorage(sc);
        return tasks;
    }

    private List<Task> populateTaskListFromStorage(Scanner sc) throws DukeException {
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String item = sc.nextLine();
            String[] items = item.split(" \\| ");

            char type = items[0].charAt(0);
            int done = Integer.parseInt(items[1]);
            String desc = items[2];

            switch (type) {
            case 'T':
                tasks.add(new ToDo(done, desc));
                break;
            case 'E':
                tasks.add(new Event(done, desc, items[3]));
                break;
            case 'D':
                tasks.add(new Deadline(done, desc, items[3]));
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
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
            fileWriter.write(taskList.toStorageString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error saving file.");
        }
    }
}
