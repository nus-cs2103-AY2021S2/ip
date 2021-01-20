public class Task {
    private boolean markAsDone;
    private String taskName;

    public Task(String taskName) {
        this.markAsDone = false;
        this.taskName = taskName;
    }

    public void completeTask() {
        this.markAsDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", markAsDone ? 'X': ' ', this.taskName);
    }
}
