package duke.task;

import java.util.ArrayList;
import java.util.List;

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
    public String displayTasks() {
        if (this.store.size() == 0) {
            return "Congratulations, you have nothing to do!";
        } else {
            return this.toString();
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
    public String find(String item) {
        int counter = 1;
        String tasksfound = "";
        for (Task t: this.store) {
            if (t.toString().toLowerCase().contains(item.toLowerCase())) {
                tasksfound += String.format("%s. %s\n", counter, t.toString());
            }
            counter += 1;
        }
        return tasksfound;
    }

    @Override
    public String toString() {
        int counter = 1;
        String taskrep = "";
        for (Task elem: this.store) {
            taskrep += String.format("%s. %s\n", counter, elem.toString());
            counter += 1;
        }
        return taskrep;
    }
}
