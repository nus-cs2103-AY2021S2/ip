import java.util.ArrayList;

/**
 * Abstracts the data structure and relevant methods used to store the Task objects.
 * An ArrayList is used for this purpose.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task object to the data structure.
     * @param task A task object.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Find out the number of items in the data structure.
     * @return the number of task objects in the ArrayList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Obtain an item at a specific index in the data structure.
     * @param index Index where the item is to be retrieved.
     * @return the task object at the specific index in the ArrayList.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes an item at a specific index in the data structure.
     * @param index Index where the task object is to be deleted.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Allows other classes to ask for a copy of the data structure.
     * @return the ArrayList.
     */
    public static ArrayList<Task> getTasklist() {
        return tasks;
    }
}
