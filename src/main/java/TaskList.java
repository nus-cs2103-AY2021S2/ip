import java.util.ArrayList;
import java.util.List;

/**
 * List that keeps track of all the tasks currently stored in hard drive.
 * Includes functions such as deleting, adding, getting tasks.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs a task list object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a task list object.
     *
     * @param tasks takes in a list of tasks.
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
     * @param index position of the task of interest.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the task list in arraylist form.
     *
     * @return task list in arraylist form.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Gets the total number of tasks in the list.
     *
     * @return total tasks in the list
     */
    public int getTaskListLength() {
        return this.tasks.size();
    }

    /**
     * Gets the task at a particular index.
     *
     * @param index position of the task of interest.
     * @return the task at the specified index.
     */
    public Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Sorts the task list based on lexicographic ordering.
     *
     * @return sorted task list
     */
    public TaskList sort() {
        tasks.sort(new TaskComparator());
        return new TaskList(tasks);
    }
}
