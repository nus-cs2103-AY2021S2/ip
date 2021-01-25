package duke.file;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaver {
    FileWriter fw;

    public FileSaver() {
    }

    public void saveToFile(String filePath, ArrayList<Task> list) throws IOException {
        File fileCreator = new File(filePath);
//        System.out.println(fileCreator.getAbsolutePath());

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
