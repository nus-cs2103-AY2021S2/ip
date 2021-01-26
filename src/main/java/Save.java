import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// should be called fileSetup
// level 7 files should be in another folder/pkg
public class Save {
    public static Path taskListFilePath;
    public static final String projectDir = System.getProperty("user.dir");
    public static final java.nio.file.Path dataFilePath = java.nio.file.Paths.get(
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

    // testing method
    public static void setupTasksFile() throws IOException {


        boolean doesDataFileExist = doesFileOrDirExist(dataFilePath);

        if (doesDataFileExist) {
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

    public static boolean loadFromHardDisk() {
        // if no files in harddisk
            // return false and set up
        // if only empty files, return false

        return false;
    }


}
