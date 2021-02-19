import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// todo w5 / w6 storage parsing cases --> methods too long

/**
 * This class handles setting up the file to save Duke data to.
 */
public class Storage {
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final Path TASK_LIST_FILE_DIR = Paths.get(PROJECT_DIR,  "data");
    private static final Path TASK_LIST_FILE_PATH =
            Paths.get(TASK_LIST_FILE_DIR.toString(), "tasks.txt");


    /**
     * Checks if file or directory exists. Same mechanism for file or dir.
     * @param path Path to check for file or directory
     * @return True if it exists, false if it doesn't or any other error occured
     */
    private static boolean doesFileOrDirectoryExist(Path path) {
        if (java.nio.file.Files.exists(path)) {
            return true;
        } else if (java.nio.file.Files.notExists(path)) {
            return false;
        } else {
            // likely because user is not allowed access to path
            return false;
        }
    }

    /**
     * Checks if task file exists at hardcoded path
     * @return if task file exists
     */
    private static boolean doesTaskFileExist() {
        return doesFileOrDirectoryExist(TASK_LIST_FILE_PATH);
    }

    /**
     * Sets up the tasks file in the hardcoded path, if the task file doesn't exist yet
     * @throws IOException
     */
    private static void createTasksFile() throws IOException {
        assert !doesTaskFileExist() : "this shouldn't be called if task file already exists at stated path";

        boolean doesDataDirExist = doesFileOrDirectoryExist(TASK_LIST_FILE_DIR);

        // create directory if it doesn't exist
        if (!doesDataDirExist) {
            boolean hasCreated = new File(TASK_LIST_FILE_DIR.toString()).mkdir();
            System.out.println("done 2 " + hasCreated);
        }

        boolean isCreated = new File(TASK_LIST_FILE_PATH.toString()).createNewFile();

        assert isCreated : "task file still hasn't been created";
    }


    /**
     * Finds task file from hard coded path and loads them into a task file
     * @param taskList taskList to load any tasks from hard disk into
     * @return if any tasks have been found and loaded from hard disk
     * @throws IOException
     */
    private static boolean loadFromHardDisk(TaskList taskList) throws IOException {
        boolean isAnyTaskFound = false;

        if (doesTaskFileExist()) {

            File f = new File(TASK_LIST_FILE_PATH.toString());
            Scanner sc = new Scanner(f); // read from existing task file

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String letter = line.substring(0, 1);
                Task t = parseToTask(letter, line);

                taskList.add(t);
                isAnyTaskFound = true;
            }

        } else {
            createTasksFile();
        }

        return isAnyTaskFound;
    }

    private static Task parseToTask(String letter, String line) {
        Task t;

        switch (letter) {
        case "T":
            t = Todo.parse(line);
            break;
        case "E":
            t = Event.parse(line);
            break;
        case "D":
            t = Deadline.parse(line);
            break;
        default:
            t = null;
            break;
        }

        return t;
    }

    // todo setup at default location
    // how to recover from the IOException
    public static TaskList setupTaskList() throws IOException {
        if (Storage.doesTaskFileExist()) {
            TaskList t = new TaskList();
            Storage.loadFromHardDisk(t);
            return t;
        } else {
            createTasksFile();
            return new TaskList();
        }
    }

    /**
     * Saves the entire task list to hard drive (default location).
     * @throws IOException
     */
    public static void saveTasksList(TaskList taskList) throws IOException {
        File f = new File(Storage.TASK_LIST_FILE_PATH.toString());

        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < taskList.size(); i++) {
            bw.write(taskList.get(i).unparse());
        }

        bw.flush();
        bw.close();
    }

}
