public class Task {
    protected String name;
    protected boolean isDone;
    protected String priority;

    /**
     * Initiates a Task with a name and isDone status.
     * isDone is initiated to be false.
     *
     * @param name a String representing the name of the task.
     * @param priority a String representing the priority of the task.
     */
    public Task(String name, String priority) {
        this.name = name;
        this.priority = priority;
        this.isDone = false;
    }

    /**
     * Initiates a Task with a name and isDone status.
     *
     * @param name a String representing the name of the task.
     * @param priority a String representing the priority of the task.
     * @param isDone a boolean value representing whether the task has been completed.
     */
    public Task(String name, boolean isDone, String priority) {
        this.name = name;
        this.priority = priority;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[%s][X] %s", priority, name);
        } else {
            return String.format("[%s][  ] %s", priority, name);
        }
    }

    void markAsDone() {
        this.isDone = true;
    }
}
