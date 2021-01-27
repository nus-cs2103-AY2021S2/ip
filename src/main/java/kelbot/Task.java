package kelbot;

import java.io.Serializable;

public class Task implements Serializable {
    
    private String name;
    private boolean done;
    
    /*
     * Initializes Task
     */
    
    public Task(String name) {
        this.name = name;
        this.done = false;
    }
    
    /*
     * Called when the task has been completed by the user. It will mark the task as done.
     */
    
    public void complete() {
        this.done = true;
    }
    
    /*
     * Provide the status icon for the toString() method.
     */
    
    public String getStatusIcon() {
        if (this.done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
    
    
    @Override
    public String toString() {
        return this.getStatusIcon() + this.name;
    }
}
