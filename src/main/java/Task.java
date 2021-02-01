/**
 * Task is a parent class for inheriting by specific tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    protected Task(String task, boolean isCompleted) {
        this.description = task;
        this.isDone = isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.isDone;
    }

    public String isDone() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markAsDone() {
        return new Task(this.description, true);
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
