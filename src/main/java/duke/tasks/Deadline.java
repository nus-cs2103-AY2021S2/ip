package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime byDateTime;

    /**
     * Initializes a deadline task with a description and a datetime.
     *
     * @param description Description of the deadline task.
     * @param byDateTime  The task's deadline.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    /**
     * Retrieves the task's deadline time.
     *
     * @return The task's deadline.
     */
    public LocalDateTime getByDateTime() {
        return this.byDateTime;
    }

    /**
     * Converts the task's deadline to a <code>String</code>.
     *
     * @return A formatted date string corresponding to the task's deadline.
     */
    public String getByDateTimeString() {
        return this.getByDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    /**
     * Retrieves the deadline task's description and status.
     *
     * @return A formatted string displaying the deadline task's description and status.
     */
    public String getStatusString() {
        return "[D]" + super.getStatusString() + " (by: " + this.getByDateTimeString() + ")";
    }

    /**
     * Determines if the <code>Deadline</code> is overdue.
     *
     * @return True if the deadline is later than the current time, and false otherwise.
     */
    public boolean isOverdue() {
        if (this.isDone()) {
            return false;
        }

        return LocalDateTime.now().isAfter(this.getByDateTime());
    }

    /**
     * Determines if the <code>Deadline</code> is urgent.
     *
     * @param urgencyInDays Number of days to use when determining if the <code>Deadline</code>
     *                      is urgent.
     * @return True if the deadline is within <code>urgencyInDays</code> of the current time.
     */
    public boolean isUrgent(int urgencyInDays) {
        if (this.isDone()) {
            return false;
        }

        LocalDateTime urgencyMark = this.getByDateTime().minusDays(urgencyInDays);
        return LocalDateTime.now().isAfter(urgencyMark) && !this.isOverdue();
    }
}
