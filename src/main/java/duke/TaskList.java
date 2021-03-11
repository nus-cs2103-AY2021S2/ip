package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task into task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task at specified index from task list.
     *
     * @param index Index of task to be removed.
     * @return Task removed.
     */
    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        return removed;
    }

    /**
     * Marks task at specified index as done.
     *
     * @param index Index of task to be marked as done.
     */
    public void doneTask(int index) {
        Task task = tasks.get(index);
        task.checkTask();
        tasks.add(index, task);
        tasks.remove(index + 1);
    }

    /**
     * Returns number of tasks in task list.
     *
     * @return Number of tasks in task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at a specified index.
     *
     * @param index Index of task to return.
     * @return Task.
     */
    public Task getTask(int index) {
        Task task = this.tasks.get(index);
        return task;
    }
}
