package duke.tasks;

import java.time.LocalDate;

/**
 * Represents the Task that contains the description of the task with a boolean to show if the
 * task is done.
 */
public abstract class Task implements Comparable<Task> {
    protected boolean isDone;
    protected final String description;
    protected final LocalDate date;

    /**
     * Constructs a Task that contains the description and defaulted as not done.
     * @param description description of the Task.
     */
    public Task(String description, LocalDate date) {
        this.description = description;
        isDone = false;
        this.date = date;
    }

    /**
     * Sets the task as done.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Returns a data representation of the task to be saved in the save.txt file.
     * @return data representation of Task.
     */
    public String data() {
        String done = isDone ? "1" : "0";
        return String.format("%s | %s", done, description);
    }

    /**
     * Returns string representation of the Task to be shown to the user.
     * @return string representation of the Task.
     */
    @Override
    public String toString() {
        String output;
        if (isDone) {
            output = String.format("[X] %s", description);
        } else {
            output = String.format("[ ] %s", description);
        }
        return output;
    }

    /**
     * Returns integer comparing whether either tasks are completed, then dates of other task,
     * then other task's names.
     * @param otherTask other task used for comparing.
     * @return negative integer if this is done, or earlier date, or if the three criteria are
     * the same, positive if this task is not done, or later date. If first two criteria are the
     * same, compare the names.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (areDifferentStates(otherTask)) {
            return isDone ? 1 : -1;
        }
        if (this.areDifferentDates(otherTask)) {
            return date.compareTo(otherTask.date);
        }
        return description.compareTo(otherTask.description);
    }

    private boolean areDifferentStates(Task otherTask) {
        return this.isDone != otherTask.isDone;
    }

    private boolean areDifferentDates(Task otherTask) {
        return date.compareTo(otherTask.date) != 0;
    }
}
