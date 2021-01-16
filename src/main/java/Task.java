public class Task {
    private final String description;
    private TaskStatus status = TaskStatus.PENDING;

    public Task(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return this.status == TaskStatus.COMPLETED;
    }

    public void markComplete() {
        this.status = TaskStatus.COMPLETED;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
