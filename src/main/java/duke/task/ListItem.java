package duke.task;

/**
 * the abstract task class as a base for the tasks "deadline", "event" annd "todo"
 * that requires the subclass to define <code>markAsDone()</code> and <code>getDate()</code>
 */
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

    /**
     * @return a boolean of the task's status,
     * useful for subclasses that passed the status to parent (this class)
     */
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