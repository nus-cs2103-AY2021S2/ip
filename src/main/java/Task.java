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
     * @return
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Provide the task's typing
     * @return
     */
    public String getTypeSymbol() {
        return " ";
    }

    /**
     * Provide the completion status of the task
     * @return
     */
    public String getStatusSymbol() {
        return this.isDone ? "X" : " ";
    }

    public String toSaveInfoString() {
        return getStatusSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc;
    }
}
