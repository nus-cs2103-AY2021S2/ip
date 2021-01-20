public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    private Task(String task, boolean isCompleted) {
        this.task = task;
        this.isDone = isCompleted;
    }

    public String isDone() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markAsDone() {
        return new Task(this.task, true);
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        }
        return "[ ] " + this.task;
    }
}
