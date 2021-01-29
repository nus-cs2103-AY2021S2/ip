package duke.model;

public class Task {
    private final char taskType;
    private boolean isCompleted;
    private final String taskName;

    public Task(char taskType, boolean isCompleted, String taskName) {
        this.taskType = taskType;
        this.isCompleted = isCompleted;
        this.taskName = taskName;
    }

    public String generateFileFormatString() {
        return String.format("%c // %d // %s", taskType, isCompleted ? 1 : 0, taskName);
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public boolean containSubstring(String str) {
        return this.taskName.contains(str);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isCompleted ? 'X': ' ', this.taskName);
    }
}
