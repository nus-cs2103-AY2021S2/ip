package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initialises a new TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Initialises a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the Task at the specific index.
     *
     * @param num Integer index.
     * @return Task at specific index.
     */
    public Task getTask(int num) {
        return this.tasks.get(num - 1);
    }

    /**
     * Returns the total number of tasks.
     *
     * @return The total number of tasks.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns a List of tasks.
     *
     * @return A list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Add task to list.
     *
     * @param task Task.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Delete task at specific index.
     *
     * @param num Integer index.
     */
    public void delete(int num) {
        tasks.remove(num - 1);
    }

    /**
     * Mark task as completed.
     *
     * @param num Integer index.
     */
    public void markAsDone(int num) {
        tasks.get(num - 1).markAsDone();
    }


}
