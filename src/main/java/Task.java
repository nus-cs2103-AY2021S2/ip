public class Task {
    private char taskType;
    private boolean markAsDone;
    private String taskName;

    public Task(boolean markAsDone, String taskName) {
        this.taskType = 'T';
        this.markAsDone = markAsDone;
        this.taskName = taskName;
    }

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
