import java.io.File;
import java.io.IOException;

public class Storage {
    public static TaskList runFile(File file) throws IOException {
        return FileReading.loadTask(file);
    }

    public static void saveFile(File file, Duke duke) throws IOException {
        FileWriting.writeToFile(file, duke);
    }
}
