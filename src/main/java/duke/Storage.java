package duke;

import duke.command.LoadCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class which handles the saving and laoding of tasklist to and from a text file
 */
public class Storage {

    public Storage() {

    }

    /**
     * Saves the existing tasks in the tasklist to a textfile
     *
     * @throws IOException when the filename does not exist
     */
    public static String saveTaskList() throws IOException {
        try {
            File file = new File("src/main/java/duke/duke.txt");
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < Parser.getTaskList().size(); ++i) {
                fw.write(Parser.getTaskList().get(i).toString() + "\n");
            }
            fw.close();
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
        return ("Your tasks are saved");
    }

    /**
     * Loads the tasks from the text file back into the task list array
     *
     * @throws FileNotFoundException if the specified file does not exist
     */
    public static String loadTaskList() throws FileNotFoundException {
        try {
            LoadCommand.runCommand();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ("Your tasks are loaded");
    }
}
