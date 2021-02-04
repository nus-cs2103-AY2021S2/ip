package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.command.CommandType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class which contains methods to initialise and update a data text file in a pre-determined path.
 */
public class Storage {
    public static final String SPLITTER = " /&/ ";
    private static ArrayList<Task> tasks;
    private static String pathName;

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Initialises the path name of the data file.
     *
     * @param newPathName path where the data file should be created or should be accessed from.
     */
    public static void initialisePath(String newPathName) {
        pathName = newPathName;
    }

    /**
     * Processes the tasks stored in the data file and loads them to the program.
     */
    public static void initialiseList() {
        tasks = new ArrayList<>();
        File f = new File(pathName);
        try {
            boolean isEmptyFile = f.createNewFile();
            if (!isEmptyFile) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    Task task = convertStringToTask(sc.nextLine());
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns Task object after converting a String object in the data file.
     *
     * @param taskString formatted String containing all necessary information of a Task.
     * @return Task object that was represented by the taskString.
     * @throws DukeException if the formatting of Strings in the data file is incorrect.
     */
    private static Task convertStringToTask(String taskString) throws DukeException {
        String[] taskArgs = taskString.split(SPLITTER, 4);
        try {
            CommandType type = CommandType.valueOf(taskArgs[0]);
            boolean isDone = Integer.parseInt(taskArgs[1]) == 1;
            String desc = taskArgs[2];

            Task task = null;

            switch (type) {
            case TODO:
                task = new Todo(desc);
                break;
            case DEADLINE:
                task = new Deadline(desc, LocalDateTime.parse(taskArgs[3]));
                break;
            case EVENT:
                task = new Event(desc, LocalDateTime.parse(taskArgs[3]));
                break;
            default:
                throw new DukeException("Can't convert string from menu to order...");
            }
            if (isDone) {
                assert task != null;
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            throw new DukeException("There seems to be something wrong with the menu and we can't start up...");
        }
    }

    /**
     * Updates the data file with the tasks saved into the program. If data file has been shifted from its original
     * position, a new data file will be created with the existing task items.
     *
     * @throws DukeException if the data file cannot be written into.
     */
    public static void updateDataFile() throws DukeException {
        try {
            new File(pathName).createNewFile();
            FileWriter fw = new FileWriter(pathName);
            StringBuilder sb = new StringBuilder();
            for (Task task : tasks) {
                sb.append(task.getFormattedStorageString());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Oh no! I can't seem to update your menu...");
        }
    }
}
