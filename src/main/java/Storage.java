import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// should be called fileSetup, but loadfromharddisk method doesn't fit
// level 7 files should be in another folder/pkg

// todo
// storage objects should store info about where each list/anything should be saved
// have a final hashmap of where each object to store is stored
// todo seems like storage object might be associated with the duke object? not sure

/**
 * This class handles setting up the file to save Duke data to.
 */
public class Storage {
    public static final String projectDir = System.getProperty("user.dir");
    public static final java.nio.file.Path taskListFilePath = java.nio.file.Paths.get(
            projectDir, "src", "data", "tasks.txt"); // todo rename as tasklistF..P..


    /**
     * Checks if file or directory exists. Same mechanism for file or dir.
     * @param path Path to check for file or directory
     * @return True if it exists, false if it doesn't or any other error occured
     */
    public static boolean doesFileOrDirExist(Path path) {
        if (java.nio.file.Files.exists(path)) {
            return true;
        } else if (java.nio.file.Files.notExists(path)) {
            return false;
        } else {
            // todo throw no access exception here
            return false;
        }
    }

    /**
     * Checks if task file exists at hardcoded path
     * @return if task file exists
     */
    public static boolean doesTaskFileExist() {
        return doesFileOrDirExist(taskListFilePath);
    }

    /**
     * Sets up the tasks file in the hardcoded path, if the task file doesn't exist yet
     * @throws IOException
     */
    public static void setupTasksFile() throws IOException {
        if (doesTaskFileExist()) {
            // probably not gonna be used due to TaskList.java impl
            return;
        } else {
            java.nio.file.Path dataDirPath = java.nio.file.Paths.get(projectDir, "src", "data");
            boolean doesDataDirExist = doesFileOrDirExist(dataDirPath);

            // create directory if it doesn't exist
            if (!doesDataDirExist) {
                Path p = Paths.get(projectDir, "src", "data");
                boolean hasCreated = new File(p.toString()).mkdir();
                System.out.println("done 2 " + hasCreated);
            }

            // create file
            Path p = Paths.get(projectDir, "src", "data", "tasks.txt");
            boolean isCreated = new File(p.toString()).createNewFile();
        }
    }


    /**
     * Finds task file from hard coded path and loads them into a task file
     * @param taskList taskList to load any tasks from hard disk into
     * @return if any tasks have been found and loaded from hard disk
     * @throws IOException
     */
    public static boolean loadFromHardDisk(TaskList taskList) throws IOException {
        boolean isAnyTaskFound = false;

        if (doesTaskFileExist()) {
            // load it
            File f = new File(taskListFilePath.toString());
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                // much hardcoding to parse which class' unparse method to use
                // figure out how to use polymorphism?
                String line = sc.nextLine();
                String letter = line.substring(0, 1);
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
                    // todo
                    t = null;
                    break;
                }
                taskList.add(t);
                isAnyTaskFound = true;
            }
        } else {
            // probably not gonna be used due to TaskList.java impl
            setupTasksFile();
        }
        return isAnyTaskFound;
    }
}
