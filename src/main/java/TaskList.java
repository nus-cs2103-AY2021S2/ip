import java.util.ArrayList;
import java.util.List;

/**
 * List that keeps track of all the tasks currently stored in hard drive.
 * Supports
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Overloaded constructor method.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Overloaded constructor method
     * @param tasks takes in a list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds in task to the task list
     * @param task takes in the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task at the specified index
     * @param index
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the task list in arraylist form.
     * @return task list in arraylist form.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Gets the total number of tasks in the list
     * @return total tasks in the list
     */
    public int getTaskListLength() {
        return this.tasks.size();
    }

    /**
     * Gets the task at a particular index
     * @param index
     * @return
     */
    public Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }
}
