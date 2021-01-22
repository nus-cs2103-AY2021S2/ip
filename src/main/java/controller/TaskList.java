package controller;

import task.Task;

import java.util.ArrayList;

/**
 * A class to hold list of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Creates an empty list of Tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Creates a list of Tasks based on the collection of Tasks inputted.
     * @param listOfTasks List of Tasks.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Adds a Task into the current list.
     * @param t New Task.
     */
    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    /**
     * Removes Task from the specified index (indexed from 1).
     * @param number Index of the Task to be removed.
     * @return The removed Task.
     */
    public Task deleteTask(int number) {
        return this.listOfTasks.remove(number - 1);
    }

    /**
     * Marks a Task as done based on the specified index (indexed from 1).
     * @param number Index of the Task to be marked.
     */
    public void markAsDone(int number) {
        this.listOfTasks.get(number - 1).setDone(true);
    }

    /**
     * Obtains the Task from the specified index (indexed from 1).
     * @param number Index of the Task to be obtained.
     * @return The Task at the index.
     */
    public Task getTaskAtIndex(int number) {
        return this.listOfTasks.get(number - 1);
    }

    /**
     * Returns the number of tasks in the current list.
     * @return The number of tasks in the current list.
     */
    public int getNumberOfTasks(){
        return this.listOfTasks.size();
    }

    /**
     * Returns a string representation of the current list of Tasks.
     * @return A string representation of the current list of Tasks.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            result.append(i + 1).append(". ").append(listOfTasks.get(i)).append("\n");
        }
        return result.toString();
    }
}
