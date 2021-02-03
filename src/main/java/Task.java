public class Task {
    private String description;
    protected boolean isCompleted;
    Task(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + description;
    }
}
