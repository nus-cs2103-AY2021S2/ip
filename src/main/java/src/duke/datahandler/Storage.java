package duke.datahandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class is responsible for storing the users list of task that they
 * have created. A text file is created to store all the statuses of the task
 * that the user has inputted.
 */
public class Storage {
    /**
     * Creates and writes all the task statues in listOfTasks into the path
     * sepcified
     *
     * @param path        path where the text file would be created and written to
     * @param listOfTasks the list of tasks that would be written to the text file
     */
    public static void createAndWrite(String path, TaskList listOfTasks) {
        try {
            File savedTasks = new File(System.getProperty("user.dir"), path);
            savedTasks.createNewFile();
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < listOfTasks.numberOfTasks(); i++) {
                writer.write(listOfTasks.get(i).currentStatus());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
