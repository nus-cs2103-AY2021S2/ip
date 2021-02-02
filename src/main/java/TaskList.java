import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for an empty TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList.
     * @param tasks Tasks to be placed in TaskList.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the TaskList.
     * @param task Task to be added.
     */
    void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList.
     * @param index Index of task to be removed.
     */
    void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns a task from the TaskList.
     * @param index Index of task to be returned.
     * @return Relevant task.
     */
    Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Replaces a task in the TaskList.
     * @param index Index of task to be replaced.
     * @param task Task to replace existing task.
     */
    void set(int index, Task task) {
        this.tasks.set(index, task);
    }

    /**
     * Returns the number of tasks in TaskList.
     * @return Number of tasks.
     */
    int size() {
        return this.tasks.size();
    }
}
