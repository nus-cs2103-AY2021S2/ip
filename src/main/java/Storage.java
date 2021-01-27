import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// should be called fileSetup, but loadfromharddisk method doesn't fit

/*
// todo
// storage objects should store info about where each list/anything should be saved
// have a final hashmap of where each object to store is stored
// todo seems like storage object might be associated with the duke object? not sure
*/

public class Storage {
    // fixme this is wrong when run by bat file or jar file
    public static final String projectDir = System.getProperty("user.dir");

    public static final java.nio.file.Path taskListFilePath = java.nio.file.Paths.get(
            projectDir, "src", "data", "tasks.txt");

    public static boolean doesFileOrDirectoryExist(Path path) {
        if (java.nio.file.Files.exists(path)) {
            return true;
        } else if (java.nio.file.Files.notExists(path)) {
            return false;
        } else {
            // todo throw no access exception here
            return false;
        }
    }

    public static boolean doesTaskFileExist() {
        return doesFileOrDirectoryExist(taskListFilePath);
    }

    // testing method
    public static void setupTasksFile() throws IOException {
        if (doesTaskFileExist()) {
            // probably not gonna be used due to TaskList.java impl
            return;
        } else {
            java.nio.file.Path dataDirPath = java.nio.file.Paths.get(projectDir, "src", "data");
            boolean doesDataDirExist = doesFileOrDirectoryExist(dataDirPath);

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


    // return whether any tasks have been loaded?
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
                    // todo create exceptions for parsing from hard disk
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
