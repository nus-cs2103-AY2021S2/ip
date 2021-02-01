package fakebot.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract Task Class.
 */
public abstract class Task {
    protected String taskName;
    protected boolean isComplete;

    protected DateTimeFormatter printDateFormat;
    protected DateTimeFormatter printTimeFormat;

    protected DateTimeFormatter saveDateFormat;
    protected DateTimeFormatter saveTimeFormat;

    /**
     * Class constructor specifying the task description.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isComplete = false;

        this.printDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.printTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

        this.saveDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.saveTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
    }

    /**
     * Returns Task Name.
     *
     * @return Task Name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Marks Task as Complete.
     */
    public void markComplete() {
        isComplete = true;
    }

    /**
     * Returns if Task is Complete.
     *
     * @return If Task is Complete.
     */
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String toString() {
        return (isComplete ? "[X] " + taskName : "[ ] " + taskName);
    }
}
