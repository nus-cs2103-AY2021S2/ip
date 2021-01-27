package duke.tasks;

public abstract class Task {

    protected String description;
    protected Boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "Task";
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = "Task";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
