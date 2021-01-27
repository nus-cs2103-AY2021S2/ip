package kelbot;

import java.io.Serializable;

public class Task implements Serializable {
    
    private String name;
    private boolean isDone;
    
    /**
     * Initializes Task
     *
     * @param name The name of the task
     */
    
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    
    /**
     * Called when the task has been completed by the user. It will mark the task as done.
     */
    
    public void complete() {
        isDone = true;
    }
    
    /**
     * Provide the status icon for the toString() method.
     *
     * @return the status icon
     */
    
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
    
    @Override
    public String toString() {
        return this.getStatusIcon() + name;
    }
}
