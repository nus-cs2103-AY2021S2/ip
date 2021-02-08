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
        Paths.get(rootProject,  "data", "duke.txt");
    private static Path dataFolderPath =
        Paths.get(rootProject, "data");

    /**
     * Initializes the storage class
     * @throws DukeException when an error happens when loading data from storage
     */
    public Storage() throws DukeException {
        init();
    }

    private static void init() throws DukeException {
        storageExistOrCreate();
        taskList = loadTaskListFromStorage();
    }

    private static TaskList loadTaskListFromStorage() throws DukeException {
        ArrayList<Task> tasks = loadDataFromStorage(dataFilePath);
        return new TaskList(tasks);
    }

    /**
     * Saves the current task list into storage, used by the public.
     * @throws DukeException when an error occurs while saving data, eg. no access rights
     */
    public static void saveDataToStorage() throws DukeException {
        ArrayList<Task> tasks = taskList.getAllTasks();
        saveDataToStorage(dataFilePath, tasks);
    }

    private static void saveDataToStorage(Path filePath, ArrayList<Task> tasks) throws DukeException {
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

    private static ArrayList<Task> loadDataFromStorage(Path filePath) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String taskInfoInString;
            while ((taskInfoInString = br.readLine()) != null) {
                Task task = stringToTask(taskInfoInString);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("error when loading Data. Closing..."
                + System.lineSeparator()
                + "      " + e);
        }
        return tasks;
    }

    private static Task stringToTask(String taskInfo) throws DukeException {
        String[] taskInfoArr = taskInfo.split("\\|");
        String type = taskInfoArr[0].strip();
        boolean isDone = taskInfoArr[1].strip().equals("1");
        String description = taskInfoArr[2].strip();

        Task task = null;
        switch(type) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "E":
            String at = taskInfoArr[3].strip();
            task = new Event(description, isDone, at);
            break;
        case "D":
            String by = taskInfoArr[3].strip();
            task = new Deadline(description, isDone, by);
            break;
        default:
            throw new DukeException("Invalid task info found in storage.");
        }
        return task;
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
