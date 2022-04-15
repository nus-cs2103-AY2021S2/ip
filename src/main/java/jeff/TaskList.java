package jeff;

import java.util.ArrayList;

import jeff.task.Task;

/**
 * Represents a list of Task objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList. Initializes empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList. Initializes TaskList with given tasks.
     *
     * @param loadedTasks List of tasks loaded from hard disk.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
    }

    /**
     * Returns TaskList as ArrayList of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns number of tasks contained in TaskList.
     *
     * @return Number of tasks.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Return number of tasks formatted as a message.
     *
     * @return Message describing number of tasks.
     */
    public String formatNumTasks() {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns the Task at given index.
     *
     * @param index Index to retrieve Task from.
     * @return Task at index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a Task to TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Sets a Task at a certain index in TaskList.
     *
     * @param task Task to be added.
     */
    public void setTask(int index, Task task) {
        tasks.set(index, task);
    }

    /**
     * Deletes Task at given index.
     * @param index Index to delete Task from.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns tasks matching search string.
     *
     * @param s Search string.
     * @return ArrayList of tasks matching the search.
     */
    public ArrayList<Task> findTask(String s) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getName().contains(s)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}
