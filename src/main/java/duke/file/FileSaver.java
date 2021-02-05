package duke.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * File saver to save task list to a local file.
 *
 * @author  Nicole Ang
 */
public class FileSaver {
    private FileWriter fw;

    /**
     * Constructs a new FileSaver object to save task list to a local file.
     */
    public FileSaver() {
    }

    /**
     * Saves task list to file path specified.
     * If file specified is not found, a new file is created at that location.
     *
     * @param filePath  Local path to store task list to.
     * @param list  Task list.
     * @throws IOException  If file specified is not found.
     */
    public void saveToFile(String filePath, ArrayList<Task> list) throws IOException {
        File fileCreator = new File(filePath);

        if (!fileCreator.createNewFile()) {
            fileCreator.delete();
            fileCreator.createNewFile();
            fw = new FileWriter(filePath);

            for (Task t : list) {
                fw.write(t.toString() + "\n");
            }

            fw.close();
        }
    }

}
