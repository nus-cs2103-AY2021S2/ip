package duke.task;

public abstract class Task {
    protected String task;
    protected boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    public String getTask() {
        return this.task;
    }

    public abstract String fileString();

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
