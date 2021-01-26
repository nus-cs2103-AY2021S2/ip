package core;

import core.task.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Interface having methods to facilitate storing of tasks.
 */
public abstract class TaskStorage {
    /**
     * Saves the task list.
     * @param tm - the {@code TaskManager} to save.
     * @param fileLocation - the file location to save to.
     * @throws IOException - thrown if any IOException is encountered
     */
    public abstract void saveTaskList(ArrayList<Task> tm, Path fileLocation) throws IOException;

    /**
     * Retrieves the task list from the given {@code Path} location.
     * @param fileLocation - file location to load from
     * @return - the task list in the form of an {@code ArrayList<Task>}
     * @throws IOException - thrown if any IOException is encountered
     */
    public abstract ArrayList<Task> retrieveTaskList(Path fileLocation) throws IOException;
}
