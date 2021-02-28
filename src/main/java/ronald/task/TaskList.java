package ronald.task;

import java.util.ArrayList;

import ronald.RonaldException;

/**
 * Class containing methods to edit the program's existing task list.
 */
public class TaskList {

    /**
     * Adds a task to an ArrayList of tasks.
     *
     * @param task  task to be added.
     * @param tasks ArrayList to be added to.
     * @throws RonaldException if ArrayList is not initialised.
     */
    public static void addTask(Task task, ArrayList<Task> tasks) throws RonaldException {
        try {
            tasks.add(task);
        } catch (Exception e) {
            throw new RonaldException(e.getMessage());
        }
    }

    /**
     * Deletes a task from an ArrayList of tasks.
     *
     * @param task  task to be deleted.
     * @param tasks ArrayList to delete the task from.
     * @return Task that was deleted.
     * @throws RonaldException if ArrayList is not initialised or taskId is invalid.
     */
    public static boolean deleteTask(Task task, ArrayList<Task> tasks) throws RonaldException {
        try {
            return tasks.remove(task);
        } catch (Exception e) {
            throw new RonaldException(e.getMessage());
        }
    }
}
