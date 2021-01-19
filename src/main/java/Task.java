public class Task {
    protected String name;
    protected boolean isDone;

    Task(String taskName) {
        name = taskName;
        isDone = false;
    }

    /**
     * This is the setter to for the "isDone"
     * status of the Task.
     */
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

    /**
     * This method overrides the toString() method.
     * @return the name of the Task
     */
    @Override
    public String toString() {
        return name;
    }
}
