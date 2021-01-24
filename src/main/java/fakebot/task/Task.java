package fakebot.task;

import java.time.format.DateTimeFormatter;

//Tasks Class that is parent of deadline, Events and Todo
public abstract class Task {
    protected String taskName;
    protected boolean isComplete;

    protected DateTimeFormatter printDateFormat;
    protected DateTimeFormatter printTimeFormat;

    protected DateTimeFormatter saveDateFormat;
    protected DateTimeFormatter saveTimeFormat;

    public Task(String taskName) {
        this.taskName = taskName;
        isComplete = false;

        this.printDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.printTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

        this.saveDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.saveTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
    }

    /**
     * Get Task Name.
     *
     * @return Task Name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Mark Task as Complete.
     */
    public void markComplete() {
        isComplete = true;
    }

    /**
     * Get if Task is Complete.
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
