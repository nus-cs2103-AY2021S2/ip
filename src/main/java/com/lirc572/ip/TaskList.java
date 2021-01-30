package com.lirc572.ip;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Represents a task list.
 */
public class TaskList {

    /**
     * An ArrayList of all tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the specified tasks.
     *
     * @param tasks The tasks to be put into the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Clears the task list and put the specified tasks into it as replacement.
     *
     * @param tasks The new tasks to put into the task list.
     */
    public void replaceWith(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Performs the given action for each element of the Iterable until all elements have been processed.
     * This method simply wraps around the forEach() method of the ArrayList <code>tasks</code>.
     *
     * @param action The action to be performed for each element.
     */
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    /**
     * Prints all tasks in a formatted manner.
     */
    public void printAll() {
        int index = 0;
        for (Task task : this.tasks) {
            Ui.printLine(String.format("%d.%s", ++index, task.toString()));
        }
    }

    /**
     * Returns the string representation of the TaskList for storage.
     *
     * @return The string representation of the TaskList for storage.
     */
    public String toSavedString() {
        // Convert task list to a string
        StringBuilder data = new StringBuilder();
        for (Task task : this.tasks) {
            data.append(task.toSavedString() + "\n");
        }
        return data.toString();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks the task with the specified index as done.
     *
     * @param index The index of the task to be marked as done (one-based).
     */
    public void markAsDone(int index) {
        this.tasks.get(index - 1).setIsDone(true);
    }

    /**
     * Returns the string representation of the task with the specified index.
     *
     * @param index The index of the task to get (one-based).
     * @return The string representation of the specified task.
     */
    public String getTaskString(int index) {
        return this.tasks.get(index - 1).toString();
    }

    /**
     * Deletes the task with the specified index.
     *
     * @param index The index of the task to be deleted (one-based).
     * @return The string representation of the task deleted.
     */
    public String delete(int index) {
        String taskString = this.getTaskString(index);
        this.tasks.remove(index - 1);
        return taskString;
    }
}
