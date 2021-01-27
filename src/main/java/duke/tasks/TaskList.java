package duke.tasks;

import java.util.ArrayList;

/**
 * Represents a TaskList containing a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     * @param taskList ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a Task to the TaskList.
     * @param task A Task.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a Task from the TaskList.
     * @param task A task.
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Removes a Task from the TaskList.
     * @param i Integer representing index of Task.
     */
    public void removeTask(Integer i) {
        taskList.remove(i);
    }

    /**
     * Returns a Task at index i.
     * @param i Integer representing index of Task..
     * @return A task at index i.
     */
    public Task getTask(Integer i) {
        return taskList.get(i);
    }

    /**
     * Returns the ArrayList of Tasks.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
