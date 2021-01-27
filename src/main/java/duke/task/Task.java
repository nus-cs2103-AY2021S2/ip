package duke.task;

/** A piece of work that user want to track */
public class Task {
    /** Description of a task */
    protected String desc;
    /** Indicate completion of a task */
    protected boolean isDone;

    /**
     * Constructor for a Task
     * @param desc Description of a task
     * @param isDone Completion of a task
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Sets the task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Provides the description of the task
     * @return Description of the task
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Provides the symbol for the task type
     * @return Symbol for the task type
     */
    public String getTypeSymbol() {
        return " ";
    }

    /**
     * Provides the completion status of the task
     * @return Completion status of the task
     */
    public String getStatusSymbol() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns the task's details in a format to be saved into the hard disk
     * @return Task's detail in a savable format
     */
    public String toSaveInfoString() {
        return this.getTypeSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc;
    }
}
