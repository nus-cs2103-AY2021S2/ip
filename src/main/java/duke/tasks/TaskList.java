package duke.tasks;

import java.util.List;

/**
 * Responsible for storing all tasks in a list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList with given list of Task.
     *
     * @param tasks List of Task.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds given Task to TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes Task from TaskList.
     *
     * @param index Index of Task to be removed.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the Task indicated in TaskList.
     *
     * @param index Index of the Task to be returned.
     * @return Task indicated.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of Task.
     *
     * @return List of Task.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns the size of TaskList.
     *
     * @return size of TaskList.
     */
    public int size() {
        return this.tasks.size();
    }
}
