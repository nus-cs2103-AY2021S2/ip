package duke.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;


/**
 * Stores the task list and implements functions for saving and loading.
 */
public class Storage {
    private static TaskList taskList;
    private static String rootProject = System.getProperty("user.dir");
    private static Path dataFilePath =
        Paths.get(rootProject, "src", "main", "java", "duke", "data", "duke.txt");
    private static Path dataFolderPath =
        Paths.get(rootProject, "src", "main", "java", "duke", "data");

    /**
     * Initializes the storage class
     * @throws DukeException when an error happens when loading data from storage
     */
    public Storage() throws DukeException {
        init();
    }

    private static void init() throws DukeException {
        storageExistOrCreate();
        taskList = new TaskList(loadData(dataFilePath));
    }

    /**
     * Saves the current task list into storage, used by the public.
     * @throws DukeException when an error occurs while saving data, eg. no access rights
     */
    public static void saveData() throws DukeException {
        saveData(dataFilePath, TaskList.getAllTasks());
    }

    private static void saveData(Path filePath, ArrayList<Task> tasks) throws DukeException {
        try {
            ArrayList<String> tasksInfoToStore = new ArrayList<>();
            for (Task task : tasks) {
                tasksInfoToStore.add(task.infoToStore());
            }
            Files.write(filePath, tasksInfoToStore);
        } catch (IOException e) {
            throw new DukeException("error in saving data."
                    + System.lineSeparator()
                    + "      " + e);
        }
    }

    private static ArrayList<Task> loadData(Path filePath) throws DukeException {
        ArrayList<Task> res = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String line;
            while ((line = br.readLine()) != null) {
                Task task = stringToTask(line);
                if (task != null) {
                    res.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("error when loading Data. Closing..."
                    + System.lineSeparator()
                    + "      " + e);
        }
        return res;
    }

    private static Task stringToTask(String taskInfo) throws DukeException {
        String[] taskInfoArr = taskInfo.split("\\|");
        String type = taskInfoArr[0].strip();
        boolean isDone = taskInfoArr[1].strip().equals("1");
        String description = taskInfoArr[2].strip();
        Task res = null;
        switch(type) {
        case "T":
            res = new Todo(description, isDone);
            break;
        case "E":
            String at = taskInfoArr[3].strip();
            res = new Event(description, isDone, at);
            break;
        case "D":
            String by = taskInfoArr[3].strip();
            res = new Deadline(description, isDone, by);
            break;
        default:
            throw new DukeException("Invalid Taskinfo found in storage.");
        }
        return res;
    }

    private static void storageExistOrCreate() throws DukeException {
        try {
            if (Files.notExists(dataFilePath)) {
                if (Files.notExists(dataFolderPath)) {
                    dataFolderPath = Files.createDirectories(dataFolderPath);
                }
                dataFilePath = Files.createFile(dataFilePath);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to access data stored, access rights issue. Closing..."
                    + System.lineSeparator()
                    + "      " + e);
        }
    }
}
