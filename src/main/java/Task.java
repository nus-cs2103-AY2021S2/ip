public class Task {
    private final char taskType;
    private boolean markAsDone;
    private final String taskName;

    public Task(char taskType, boolean markAsDone, String taskName) {
        this.taskType = taskType;
        this.markAsDone = markAsDone;
        this.taskName = taskName;
    }

    public String generateFileFormatString() {
        return String.format("%c // %d // %s", taskType, markAsDone ? 1 : 0, taskName);
    }

    public void completeTask() {
        this.markAsDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", markAsDone ? 'X': ' ', this.taskName);
    }
}
