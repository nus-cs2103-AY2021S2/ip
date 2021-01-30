package fakebot.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline Task Type
 */
public class Deadlines extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Class constructor specifying the task description, deadline date, deadline time,
     */
    public Deadlines(String name, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(name);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;

    }

    /**
     * Get Task Deadline Date.
     *
     * @return Return Deadline Date as String.
     */
    public String getDeadlineDate() {
        return saveDateFormat.format(deadlineDate);
    }

    /**
     * Get Task Deadline Time.
     *
     * @return Return Deadline Time as String.
     */
    public String getDeadlineTime() {
        return saveTimeFormat.format(deadlineTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + printDateFormat.format(deadlineDate) + " " + printTimeFormat.format(deadlineTime) + ")";
    }
}

