package Duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index Index of the task in the list to be removed.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        return removed;
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index Index of the task in the list to be marked done.
     */
    public void doneTask(int index) {
        Task task = tasks.get(index);
        task.checkTask();
        tasks.add(index, task);
        tasks.remove(index + 1);
    }

    /**
     * Returns the number of items in the task list.
     *
     * @return Number of items in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task in the list at the indicated index.
     *
     * @param index Index of task to be returned.
     * @return Task at specified index.
     */
    public Task getTask(int index) {
        Task task = this.tasks.get(index);
        return task;
    }
}
