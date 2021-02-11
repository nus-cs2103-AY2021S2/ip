package duke.task;

import java.util.LinkedList;

/**
 * Represents a task list. A <code>task list</code> object stores
 * the information of all tasks in the program. It has two
 * fields, which are the task list stored in the linked list and the number of the tasks.
 */
public class TaskList {
    private LinkedList<Task> Tasks;
    private int numOfTasks;


    /**
     * Default Constructor for TaskList object
     * that has an empty LinkedList that can store Task object
     * and the number of tasks as 0.
     *
     */
    public TaskList() {
        Tasks = new LinkedList<Task>();
        numOfTasks = 0;
    }


    /**
     * Getter for TaskList object's current number of tasks.
     *
     * @return the number of tasks in the TaskList object.
     */
    public int getNumOfTasks() {
        return numOfTasks;
    }



    /**
     * Getter for TaskList object's List of the current tasks.
     *
     * @return LinkedList of current tasks.
     */
    public LinkedList<Task> getTasks() {
        return Tasks;
    }


    /**
     * Setter for adding task to a TaskList object.
     *
     * @param task the task you want to add.
     */
    public void addTask(Task task) {
        Tasks.add(task);
        numOfTasks++;
    }
    /**
     * Setter for deleting a task to a TaskList object.
     *
     * @param task the task you want to delete.
     */
    public void delete(Task task) {
        int taskIndex = Tasks.indexOf(task);
        Tasks.remove(taskIndex);
        numOfTasks -= 1;
    }


}
