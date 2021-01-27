package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

/**
 * Class representing A List of Tasks.
 */
public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    /**
     * Constructor for TaskList.
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Constructor for TaskList.
     * Constructs a TaskList with existing tasks specified in the method parameter.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Returns an iterator for the list of tasks.
     *
     * @return A task list iterator.
     */
    public ListIterator<Task> listIterator() {
        return this.tasks.listIterator();
    }

    /**
     * Retrieves a task from the list of tasks.
     *
     * @param taskIndex The index of the task to be retrieved.
     * @return A task from the list of tasks.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Gets the size of TaskList.
     *
     * @return An integer representing the number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds task to the list.
     *
     * @param task The task to be added to the list of tasks.
     */

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from the list.
     *
     * @param taskIndex The index of the task to be removed.
     */

    public Task deleteTask(int taskIndex) {
        Task toRemove = tasks.get(taskIndex);
        toRemove.markIncomplete();
        tasks.remove(taskIndex);
        return toRemove;
    }
}
