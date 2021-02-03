public abstract class Task {
    protected String description;
    protected boolean isCompleted;
    Task(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + description;
    }
}
