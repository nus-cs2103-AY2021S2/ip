public class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Constructor for a task
     * @param desc Description of a Task
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Set the task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Provide the description of the task
     * @return Task's description
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Provide the task's typing
     * @return Symbol of task's type
     */
    public String getTypeSymbol() {
        return " ";
    }

    /**
     * Provide the completion status of the task
     * @return Completion status of task
     */
    public String getStatusSymbol() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns task's details in a format to be saved into the hard disk
     * @return Task's detail in a savable format
     */
    public String toSaveInfoString() {
        return this.getTypeSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc;
    }
}
