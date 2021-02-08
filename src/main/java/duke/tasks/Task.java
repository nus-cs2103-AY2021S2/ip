package duke.tasks;

/**
 * Abstract parent class for all tasks i.e ToDo, Event, Deadline.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method for Task class
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor method for Task class.
     * @param description The task description.
     * @param isDone Boolean for whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     * @return True
     */
    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }
    
    public String getType() {
        if(this instanceof ToDo) {
            return "T";
        } else if(this instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
