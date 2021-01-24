package duke.task;

public abstract class Task {

    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public abstract String getSaveTime();

    public abstract String getSaveType();

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
