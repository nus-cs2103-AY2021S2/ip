package kelbot;

import java.io.Serializable;

public class Task implements Serializable {
    
    private String name;
    private boolean isDone;
    
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    
    /*
     * Called when the task has been completed by the user. It will mark the task as done.
     */
    public void complete() {
        this.isDone = true;
    }
    
    /*
     * Provide the status icon for the toString() method.
     */
    public String getStatusIcon() {
        if (this.isDone) {
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
