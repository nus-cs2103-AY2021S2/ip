package duke.task;

public abstract class ListItem {
    private final boolean isDone;
    private final String task;

    public ListItem(String task) {
        this.task = task;
        this.isDone = false;
    }

    public ListItem(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getTask() {
        return this.task;
    }

    public abstract ListItem markAsDone();

    public abstract String getDate();

    @Override
    public String toString() {
        return (isDone == true ? "[X] " : "[ ] ") + this.task;
    }
}