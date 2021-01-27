package kelbot;

import java.io.Serializable;

public class Task implements Serializable {
    
    private String name;
    private boolean done;
    
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
    
    /**
     * Checks if the task name has given keyword
     *
     * @param keyword The keyword to be searched
     * @return True if the task name has given keyword, False otherwise
     */
    
    public boolean hasKeyword(String keyword) {
        return name.contains(keyword);
    }
    
    @Override
    public String toString() {
        return this.getStatusIcon() + this.name;
    }
}
