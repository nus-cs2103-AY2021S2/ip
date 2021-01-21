public class Task {
    private String description;
    private boolean isDone;

    /**
     * Construct a new task object.
     * @param description a description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Changes the task's state to done.
     */
    public void doTask() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? "X" : " ",
                description);
    }
}
