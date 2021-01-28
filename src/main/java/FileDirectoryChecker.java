import java.io.File;
import java.io.IOException;

public class FileDirectoryChecker {

    // check if file directory exist, creates a new folder if folder does not exist, creates a new file if file
    // does not exist
    public static void doesFileExist(String dir) {
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
