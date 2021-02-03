package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class that handles list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Default constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to the list of tasks.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from the list of tasks
     * @param taskIndex task to be removed.
     */
    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    /**
     * Marks a given task as done.
     * @param taskIndex task to be marked done.
     */
    public void markDone(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        Task updatedTask = task.markDone();
        updateTask(taskIndex, updatedTask);
    }

    /**
     * Updates task with newly given task.
     * @param taskIndex index of task to be updated.
     * @param task new task to update with.
     */
    private void updateTask(int taskIndex, Task task) {
        tasks.set(taskIndex - 1, task);
    }

    /**
     * Returns the number of tasks in the list.
     * @return number of tasks in list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Return the requested task.
     * @param taskIndex index of the task requested.
     * @return requested task.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            s += Integer.toString(i + 1) + "."
                    + t + "\n";
        }
        return s;
    }
}
