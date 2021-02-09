package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs new task object.
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns string logo for task's completion.
     * 'X' is done. ' ' is not done.
     *
     * @return string logo.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns string for task date and timing.
     *
     * @param dateTime date and timing of task.
     * @return String for task date and timing.
     */
    public String timeFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy Hmm"));
    }

    /**
     * Returns task name.
     * @return String for task name.
     */
    public String getName() {
        return description;
    }

    /**
     * Finishes the task.
     */
    public void finishTask() {
        isDone = true;
    }

    public abstract String saveTask();


    /**
     * Returns string for task.
     * String contains type and name of task.
     * @return String message for task information.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
