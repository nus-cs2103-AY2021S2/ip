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
     * Returns the ArrayList of Tasks.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskList() {
        assert taskList != null : "taskList should not be null!";
        return taskList;
    }
}
