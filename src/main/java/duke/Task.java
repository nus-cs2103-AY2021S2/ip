package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String dateTime;

    /**
     * Constructor which creates a task object with a description of task.
     * @param description A description describing details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * An overloaded constructor which creates a task object with a description of task and date/time information.
     * @param description A description describing details of the task.
     * @param dateTime The date of the task.
     */
    public Task(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    public void markDone() {
        this.isDone = true;
    }
    public String getDoneStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * An encode method which encodes user raw input to storage format.
     * @return A processed string of input to be stored in file.
     */
    public String encode() {
        if (isDone) {
            return "X|" + this.description;
        } else {
            return " |" + this.description;
        }
    }

    @Override
    public String toString() {
        return "[" + getDoneStatus() + "]" + getDescription();

    }
}
