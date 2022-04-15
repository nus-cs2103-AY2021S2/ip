package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline implementation of the super class task. In addition to tracking
 * event time, this class provide methods to return strings relevant to user
 * input.
 */
public class Deadline extends Task {
    private LocalDate deadlineTime;

    /**
     * Constructs a deadline with specified name and time.
     *
     * @param description  the name of the task
     * @param deadlineTime when the task would need to be done by
     */
    public Deadline(String description, LocalDate deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Makes use of the time and desciption to return well formatted status of
     * deadline
     *
     * @return status, descripton and time of deadline
     */
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + "(by: " + this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns the current status of the deadline
     *
     * @return status of deadline
     */
    @Override
    public String currentStatus() {
        return "D" + super.currentStatus();
    }

    /**
     * Changes the task time to a new time
     */
    @Override
    public void changeEventTime(LocalDate newEventTime) {
        this.deadlineTime = newEventTime;
    }
}
