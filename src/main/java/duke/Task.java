package duke;

public class Task {
    private static String taskType = "Task";
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return taskType;
    }

    public Task setIsDone(boolean done) {
        return new Task(this.description, done);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
