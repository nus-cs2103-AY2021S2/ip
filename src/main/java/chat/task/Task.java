package chat.task;

/**
 * An abstract class representing the basic form of a task.
 * <p>A task has a <b>name</b> (or description) to describe the task 
 * and a boolean <b>isDone</b> that tracks if the task has been completed.
 * <p>The boolean <b>isDone</b> is set to false by default.</p>
 */
public abstract class Task {
    
    private String name;
    private boolean isDone = false;
    
    protected Task (String name) {
        this.name = name;
    }
    
    protected Task (boolean isDone, String name) { 
        this.isDone = isDone; 
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
     * Returns boolean isDone that shows if task has been completed.
     * If true, then task is complete.
     * 
     * @return Boolean isDone that tells if task is completed.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks task as complete by setting isDone to true.
     */
    public void completed() {
        this.isDone = true;
    }

    /**
     * Abstract method that returns a comma separated string of all parameters.
     * 
     * @return Comma separated string with all parameters listed out.
     */
    public abstract String allParameterStr();

    /**
     * Returns a string that shows the details of the task.
     * <p>[ ] will be displayed if isDone = false.</p>
     * <p>[X] will be displayed if isDone = true.</p>
     * 
     * @return String showing details of task, i.e. [ ] name.
     */
    @Override
    public String toString() {
        if (this.getIsDone()) {
            return "[X] " + this.getName();
        } else {
            return "[ ] " + this.getName();
        }
    }

}
