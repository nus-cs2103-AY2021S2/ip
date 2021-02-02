public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Initiates a Task with a name and isDone status.
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Initiates a Task with a name and isDone status.
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", name);
        } else {
            return String.format("[ ] %s", name);
        }
    }

    void markAsDone() {
        this.isDone = true;
    }
}
