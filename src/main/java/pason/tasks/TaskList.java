package pason.tasks;

import java.io.IOException;
import java.util.List;

import pason.exceptions.PasonException;
import pason.storage.Storage;

/**
 * TaskList class for managing all tasks.
 */
public class TaskList {
    private static List<Task> tasks;
    private static Storage storage;

    /**
     * Initialises TaskList with Storage object.
     *
     * @param storage  Storage object used for saving to file.
     * @throws PasonException  If invalid input or formatting.
     */
    public TaskList(Storage storage) throws Exception {
        try {
            tasks = storage.loadTasks();
            this.storage = storage;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Adds task to TaskList and saves to storage.
     *
     * @param task  Task object to add.
     * @throws IOException  If storage file cannot be found.
     */
    public void addTask(Task task) throws Exception {
        try {
            tasks.add(task);
            storage.appendTask(task);
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Gets all tasks in TaskList
     *
     * @return List of tasks in the task list.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param index  Task to be marked as done.
     * @return Success message.
     * @throws PasonException  If task not found or already done.
     */
    public String doneTask(int index) throws PasonException {
        try {
            if (tasks.get(index - 1).isDone()) {
                throw new PasonException("You've already marked this task as done.");
            }
            tasks.get(index - 1).markAsDone();
            storage.saveAllTasks(this.tasks);
            return "Good job! I've marked this task as done:\n"
                    + tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new PasonException("We couldn't find this task. "
                    + "Please enter the correct task number.");
        } catch (Exception e) {
            throw new PasonException(e.getMessage());
        }
    }

    /**
     * Deletes a task from task list.
     *
     * @param index  Task to be deleted.
     * @return Success message.
     * @throws PasonException  If task not found.
     */
    public String deleteTask(int index) throws PasonException {
        index = index - 1;
        try {
            if (index > tasks.size() - 1 || index < 0) {
                throw new PasonException("You've entered an invalid task number.");
            } else {
                String removedTask = tasks.get(index).toString();
                tasks.remove(index);
                storage.saveAllTasks(tasks);
                return "Okay! I've removed this task:\n\t" + removedTask
                        + "\nNow there are " + tasks.size()
                        + " tasks in your list.";
            }
        } catch (Exception e) {
            throw new PasonException(e.getMessage());
        }
    }
}
