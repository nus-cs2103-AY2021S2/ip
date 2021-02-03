package main.java.classes;

import java.util.ArrayList;

/**
 * TaskList class with specific methods.
 */
public class TaskList {
    private ArrayList<Task> lst;

    /**
     * Constructor method.
     */
    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    /**
     * Overloaded constructor method.
     * @param lst an ArrayList containing Task objects.
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Method to get TaskList.
     * @return Returns an ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return lst;
    }

    /**
     * Method to add Task to TaskList.
     * @param task standard Task object.
     */
    public void addTask(Task task) {
        lst.add(task);
    }

    /**
     * Method to delete Task from TaskList.
     * @param index index of Task to be deleted.
     */
    public void deleteTask(int index) {
        lst.remove(index);
    }

    /**
     * Method to get size of TaskList.
     * @return Returns an integer which is the size of Tasklist.
     */
    public int getListSize() {
        return this.lst.size();
    }

    /**
     * Method to get Task at a specific index.
     * @param index index of Task to be retrieved.
     * @return Returns the specified Task object.
     */
    public Task getTask(int index) {
        return this.lst.get(index);
    }
}
