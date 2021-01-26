package duke;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates information of all tasks entered by user.
 * Supports operations to modify and display stored tasks.
 */
public class TaskManager {
	/** List of all tasks entered by user. */
    private ArrayList<Task> store = new ArrayList<>();

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
    public void taskDone(int n) {
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
     * Saves the latest list and states of tasks to a file.
     */
    public void writeToDisk() {
        try {
            FileWriter fw = new FileWriter("tasklist/mytasks.txt");
            for (Task t: this.store) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
