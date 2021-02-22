package duke.tasks;

import java.util.ArrayList;


/**
 * Collects a list of <code>Task</code> objects. Note that the <code>Task</code> objects
 * will be indexed according to the time that they were added - a <code>Task</code> added
 * earlier will have a smaller index, whereas a <code>Task</code> added later will have
 * a larger index.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes an indexed, ordered collection of <code>Task</code> objects.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets an indexed, ordered list of the <code>Task</code> objects that were collected.
     *
     * @return An <code>ArrayList</code> of <code>Task</code> objects.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.tasks;
    }

    /**
     * Adds a <code>Task</code> object to the collection.
     *
     * @param task The <code>Task</code> object to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves a Task in the collection by index (starting from 1).
     *
     * @param index The index of the <code>Task</code> to retrieve.
     * @return A <code>Task</code> object if one exists at the specified index, else null.
     */
    public Task getTaskByIndex(int index) {
        try {
            return this.tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Retrieves a Task in the collection by index (starting from 1). The retrieved
     * task is removed from the collection.
     *
     * @param index The index of the <code>Task</code> to retrieve.
     * @return A <code>Task</code> object if one exists at the specified index, else null.
     */
    public Task popTaskByIndex(int index) {
        try {
            return this.tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Gets the size of the collection, i.e. the number of <code>Task</code> objects in the collection.
     *
     * @return The size of the collection.
     */
    public int getSize() {
        return this.tasks.size();
    }
}
