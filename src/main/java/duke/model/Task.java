package duke.model;

/**
 * A task class denotes a task.
 */
public class Task {
    private final char taskType;
    private boolean isCompleted;
    private final String taskName;

    /**
     * Construct a task.
     * @param taskType    The type of the task.
     * @param isCompleted  Checked if the task is completed.
     * @param taskName    The task name.
     */
    public Task(char taskType, boolean isCompleted, String taskName) {
        this.taskType = taskType;
        this.isCompleted = isCompleted;
        this.taskName = taskName;
    }

    /**
     * Generate a string to store in a file.
     * @return   A string that will be store in a file.
     */
    public String generateFileFormatString() {
        return String.format("%c // %d // %s", taskType, isCompleted ? 1 : 0, taskName);
    }

    /**
     * Completed the task.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    public boolean containSubstring(String str) {
        return this.taskName.contains(str);
    }

    /**
     * A string representation of a task.
     * @return  A string representing a task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", isCompleted ? 'X' : ' ', this.taskName);
    }
}
