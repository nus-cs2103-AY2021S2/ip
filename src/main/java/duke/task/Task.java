package duke.task;

import java.time.LocalDateTime;

public class Task {
    private static final String SEPARATOR = "|";
    protected String taskName;
    protected boolean isDone;
    protected LocalDateTime doneDate;


    /**
     * Create and initialize a Task.
     *
     * @param taskName The name of the task the user wishes to add.
     */
    public Task (String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.doneDate = null;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Marks a task as done and saves the date and time the task was marked as done.
     */
    public void markDone () {
        this.isDone = true;
        this.doneDate = LocalDateTime.now();
    }

    /**
     * Marks a task as done but sets the done date and time as pastDate.
     *
     * @param pastDate The saved date and time of when the task was marked as done.
     */
    public void markDonePast (LocalDateTime pastDate) {
        this.isDone = true;
        this.doneDate = pastDate;
    }

    public boolean isWithinNextWeek() {
        return true;
    }

    /**
     * Checks if a task was completed within the past one week from the current date and time.
     *
     * @return True if task was completed within the past one week, false otherwise.
     */
    public boolean isDoneWithinPastWeek() {
        assert doneDate != null : "Done Date cannot be null";
        LocalDateTime taskDoneDateTime = this.doneDate;

        LocalDateTime currDateTime = LocalDateTime.now();
        LocalDateTime pastWeek = currDateTime.minusWeeks(1);

        return (taskDoneDateTime.isAfter(pastWeek) && taskDoneDateTime.isBefore(currDateTime));
    }

    public boolean isDone() {
        return this.isDone;
    }

    public LocalDateTime getDoneDate() {
        return this.doneDate;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[X]" : "[  ]";
        return status + " " + taskName;
    }

    public String getSavingString() {
        return SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + taskName;
    }
}
