package duke.tasks;

import java.util.ArrayList;

/**
 * ArrayList that keeps track of all the tasks stored in the hard drive.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructor method for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor method for TaskList class.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes task for the list of task at specific index.
     * @param index The index of task to remove.
     * @return The list of tasks after removing the task at specific index.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Adds task to the current list of tasks.
     * @param task The task to add to the list of tasks.
     * @return The list of tasks after adding the task.
     */
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

}
