package dukebody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duketask.Task;

/**
 * represents a database to query from and update to
 */
public class DataBase {
    public static class LegacyDataException extends Exception {
        public LegacyDataException (Exception e) {
            super("! Error in reading previously saved tasks due to unresolved differences in "
                    + "Legacy parse formats: " + e.getMessage());
        }
    }

    Path dirPath;

    public DataBase (String path) {
        dirPath = Paths.get(path);
    }

    // default constructor set child directory in current working directory as path.
    public DataBase () {
        this(Paths.get("").toAbsolutePath().toString() + File.separator
                + "userdata");
    }

    // accessors
    /**
     * returns a list of tasks from stored userdata at the directory
     * path associated with this DataBase object. The data is stored
     * as: directory_path\\username.txt
     * @return  TaskList object containing the list of tasks stored
     *          previously. TaskList is empty is it is a new user.
     */
    public TaskList queryTasks (String username) throws DataBase.LegacyDataException {
        TaskList tasks = new TaskList();

        try {
            File userdata = new File(dirPath.toAbsolutePath() + File.separator
                    + username + ".txt");

            Scanner scanner = new Scanner(userdata);
            while (scanner.hasNextLine()) {
                tasks.add(Parser.commandToTask(scanner.nextLine()));
            }

            scanner.close();
        } catch (IOException E) {
            // userdata not found (new user: no action)
        } catch (Duke.UnrecognisedCommandException
                | Task.EmptyDescriptionException e) {
            throw new LegacyDataException(e);
        }

        return tasks;
    }

    /**
     * Saves the task to the stored database location determined by the dirpath
     * member and the username.
     * @param username  the username which doubles as the file name.
     * @param tasks     the list of tasks to save.
     * @throws IOException
     */
    public void updateTasks (String username, TaskList tasks) throws IOException {
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        assert Files.exists(dirPath): "database directory not found on update.";
        FileWriter writer = new FileWriter(dirPath.toAbsolutePath()
                + File.separator + username + ".txt");

        for (Task task: tasks) {
            writer.write(Parser.taskToCommand(task) + "\n");
        }

        writer.close();
    }

    // mutators
    /**
     * Changes the directory path associated with the database connection.
     * @param path      the new path to store and read tasks from.
     * @throws java.nio.file.InvalidPathException
     */
    public void changePath (String path) throws java.nio.file.InvalidPathException {
        dirPath = Paths.get(path);
    }
}
