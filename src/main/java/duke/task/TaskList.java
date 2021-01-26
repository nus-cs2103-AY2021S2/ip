package duke.task;

import java.util.ArrayList;

/**
 * TaskList class which keeps tracks all the tasks in a list
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * TaskList class constructor
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList overload constructor
     *
     * @param tasks Consists of all the tasks added into the list as given by the user input
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    /**
     * Adds task to the list of tasks
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes selected task from the list of tasks
     *
     * @param task Task to be removed
     */
    public void remove(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Obtains the size of the TaskList
     *
     * @return TaskList Size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Find the task from TaskList
     *
     * @param index Index of task in the TaskList (starting from 0)
     * @return Task as requested by the Duke Bot
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }
}
