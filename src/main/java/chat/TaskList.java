package chat;

import java.util.ArrayList;

import chat.task.Task;

/**
 * TaskList class contains a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initialise TaskList object with empty list of tasks.
     */
    public TaskList() { 
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initialise TaskList object.
     * 
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) { 
        this.tasks = tasks;
    }

    /**
     * Returns list of tasks.
     * 
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    
}
