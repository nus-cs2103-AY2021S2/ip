package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskDate() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
