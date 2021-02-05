package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public static void createAndWrite(String path, TaskList listOfTasks) {
        try {
            File savedTasks = new File(path);
            savedTasks.createNewFile();
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < listOfTasks.numberOfTasks(); i++) {
                writer.write(listOfTasks.get(i).saveStatus());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
