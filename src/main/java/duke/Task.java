package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return string logo for task's completion.
     * 'X' is done. ' ' is not done.
     *
     * @return string logo.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * String for task date and timing.
     *
     * @param dateTime date and timing of task.
     * @return String for task date and timing.
     */
    public String timeFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy Hmm"));
    }

    public String getName() {
        return description;
    }

    public abstract String saveTask();

    public abstract Task finishTask();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}