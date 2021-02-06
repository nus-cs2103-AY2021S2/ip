package fakebot.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline Task Type.
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Class constructor specifying the task description, deadline date, deadline time,
     */
    public Deadline(String name, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(name);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;

    }

    /**
     * Returns task deadline date.
     *
     * @return an string containing deadline time.
     */
    public String getDeadlineDate() {
        return saveDateFormat.format(deadlineDate);
    }

    /**
     * Returns task deadline time.
     *
     * @return an string containing deadline time.
     */
    public String getDeadlineTime() {
        return saveTimeFormat.format(deadlineTime);
    }

    @Override
    public String toString() {
        String deadlineDateString = printDateFormat.format(deadlineDate);
        String deadlineTimeString = printTimeFormat.format(deadlineTime);
        return "[D]" + super.toString() + " (by: " + deadlineDateString + " " + deadlineTimeString + ")";
    }
}

