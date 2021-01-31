package duke.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

public class FileSaver {
    private FileWriter fw;

    public FileSaver() {
    }

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
