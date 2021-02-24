package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Deals with loading tasks from a file and saving tasks to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage object with a file path.
     *
     * @param filePath File path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task commands from the file specified by filePath and stores them in an ArrayList.
     * If the file does not exist, a new file is created in that path.
     *
     * @return ArrayList of Strings representing the task commands.
     * @throws LoadTasksException If an error is encountered when trying to load tasks from the file.
     */
    public ArrayList<String> load() throws LoadTasksException {
        ArrayList<String> taskCommands = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String taskCommand = br.readLine();
                while (taskCommand != null) {
                    taskCommands.add(taskCommand);
                    taskCommand = br.readLine();
                }
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new LoadTasksException();
        }
        return taskCommands;
    }

    /**
     * Writes the task commands of current tasks in the TaskList to the file specified by filePath.
     *
     * @param tasks TaskList containing the current tasks.
     * @throws WriteTasksException If an error is encountered when trying to write tasks to the file.
     */
    public void writeTasksToDisk(TaskList tasks) throws WriteTasksException {
        try {
            ArrayList<String> writableTasks = tasks.getWritableTasks();
            FileWriter myWriter = new FileWriter(filePath);
            for (String writableTask : writableTasks) {
                myWriter.write(writableTask + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw new WriteTasksException();
        }
    }
}
