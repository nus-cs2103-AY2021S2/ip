package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the duke.Tasks.Task class.
     * @param description A brief description of the task.
     */

    protected Task(String description, String isDone) {
        this.description = description;
        this.isDone = isDone.equals("1") ? true : false;
    }

    /**
     * Display whether a task is completed.
     * @return A cross symbol if task is incomplete, a tick symbol if task is completed.
     */

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Update the task as completed.
     */

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Update the task as incomplete.
     */

    public void setNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
