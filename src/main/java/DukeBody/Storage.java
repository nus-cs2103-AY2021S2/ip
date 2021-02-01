package dukebody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import duketask.Task;

/**
 * represents a data storage accessor and mutator with
 * LogIn functionality to track the credentials of the
 * current user.
 */
public class Storage {
    // members
    private String dirpath;
    private String username;

    public Storage (String username) {
        this(Paths.get("").toAbsolutePath().toString() + File.separator + "src"
                + File.separator + "main" + File.separator + "data", username);
    }

    public Storage (String dirPath, String username) {
        this.dirpath = dirPath;
        this.username = username;
    }

    /**
     * read the list of tasks from a txt file based on the stored
     * directory path and username as
     *      directory_path\\username.txt
     * @return  TaskList object representing the list of tasks.
     */
    public TaskList readTasks () {
        TaskList tasks = new TaskList();

        try {
            File dataFile = new File(this.dirpath + File.separator
                    + this.username + ".txt");

            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNextLine()) {
                tasks.add(Parser.commandToTask(scanner.nextLine()));
            }

            scanner.close();
        } catch (IOException | Parser.UnrecognisedCommandException
                | Task.EmptyDescriptionException E) {
          // empty data
        }

        return tasks;
    }

    /**
     * Saves the user's tasks based on the stored username and
     * directory path as:
     *      directory_path\\username.txt
     * @param tasks     the list of tasks to save.
     * @throws IOException
     */

    public void saveTasks (TaskList tasks) throws IOException {

        File dataDirectory = new File(this.dirpath);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        FileWriter writer = new FileWriter(this.dirpath + File.separator
                + this.username + ".txt");

        for (Task task: tasks) {
            writer.write(Parser.taskToCommand(task) + "\n");
        }

        writer.close();
    }
}
