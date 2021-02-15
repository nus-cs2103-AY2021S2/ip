public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected Priority priority;

    /**
     * Creates a Task object.
     *
     * @param description String containing description of the task.
     * @param type type of task it is.
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    void completed() {
        this.isDone = true;
    }

    /**
     * Converts the task object into a string.
     *
     * @return String containing information about the task object.
     */
    @Override
    public String toString() {
        return this.isDone ? "[X] " + description : "[ ] " + description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
