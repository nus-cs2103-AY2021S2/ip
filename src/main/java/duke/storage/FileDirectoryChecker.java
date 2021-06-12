package duke.storage;

import java.io.File;
import java.io.IOException;

/**
 * Checks and prepares the directory for Duke
 */
public class FileDirectoryChecker {

    /**
     * Checks if the directory exists and creates a new directory and file if they do not exist
     * @param dir the file path of the internal storage
     */
    public static void prepareFile(String dir) {
        File directory = new File(dir);
        File pathDir = directory.getParentFile();
        checkPath(pathDir);
        try {
            checkFile(directory);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkPath(File dir) {
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private static void checkFile(File dir) throws IOException {
        dir.createNewFile();
    }
}
