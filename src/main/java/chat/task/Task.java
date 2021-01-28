package chat.task;

/**
 * An abstract class representing the basic form of a task.
 * <p>A task has a <b>name</b> (or description) to describe the task 
 * and a boolean <b>done</b> that tracks if the task has been completed.
 * <p>The boolean <b>done</b> is set to false by default.</p>
 */
public abstract class Task {
    
    private String name;
    private boolean done = false;
    
    protected Task (String name) {
        this.name = name;
    }
    
    protected Task (boolean done, String name) { 
        this.done = done; 
        this.name = name;
    }

    /**
     * Returns the name of the task.
     * 
     * @return Name or description of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns boolean done that shows if task has been completed.
     * If true, then task is complete.
     * 
     * @return Boolean done that tells if task is completed.
     */
    public boolean getDone() {
        return this.done;
    }

    /**
     * Marks task as complete by setting done to true.
     */
    public void completed() {
        this.done = true;
    }

    /**
     * Abstract method that returns a comma separated string of all parameters.
     * 
     * @return Comma separated string with all parameters listed out.
     */
    public abstract String allParameterStr();

    /**
     * Returns a string that shows the details of the task.
     * <p>[ ] will be displayed if done = false.</p>
     * <p>[X] will be displayed if done = true.</p>
     * 
     * @return String showing details of task, i.e. [ ] name.
     */
    @Override
    public String toString() {
        if (this.getDone()) {
            return "[X] " + this.getName();
        } else {
            return "[ ] " + this.getName();
        }
    }

}
