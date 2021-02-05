package duke;

import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates information of all tasks entered by user.
 * Supports operations to modify and display stored tasks.
 */
public class TaskManager {
    /** List of all tasks entered by user. */
    private List<Task> store;

    /** Initialises a task manager with no tasks. */
    public TaskManager() {
        this.store = new ArrayList<>();
    }

    /** Initialises a task manager with pre-loaded list of tasks. */
    public TaskManager(List<Task> store) {
        this.store = store;
    }

    /** Retrieves the whole list of tasks.
     * @return List of tasks.
     */
    public List<Task> getStore() {
        return this.store;
    }

    /**
     * Adds new task to list of all tasks.
     */
    public void addTask(Task t) {
        this.store.add(t);
    }

    /**
     * Removes task from list of all tasks.
     */
    public void deleteTask(int n) {
        this.store.remove(n - 1);
    }

    /**
     * Prints the list of all tasks and their statuses.
     */
    public void displayTasks() {
        int counter = 1;
        for (Task elem: this.store) {
            System.out.println(counter + ". " + elem.toString());
            counter += 1;
        }
    }

    /**
     * Accesses task at target index and mark it as done.
     */
    public void markTaskDone(int n) {
        this.store.get(n - 1).markAsDone();
    }

    /**
     * Find out the total number of tasks.
     * @return total number of tasks
     */
    public int taskVolume() {
        return this.store.size();
    }

    /**
     * Prints the line(s) that contain the element user is finding.
     */
    public void find(String item) {
        int counter = 1;
        for (Task t: this.store) {
            if (t.toString().contains(item)) {
                System.out.println(counter + ". " + t.toString());
            }
            counter += 1;
        }
    }
}
