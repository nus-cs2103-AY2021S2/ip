public class Task {
    private boolean isDone;
    private String name;

    public Task(String n) {
        isDone = false;
        name = n;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
