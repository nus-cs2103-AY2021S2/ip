package duke.task;

/**
 * the abstract task class as a base for the tasks "deadline", "event" annd "todo"
 * that requires the subclass to define <code>markAsDone()</code> and <code>getDate()</code>
 */
public abstract class ListItem {
    private final boolean doneStatus;
    private final String task;
    public ListItem(String task){
        this.task = task;
        this.doneStatus = false;
    }

    public ListItem(String task, boolean isDone){
        this.task = task;
        this.doneStatus = isDone;
    }

    /**
     * @return a boolean of the task's status,
     * useful for subclasses that passed the status to parent (this class)
     */
    public boolean getDoneStatus() {
        return this.doneStatus;
    }

    public String getTask() {
        return this.task;
    }

    public abstract ListItem markAsDone();

    public abstract String getDate();

    @Override
    public String toString() {
        return (doneStatus == true ? "[X] " : "[ ] ") + this.task;
    }
}