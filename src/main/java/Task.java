abstract public class Task {
    protected boolean isDone;
    protected String name;

    public abstract String toText();

    public void markAsDone() {
        isDone = true;
    }
}
