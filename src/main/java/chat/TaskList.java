package chat;

import java.util.ArrayList;

import chat.task.Task;

/**
 * TaskList class contains a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Initialise TaskList object with empty list of tasks.
     */
    public TaskList() { 
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Initialise TaskList object.
     * 
     * @param taskList List of tasks.
     */
    public TaskList(ArrayList<Task> taskList) { 
        this.taskList = taskList;
    }

    /**
     * Returns list of tasks.
     * 
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    
}
