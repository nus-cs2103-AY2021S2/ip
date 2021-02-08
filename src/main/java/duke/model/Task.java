package duke.model;

import java.time.LocalDate;
/**
 * A task class denotes a task.
 */
public abstract class Task {
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

    /**
     * Check task name contains the substring str.
     * @param str  A substring.
     * @return     True if task name contains str, otherwise false.
     */
    public boolean containSubstring(String str) {
        return this.taskName.contains(str);
    }

    /**
     * Check if the given date equals to the task date time.
     * @param date   A local date.
     * @return       True if the given date equals to the task date time, otherwise false.
     */
    public abstract boolean checkEqualDate(LocalDate date);

    /**
     * A string representation of a task.
     * @return  A string representing a task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", isCompleted ? 'X' : ' ', this.taskName);
    }
}
