package duke.task;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void doTask() {
        this.isDone = true;
    }

    public String getTask() {
        return this.task;
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
