package duke.storage;

import duke.main.Deadline;
import duke.main.DukeException;
import duke.main.Event;
import duke.main.Task;
import duke.main.Todo;
import duke.tasklist.TaskList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static TaskList taskList;
    private static String rootProject = System.getProperty("user.dir");
    private static Path dataFilePath =
            Paths.get(rootProject,"src", "main", "java", "duke", "data", "duke.txt");
    private static Path dataFolderPath =
            Paths.get(rootProject,"src", "main", "java", "duke", "data");

    public Storage() throws DukeException {
        init();
    }

    private static void init() throws DukeException {
        storageExistOrCreate();
        taskList = new TaskList(loadData(dataFilePath));
    }

    public static void saveData() throws DukeException {
        saveData(dataFilePath, TaskList.getAllTasks());
    }

    private static void saveData(Path filePath, ArrayList<Task> tasks) throws DukeException {
        try {
            ArrayList<String> tasksInfoToStore = new ArrayList<>();
            for(Task task : tasks) {
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
            while((line = br.readLine()) != null) {
                Task task = stringToTask(line);
                if(task != null) {
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
        }
        return res;
    }

    private static void storageExistOrCreate()
            throws DukeException{
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
