package checklst.task;

/**
 * The Task class is an abstract skeleton that all Tasks (Todo, Event, Deadline) inherit from.
 */
public abstract class Task {

    protected final String name;
    protected final boolean isCompleted;

    protected Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    protected Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a String representation of the Task for exporting.
     * Format: "Type | Completed | Name | Optional: Date"
     * @return String representation of Task.
     */
    public abstract String export();

    /**
     * Returns a new Task object which has been completed.
     * @return New completed Task.
     */
    public abstract Task complete();

    @Override
    public String toString() {
        String isCompleted = this.isCompleted ? "[X]" : "[ ]";
        return isCompleted + " " + this.name;
    }

}
