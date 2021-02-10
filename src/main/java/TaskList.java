import java.util.ArrayList;
import java.util.List;

/**
 * List that keeps track of all the tasks currently stored in hard drive.
 * Supports
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs the task list.
     *
     * @param tasks takes in a list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds in task to the task list.
     *
     * @param task takes in the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task at the specified index.
     *
     * @param index
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    public List<Task> getTaskList() {
        return this.tasks;
    }

    public int getTaskListLength() {
        return this.tasks.size();
    }

    public Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }
}
