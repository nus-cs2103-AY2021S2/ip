import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

// should be called fileSetup, but loadfromharddisk method doesn't fit
// level 7 files should be in another folder/pkg
public class Save {
    public static final String projectDir = System.getProperty("user.dir");
    public static final java.nio.file.Path taskListFilePath = java.nio.file.Paths.get(
            projectDir, "src", "data", "tasks.txt"); // todo rename as tasklistF..P..

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

    public static boolean doesTaskFileExist() {
        return doesFileOrDirExist(taskListFilePath);
    }

    // testing method
    public static void setupTasksFile() throws IOException {
        if (doesTaskFileExist()) {
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



    // todo abstract everything away
    // duke should only call save() function after any successful call
    // save function checks if files exist, and then write the entire list to hard drive
        // not exactly sure if the files existing should happen only at the start of the programme?
        // e.g. public static void setupDataFiles();
        // before that, call loadFromHardDisk();

    public static boolean loadFromHardDisk(ArrayList<Task> taskList) throws IOException {
        // if no files in harddisk
            // return false and set up
        // if only empty files, return false

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
            }
            return true;
        } else {
            setupTasksFile();
            return false;
        }
    }


}
