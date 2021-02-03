package main.java;

import java.util.List;
import java.util.ArrayList;

/*
 * Keeps track of all Tasks in a list.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /*
     * Get a specific task from the list.
     *
     * @param index Position of the task in the list.
     * @return The chosen task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /*
     * Add a task to the list.
     *
     * @param task A task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /*
     * Delete a specific task from the list.
     *
     * @param index Position of the task in the list.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        Task deletedTask = getTask(index);
        tasks.remove(index);
        return deletedTask;
    }

    /*
     * Replace a specific task with another in the list.
     *
     * @param index Position of the to-be-replaced task in the list.
     * @oaram task The new task that will replace the current one.
     */
    public void replaceTask(int index, Task task) {
        tasks.set(index, task);
    }

    /*
     * Get the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int numOfTasks() {
        return tasks.size();
    }
}
