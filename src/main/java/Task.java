/** Describes generic Task objects. */
abstract public class Task {
    protected boolean isDone;
    protected String name;

    public abstract String toText();

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }
}
