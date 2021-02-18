package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Handles the ArrayList representing the list of tasks
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for the TaskList
     * @param list the list comprising the tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * A default constructor for the TaskList to initialise with an empty ArrayList<>
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the list
     * @param t the task to be added
     */
    public void addTask(Task t) {
        this.list.add(t);
    }

    /**
     * Deletes a task from the list
     * @param index the index of the list to be deleted
     */
    public void deleteTask(int index) {
        this.list.remove(index);
    }

    /**
     * Gets the size of the list of tasks
     * @return the size of the list
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Gets the list of tasks
     * @return the list of tasks
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Gets an item in the list of tasks
     * @param index the index of the item
     * @return the item in that index in the list
     */
    public Task getItem(int index) {
        return this.list.get(index);
    }

}
