public class Task {
    protected String name;
    protected boolean isDone;

    Task(String taskName) {
        name = taskName;
        isDone = false;
    }

    public void complete() {
        isDone = true;
    }

    /**
     * This method is the getter of status.
     * @return the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return name;
    }
}
