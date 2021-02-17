import java.util.ArrayList;

/**
 * Class that can create a task list object to store
 * task objects in a list.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructor that creates a task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Method that adds a new task to the task list.
     *
     * @param task the new task to be added to the task list.
     */
    public void addTask(Task task) {
        super.add(task);
    }

    /**
     * Method that deletes a task from the task list.
     * @param index the index corresponding to the task to be
     *              deleted.
     * @return the task that was deleted from the task list.
     */
    public Task deleteTask(int index) {
        return super.remove(index);
    }
}